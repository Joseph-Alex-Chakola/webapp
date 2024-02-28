# Install packer plugin for gcp
packer {
  required_plugins {
    googlecompute = {
      source  = "github.com/hashicorp/googlecompute"
      version = "~> 1"
    }
  }
}

variable "project_id" {
  type    = string
  default = "csye6225dev-415015"
}

variable "source_image_family" {
  type    = string
  default = "centos-stream-8"
}

variable "zone" {
  type    = string
  default = "us-central1-a"
}

variable "ssh_username" {
  type    = string
  default = "packer"
}

variable "image_name" {
  type    = string
  default = "csye-centos-stream-8"
}

variable "machine_type" {
  type    = string
  default = "n1-standard-4"
}
variable "account_file" {
  type    = string
  default = "csye6225.json"
}

source "googlecompute" "centos-stream-8" {
  project_id          =   var.project_id
  zone                = var.zone
  ssh_username        = var.ssh_username
  image_name          = var.image_name
  source_image_family = var.source_image_family
  machine_type        = var.machine_type
  credentials_file    = var.account_file
}

build {
  sources = ["source.googlecompute.centos-stream-8"]
  provisioner "shell" {
    script = "./scripts/install.sh"
  }
  provisioner "file" {
    source      = "../target/webapp.jar"
    destination = "/home/${var.ssh_username}/webapp.jar"
  }
  provisioner "file" {
    source      = "./scripts/webapp.sh"
    destination = "/tmp/webapp.sh"
  }
  provisioner "file" {
    source      = "./scripts/startup.service"
    destination = "/tmp/startup.service"
  }
  provisioner "shell" {
    script = "./scripts/startup.sh"
  }
}

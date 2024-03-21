#! /bin/bash
sudo yum -y install wget
wget https://download.java.net/java/GA/jdk18/43f95e8614114aeaa8e8a5fcf20a682d/36/GPL/openjdk-18_linux-x64_bin.tar.gz
sudo tar -xvf openjdk-18_linux-x64_bin.tar.gz -C /opt
curl -sSO https://dl.google.com/cloudagents/add-google-cloud-ops-agent-repo.sh
sudo bash add-google-cloud-ops-agent-repo.sh --also-install
cd /tmp || exit 1
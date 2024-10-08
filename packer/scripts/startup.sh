#! /bin/bash
sudo mv /tmp/startup.service /etc/systemd/system/startup.service
sudo mv /tmp/config.yaml /etc/google-cloud-ops-agent/config.yaml
sudo systemctl daemon-reload
sudo systemctl restart google-cloud-ops-agent
sudo systemctl enable startup.service
sudo mkdir -p /home/csye6225
sudo groupadd csye6225
sudo mkdir -p /var/log/csye6225
sudo useradd -M -N -g csye6225 -s /usr/sbin/nologin csye6225
sudo chown -R csye6225:csye6225 /home/csye6225
sudo chown -R csye6225:csye6225 /var/log/csye6225
cd ~ || exit 1
sudo mv webapp.jar /home/csye6225/webapp.jar
sudo chown -R csye6225:csye6225 /home/csye6225/webapp.jar
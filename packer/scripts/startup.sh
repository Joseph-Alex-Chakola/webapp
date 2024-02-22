#! /bin/bash
sudo mv /tmp/startup.service /etc/systemd/system/startup.service
sudo systemctl daemon-reload
sudo systemctl enable postgresql
sudo systemctl enable startup.service
sudo mkdir -p /home/csye6225
sudo groupadd csye6225
sudo useradd -M -N -g csye6225 -s /usr/sbin/nologin csye6225
sudo chown -R csye6225:csye6225 /home/csye6225
cd ~ || exit 1
sudo mv webapp.jar /home/csye6225/webapp.jar
sudo chown -R csye6225:csye6225 /home/csye6225/webapp.jar
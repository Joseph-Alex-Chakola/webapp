#! /bin/bash
sudo mv /tmp/startup.service /etc/systemd/system/startup.service
sudo systemctl daemon-reload
sudo systemctl enable postgresql
sudo systemctl enable statup.service
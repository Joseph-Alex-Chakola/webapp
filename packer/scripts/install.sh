#! /bin/bash
sudo yum -y install wget
sudo dnf -y install postgresql-server postgresql-contrib
wget https://download.java.net/java/GA/jdk18/43f95e8614114aeaa8e8a5fcf20a682d/36/GPL/openjdk-18_linux-x64_bin.tar.gz
sudo tar -xvf openjdk-18_linux-x64_bin.tar.gz -C /opt
cd /tmp || exit 1
sudo postgresql-setup --initdb
sudo systemctl start postgresql
sudo -u postgres createdb -O postgres NEU
echo "ALTER USER postgres PASSWORD 'password';" | sudo -u postgres psql
cd ~ || exit 1
sudo sed -i 's/ident/md5/g' /var/lib/pgsql/data/pg_hba.conf
sudo systemctl restart postgresql
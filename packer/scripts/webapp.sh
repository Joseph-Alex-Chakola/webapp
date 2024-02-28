# Fetch values
echo "Fetching values from metadata"
DB_USERNAME=$(curl -H "Metadata-Flavor: Google" http://metadata.google.internal/computeMetadata/v1/instance/attributes/db_username)
DB_PASSWORD=$(curl -H "Metadata-Flavor: Google" http://metadata.google.internal/computeMetadata/v1/instance/attributes/db_password)
DB_HOST=$(curl -H "Metadata-Flavor: Google" http://metadata.google.internal/computeMetadata/v1/instance/attributes/db_host | sed 's/\./\./g')
DB_PORT=$(curl -H "Metadata-Flavor: Google" http://metadata.google.internal/computeMetadata/v1/instance/attributes/db_port)
DB_NAME=$(curl -H "Metadata-Flavor: Google" http://metadata.google.internal/computeMetadata/v1/instance/attributes/db_name)

# Export as environment variables
echo "Exporting environment variables"
export DB_USERNAME
export DB_PASSWORD
export DB_HOST
export DB_PORT
export DB_NAME
export JAVA_HOME=/opt/jdk-18
export PATH=$PATH:$JAVA_HOME/bin

# Run the webapp
echo "Running the webapp"
/opt/jdk-18/bin/java -jar /home/csye6225/webapp.jar
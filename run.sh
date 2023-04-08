#!/bin/bash

# Convert mvnw file to Unix format
dos2unix mvnw

# Grant execute permission for the script to the current user
chmod +x mvnw

# Start Spring Boot application in the background
./mvnw spring-boot:run -Dspring-boot.run.jvmArguments="-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005" &

# Watch for changes in src directory and compile on change
while true; do
  inotifywait -e modify,create,delete,move -r ./src/ && ./mvnw compile
done
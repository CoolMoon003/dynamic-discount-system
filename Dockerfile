# Replace the old openjdk image with the new standard (Eclipse Temurin)
FROM eclipse-temurin:17-jdk-alpine

# Set the working directory inside the container
WORKDIR /app

# Copy the executable jar file from the target folder to the container
# Ensure the filename matches what was generated in your /target folder
COPY target/dynamic-discount-system-1.0-SNAPSHOT.jar app.jar

# Run the jar file
ENTRYPOINT ["java", "-jar", "app.jar"]
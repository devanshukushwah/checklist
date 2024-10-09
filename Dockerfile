# Step 1: Use an official Maven image to build the application
FROM maven:3.9.0-eclipse-temurin-8-alpine AS builder

# Set the working directory inside the container
WORKDIR /app

# Step 2: Copy the pom.xml and the source code
COPY pom.xml .
COPY src ./src

# Step 3: Build the application (this will create the WAR file)
RUN mvn clean package

# Step 4: Use an official Tomcat image as the base for the final image
FROM tomcat:9-jdk8

# Set the working directory
WORKDIR /usr/local/tomcat

# Step 5: Copy the WAR file from the builder stage to the webapps directory in Tomcat
COPY --from=builder /app/target/*.war webapps/ROOT.war

# Step 6: Expose the port on which Tomcat runs (8080)
EXPOSE 8080

# Step 7: Start Tomcat server
CMD ["catalina.sh", "run"]

# Step 1: Use an official Tomcat image as the base
FROM tomcat:9-jdk8

WORKDIR /usr/local/tomcat

# Step 3: Copy your WAR file to the webapps directory in Tomcat
COPY target/*.war webapps/checklist.war

# Step 4: Expose the port on which Tomcat runs (8080)
EXPOSE 8080

# Step 5: Start Tomcat server
CMD ["catalina.sh", "run"]

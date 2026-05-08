# Build stage
FROM maven:3.9.6-eclipse-temurin-17 AS build-stage
WORKDIR /app
COPY . .
# This generates the .war file in the target folder
RUN mvn clean package -DskipTests

# Run stage (Tomcat)
FROM tomcat:9.0-jdk17-temurin
WORKDIR /usr/local/tomcat/webapps/

# Remove default Tomcat apps to keep it clean# Build stage
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app
COPY . .
# This generates the .war file in the target folder
RUN mvn clean package -DskipTests

# Run stage (Standalone Java)
FROM eclipse-temurin:17-jre
WORKDIR /app

# Copy the WAR file from the build stage
# We keep the name clear, but Spring Boot can run .war files directly with java -jar
COPY --from=build /app/target/*.war app.war

EXPOSE 8080

# Execute the WAR using the embedded server
ENTRYPOINT ["java", "-jar", "app.war"]

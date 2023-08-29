# Fetching latest version of Java
FROM openjdk:20

#Create Mkdir
RUN mkdir /app

# Setting up work directory
WORKDIR /app

# Copy the jar file into our app
COPY ./agenda/target/agenda-0.0.1-SNAPSHOT.jar /app

# Exposing port 8080
EXPOSE 8080

# Starting the application
CMD ["java", "-jar", "agenda-0.0.1-SNAPSHOT.jar"]
# Start with the official Gradle image

FROM gradle:latest AS builder




# Set the working directory to /app

WORKDIR /app




# Copy the build.gradle and settings.gradle files

COPY build.gradle settings.gradle /app/




# Copy the source code

COPY src /app/src

RUN java -version


# Build the application

RUN gradle clean build




# Start with a new image

FROM openjdk:17-jdk




# Set the working directory to /app

WORKDIR /app




ENV LD_LIBRARY_PATH=/path/to/jni/library:$LD_LIBRARY_PATH




# Copy the built JAR file from the builder image

COPY --from=builder /app/build/libs/*.jar /app/aiops.jar




# Expose the port on which the application will listen

EXPOSE 8080


RUN java -version


# Run the application when the container starts

CMD ["java", "-jar", "aiops.jar"]

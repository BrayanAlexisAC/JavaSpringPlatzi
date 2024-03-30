FROM ubuntu:22.04
LABEL authors="Brayan Alexis Aguayo Contreras"
RUN apt-get update && apt-get install -y openjdk-17-jdk
WORKDIR /testingSpringboot
COPY .. /testingSpringboot
CMD ["./gradlew", "bootRun"]
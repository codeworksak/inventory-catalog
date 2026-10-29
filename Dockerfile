#FROM quay.io/centos/centos:stream9
#
#
#RUN dnf -y update && \
#    dnf -y install java-25-openjdk.x86_64 && \
#    dnf clean all
#
#WORKDIR /catalog-app
#
#ARG JAR_FILES=target/*.jar
#COPY ${JAR_FILES} catalog-app.jar
#
#EXPOSE 8081
#
#ENTRYPOINT ["java", "-jar", "/catalog-app.jar"]


# Multi stage build
# Stage 1: Build the application
#FROM quay.io/centos/centos:stream9 AS builder
#RUN dnf -y update && dnf -y install java-17-openjdk-devel maven && dnf clean all
#WORKDIR /build
#COPY . .
#RUN ./mvnw clean package -DskipTests

# Stage 2: Create the lightweight CentOS 9 runtime image
#FROM quay.io/centos/centos:stream9
#RUN dnf -y update && dnf -y install java-17-openjdk-headless && dnf clean all
#WORKDIR /app
#COPY --from=builder /build/target/*.jar app.jar

#EXPOSE 8080
#ENTRYPOINT ["java", "-jar", "/app.jar"]

FROM eclipse-temurin:25-jre-alpine

WORKDIR /catalog-app

ARG JAR_FILES=target/*.jar
COPY ${JAR_FILES} /catalog-app.jar

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "/catalog-app.jar"]


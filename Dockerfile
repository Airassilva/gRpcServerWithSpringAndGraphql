FROM maven:3.9.9-eclipse-temurin-21 AS build
WORKDIR /app

COPY . .

RUN mvn clean install -DskipTests

FROM eclipse-temurin:21-jdk AS hotel-catalog
WORKDIR /app

COPY --from=build /app/stayreserve-hotel-catalog/target/*.jar app.jar

EXPOSE 6565 5005
ENV SPRING_PROFILES_ACTIVE=docker
ENV GRPC_SERVER_PORT=6565
ENV JAVA_TOOL_OPTIONS="-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005"

ENTRYPOINT ["java", "-jar", "app.jar"]

FROM eclipse-temurin:21-jdk AS reservation
WORKDIR /app

COPY --from=build /app/stayreserve-aggregator-servic/target/*.jar app.jar

EXPOSE 5006
ENV SPRING_PROFILES_ACTIVE=docker
ENV JAVA_TOOL_OPTIONS="-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5006"

ENTRYPOINT ["java", "-jar", "app.jar"]


FROM maven:3.8.1-adoptopenjdk-8 AS builder
WORKDIR /usr/src/app
COPY pom.xml .
RUN mvn -B -s /usr/share/maven/ref/settings.xml dependency:resolve
COPY . .
RUN mvn -B -s /usr/share/maven/ref/settings.xml package -DskipTests
RUN mv target/*.jar fatjar.jar
RUN java -Djarmode=layertools -jar fatjar.jar extract

FROM openjdk8:jre8u292-b10-alpine
ENV SPRING_PROFILES_ACTIVE dev
ENV JAVA_OPTS -XX:+UseContainerSupport -XX:MaxRAMPercentage=75.0
WORKDIR application
COPY --from=builder /usr/src/app/application/ ./
COPY --from=builder /usr/src/app/dependencies/ ./
COPY --from=builder /usr/src/app/snapshot-dependencies/ ./
COPY --from=builder /usr/src/app/spring-boot-loader/ ./
ENTRYPOINT exec java $JAVA_OPTS org.springframework.boot.loader.JarLauncher
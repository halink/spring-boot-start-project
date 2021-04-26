FROM maven:3-jdk-8-alpine AS buildserver
WORKDIR /opt/src/halink-boot
COPY pom.xml .
RUN mvn -B -f pom.xml -s /usr/share/maven/ref/settings-docker.xml dependency:resolve
COPY . .
RUN mvn -B -s /usr/share/maven/ref/settings-docker.xml package -DskipTests

FROM openjdk:8-jdk-alpine
ENV SPRING_PROFILES_ACTIVE test
ENV JAVA_OPTS -Xms2g -Xmx2g -Xmn1g -XX:+UseConcMarkSweepGC
VOLUME /tmp
WORKDIR /app
COPY --from=buildserver /opt/src/halink-boot/target/halink-boot-0.0.1-SNAPSHOT.jar .
EXPOSE 8080
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app/halink-boot-0.0.1-SNAPSHOT.jar --spring.profiles=$SPRING_PROFILES_ACTIVE" ]
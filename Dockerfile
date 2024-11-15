# OpenJDK 17을 기반 이미지로 사용
FROM openjdk:17-jdk-slim

# JAR 파일을 컨테이너로 복사
ARG JAR_FILE=target/springboot-test-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar

# 애플리케이션 실행
ENTRYPOINT ["java", "-jar", "/app.jar"]
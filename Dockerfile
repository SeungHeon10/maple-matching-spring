FROM openjdk:17-jdk-slim
WORKDIR /app

# 프로젝트 전체 복사
COPY . .

RUN chmod +x ./gradlew

# Gradle 빌드 (JAR 생성)
RUN ./gradlew build --no-daemon

# 빌드된 JAR 복사 → app.jar로 실행
COPY build/libs/*.jar app.jar

# 앱 실행
ENTRYPOINT ["java", "-jar", "/app.jar"]

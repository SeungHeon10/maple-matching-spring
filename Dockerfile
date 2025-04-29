FROM openjdk:17-jdk-slim
WORKDIR /app

COPY . .

RUN chmod +x ./gradlew
RUN ./gradlew build --no-daemon

# 직접 실행 (복사 없이 실행)
CMD ["java", "-jar", "build/libs/demo-0.0.1-SNAPSHOT.jar"]

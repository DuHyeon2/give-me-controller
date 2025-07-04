# jdk-17 설치
FROM openjdk:17-jdk-alpine as stage1

# 파일 복사
WORKDIR /app
COPY gradle gradle
COPY src src
COPY build.gradle .
COPY settings.gradle .
COPY gradlew .
COPY gradle/wrapper gradle/wrapper

# 빌드
RUN chmod +x gradlew
RUN ./gradlew bootJar

# 두번째 스테이지
FROM openjdk:17-jdk-alpine
WORKDIR /app
COPY --from=stage1 /app/build/libs/*.jar app.jar

# 실행
ENTRYPOINT ["java", "-jar", "app.jar"]
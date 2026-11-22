# 1단계: jar 빌드
FROM eclipse-temurin:21-jdk-alpine AS builder
WORKDIR /app

# 의존성 먼저 받아서 레이어 캐시 활용
COPY gradlew settings.gradle build.gradle ./
COPY gradle gradle
RUN ./gradlew dependencies --no-daemon > /dev/null

COPY src src
RUN ./gradlew bootJar --no-daemon -x test

# jar 를 레이어 단위로 분리 (변경 없는 의존성 레이어는 캐시 재사용)
RUN java -Djarmode=tools -jar build/libs/*.jar extract --layers --launcher --destination extracted

# 2단계: 실행 이미지
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app

COPY --from=builder /app/extracted/dependencies/ ./
COPY --from=builder /app/extracted/spring-boot-loader/ ./
COPY --from=builder /app/extracted/snapshot-dependencies/ ./
COPY --from=builder /app/extracted/application/ ./

ENV SPRING_PROFILES_ACTIVE=dev
EXPOSE 8080

ENTRYPOINT ["java", "-Xmx384m", "org.springframework.boot.loader.launch.JarLauncher"]

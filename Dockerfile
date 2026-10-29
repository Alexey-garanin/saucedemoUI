FROM mcr.microsoft.com/playwright/java:v1.50.0-jammy

COPY build.gradle.kts settings.gradle.kts ./
COPY gradle gradle/
COPY gradlew gradlew.bat ./

WORKDIR /app

COPY . .

RUN chmod +x ./gradlew

CMD ["./gradlew", "test"]
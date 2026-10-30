# Etapa 1: Compilación
FROM eclipse-temurin:21-jdk-alpine AS build
WORKDIR /app

# Copiar el wrapper de Gradle y los archivos de configuración
COPY gradlew .
COPY gradle ./gradle
COPY build.gradle .
COPY settings.gradle .

# Dar permisos de ejecución al wrapper (necesario en Linux/Docker)
RUN chmod +x ./gradlew

# Copiar el código fuente y compilar
COPY src ./src
RUN ./gradlew bootJar -x test

# Etapa 2: Ejecución
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
# Gradle guarda el .jar generado en build/libs/ en lugar de target/
COPY --from=build /app/build/libs/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
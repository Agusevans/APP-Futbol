# Usa una imagen base de Maven con OpenJDK 8
FROM maven:3.8.6-openjdk-8-slim AS builder

# Define el directorio de trabajo
WORKDIR /app

# Copia el archivo pom.xml y las fuentes del proyecto (para optimizar el build)
COPY pom.xml /app/
COPY src /app/src/

# Ejecuta el build de Maven
RUN mvn clean package -DskipTests

# Usa una imagen base más ligera para ejecutar la app
FROM openjdk:8-jdk-alpine

# Define el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copia el JAR generado desde la imagen "builder"
COPY --from=builder /app/target/APP-Futbol-1.0-SNAPSHOT-jar-with-dependencies.jar /app/APP-Futbol.jar

# Exponer el puerto en el que correrá la aplicación
EXPOSE 10000

# Comando para ejecutar la aplicación cuando el contenedor arranque
CMD ["java", "-jar", "/app/APP-Futbol.jar"]
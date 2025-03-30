# Usa una imagen base de OpenJDK
FROM openjdk:8-jdk-alpine

# Establecer el directorio de trabajo
WORKDIR /app

# Copiar el archivo pom.xml y las dependencias (esto ayuda a la optimización de la caché de Docker)
COPY pom.xml .

# Descargar las dependencias del proyecto
RUN mvn dependency:go-offline

# Copiar todo el código fuente
COPY src /app/src

# Compilar el proyecto usando Maven
RUN mvn clean package

# Exponer el puerto que usará el contenedor (Render usa 10000 por defecto)
EXPOSE 10000

# Comando para ejecutar la aplicación
CMD ["java", "-jar", "target/APP-Futbol-1.0-SNAPSHOT.jar"]

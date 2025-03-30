# Usa una imagen base de OpenJDK 8
FROM openjdk:8-jdk-alpine

# Establecer el directorio de trabajo
WORKDIR /app

# Copiar el archivo .jar con las dependencias
COPY target/APP-Futbol-1.0-SNAPSHOT-jar-with-dependencies.jar /app/APP-Futbol.jar

# Exponer el puerto que usará el contenedor
EXPOSE 10000

# Comando para ejecutar la aplicación usando el archivo JAR generado
CMD ["java", "-jar", "APP-Futbol.jar"]

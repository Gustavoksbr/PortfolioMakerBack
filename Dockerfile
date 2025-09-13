# Etapa 1: Build da aplicação
FROM gradle:7.6.2-jdk17 AS builder

# Define diretório de trabalho
WORKDIR /app

# Copia os arquivos do projeto para o container
COPY . .

# Executa o build do JAR sem rodar testes
RUN gradle build -x test

# Etapa 2: Runtime
FROM openjdk:17-jdk-slim

WORKDIR /app

# Copia o JAR gerado da etapa anterior
COPY --from=builder /app/build/libs/*.jar app.jar

# Expõe a porta padrão do Spring Boot
EXPOSE 8080

# Comando para iniciar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]

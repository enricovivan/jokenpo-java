FROM eclipse-temurin:25-jdk AS build
WORKDIR /app

# Copia os arquivos do maven wrapper e pom.xml
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .

# Dá permissão de execução para o wrapper
RUN chmod +x mvnw

# Baixa as dependências offline (melhora o cache do docker)
RUN ./mvnw dependency:go-offline

# Copia o código fonte e faz o build
COPY src src
RUN ./mvnw package -DskipTests

# Usa uma imagem mais leve apenas com o JRE para rodar em produção
FROM eclipse-temurin:25-jre
WORKDIR /app

# Copia o .jar gerado no estágio anterior
COPY --from=build /app/target/*.jar app.jar

# Expõe a porta que a aplicação roda
EXPOSE 8080

# Comando para rodar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]

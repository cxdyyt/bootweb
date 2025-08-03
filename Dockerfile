# 第一阶段：编译项目
FROM maven:3.8.6-openjdk-8 AS builder
WORKDIR /bootweb
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src ./src
RUN mvn clean package -DskipTests

# 第二阶段：运行应用
FROM openjdk:8-jre-slim
WORKDIR /app
COPY --from=builder /app/target/your-project.jar app.jar
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]
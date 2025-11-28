# 基础镜像：OpenJDK 17 + Maven（用于构建）
FROM maven:3.8.8-openjdk-17 AS builder

# 定义 Maven 缓存目录（与 PVC 挂载路径一致）
ENV MAVEN_OPTS="-Dmaven.repo.local=/maven-cache/repository"

# 创建缓存目录并授权
RUN mkdir -p /maven-cache/repository && chmod 777 /maven-cache/repository

# 复制项目文件到容器
COPY pom.xml /app/
COPY src /app/src/

# 切换工作目录
WORKDIR /app

# 构建项目（依赖会缓存到 /maven-cache/repository）
RUN mvn clean package -DskipTests

# 运行阶段：使用精简的 OpenJDK 镜像
FROM openjdk:17-jdk-slim

# 复制构建产物到运行镜像
COPY --from=builder /app/target/*.jar /app/bootweb.jar

# 暴露应用端口（根据你的项目配置修改）
EXPOSE 8080

# 启动应用
ENTRYPOINT ["java", "-jar", "/app/bootweb.jar"]
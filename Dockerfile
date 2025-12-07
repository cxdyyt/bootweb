# syntax=docker/dockerfile:1.5

############################
# 依赖预热阶段
############################
FROM maven:3.8.8-openjdk-17 AS deps
WORKDIR /app

# 只复制构建元数据，最大化缓存命中
COPY pom.xml .

# 预下载依赖（配合 BuildKit cache，可以跨次构建复用）
RUN --mount=type=cache,target=/root/.m2 mvn -B dependency:go-offline

############################
# 构建阶段
############################
FROM maven:3.8.8-openjdk-17 AS builder
WORKDIR /app

# 复用依赖缓存
COPY --from=deps /root/.m2 /root/.m2
COPY pom.xml .
COPY src ./src

RUN --mount=type=cache,target=/root/.m2 mvn -B clean package -DskipTests

############################
# 运行阶段
############################
FROM eclipse-temurin:17-jre-jammy
WORKDIR /app

# 创建非 root 用户，提升运行时安全性
RUN groupadd --system spring && useradd --system --gid spring spring

# 复制构建产物
COPY --from=builder /app/target/*.jar /app/bootweb.jar

USER spring
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/bootweb.jar"]
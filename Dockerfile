# ==============================
# 第一阶段：运行阶段 (node)
# ==============================
FROM node:18-alpine AS frontend-builder

WORKDIR /app/frontend

# 复制前端依赖文件
COPY repo/frontend/package*.json ./

# 安装依赖
RUN npm install

# 复制前端源码
COPY repo/frontend/ ./

# 执行构建
RUN npm run build

# ==============================
# 第二阶段：构建阶段 (Backend Builder)
# ==============================
# 使用 Java 17 Maven 镜像进行构建
FROM maven:3.9.6-eclipse-temurin-17 AS backend-builder

WORKDIR /app/backend

# 复制后端构建文件
COPY repo/backend/pom.xml .
COPY repo/backend/.mvn .mvn
COPY repo/backend/mvnw.cmd .

# 下载依赖
RUN mvn dependency:go-offline -B

# 复制后端源码
COPY repo/backend/src ./src

# 执行构建
RUN mvn clean package -DskipTests -B

# ==============================
# 第三阶段：运行阶段 (Runtime)
# ==============================
# 使用轻量级的 Java 17 JRE 镜像
FROM eclipse-temurin:17-jre-alpine

# 设置工作目录
WORKDIR /app

# 从构建阶段复制打包好的 jar 文件
COPY --from=backend-builder /app/backend/target/user-management-1.0.0.jar app.jar

# 安装 nginx
RUN apk add --no-cache nginx

# 复制 nginx 配置文件（覆盖默认的）
COPY repo/frontend/nginx.conf /etc/nginx/http.d/default.conf

# 复制前端构建产物到 nginx 静态资源目录
COPY --from=frontend-builder /app/frontend/dist /usr/share/nginx/html

# 暴露端口（根据 application.yml 配置为 8080）
EXPOSE 80 8080

# 健康检查
HEALTHCHECK --interval=30s --timeout=3s --start-period=40s --retries=3 \
  CMD wget --no-verbose --tries=1 --spider http://localhost:8080/api/actuator/health || exit 1

# JVM 参数优化（可根据需要调整）
# -Xmx: 最大堆内存
# -Xms: 初始堆内存
# -XX:+UseContainerSupport: 启用容器支持，更好地在容器中管理内存
ENV JAVA_OPTS="-Xmx512m -Xms256m -XX:+UseContainerSupport"

# 启动应用
CMD ["sh", "-c", "nginx -g 'daemon off;' & java $JAVA_OPTS -jar /app/app.jar"]
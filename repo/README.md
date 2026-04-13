# 用户管理系统

一个基于 Spring Boot 3 + MyBatis-Plus 和 Vue 3 + TypeScript 的用户管理系统。

## 技术栈

### 后端
- Java 17
- Spring Boot 3.2.0
- MyBatis-Plus 3.5.5
- H2 Database（内存数据库）
- SpringDoc OpenAPI 3 (Swagger)

### 前端
- Vue 3
- TypeScript
- Element Plus
- Vite

## 快速开始

### 方法一：使用启动脚本（推荐）

1. 双击运行 `start.bat` 文件
2. 等待后端和前端服务启动
3. 浏览器访问：http://localhost:3000

### 方法二：手动启动

#### 启动后端服务
```bash
cd backend
java -jar target\user-management-1.0.0.jar
```

#### 启动前端服务
如果有 Python 环境：
```bash
cd frontend
python -m http.server 3000
```

或者直接打开 `frontend/index.html` 文件

## 测试账号

| 用户名 | 密码 | 角色 | 状态 |
|--------|------|------|------|
| admin | 123456 | administrator | 正常 |
| zhangsan | 123456 | user | 正常 |
| lisi | 123456 | user | 正常 |
| wangwu | 123456 | user | 已禁用 |
| test | 123456 | tester | 正常 |

## API 接口

### Swagger 文档
- **Swagger UI**: http://localhost:8080/api/swagger-ui/index.html
- **OpenAPI JSON**: http://localhost:8080/api/v3/api-docs

在 Swagger UI 中，您可以：
- 查看所有 API 接口文档
- 在线测试 API 接口
- 查看请求/响应模型
- 下载 OpenAPI 规范文件

### 登录接口
- **URL**: `POST /api/auth/login`
- **请求体**:
```json
{
  "username": "admin",
  "password": "123456"
}
```
- **响应示例**:
```json
{
  "success": true,
  "message": "登录成功",
  "user": {
    "id": 1,
    "username": "admin",
    "email": "admin@example.com",
    "role": "administrator",
    "status": "active"
  }
}
```

## 测试用例

### 正向测试
1. ✅ 使用 admin/123456 登录成功
2. ✅ 使用 zhangsan/123456 登录成功
3. ✅ 使用 lisi/123456 登录成功

### 反向测试
1. ✅ 密码错误：返回"密码错误"
2. ✅ 用户不存在：返回"用户不存在"
3. ✅ 用户已禁用：返回"用户账号已被禁用"
4. ✅ 用户名为空：返回"用户名不能为空"
5. ✅ 密码为空：返回"密码不能为空"

## 项目结构

```
traedockerfir/
├── backend/                          # 后端项目
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/example/usermanagement/
│   │   │   │   ├── controller/      # 控制器层
│   │   │   │   ├── service/         # 服务层
│   │   │   │   ├── mapper/          # 数据访问层
│   │   │   │   ├── entity/          # 实体类
│   │   │   │   ├── dto/             # 数据传输对象
│   │   │   │   └── config/          # 配置类
│   │   │   └── resources/
│   │   │       ├── application.yml  # 配置文件
│   │   │       └── schema.sql       # 数据库初始化脚本
│   │   └── test/                    # 测试代码
│   └── pom.xml                      # Maven 配置
├── frontend/                         # 前端项目
│   ├── src/
│   │   ├── views/                   # 页面组件
│   │   ├── services/                # API 服务
│   │   └── types/                   # TypeScript 类型
│   └── index.html                   # 静态 HTML 页面
├── user.txt                          # 初始化用户数据
└── start.bat                         # 启动脚本
```

## 功能特性

1. ✅ 用户登录功能
2. ✅ 身份验证（用户名 + 密码）
3. ✅ 用户状态检查（active/inactive）
4. ✅ 输入验证（必填字段）
5. ✅ CORS 跨域支持
6. ✅ RESTful API 设计
7. ✅ 内存数据库（无需安装数据库）
8. ✅ 响应式 UI 设计

## 注意事项

1. 后端服务运行在端口 8080
2. 前端服务运行在端口 3000（如果使用 Python HTTP 服务器）
3. H2 数据库是内存数据库，重启后数据会重置
4. 所有用户密码都是明文存储（仅用于演示）

## 编译说明

### 后端编译
```bash
cd backend
C:\Temp\apache-maven-3.9.6\bin\mvn.cmd clean package -DskipTests
```

### 运行测试
```bash
cd backend
C:\Temp\apache-maven-3.9.6\bin\mvn.cmd test
```

## 访问地址

- 前端页面：http://localhost:3000（或直接打开 frontend/index.html）
- 后端 API：http://localhost:8080/api
- **Swagger UI**: http://localhost:8080/api/swagger-ui/index.html
- H2 控制台：http://localhost:8080/api/h2-console

## 常见问题

**Q: 前端无法连接后端？**
A: 确保后端服务已启动，并且前端页面的 API_BASE 地址配置正确。

**Q: 登录时提示 CORS 错误？**
A: 后端已配置 CORS，如果使用本地文件打开 HTML，可能需要使用 HTTP 服务器。

**Q: 数据库数据如何修改？**
A: 修改 `backend/src/main/resources/schema.sql` 文件，然后重启后端服务。

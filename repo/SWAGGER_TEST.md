# Swagger API 测试用例

## 访问 Swagger UI

打开浏览器访问：http://localhost:8080/api/swagger-ui/index.html

## 测试用例

### 测试用例 1：成功登录

**接口**: `POST /api/auth/login`

**请求参数**:
```json
{
  "username": "admin",
  "password": "123456"
}
```

**预期响应**:
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

**测试步骤**:
1. 在 Swagger UI 中展开 `POST /api/auth/login` 接口
2. 点击 "Try it out" 按钮
3. 在 Request body 中输入上述 JSON
4. 点击 "Execute" 按钮
5. 验证响应状态码为 200，success 字段为 true

---

### 测试用例 2：密码错误

**接口**: `POST /api/auth/login`

**请求参数**:
```json
{
  "username": "admin",
  "password": "wrongpassword"
}
```

**预期响应**:
```json
{
  "success": false,
  "message": "密码错误"
}
```

**测试步骤**:
1. 在 Swagger UI 中展开 `POST /api/auth/login` 接口
2. 点击 "Try it out" 按钮
3. 输入错误的密码
4. 点击 "Execute" 按钮
5. 验证响应状态码为 200，success 字段为 false，message 为"密码错误"

---

### 测试用例 3：用户不存在

**接口**: `POST /api/auth/login`

**请求参数**:
```json
{
  "username": "nonexistent",
  "password": "123456"
}
```

**预期响应**:
```json
{
  "success": false,
  "message": "用户不存在"
}
```

**测试步骤**:
1. 在 Swagger UI 中展开 `POST /api/auth/login` 接口
2. 点击 "Try it out" 按钮
3. 输入不存在的用户名
4. 点击 "Execute" 按钮
5. 验证响应状态码为 200，success 字段为 false，message 为"用户不存在"

---

### 测试用例 4：用户已禁用

**接口**: `POST /api/auth/login`

**请求参数**:
```json
{
  "username": "wangwu",
  "password": "123456"
}
```

**预期响应**:
```json
{
  "success": false,
  "message": "用户账号已被禁用"
}
```

**测试步骤**:
1. 在 Swagger UI 中展开 `POST /api/auth/login` 接口
2. 点击 "Try it out" 按钮
3. 输入用户名 wangwu
4. 点击 "Execute" 按钮
5. 验证响应状态码为 200，success 字段为 false，message 为"用户账号已被禁用"

---

### 测试用例 5：用户名为空（验证失败）

**接口**: `POST /api/auth/login`

**请求参数**:
```json
{
  "username": "",
  "password": "123456"
}
```

**预期响应**:
- 状态码：400 Bad Request
- 响应体包含验证错误信息

**测试步骤**:
1. 在 Swagger UI 中展开 `POST /api/auth/login` 接口
2. 点击 "Try it out" 按钮
3. 用户名为空
4. 点击 "Execute" 按钮
5. 验证响应状态码为 400，包含验证错误信息

---

### 测试用例 6：密码为空（验证失败）

**接口**: `POST /api/auth/login`

**请求参数**:
```json
{
  "username": "admin",
  "password": ""
}
```

**预期响应**:
- 状态码：400 Bad Request
- 响应体包含验证错误信息

**测试步骤**:
1. 在 Swagger UI 中展开 `POST /api/auth/login` 接口
2. 点击 "Try it out" 按钮
3. 密码为空
4. 点击 "Execute" 按钮
5. 验证响应状态码为 400，包含验证错误信息

---

## 其他测试账号

| 用户名 | 密码 | 预期结果 |
|--------|------|----------|
| zhangsan | 123456 | 登录成功（普通用户） |
| lisi | 123456 | 登录成功（普通用户） |
| test | 123456 | 登录成功（测试员） |

## API 文档说明

### 注解说明

代码中使用的 Swagger 注解：

- `@Tag`: 标记 Controller，定义分组名称和描述
- `@Operation`: 标记方法，定义操作的摘要和描述
- `@ApiResponses`: 定义可能的响应码和描述
- `@ApiResponse`: 定义单个响应的详细信息
- `@Parameter`: 描述参数信息
- `@Schema`: 定义数据模型的 schema 信息

### 示例代码

```java
@Tag(name = "认证管理", description = "用户登录认证相关接口")
@RestController
@RequestMapping("/auth")
public class AuthController {
    
    @PostMapping("/login")
    @Operation(summary = "用户登录", description = "使用用户名和密码进行登录")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "登录成功"),
        @ApiResponse(responseCode = "400", description = "请求参数错误")
    })
    public ResponseEntity<LoginResponse> login(
            @Parameter(description = "登录请求对象", required = true)
            @Valid @RequestBody LoginRequest request) {
        // ...
    }
}
```

## 注意事项

1. 所有测试都需要确保后端服务已启动
2. Swagger UI 会自动刷新，每次修改代码后重新编译即可看到最新文档
3. 可以在 Swagger UI 中直接下载 OpenAPI 规范文件（JSON/YAML 格式）
4. 测试完成后建议使用 "Clear" 按钮清除测试数据

# MINIGAME-001 进度记录

## 已完成的内容

1. **项目初始化**
   - 使用 Spring Boot (3.5.6) + Maven 创建项目
   - 依赖：Spring Web、Spring Security、MyBatis、MySQL Driver、Lombok、Validation

2. **数据库**
   - 本地 MySQL 已安装并运行
   - 新建数据库：`minigame001`
   - 建表 SQL：
     ```sql
     CREATE TABLE IF NOT EXISTS users (
       id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '主键ID',
       username VARCHAR(50) NOT NULL UNIQUE COMMENT '用户名',
       email VARCHAR(100) UNIQUE COMMENT '邮箱',
       password_hash VARCHAR(255) NOT NULL COMMENT '加密后的密码',
       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间'
     ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
     ```

3. **后端代码**
   - **配置**  
     - `application.yml` 配置了本地 MySQL 数据源  
     - `SecurityConfig`：暂时放开所有接口（`permitAll()`）
   - **接口**  
     - `HealthController` → `/api/health` 返回 `"ok"`  
     - `AuthController` → `/api/auth/register` 实现注册接口（写入数据库）
   - **MyBatis**  
     - `User` 实体类  
     - `UserMapper` + `UserMapper.xml`（insert / findByUsername）

4. **验证**
   - 用 Postman 成功调用 `/api/auth/register`，插入用户到数据库
   - 返回结果：
     ```json
     {"ok": true, "id": 1}
     ```

5. **Git 管理**
   - 已初始化 Git 仓库
   - `.gitignore` 已配置（忽略 `.idea/`、`target/`、日志文件等）
   - 准备推送到 GitHub

---

## 当前项目结构

```
src/main/java/com/hongbin/minigame001/
├── Minigame001Application.java
├── config/
│   └── SecurityConfig.java
├── controller/
│   ├── HealthController.java
│   └── AuthController.java
├── domain/
│   └── User.java
└── mapper/
    └── UserMapper.java

src/main/resources/
├── application.yml
└── mapper/
    └── UserMapper.xml
```

---

## 下一步计划

1. 推送代码到 GitHub 仓库（`git remote add origin ...` → `git push`）。
2. 实现 **登录接口**：
   - 用户输入用户名 + 密码
   - 验证后生成 **JWT accessToken**
   - 返回给前端使用
3. 改造 Security：
   - `/api/auth/**` → 放行
   - 其他接口 → 需要 JWT 鉴权
4. 后续再加 refresh token / 登出逻辑。

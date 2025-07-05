# 小海 OJ 系统环境配置指导文档

## 项目概述
小海 OJ 是一个基于 Spring Boot 的在线判题系统，支持代码提交、在线判题、讨论区等功能。

## 技术栈
- **后端框架**: Spring Boot 2.7.2
- **数据库**: MySQL 8.0.32
- **缓存**: Redis 6.2
- **消息队列**: RabbitMQ 3.12.9
- **对象存储**: MinIO
- **容器化**: Docker
- **构建工具**: Maven
- **JDK 版本**: Java 8

## 环境要求
- JDK 8 或更高版本
- Maven 3.6+
- Docker 和 Docker Compose（推荐）
- 至少 4GB 内存

## 快速启动（推荐 Docker 方式）

### 1. 克隆项目
```bash
git clone [项目地址]
cd xiaohai-oj
```

### 2. 启动基础环境
```bash
# 启动数据库、Redis、RabbitMQ 等基础服务
docker-compose -f dev-ops/docker-compose-environment.yml up -d
```

### 3. 等待服务启动
等待所有服务健康检查通过（大约 30-60 秒）

### 4. 构建和启动应用
```bash
# 构建项目
mvn clean package -DskipTests

# 启动应用
java -jar target/xiaohai-oj-1.0-SNAPSHOT.jar --spring.profiles.active=prop
```

## 详细配置说明

### 数据库配置

#### 服务信息
- **地址**: `117.72.91.159:13306`（远程服务器）或 `localhost:13306`（Docker）
- **数据库名**: `oj`
- **用户名**: `root`
- **密码**: `123456`

#### 数据库管理
- **phpMyAdmin**: http://localhost:8899
- **用户名**: `root`
- **密码**: `123456`

#### 初始化数据
数据库会自动初始化，SQL 脚本位于 `dev-ops/mysql/sql/` 目录：
- `oj.sql`: 主要业务表
- `big_market.sql`: 其他相关表

### Redis 配置

#### 服务信息
- **地址**: `117.72.91.159:16379`（远程服务器）或 `localhost:16379`（Docker）
- **密码**: 无
- **用途**: 
  - 用户 Token 存储
  - 验证码缓存
  - 会话管理

#### Redis 管理
- **Redis Commander**: http://localhost:8081
- **用户名**: `admin`
- **密码**: `admin`

### MinIO 对象存储配置

#### 服务信息
- **地址**: `http://117.72.91.159:8999`
- **Access Key**: `minioadmin`
- **Secret Key**: `minioadmin`
- **存储桶**: `user01`
- **用途**: 用户头像上传存储

### RabbitMQ 配置

#### 服务信息
- **地址**: `localhost:5672`
- **管理界面**: http://localhost:15672
- **用户名**: `admin`
- **密码**: `admin`

## 应用配置说明

### 配置文件
项目使用多环境配置：
- `application.yml`: 主配置文件，激活 `prop` 环境
- `application-prop.yml`: 生产环境配置
- `application-dev.yml`: 开发环境配置

### 端口配置
- **应用端口**: 8018（生产环境）/ 8081（开发环境）
- **接口文档**: http://localhost:8018/doc.html

### 切换环境
修改 `application.yml` 中的 `spring.profiles.active` 值：
- `prop`: 生产环境（默认）
- `dev`: 开发环境

## 本地开发环境配置

### 1. 安装 JDK 8
```bash
# 验证 Java 版本
java -version
```

### 2. 安装 Maven
```bash
# 验证 Maven 版本
mvn -version
```

### 3. 配置 IDE
推荐使用 IntelliJ IDEA 或 Eclipse，导入 Maven 项目。

### 4. 本地服务配置
如果不使用 Docker，需要本地安装：
- MySQL 8.0+
- Redis 6.0+
- RabbitMQ 3.12+

### 5. 修改配置文件
将 `application-dev.yml` 中的远程地址改为本地地址：
```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/oj?useSSL=false&serverTimezone=UTC
  redis:
    host: localhost
    port: 6379
```

## 功能说明

### 已实现功能
1. **用户系统**
   - 用户注册/登录
   - 验证码功能
   - Token 自动刷新
   - 用户头像上传

2. **题目管理**
   - 题目创建和管理
   - 测试用例配置
   - 题目分页查询

3. **代码判题**
   - 代码提交
   - 在线判题
   - 结果统计

4. **讨论区**
   - 富文本编辑器
   - 问题讨论
   - 回复功能

5. **其他功能**
   - 用户排行榜
   - 提交记录查询
   - 接口文档（Swagger）

### 系统架构
- **前端**: 支持 Markdown 渲染
- **后端**: RESTful API 设计
- **数据库**: MySQL 主从架构支持
- **缓存**: Redis 分布式缓存
- **存储**: MinIO 对象存储
- **消息**: RabbitMQ 消息队列

## 常见问题解决

### 1. 数据库连接失败
- 检查数据库服务是否启动
- 验证连接地址和端口
- 确认用户名密码正确

### 2. Redis 连接失败
- 检查 Redis 服务状态
- 验证地址和端口配置
- 检查网络连接

### 3. MinIO 上传失败
- 检查 MinIO 服务状态
- 验证 Access Key 和 Secret Key
- 确认存储桶是否存在

### 4. 编译错误
- 检查 JDK 版本（必须是 Java 8）
- 清理 Maven 缓存：`mvn clean`
- 重新下载依赖：`mvn dependency:resolve`

### 5. 端口占用
- 检查端口是否被占用：`netstat -ano | findstr 8018`
- 修改配置文件中的端口号

## 开发建议

### 1. 代码规范
- 使用 Lombok 减少样板代码
- 遵循 RESTful API 设计原则
- 添加适当的注释和文档

### 2. 测试建议
- 编写单元测试
- 使用 H2 内存数据库进行测试
- 配置测试环境配置文件

### 3. 部署建议
- 使用 Docker 容器化部署
- 配置健康检查
- 设置适当的日志级别

## 监控和日志

### 日志配置
- 默认使用 Logback
- 日志级别可在配置文件中调整
- 建议在生产环境使用 INFO 级别

### 性能监控
- 可集成 Spring Boot Actuator
- 配置 JVM 监控
- 使用 APM 工具监控应用性能

## 联系和支持

如果在配置过程中遇到问题，请：
1. 检查本文档的常见问题部分
2. 查看应用日志文件
3. 联系项目维护者

---

**注意**: 
- 生产环境请修改默认密码
- 定期备份数据库
- 关注安全更新
- 监控系统资源使用情况 
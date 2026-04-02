# 农作物信息管理系统

基于 Spring Boot 的农作物信息管理 RESTful API 系统。

## 技术栈

| 技术 | 说明 |
|------|------|
| Spring Boot 3.2.0 | 后端框架 |
| MyBatis 3.0.3 | 数据库访问层 |
| MySQL 8.0 | 数据库 |
| SpringDoc OpenAPI | 接口文档 (Swagger) |
| Lombok | 简化代码 |
| Maven | 构建工具 |

## 项目结构

```
src/main/java/com/example/crop/
├── MainApplication.java          # 启动类
├── controller/
│   └── CropController.java       # REST 接口层
├── entity/
│   └── Crop.java                 # 实体类
├── mapper/
│   └── CropMapper.java           # 数据访问层
└── service/
    ├── CropService.java          # 业务接口
    └── impl/
        └── CropServiceImpl.java  # 业务实现
```

## 接口列表

| 方法 | 接口 | 功能 |
|------|------|------|
| POST | `/crop` | 添加农作物 |
| GET | `/crop/{id}` | 根据ID查询 |
| GET | `/crop/list` | 查询所有 |
| PUT | `/crop/{id}` | 更新农作物 |
| DELETE | `/crop/{id}` | 删除农作物 |

## 快速开始

### 1. 环境要求

- JDK 17+
- Maven 3.6+
- MySQL 8.0+

### 2. 数据库配置

创建数据库并执行 `init.sql`：

```sql
CREATE DATABASE crop_db DEFAULT CHARACTER SET utf8mb4;
USE crop_db;
CREATE TABLE crop (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL COMMENT '作物名称',
    category VARCHAR(30) COMMENT '作物类别',
    growth_cycle INT COMMENT '生长周期（天）',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP,
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);
```

### 3. 修改配置

编辑 `src/main/resources/application.yml`，修改数据库密码：

```yaml
spring:
  datasource:
    password: 你的MySQL密码
```

### 4. 运行项目

```bash
mvn spring-boot:run
```

### 5. 访问接口文档

- Swagger UI: http://localhost:8080/swagger-ui.html
- API Docs: http://localhost:8080/api-docs

## 接口测试示例

### 添加农作物

```bash
curl -X POST http://localhost:8080/crop \
  -H "Content-Type: application/json" \
  -d '{"name":"水稻","category":"粮食","growthCycle":120}'
```

### 查询所有

```bash
curl http://localhost:8080/crop/list
```

### 根据ID查询

```bash
curl http://localhost:8080/crop/1
```

### 更新农作物

```bash
curl -X PUT http://localhost:8080/crop/1 \
  -H "Content-Type: application/json" \
  -d '{"name":"小麦","category":"粮食","growthCycle":100}'
```

### 删除农作物

```bash
curl -X DELETE http://localhost:8080/crop/1
```

## 数据库表结构

| 字段 | 类型 | 说明 |
|------|------|------|
| id | BIGINT | 主键，自增 |
| name | VARCHAR(50) | 作物名称 |
| category | VARCHAR(30) | 作物类别 |
| growth_cycle | INT | 生长周期（天） |
| create_time | DATETIME | 创建时间 |
| update_time | DATETIME | 更新时间 |

## License

MIT

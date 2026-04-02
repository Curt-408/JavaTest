# AGENTS.md - JavaTest Project Guidelines

## Project Overview
- **Project**: Crop Information Management System (农作物信息管理系统)
- **Type**: Spring Boot REST API with MyBatis and MySQL
- **Language**: Java 17
- **Build Tool**: Maven

---

## Build Commands

```bash
# Navigate to project directory first
cd JavaTest

# Compile the project
./mvnw compile          # or: mvn compile

# Run the application
./mvnw spring-boot:run  # or: mvn spring-boot:run

# Build JAR package
./mvnw package          # or: mvn package

# Clean build artifacts
./mvnw clean            # or: mvn clean

# Run a single test class
./mvnw test -Dtest=ClassName           # e.g., mvn test -Dtest=CropServiceTest
./mvnw test -Dtest=ClassName#methodName # run specific method

# Skip tests during build
./mvnw package -DskipTests

# Run all tests
./mvnw test

# Check code quality (SpotBugs, Checkstyle, etc.)
./mvnw verify
```

---

## Project Structure

```
src/main/java/com/example/crop/
├── entity/          # Entity/DO classes (数据库实体)
├── mapper/          # MyBatis Mapper interfaces (数据访问层)
├── service/         # Service interfaces and implementations (业务逻辑层)
│   └── impl/        # Service implementations
└── controller/      # REST Controllers (接口层)

src/main/resources/
├── mapper/          # MyBatis XML mapper files
└── application.yml  # Application configuration
```

---

## Code Style Guidelines

### Naming Conventions

| Element | Convention | Example |
|---------|------------|---------|
| Classes | PascalCase | `CropController`, `CropServiceImpl` |
| Methods | camelCase | `getCropById`, `insertCrop` |
| Variables | camelCase | `cropName`, `growthCycle` |
| Constants | UPPER_SNAKE_CASE | `MAX_PAGE_SIZE` |
| Packages | lowercase | `com.example.crop.entity` |
| Database tables | snake_case | `crop`, `user_info` |
| REST endpoints | kebab-case | `/crop-info`, `/crop-list` |

### Import Organization
1. `java.*` standard library
2. Third-party libraries (`org.*`, `com.*`)
3. `javax.*` packages
4. Static imports last

```java
import java.util.List;
import java.time.LocalDateTime;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.crop.entity.Crop;
import com.example.crop.service.CropService;
```

### Class Structure
```java
// 1. Package declaration
package com.example.crop.controller;

// 2. Import statements
import ...;

// 3. Class-level annotations
@RestController
@RequestMapping("/crop")
public class CropController {

    // 4. Fields (private, with @Autowired via constructor or @RequiredArgsConstructor)

    // 5. Constructor

    // 6. Public methods

    // 7. Private methods

    // 8. Inner classes (if needed)
}
```

### Method Return Types
- Use `ResponseEntity<T>` for REST endpoints
- Use `List<T>` for collection returns
- Use `Optional<T>` when null is possible
- Use `void` only for procedures with no return

### Java 17 Features
- Use `record` for DTOs where appropriate: `record CropDTO(Long id, String name) {}`
- Use `sealed` classes for controlled hierarchies
- Use pattern matching in `instanceof`
- Use text blocks for multi-line strings

---

## API Design

### REST Endpoints Pattern
| Operation | HTTP Method | URL | Request Body | Response |
|-----------|-------------|-----|--------------|----------|
| Create | POST | `/crop` | Crop JSON | Created Crop |
| Read One | GET | `/crop/{id}` | - | Crop |
| Read All | GET | `/crop/list` | - | List<Crop> |
| Update | PUT | `/crop/{id}` | Crop JSON | Updated Crop |
| Delete | DELETE | `/crop/{id}` | - | 204 No Content |

### Request/Response Format
```json
// Success Response
{
  "code": 200,
  "message": "success",
  "data": { ... }
}

// Error Response
{
  "code": 400,
  "message": "error description",
  "data": null
}
```

---

## Error Handling

### Exception Types
- Use `RuntimeException` for business logic errors
- Create custom exceptions: `CropNotFoundException`, `ValidationException`
- Use `@ControllerAdvice` for global exception handling

### Example
```java
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CropNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(CropNotFoundException ex) {
        return ResponseEntity.status(404)
            .body(new ErrorResponse(404, ex.getMessage()));
    }
}
```

---

## Database Guidelines

### MyBatis Mapper
- Use XML mappers for complex queries
- Use annotations (`@Select`, `@Insert`) for simple CRUD
- Always specify `useGeneratedKeys="true"` for auto-increment IDs

### Transaction Management
- Use `@Transactional(readOnly = true)` for read operations
- Use `@Transactional` for write operations
- Keep transactions short; avoid long-running operations

---

## Documentation

### Swagger/OpenAPI
- Use `@Tag` for grouping controller endpoints
- Use `@Operation` for endpoint descriptions
- Use `@Schema` for model documentation

```java
@Tag(name = "Crop Management", description = "Crop CRUD operations")
@RestController
@RequestMapping("/crop")
public class CropController {

    @Operation(summary = "Get crop by ID", description = "Retrieves a single crop")
    @GetMapping("/{id}")
    public ResponseEntity<Crop> getCrop(@Parameter(description = "Crop ID") @PathVariable Long id) {
        // ...
    }
}
```

---

## Testing

### Test Structure
```java
@SpringBootTest
class CropServiceTest {

    @Autowired
    private CropService cropService;

    @Test
    void shouldReturnCropWhenExists() {
        // given
        Long cropId = 1L;

        // when
        Crop result = cropService.getCropById(cropId);

        // then
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(cropId);
    }
}
```

### Test Naming
- Format: `should[ExpectedBehavior]When[Condition]`
- Examples: `shouldReturnCropWhenExists`, `shouldThrowExceptionWhenCropNotFound`

---

## Git Conventions

### Commit Message Format
```
<type>(<scope>): <subject>

types: feat, fix, docs, style, refactor, test, chore
```

Examples:
```
feat(crop): add crop list endpoint
fix(crop): handle null growth cycle
docs: update API documentation
```

---

## IDE Setup (IntelliJ IDEA)

### Recommended Settings
- **Code Style**: Google Java Style Guide or Alibaba Java Coding Guidelines
- **Tabs**: Use spaces (2 or 4 based on project)
- **Line length**: 120 characters
- **File encoding**: UTF-8

### Useful Shortcuts
| Action | Shortcut |
|--------|----------|
| Reformat code | Ctrl+Alt+L |
| Optimize imports | Ctrl+Alt+O |
| Run tests | Ctrl+Shift+F10 |
| Search everywhere | Shift+Shift |

---

## Configuration

### application.yml Structure
```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/crop_db?useUnicode=true&characterEncoding=utf8&serverTimezone=Asia/Shanghai
    username: root
    password: ${DB_PASSWORD}
    driver-class-name: com.mysql.cj.jdbc.Driver

mybatis:
  mapper-locations: classpath:mapper/*.xml
  type-aliases-package: com.example.crop.entity

springdoc:
  api-docs:
    path: /api-docs
  swagger-ui:
    path: /swagger-ui.html
```

# Sistema de Gestión Académica - Backend Java Spring Boot

## Configuración del Proyecto

### Requisitos
- Java 17 o superior
- Maven 3.6+
- MongoDB Atlas (Ya configurado)

### Estructura del Proyecto

\`\`\`
src/main/java/com/academia/
├── AcademicSystemApplication.java      # Clase principal
├── config/
│   ├── SecurityConfig.java             # Configuración de seguridad
│   └── UserDetailsServiceImpl.java      # Servicio de detalles de usuario
├── controller/
│   ├── AuthController.java             # Autenticación
│   ├── UserController.java             # Gestión de usuarios
│   ├── GradeController.java            # Calificaciones
│   ├── SubjectController.java          # Materias
│   ├── GroupController.java            # Grupos
│   ├── EnrollmentController.java       # Inscripciones
│   └── HealthController.java           # Health check
├── model/
│   ├── User.java
│   ├── Grade.java
│   ├── Subject.java
│   ├── Group.java
│   ├── Enrollment.java
│   ├── AcademicPeriod.java
│   ├── TeacherAssignment.java
│   ├── Notification.java
│   └── InstitutionConfig.java
├── repository/
│   ├── UserRepository.java
│   ├── GradeRepository.java
│   ├── SubjectRepository.java
│   ├── GroupRepository.java
│   ├── EnrollmentRepository.java
│   ├── AcademicPeriodRepository.java
│   └── TeacherAssignmentRepository.java
├── security/
│   ├── JwtTokenProvider.java
│   ├── JwtAuthenticationFilter.java
│   └── JwtAuthenticationEntryPoint.java
├── service/
│   ├── UserService.java
│   ├── GradeService.java
│   └── EnrollmentService.java
└── dto/
    ├── AuthRequest.java
    ├── AuthResponse.java
    └── UserRequest.java
\`\`\`

### Instalación y Ejecución

1. **Clonar el repositorio**
\`\`\`bash
git clone <repo-url>
cd academic-system
\`\`\`

2. **Instalar dependencias**
\`\`\`bash
mvn clean install
\`\`\`

3. **Ejecutar la aplicación**
\`\`\`bash
mvn spring-boot:run
\`\`\`

La aplicación estará disponible en: `http://localhost:8080/api`

### Configuración de MongoDB

La URI de conexión ya está configurada en `application.properties`:
\`\`\`
spring.data.mongodb.uri=mongodb+srv://mariajrodrigueza_db_user:ICvKn0pIIwkjHZeu@cluster0.9i5mpfv.mongodb.net/academic_system?retryWrites=true&w=majority
\`\`\`

## API Endpoints

### Autenticación

**Login**
\`\`\`bash
POST /auth/login
Content-Type: application/json

{
  "email": "admin@admin.com",
  "password": "Admin123"
}
\`\`\`

**Registro**
\`\`\`bash
POST /auth/register
Content-Type: application/json

{
  "email": "nuevo@usuario.com",
  "password": "Password123",
  "fullName": "Nombre Completo",
  "phone": "+57 300 1234567",
  "role": "STUDENT",
  "document": "1023456789"
}
\`\`\`

### Usuarios

\`\`\`bash
# Listar todos
GET /users

# Obtener por ID
GET /users/{id}

# Listar por rol
GET /users/role/{role}

# Actualizar
PUT /users/{id}

# Eliminar
DELETE /users/{id}

# Bloquear/Desbloquear
PUT /users/{id}/lock?lock=true
\`\`\`

### Calificaciones

\`\`\`bash
# Crear calificación
POST /grades

# Obtener todas
GET /grades

# Obtener por estudiante
GET /grades/student/{studentId}

# Obtener promedio del estudiante
GET /grades/student/{studentId}/average

# Obtener por grupo
GET /grades/group/{groupId}

# Obtener por profesor
GET /grades/teacher/{teacherId}

# Actualizar
PUT /grades/{id}

# Eliminar
DELETE /grades/{id}
\`\`\`

### Materias

\`\`\`bash
# Crear materia
POST /subjects
{
  "name": "Matemáticas",
  "code": "MAT-101",
  "description": "Curso de matemáticas básicas",
  "credits": 4,
  "area": "Ciencias"
}

# Listar todas
GET /subjects

# Obtener por ID
GET /subjects/{id}

# Actualizar
PUT /subjects/{id}

# Eliminar
DELETE /subjects/{id}
\`\`\`

### Grupos

\`\`\`bash
# Crear grupo
POST /groups
{
  "name": "10-A",
  "grade": "10",
  "section": "A",
  "maxStudents": 35,
  "academicYearId": "2024-1"
}

# Listar todos
GET /groups

# Obtener por ID
GET /groups/{id}

# Actualizar
PUT /groups/{id}

# Eliminar
DELETE /groups/{id}
\`\`\`

### Inscripciones

\`\`\`bash
# Crear inscripción
POST /enrollments
{
  "studentId": "id-estudiante",
  "groupId": "id-grupo",
  "academicPeriodId": "id-periodo"
}

# Listar inscripciones de un estudiante
GET /enrollments/student/{studentId}

# Listar inscripciones de un grupo
GET /enrollments/group/{groupId}

# Actualizar estado
PUT /enrollments/{id}

# Eliminar
DELETE /enrollments/{id}
\`\`\`

### Health Check

\`\`\`bash
GET /health
\`\`\`

## Credenciales de Prueba

| Email | Contraseña | Rol |
|-------|-----------|-----|
| admin@admin.com | Admin123 | ADMIN |
| profesor@ejemplo.com | Profesor123 | TEACHER |
| estudiante@ejemplo.com | Estudiante123 | STUDENT |

## Seguridad

- **JWT Token**: Todos los endpoints excepto `/auth/**` y `/health` requieren autenticación
- **BCrypt**: Las contraseñas se encriptan con BCrypt
- **CORS**: Configurado para conectarse desde localhost:3000 y localhost:3001
- **Spring Security**: Configurado con control de acceso por rol

## Variables de Entorno

```properties
# JWT
jwt.secret=your-secret-key-change-in-production-12345678901234567890
jwt.expiration=86400000

# MongoDB
spring.data.mongodb.uri=mongodb+srv://...

# Server
server.port=8080

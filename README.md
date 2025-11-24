# Sistema de Gestión Académica

Una aplicación completa de gestión de estudiantes y calificaciones construida con Next.js 16, React 19, MongoDB y TypeScript.

## Características

### Panel Administrativo
- Gestión completa de usuarios (crear, editar, eliminar)
- Administración de materias y grupos
- Configuración de periodos académicos
- Asignación de profesores a materias
- Matrícula de estudiantes en grupos
- Generación y exportación de reportes

### Panel de Profesor
- Visualización de cursos asignados
- Registro y edición de calificaciones
- Agregar observaciones a notas
- Consulta de promedios por grupo
- Filtrado de notas por periodo
- Descarga de reportes en CSV/PDF

### Panel de Estudiante
- Consulta de calificaciones por materia
- Visualización de promedio general
- Historial académico completo
- Descarga de boletín académico
- Visualización de comentarios del profesor
- Actualización de datos personales

## Tecnologías Utilizadas

- **Frontend**: Next.js 16, React 19, TypeScript, Tailwind CSS 4
- **Backend**: Next.js API Routes, Node.js
- **Base de Datos**: MongoDB con Mongoose
- **Autenticación**: JWT (JSON Web Tokens)
- **Seguridad**: bcryptjs para hash de contraseñas
- **UI Components**: shadcn/ui, Radix UI
- **Estado**: localStorage para manejo de sesión

## Instalación

### Requisitos Previos
- Node.js 18+
- npm o yarn
- Cuenta de MongoDB Atlas

### Pasos de Instalación

1. **Clonar el repositorio**
\`\`\`bash
git clone <tu-repositorio>
cd sistema-gestion-academica
\`\`\`

2. **Instalar dependencias**
\`\`\`bash
npm install
\`\`\`

3. **Configurar variables de entorno**
Crea un archivo `.env.local` en la raíz del proyecto:

\`\`\`env
MONGODB_URI=mongodb+srv://mariajrodrigueza_db_user:ICvKn0pIIwkjHZeu@cluster0.9i5mpfv.mongodb.net/?appName=Cluster0
JWT_SECRET=tu-clave-secreta-super-segura-aqui
NEXT_PUBLIC_API_URL=http://localhost:3000
\`\`\`

4. **Ejecutar el servidor de desarrollo**
\`\`\`bash
npm run dev
\`\`\`

La aplicación estará disponible en `http://localhost:3000`

## Estructura de Carpetas

\`\`\`
.
├── app/
│   ├── admin/
│   │   └── dashboard/
│   ├── teacher/
│   │   └── dashboard/
│   ├── student/
│   │   └── dashboard/
│   ├── api/
│   │   ├── auth/
│   │   ├── users/
│   │   ├── subjects/
│   │   ├── groups/
│   │   ├── grades/
│   │   ├── academic-periods/
│   │   └── reports/
│   ├── login/
│   ├── unauthorized/
│   ├── layout.tsx
│   └── page.tsx
├── components/
│   ├── admin/
│   ├── teacher/
│   ├── student/
│   ├── auth/
│   └── ui/
├── models/
│   ├── User.ts
│   ├── Subject.ts
│   ├── Group.ts
│   ├── Grade.ts
│   ├── AcademicPeriod.ts
│   ├── Enrollment.ts
│   ├── TeacherAssignment.ts
│   ├── Notification.ts
│   └── InstitutionConfig.ts
├── hooks/
│   ├── useAuth.ts
│   └── useProtectedRoute.ts
├── lib/
│   └── mongodb.ts
├── middleware.ts
└── .env.local
\`\`\`

## Usuarios de Prueba

Para acceder al sistema, usa las siguientes credenciales:

### Administrador
- **Email**: admin@admin.com
- **Contraseña**: Admin123
- **Rol**: Administrador

### Profesor
- **Email**: profesor@ejemplo.com
- **Contraseña**: Profesor123
- **Rol**: Profesor

### Estudiante
- **Email**: estudiante@ejemplo.com
- **Contraseña**: Estudiante123
- **Rol**: Estudiante

## API Endpoints

### Autenticación
- `POST /api/auth/login` - Iniciar sesión
- `POST /api/auth/register` - Registrar nuevo usuario
- `POST /api/auth/logout` - Cerrar sesión

### Usuarios
- `GET /api/users` - Listar usuarios (filtrable por rol)
- `POST /api/users` - Crear usuario
- `GET /api/users/[id]` - Obtener usuario específico
- `PUT /api/users/[id]` - Actualizar usuario
- `DELETE /api/users/[id]` - Eliminar usuario

### Materias
- `GET /api/subjects` - Listar materias
- `POST /api/subjects` - Crear materia
- `PUT /api/subjects/[id]` - Actualizar materia
- `DELETE /api/subjects/[id]` - Eliminar materia

### Grupos
- `GET /api/groups` - Listar grupos
- `POST /api/groups` - Crear grupo
- `PUT /api/groups/[id]` - Actualizar grupo
- `DELETE /api/groups/[id]` - Eliminar grupo

### Calificaciones
- `GET /api/grades` - Listar calificaciones (filtrable)
- `POST /api/grades` - Crear calificación
- `PUT /api/grades/[id]` - Actualizar calificación
- `DELETE /api/grades/[id]` - Eliminar calificación

### Períodos Académicos
- `GET /api/academic-periods` - Listar periodos
- `POST /api/academic-periods` - Crear periodo
- `PUT /api/academic-periods/[id]` - Actualizar periodo
- `DELETE /api/academic-periods/[id]` - Eliminar periodo

### Reportes
- `POST /api/reports/generate` - Generar reporte (JSON o CSV)

## Modelos de Base de Datos

### User
- email (único)
- password (hasheado)
- fullName
- role (admin, teacher, student)
- phone
- isActive
- isBlocked

### Subject
- name (único)
- code (único)
- description
- credits

### Group
- name (único)
- grade
- section
- capacity

### Grade
- studentId (referencia a User)
- teacherId (referencia a User)
- subjectId (referencia a Subject)
- groupId (referencia a Group)
- periodId (referencia a AcademicPeriod)
- score (0-100)
- evaluationType
- observations

### AcademicPeriod
- name
- startDate
- endDate
- isActive

## Seguridad

- Contraseñas hasheadas con bcryptjs
- Autenticación mediante JWT
- Validación de campos en cliente y servidor
- Protección de rutas según roles
- Variables sensibles en .env.local

## Próximas Mejoras

- [ ] Implementar notificaciones en tiempo real
- [ ] Agregar sistema de mensajería
- [ ] Subida de archivos para tareas
- [ ] Generación de PDF para boletines
- [ ] Gráficos avanzados de desempeño
- [ ] Exportación a Excel
- [ ] Autenticación OAuth
- [ ] Sistema de recuperación de contraseña

## Contribuciones

Las contribuciones son bienvenidas. Para cambios importantes, abre un issue primero para discutir los cambios propuestos.

## Licencia

Este proyecto está bajo licencia MIT.

## Soporte

Para obtener soporte, contacta al administrador del sistema o abre un issue en el repositorio.

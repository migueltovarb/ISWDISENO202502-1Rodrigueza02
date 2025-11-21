# Sistema de Gestión Automotriz

API REST moderna para administrar manufacturas, instalaciones y automóviles utilizando MongoDB Atlas. Desarrollada con Java 21 y Spring Boot 3.3.

## 🚀 Características Principales

- ✅ Operaciones CRUD completas para Manufacturas, Instalaciones y Automóviles
- ✅ Validaciones robustas de integridad referencial
- ✅ Sistema centralizado de manejo de excepciones
- ✅ Integración segura con MongoDB Atlas
- ✅ DTOs optimizados para transferencia de datos
- ✅ Sistema de logging avanzado
- ✅ Respuestas HTTP estandarizadas

## 📁 Arquitectura del Proyecto

\`\`\`
src/
├── main/
│   ├── java/com/automotriz/
│   │   ├── SistemaAutomotrizApplication.java
│   │   ├── controller/
│   │   │   ├── ManufacturaController.java
│   │   │   ├── InstalacionController.java
│   │   │   └── AutomovilController.java
│   │   ├── service/
│   │   │   ├── ManufacturaService.java
│   │   │   ├── InstalacionService.java
│   │   │   └── AutomovilService.java
│   │   ├── repository/
│   │   │   ├── ManufacturaRepository.java
│   │   │   ├── InstalacionRepository.java
│   │   │   └── AutomovilRepository.java
│   │   ├── model/
│   │   │   ├── Manufactura.java
│   │   │   ├── Instalacion.java
│   │   │   └── Automovil.java
│   │   ├── dto/
│   │   │   ├── ManufacturaDTO.java
│   │   │   ├── InstalacionDTO.java
│   │   │   └── AutomovilDTO.java
│   │   └── exception/
│   │       ├── ManejadorExcepcionesGlobal.java
│   │       ├── RecursoNoEncontradoException.java
│   │       ├── OperacionInvalidaException.java
│   │       └── ErrorResponse.java
│   └── resources/
│       └── application.yml
└── pom.xml
\`\`\`

## 📋 Requisitos Previos

- Java 21 o superior
- Maven 3.6+
- Cuenta en MongoDB Atlas (gratuita)
- Conexión a Internet

## ⚙️ Configuración de MongoDB Atlas

### Paso 1: Crear tu base de datos

1. Ve a [MongoDB Atlas](https://cloud.mongodb.com/)
2. Crea una cuenta gratuita o inicia sesión
3. Crea un nuevo cluster (el tier gratuito es suficiente)
4. Ve a **Database Access**:
   - Crea un nuevo usuario
   - Asigna una contraseña segura
   - Guarda las credenciales
5. Ve a **Network Access**:
   - Agrega tu dirección IP actual
   - O usa `0.0.0.0/0` para acceso desde cualquier lugar (solo desarrollo)
6. Ve a **Database** → **Connect**:
   - Selecciona "Connect your application"
   - Copia la cadena de conexión
   - Reemplaza `<password>` con tu contraseña

### Paso 2: Configurar la aplicación

Edita `src/main/resources/application.yml` y reemplaza la URI de MongoDB:

\`\`\`yaml
spring:
  data:
    mongodb:
      uri: mongodb+srv://TU_USUARIO:TU_PASSWORD@TU_CLUSTER.mongodb.net/sistema_automotriz?retryWrites=true&w=majority
      database: sistema_automotriz
\`\`\`

## 🔧 Instalación y Ejecución

### 1. Compilar el proyecto

\`\`\`bash
mvn clean install
\`\`\`

### 2. Ejecutar la aplicación

\`\`\`bash
mvn spring-boot:run
\`\`\`

O ejecuta el JAR generado:

\`\`\`bash
java -jar target/sistema-automotriz-2.0.0.jar
\`\`\`

La API estará disponible en: `http://localhost:9090/api/v2`

## 📊 Modelos de Datos

### Manufactura
\`\`\`json
{
  "id": "ObjectId (auto-generado)",
  "denominacion": "string (requerido)",
  "region": "string (requerido)",
  "instalacionesIds": ["array de IDs"]
}
\`\`\`

### Instalación
\`\`\`json
{
  "id": "ObjectId (auto-generado)",
  "denominacion": "string (requerido)",
  "localizacion": "string (requerido)",
  "manufacturaId": "string (requerido)"
}
\`\`\`

### Automóvil
\`\`\`json
{
  "id": "ObjectId (auto-generado)",
  "fabricante": "string (requerido)",
  "version": "string (requerido)",
  "categoriaRuedas": "string (requerido)",
  "cantidadPuertas": "integer (requerido, >= 1)",
  "instalacionId": "string (requerido)"
}
\`\`\`

## 🔌 Endpoints de la API

### Manufacturas

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| POST | `/api/v2/manufacturas` | Crear manufactura |
| GET | `/api/v2/manufacturas` | Listar todas |
| GET | `/api/v2/manufacturas/{id}` | Obtener por ID |
| PUT | `/api/v2/manufacturas/{id}` | Actualizar |
| DELETE | `/api/v2/manufacturas/{id}` | Eliminar |

### Instalaciones

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| POST | `/api/v2/instalaciones` | Crear instalación |
| GET | `/api/v2/instalaciones` | Listar todas |
| GET | `/api/v2/instalaciones/{id}` | Obtener por ID |
| GET | `/api/v2/instalaciones/manufactura/{id}` | Por manufactura |
| PUT | `/api/v2/instalaciones/{id}` | Actualizar |
| DELETE | `/api/v2/instalaciones/{id}` | Eliminar |

### Automóviles

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| POST | `/api/v2/automoviles` | Crear automóvil |
| GET | `/api/v2/automoviles` | Listar todos |
| GET | `/api/v2/automoviles/{id}` | Obtener por ID |
| GET | `/api/v2/automoviles/instalacion/{id}` | Por instalación |
| GET | `/api/v2/automoviles/fabricante/{nombre}` | Por fabricante |
| PUT | `/api/v2/automoviles/{id}` | Actualizar |
| DELETE | `/api/v2/automoviles/{id}` | Eliminar |

## 📝 Ejemplos de Uso

### Crear Manufactura

\`\`\`bash
POST http://localhost:9090/api/v2/manufacturas
Content-Type: application/json

{
  "denominacion": "Manufactura BMW Latinoamérica",
  "region": "Argentina"
}
\`\`\`

### Crear Instalación

\`\`\`bash
POST http://localhost:9090/api/v2/instalaciones
Content-Type: application/json

{
  "denominacion": "Instalación Buenos Aires",
  "localizacion": "Buenos Aires, Argentina",
  "manufacturaId": "673a5c2e1f2a3b4c5d6e7f8g"
}
\`\`\`

### Crear Automóvil

\`\`\`bash
POST http://localhost:9090/api/v2/automoviles
Content-Type: application/json

{
  "fabricante": "BMW",
  "version": "Serie 3 320i",
  "categoriaRuedas": "Bridgestone Turanza",
  "cantidadPuertas": 4,
  "instalacionId": "673a5c2e1f2a3b4c5d6e7f9h"
}
\`\`\`

## ⚠️ Códigos de Estado

| Código | Significado |
|--------|-------------|
| 200 | OK - Operación exitosa |
| 201 | Created - Recurso creado |
| 204 | No Content - Eliminación exitosa |
| 400 | Bad Request - Error de validación |
| 404 | Not Found - Recurso no encontrado |
| 500 | Internal Server Error - Error del servidor |

## 🛡️ Reglas de Validación

1. **Crear Instalación**: La manufactura debe existir
2. **Crear Automóvil**: La instalación debe existir
3. **Eliminar Manufactura**: No puede tener instalaciones asociadas
4. **Eliminar Instalación**: No puede tener automóviles asociados
5. **Cantidad de Puertas**: Debe ser mínimo 1

## 🔍 Solución de Problemas

### Error de conexión a MongoDB

- Verifica que la URI en `application.yml` sea correcta
- Confirma que tu IP esté en la lista de acceso de MongoDB Atlas
- Asegúrate de haber reemplazado `<password>` con tu contraseña real

### Puerto ocupado

Si el puerto 9090 está en uso, cámbialo en `application.yml`:

\`\`\`yaml
server:
  port: 8081
\`\`\`

## 📦 Tecnologías Utilizadas

- Spring Boot 3.3.0
- Spring Data MongoDB
- Java 21
- Lombok
- Jakarta Validation
- Maven

## 📄 Licencia

MIT License

## 👨‍💻 Autor

Proyecto desarrollado para gestión integral de sistemas automotrices.

---

**Nota**: Recuerda mantener tus credenciales de MongoDB seguras y nunca compartirlas en repositorios públicos.

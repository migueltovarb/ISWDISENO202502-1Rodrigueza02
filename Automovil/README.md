# Sistema de Gestión Automotriz

## Arquitectura del Proyecto

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

### Ejecutar la aplicación

\`\`\`bash
mvn spring-boot:run
\`\`\`

La API estará disponible en: `http://localhost:9090/api/v2`

## Modelos de Datos

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

## Ejemplos de Uso

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

## Tecnologías Utilizadas

- Spring Boot 3.3.0
- Spring Data MongoDB
- Java 21
- Lombok
- Jakarta Validation
- Maven


## Autor

Juliana Rodriguez

---



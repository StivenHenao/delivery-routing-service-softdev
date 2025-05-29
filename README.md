# SofDev API Gateway

Este es el **API Gateway** de **SofDev**, desarrollado con **Spring Cloud Gateway**. Su propósito es gestionar las solicitudes hacia los microservicios del ecosistema de SoftDev, actuando como un punto central de entrada para la comunicación entre clientes y servicios.

## 🚀 Tecnologías

- **Java 21**
- **Spring Boot 3.2.4**
- **Spring Cloud Gateway**

## 📂 Estructura del Proyecto

```
sofdev-api-gateway/
├── src/main/java/com/sofdev/gateway
│   ├── SofdevApiGatewayApplication.java
│   ├── config/
│   │   ├── RouteConfig.java
│   │   ├── GlobalFilter.java
│   │   └── SecurityConfig.java
│   └── controllers/
├── src/main/resources/
│   ├── application.yml
│   └── bootstrap.yml
├── pom.xml
└── README.md
```

## ⚙️ Configuración

El API Gateway enruta solicitudes a los microservicios según `application.yml`:

```yaml
spring:
  cloud:
    gateway:
      routes:
        - id: auth-service
          uri: http://localhost:8081
          predicates:
            - Path=/auth/**

        - id: users-service
          uri: http://localhost:8082
          predicates:
            - Path=/users/**
```

## 🏗️ Instalación y Ejecución

### 1️⃣ Clonar el repositorio

```sh
git clone git@github.com:SofDev/sofdev-api-gateway.git
cd sofdev-api-gateway
```

### 2️⃣ Construir el proyecto con Maven

```sh
./mvnw clean install
```

### 3️⃣ Ejecutar la API Gateway

```sh
./mvnw spring-boot:run
```

## 📜 Licencia

Este proyecto está bajo la **Licencia MIT**. Puedes leer más en el archivo [LICENSE](LICENSE).

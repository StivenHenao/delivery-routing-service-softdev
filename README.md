
# Delivery Routing Service

Este es el microservicio **Delivery Routing** de **SoftDev**, desarrollado con **Spring Boot**. Gestiona la **asignación de pedidos a repartidores** y el **seguimiento de entregas**, comunicándose de forma asíncrona con el microservicio de pedidos mediante **RabbitMQ**.

## 🚀 Tecnologías

- Java 21
- Spring Boot 3.2.4
- RabbitMQ
- Spring Data JPA (si aplica)
- Docker

## 📦 Funcionalidades

- Asignación de pedidos a repartidores.
- Recepción de eventos de nuevos pedidos mediante RabbitMQ.
- API REST para gestión de entregas.

## 🏗️ Uso Rápido
```bash
git clone git@github.com:SofDev/delivery-routing-service.git
cd delivery-routing-service
```

```bash
./mvnw clean install
./mvnw spring-boot:run
```
Para Docker:
```bash
docker build -t stivenh06/softdev:delivery-routing-service .
docker push stivenh06/softdev:delivery-routing-service
```

## 🌐 Comunicación

- Comunicación asíncrona con el microservicio de pedidos (RabbitMQ).
- Integración vía API Gateway de SoftDev.

## 📜 Licencia

MIT
Este proyecto está bajo la **Licencia MIT**. Puedes leer más en el archivo [LICENSE](LICENSE).

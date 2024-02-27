# Overview

**Project goal:** Practice building microservices using Java SpringBoot.

**Technologies used:**

- Java SpringBoot

- PostgreSQL: databases for microservices

- Eureka: Service registry for discovering other services.

- Load balancer: Distributes traffic among multiple instances of a service.

- Zipkin Sleuth: Distributed tracing for monitoring and troubleshooting microservices.

- OpenFeign: Simplifies making HTTP requests to other microservices.

- RabbitMQ: Message queue for asynchronous communication between microservices.

- Docker: Containerizes the microservices for easier deployment and scalability.

## Architecture
![microservices 10_01_2024 drawio](https://github.com/skalskibukowa/bmmicroservices/assets/29678557/5e80f5d0-aff8-4026-84cc-43390416ec68)


- Public Network: This is where external users interact with the e-commerce platform.
- Private Network: This is where the microservices reside.
- E-commerce Platform: This is the main application that users interact with.
- Microservices: These are the individual services that make up the e-commerce platform, such as order, inventory, fraud, and notification services.
- Pull Environment: This is where the Docker images for the microservices are pulled from.
- Private Docker Registry: This is a private repository for storing Docker images.
- Eureka Server: This is the service registry that all microservices register with.
- Load Balancer: This distributes traffic among the different instances of each microservice.
- Message Queue: This is a queue that allows microservices to communicate with each other asynchronously. RabbitMQ is one example of a message queue.
- Distributed Tracing: This allows you to track the flow of a request across all of the microservices involved in processing it. Zipkin Sleuth is one example of a distributed tracing tool.

## How to start the project

1. Clone repository
2. Create docker image: docker-compose up -d
3. In the PostgreSQL create four databases: product / fraud / orders / notification


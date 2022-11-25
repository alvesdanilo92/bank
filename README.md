# Bank

## Description

This is a project for proofs of concepts (POCs) using Spring Boot.

Some basic modules were created for operation, such as authentication, customer and libraries for communication between services.

The goal of this POC was to create a unit test that would cover as much of the project as possible, without much effort, but that would guarantee the quality of the program.

The idea of the project is that each application can run the test without depending on others.

## Settings

To run the unit tests, we are using an in-memory database that does not require installation, but to upload the application without testing, a MySql database must be configured

A option is to use [Docker](https://www.docker.com/) to upload a MySql database. And for that, I left a "docker-compose" ready to use.

```
cd src/main/resources/docker/mysql
docker-compose up -d
cd ../../../../..
```

# Future

This project can be used as a basis for other POCs, such as:

* Redis connection for caching
* Communication with various services
* Work with asynchronous, event-driven processes (Example: Kafka)
* Secret Manager (Example: Vault, AWS Secrets Manager, Azure Key Vault, Docker secrets)

Anyway, as this project has the basics to work, all you have to do is use your creativity to carry out any test concept using Spring Boot

### Happy studying!
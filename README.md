# Handle Work Service

Handle Work Service é responsável por gerenciar os trabalhos ofertados pelos prestadores de serviço na plataforma. O principal objetivo deste serviço é atuar como um CRUD de trabalhos.

## 🏗️ Estrutura

A estrutura do projeto está organizada da seguinte forma:

```bash
.
├── gradle
│   └── wrapper
└── src
    ├── main
    │   ├── java
    │   │   └── com
    │   │       └── handleservice
    │   │           └── handleworkservice
    │   │               ├── config
    │   │               ├── controller
    │   │               ├── dto
    │   │               ├── exception
    │   │               ├── mapper
    │   │               ├── model
    │   │               ├── repository
    │   │               ├── service
    │   │               └── utils
    │   └── resources
    │       └── db
    │           └── migration
    └── test
        ├── java
        │   └── com
        │       └── handleservice
        │           └── handleworkservice
        │               ├── infra
        │               ├── integrationTests
        │               └── unitTests
        └── resources

30 directories

```

O projeto segue a estrutura de um projeto Spring Boot, seguindo a organização de pacotes por funcionalidade.

Os testes estão localizados na pasta `test` e estão divididos em testes unitários e testes de integração.

Os arquivos de migração do banco de dados estão localizados na pasta `db/migration`.

## 📚 Documentação

A documentação da API está disponível no Swagger, que pode ser acessado através no localhost na porta 8080 após a execução do projeto no link `http://localhost:8080/swagger-ui.html`.

## 🚀 Como rodar

### Requisitos

Para executar o projeto é necessário ter instalado as seguintes ferramentas:

- ☕ [java opensdk21 ou superior](https://openjdk.java.net)
- 🐋 [docker](https://docker.com)
- 🚢 [docker-compose](https://docs.docker.com/compose)

### Execução

Agora que você já possui as ferramentas necessárias, siga os passos abaixo para executar o projeto:

1. Clone o repositório:

    ```bash
    git clone https://github.com/HandleServices/handle-work-service
    ```

2. Rode o comando abaixo para subir o banco de dados:

    ```bash
    docker compose up -d
    ```

    Esse comando irá subir um container docker com o banco de dados PostgreSQL.

3. Execute o projeto:

    Na raiz do projeto, execute o comando abaixo:

    ```bash
    ./gradlew clean bootRun
    ```

    Esse comando irá baixar as dependências do projeto, rodar as migrations do banco de dados usando flyway e subir a aplicação.

4. Acesse a documentação da API:

    Acesse a documentação da API no link `http://localhost:8080/swagger-ui.html`.

## 🧪 Testes

Os testes da plataforma foram desenvolvidos utilizando JUnit 5, Mockito e Spring Boot Test. Levando em consideração a pirâmide de testes, foram desenvolvidos testes unitários e testes de integração.

### Estrutura dos testes

Os testes estão divididos em testes unitários e testes de integração.

```bash
.
└── test
    ├── java
    │   └── com
    │       └── handleservice
    │           └── handleworkservice
    │               ├── infra
    │               ├── integrationTests
    │               │   ├── controller
    │               │   └── repository
    │               └── unitTests
    │                   ├── config
    │                   │   ├── argumentsolver
    │                   │   └── interceptor
    │                   ├── controller
    │                   ├── mapper
    │                   ├── service
    │                   └── validation
    └── resources

19 directories

```

Os testes unitários estão localizados na pasta `unitTests` e os testes de integração estão localizados na pasta `integrationTests`.

A pasta `infra` contém classes utilitárias para os testes.

Os testes de integração são realizados com um container docker do banco de dados PostgreSQL.

### Execução dos testes

Para executar os testes, execute o comando abaixo na raiz do projeto:

```bash
./gradlew clean test
```

## 🛠️ Tecnologias

[![java](https://img.shields.io/badge/-Java-007396?style=flat-square&logo=java&logoColor=white)](https://java.com)
[![spring](https://img.shields.io/badge/-Spring-6DB33F?style=flat-square&logo=spring&logoColor=white)](https://spring.io)
[![lombok](https://img.shields.io/badge/-Lombok-BCB7B6?style=flat-square&logo=lombok&logoColor=black)](https://projectlombok.org)
[![mapstruct](https://img.shields.io/badge/-MapStruct-1C1C1C?style=flat-square&logo=mapstruct&logoColor=white)](https://mapstruct.org)

[![junit](https://img.shields.io/badge/-JUnit-25A162?style=flat-square&logo=junit5&logoColor=white)](https://junit.org)
[![mockito](https://img.shields.io/badge/-Mockito-DA291C?style=flat-square&logo=mockito&logoColor=white)](https://site.mockito.org)

[![git](https://img.shields.io/badge/-Git-F05032?style=flat-square&logo=git&logoColor=white)](https://git-scm.com)
[![gradle](https://img.shields.io/badge/-Gradle-02303A?style=flat-square&logo=gradle&logoColor=white)](https://gradle.org)
[![docker](https://img.shields.io/badge/-Docker-2496ED?style=flat-square&logo=docker&logoColor=white)](https://docker.com)
[![swagger](https://img.shields.io/badge/-Swagger-85EA2D?style=flat-square&logo=swagger&logoColor=black)](https://swagger.io)

[![postgresql](https://img.shields.io/badge/-PostgreSQL-336791?style=flat-square&logo=postgresql&logoColor=white)](https://postgresql.org)
[![flyway](https://img.shields.io/badge/-Flyway-0072C6?style=flat-square&logo=flyway&logoColor=white)](https://flywaydb.org)

## ✒️  Autores

- Elpidio Cabral - [@ElpidioCabral](https://github.com/elpidiocabral)
- Gabriel Al-Samir - [@GuimaraesSl](https://github.com/GuimaraesSl)
- José Souza - [@JoseEdSouza](http://github.com/JoseEdSouza)
- Mariana Hofer - [@Hofeerr](http://github.com/hofeerr)
- Luigy Gabriel - [@Dev-Luigy](http://github.com/Dev-Luigy)

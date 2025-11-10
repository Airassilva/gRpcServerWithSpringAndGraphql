# 🏨 StayReserve - Hotel Catalog & Reservation System

O **StayReserve** é uma aplicação distribuída composta por dois principais serviços que se comunicam entre si, integrando **gRPC** e **GraphQL** para oferecer uma solução moderna, performática e escalável para gerenciamento e reservas de hotéis.

---

## 🧩 Arquitetura Geral

O sistema é dividido em dois módulos principais:

### 1. **stayreserve-hotel-catalog (gRPC Server)**
- Serviço responsável por **gerenciar o catálogo de hotéis** (listagem, busca, detalhes, etc).
- Exposto via **gRPC**, permitindo comunicação rápida e tipada entre serviços.
- Implementa as definições do arquivo `.proto` compartilhado com outros serviços.
- Escrita em **Java + Spring Boot**, com **gRPC Server** integrado.
- Porta: **6565**.

### 2. **stayreserve-reservation (GraphQL Client)**
- Serviço que consome o **gRPC Server (hotel-catalog)**.
- Oferece uma **API GraphQL** para clientes externos (como front-end ou mobile).
- Traduz requisições GraphQL em chamadas gRPC internas.
- Escrita em **Java + Spring Boot + GraphQL**.
- Porta padrão: **8080**.

---

## 🧠 Comunicação entre os serviços

A comunicação entre os serviços segue este fluxo:

## 🏗️ Arquitetura do Sistema

O sistema utiliza uma arquitetura em camadas com comunicação GraphQL e gRPC:
```
┌─────────────────────────────────────┐
│  Cliente (Front-end / Postman)      │
└──────────────┬──────────────────────┘
               │ GraphQL Query/Mutation
               ↓
┌─────────────────────────────────────┐
│  GraphQL API                        │
│  (stayreserve-reservation)          │
└──────────────┬──────────────────────┘
               │ gRPC Call
               ↓
┌─────────────────────────────────────┐
│  gRPC Client                        │
└──────────────┬──────────────────────┘
               │ Protocol Buffers
               ↓
┌─────────────────────────────────────┐
│  gRPC Server  (hotel-catalog)                        │                  │
└──────────────┬──────────────────────┘
               │ SQL/ORM
               ↓
┌─────────────────────────────────────┐
│  Banco de Dados / Lógica de Negócio │
└─────────────────────────────────────┘
```
## 🛠️ Tecnologias e Dependências Principais

### 🔹 Comuns entre os módulos

- **Java 17+**
- **Spring Boot**
- **Maven**
- **Docker / Docker Compose**
- **Protobuf** (para o gRPC)
- **Lombok** (clean code)

### 🔹 stayreserve-hotel-catalog

- `spring-boot-starter`
- `grpc-spring-boot-starter`
- `protobuf-java`
- `spring-data-jpa`
- **H2**

### 🔹 stayreserve-reservation

- `spring-boot-starter-graphql`
- `spring-boot-starter-web`
- `grpc-netty-shaded` (cliente gRPC)
- `protobuf-java`
- `graphql-spring-boot-starter`
- `spring-boot-starter-test`

## 🚀 Como Rodar o Projeto

### 1. Clonar o repositório
```bash
git clone https://github.com/Airassilva/gRpcServerWithSpringAndGraphql.git
cd gRpcServerWithSpringAndGraphql
```
### 2. Subir os containers
```docker 
docker-compose up --build
```
### 3. Testar os endpoints
```
GraphQL Playground:
http://localhost:8080/graphiql

gRPC Server (teste via cliente):
Endereço → localhost:6565
```

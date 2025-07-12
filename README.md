# PManager 📋 – Gerenciador de Projetos com Spring Boot

O **PManager** é uma aplicação backend desenvolvida em Java com Spring Boot para gerenciamento de projetos,
tarefas e membros de equipe. Ideal para organizações ou squads que desejam controlar o progresso de tarefas, 
membros e status dos projetos de forma eficiente.
+
---

## 🚀 Tecnologias Utilizadas

- Java 21
- Spring Boot
- Spring Security (autenticação via API Key)
- Spring Data JPA
- H2 Database (para testes)
- Maven

---

## ⚙️ Funcionalidades

- Cadastro e listagem de membros
- Criação e gerenciamento de projetos
- Controle de tarefas por projeto
- Atualização de status (tarefas e projetos)
- Autenticação por API Key
- Exceções customizadas e tratadas globalmente
- Paginação e ordenação de resultados

---

## 📁 Estrutura de Pastas

src/
- ├── domain/ # Entidades, serviços e repositórios
- │ ├── applicationservice/# Lógica de negócio
- │ ├── document
- │ ├── entity/ # Classes como Member, Project e Task
- │ ├── exception/ # Exceções personalizadas
- │ ├── model/
- │ └── repository/ # Interfaces JPA
- ├── infrastructure/
- │ ├── config/ # Configurações da aplicação
- │ ├── controller/ # Endpoints REST
- │ ├── dto/ # Objetos de transferência de dados
- │ ├── exception/ # Exceções personalizadas
- │ ├── security/ # Filtro e autenticação com API Key
- │ └── util/

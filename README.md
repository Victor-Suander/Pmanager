# PManager  – Gerenciador de Projetos com Spring Boot

O **PManager** é uma aplicação backend desenvolvida em Java com Spring Boot para gerenciamento de projetos,
tarefas e membros de equipe. Ideal para organizações ou squads que desejam controlar o progresso de tarefas, 
membros e status dos projetos de forma eficiente.
+
---

##  Tecnologias Utilizadas

- Java 21
- Spring Boot
- Spring Security (autenticação via API Key)
- Spring Data JPA
- H2 Database (para testes)
- Maven

---

##  Funcionalidades

- Cadastro e listagem de membros
- Criação e gerenciamento de projetos
- Controle de tarefas por projeto
- Atualização de status (tarefas e projetos)
- Autenticação por API Key
- Exceções customizadas e tratadas globalmente
- Paginação e ordenação de resultados

---

##  Domain Model 

<img width="564" height="756" alt="Screenshot 2025-07-12 163158" src="https://github.com/user-attachments/assets/3a52024e-ec51-478f-b272-6a8b6fcfcc3e" />

---

##  Estrutura de Pastas

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

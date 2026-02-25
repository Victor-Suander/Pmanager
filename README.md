# PManager - Project Management API

API REST desenvolvida em Java com Spring Boot para gerenciamento de projetos, tarefas e membros de equipe.

Este projeto foi criado com o objetivo de praticar arquitetura em camadas, desenvolvimento de APIs REST, persistência com JPA e implementação de segurança básica.

---

## Sobre o Projeto

O PManager é um backend que permite:

- Criar e gerenciar projetos
- Criar tarefas associadas a projetos
- Atualizar status de tarefas
- Gerenciar membros da equipe
- Listar dados com paginação e ordenação
- Autenticação simples via API Key

A aplicação segue boas práticas de organização de código, separando responsabilidades entre:

- Controller (camada de entrada HTTP)
- Service (regras de negócio)
- Repository (acesso a dados)
- Entities (modelo de domínio)
- Exception Handling (tratamento global de erros)

---

## Tecnologias Utilizadas

- Java 17+
- Spring Boot
- Spring Web
- Spring Data JPA
- Spring Security
- H2 Database
- Docker
- Maven

---

##  Domain Model 

<img width="564" height="756" alt="Screenshot 2025-07-12 163158" src="https://github.com/user-attachments/assets/3a52024e-ec51-478f-b272-6a8b6fcfcc3e" />

---

## Arquitetura

O projeto utiliza arquitetura em camadas:

- Código: Controller → Service → Repository → Database

Essa estrutura facilita:

- Manutenção
- Testabilidade
- Escalabilidade
- Separação de responsabilidades

---

## Segurança

A API utiliza autenticação baseada em API Key.

As requisições protegidas devem incluir no header:

- Código: X-API-KEY: thekey

---

 ## Como Executar o Projeto

- Clone o repositório

git clone https://github.com/Victor-Suander/Pmanager.git

- Entre na pasta

cd Pmanager

- Execute com Maven

./mvnw spring-boot:run

Ou rode diretamente pela sua IDE.

---

## Exemplos de Endpoints

### Criar Projeto

- POST /projects

Body:

- {
-   "name": "Sistema de Vendas",
-   "description": "Projeto para controle de vendas",
-   "status": "IN_PROGRESS"
- }

---

## Listar Projetos (com paginação)

- GET /projects?page=0&size=5&sort=name

---

## Criar Tarefa

- POST /tasks

- {
-   "title": "Criar API",
-   "description": "Desenvolver endpoints principais",
-   "status": "TODO",
-   "projectId": 1
- }

---

## Conceitos Aplicados

- RESTful API design
- Paginação e ordenação com Spring Data
- Tratamento global de exceções
- Injeção de dependência
- Padrão de arquitetura em camadas
- Princípios básicos de Clean Code

---

## Objetivo do Projeto

- Demonstrar conhecimento em:
- Desenvolvimento backend com Java
- Construção de APIs empresariais
- Estruturação de aplicações escaláveis
- Organização e clareza de código

---

## Melhorias Futuras

- Implementar autenticação com JWT
- Adicionar testes unitários
- Implementar documentação com Swagger/OpenAPI
- Migrar para banco de dados PostgreSQL
- Deploy em ambiente cloud (Render, Railway ou AWS)

---

## Sobre o Autor

- Victor Suander Camargo Martins
- Desenvolvedor Java em transição de carreira, focado em backend com Spring Boot.
- GitHub: github.com/Victor-Suander
- LinkedIn: linkedin.com/in/victorsuander

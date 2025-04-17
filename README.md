# Food Donation System

## ⭐ Visão Geral
O **Food Donation System** é uma aplicação Spring Boot que facilita a conexão entre doadores e organizações que necessitam de alimentos e materiais infantis. A plataforma oferece um sistema completo para gestão de doações, desde o cadastro de itens necessários até o acompanhamento do processo de doação.

## ⭐ Funcionalidades Principais

### 🔐 Autenticação e Autorização
- Registro e login de usuários (voluntários, ONGs, igrejas, clubes sociais)
- Proteção de endpoints com JWT (JSON Web Tokens)
- Diferenciação de tipos de usuários com perfis específicos

### 📦 Gestão de Itens de Doação
- Cadastro de itens necessários (alimentos, materiais infantis)
- Listagem de itens ativos disponíveis para doação
- Marcação de itens como doados/concluídos
- Lembrete periódico automático para verificar itens pendentes

### ♻ Processo de Doação
- Criação e gerenciamento de doações
- Agendamento de retirada dos itens
- Acompanhamento em tempo real do status da doação
- Notificações por e-mail para todas as partes envolvidas

## 🛠 Tecnologias Utilizadas

**Backend:**
- Java 17
- Spring Boot 3.1.0
- Spring Security
- JWT (JSON Web Tokens)
- Hibernate/JPA
- PostgreSQL (produção) / H2 (desenvolvimento)

**Documentação:**
- Swagger/OpenAPI 3.0

**Outras:**
- Lombok
- MapStruct (para mapeamento DTO-Entity)
- Spring Mail (notificações por e-mail)

## 🏗 Boas Práticas Implementadas
✅ Validação de dados em todas as camadas  
✅ Tratamento adequado e personalizado de erros  
✅ Segurança robusta com JWT e Spring Security  
✅ Documentação completa da API com Swagger/OpenAPI  
✅ Separação clara de responsabilidades (controllers, services, repositories)  
✅ Uso de DTOs para transferência de dados  
✅ Configuração de ambiente específica (dev/prod)  

## 🚀 Como Executar o Projeto

**Pré-requisitos:**
- Java 17 JDK instalado
- Maven 3.8+
- Banco de dados PostgreSQL (ou H2 para desenvolvimento)
- SMTP configurado para envio de e-mails

**Passos:**
1. Clone o repositório:
   ```bash
   git clone https://github.com/seu-usuario/food-donation-system.git
   cd food-donation-system

2. Configure o arquivo application.properties:
    ```bash
    # Banco de dados (exemplo para PostgreSQL)
    spring.datasource.url=jdbc:postgresql://localhost:5432/donation_db
    spring.datasource.username=seu_usuario
    spring.datasource.password=sua_senha
    
    # Configuração de e-mail
    spring.mail.host=smtp.example.com
    spring.mail.port=587
    spring.mail.username=seu_email@example.com
    spring.mail.password=sua_senha
    
3. Execute a aplicação:
    ```bash
    mvn spring-boot:run
    
OU se utilizar IntelliJ, basta executar o projeto.

4. Acesse a documentação da API:
    ```bash
    http://localhost:8080/swagger-ui.html

## 📚 Documentação da API
A API segue o padrão RESTful e está completamente documentada via Swagger/OpenAPI.

**Principais endpoints:**

- /api/auth/** - Autenticação
- /api/users/** - Gestão de Usuários
- /api/items/** - Itens de Doação
- /api/donations/** - Processo de Doação


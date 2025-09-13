# 📌 PortfolioMakerBack

API desenvolvida em **Spring Boot** para gerenciamento de portfólios, com **MongoDB** como banco de dados, autenticação via **JWT** e documentação interativa através do **Swagger**.

Cada usuário pode criar **um único portfólio**, contendo informações como descrição, habilidades, projetos, experiências e links.

Veja a aplicação completa hospedada [aqui](https://gustavoksbr-portfolio-maker.vercel.app).

Veja o código do Front-End [aqui](https://github.com/Gustavoksbr/PortfolioMakerFront)

---

## 🔨 Pré-requisitos

Antes de rodar o projeto, instale os seguintes componentes:

- [Java 17+](https://adoptium.net/)
- [MongoDB](https://www.mongodb.com/try/download/community) rodando em `localhost:27017`
- [Git](https://git-scm.com/)
- [Git Bash](https://gitforwindows.org/) (necessário para gerar chaves no Windows)

---

## 📂 Clonando o projeto

```bash
git clone https://github.com/Gustavoksbr/PortfolioMakerBack.git
cd PortfolioMakerBack
```

---

## ⚙️ Configuração do `application.properties`

O arquivo `src/main/resources/application.properties` deve ser configurado com as variáveis necessárias.

Você pode:
1. Definir as variáveis diretamente no `application.properties`, ou
2. Criar um arquivo `.env` na raiz do projeto (recomendado).

Exemplo de `aplication.properties` utilizando variáveis do .env:

````properties
spring.application.name=portfoliomaker

spring.data.mongodb.uri=${PORTFOLIO_MONGODB_URI}
spring.data.mongodb.database=portfoliomaker

jwt.secret=${PORTFOLIO_JWT_SECRET}

server.port=8080
server.error.include-stacktrace=never

spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true

spring.mail.host=${PORTFOLIO_EMAIL_HOST}
spring.mail.port=${PORTFOLIO_EMAIL_PORT}
spring.mail.username=${PORTFOLIO_EMAIL}
spring.mail.password=${PORTFOLIO_EMAIL_PASSWORD}
````
Exemplo de `.env`:

````env
PORTFOLIO_MONGODB_URI=mongodb://localhost:27017
PORTFOLIO_EMAIL_HOST=smtp.gmail.com
PORTFOLIO_EMAIL_PORT=587
PORTFOLIO_EMAIL=seu-email@gmail.com
PORTFOLIO_EMAIL_PASSWORD=sua-senha-ou-app-password
PORTFOLIO_JWT_SECRET=string-qualquer-com-no-minimo-32-caracteres
````
👉 Para conexão local com o MongoDB, defina:
```
PORTFOLIO_MONGODB_URI=mongodb://localhost:27017
```

---

## 📧 Configuração de envio de e-mails

O sistema envia e-mails para **recuperação de senha**.



🔗 Guia rápido para configurar no Gmail: https://youtu.be/_MwdIaMy_Ao?si=_O3NVEdCDNSwwh1u

---

## ▶️ Executando o projeto

Na raiz do projeto, rode:

Se estiver usando **.env**:
```bash
./gradlew bootRun --args='--spring.config.import=optional:file:.env[.properties]'
```

Ou, se as variáveis estiverem no `application.properties`:
```bash
./gradlew bootRun
```

O servidor será iniciado em: [http://localhost:8080](http://localhost:8080)

---

## 📜 Documentação da API

👉 https://portfoliomakerback.onrender.com/swagger-ui/index.html

---

# 📌 PortfolioMakerBack

API desenvolvida em **Spring Boot** para gerenciamento de portfólios, com **MongoDB** como banco de dados e autenticação via **JWT**

Cada usuário pode criar **um único portfólio**, contendo informações como descrição, habilidades, projetos, experiências e links.

Veja a aplicação completa hospedada: https://gustavoksbr-portfolio-maker.vercel.app

Veja o código do Front-End: https://github.com/Gustavoksbr/PortfolioMakerFront

---

## 🔨 Pré-requisitos

Antes de rodar o projeto, instale os seguintes componentes:

- Java 17+
- MongoDB
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

cors.allowed-origins=${CORS_ALLOWED_ORIGINS}
````
Exemplo de `.env`:

````dotenv
PORTFOLIO_MONGODB_URI=mongodb://localhost:27017
CORS_ALLOWED_ORIGINS=https://seu-dominio-front-end.com,http://localhost:3000,http://localhost:4200
PORTFOLIO_JWT_SECRET=string-qualquer-com-no-minimo-32-caracteres
MAKE_API=https://sua-api-make-para-envio-de-emails.com
````
👉 Para conexão local com o MongoDB, defina:
```
PORTFOLIO_MONGODB_URI=mongodb://localhost:27017
```
---

## 📧 Configuração de envio de e-mails

O sistema envia e-mails para **recuperação de senha**.

Você pode escolher entre três modos de envio
obs: verifique o lixo eletrônico quando fizer testes de recuperação de senha

### 1. JavaMail (SMTP)

- Recomendado para testes locais, por ser mais simples de configurar
- Diferentemente das outras opções, que utiliza outros serviços para enviar o email, o servidor SMTP é esta própria aplicação
- Você precisará de uma conta de e-mail como Gmail, Outlook, etc. (Caso use Gmail, crie uma senha de app. Veja este Guia rápido para configurar no Gmail: https://youtu.be/_MwdIaMy_Ao?si=_O3NVEdCDNSwwh1u)
- Configure as variáveis de ambiente no `.env` no seu ambiente:
```
PORTFOLIO_EMAIL_HOST=smtp.gmail.com
PORTFOLIO_EMAIL_PORT=587
PORTFOLIO_EMAIL=seuemail@gmail.com
PORTFOLIO_EMAIL_PASSWORD=sua_senha_de_app
```
- E deixe seu aplication.properties assim:

```properties
spring.mail.host=${PORTFOLIO_EMAIL_HOST}
spring.mail.port=${PORTFOLIO_EMAIL_PORT}
spring.mail.username=${PORTFOLIO_EMAIL}
spring.mail.password=${PORTFOLIO_EMAIL_PASSWORD}
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true
spring.profiles.active=javamail
```
### 2. Brevo
- Recomendado para produção, por ser simples e confiável
- Crie uma conta gratuita na Brevo (https://www.brevo.com/)
- Gere uma API Key em [SMTP & API](https://app.brevo.com/settings/keys/smtp)
- Cole a API Key no seu `.env`: 
```dotenv
BREVO_API_KEY=sua_api_key_aqui
BREVO_EMAIL_FROM=seu email cadastrado na brevo
```
- E deixe seu aplication.properties assim:

```properties
brevo.api.key=${BREVO_API_KEY}
brevo.email.from=${BREVO_EMAIL_FROM}
spring.profiles.active=brevo
```

### 3. Make

- Recomendado ao depender da complexidade do envio de emails. Como a Make é um site de automações, é possível criar fluxos complexos, mas no meu caso eu só usei um módulo WebHook e outro de Gmail
- Crie uma conta gratuita na Make (https://www.make.com/)
- Crie um cenário. Nele, adicione o um webhook (módulo "Custom webhook") e conecte-o a um módulo de envio de e-mail (Gmail, Outlook, etc). Se escolheu Gmail. recomendo esse vídeo para configurar: https://youtu.be/yIr2IDM5yPY?si=5pneZM83cYb9W6EE
- Copie a URL do webhook gerado. Clique em "redetermine data structure" e teste a url utilizando algum serviço de requisições HTTP (Postman, Insomnia, Httpie, etc). Utilize método POST e envie um JSON nesse formato:
```json
{
  "to": "emailteste@email.com",
  "subject": "Teste Make",
  "body": "Olá, este é um teste"
}
```
- Isto irá salvar o formato do JSON no webhook, que ficará aparecendo como opção de preenchimento no módulo de envio de e-mail
- Cole a url no seu `.env`:
```dotenv
MAKE_API=sua_url_secreta_da_make
```
- E deixe seu aplication.properties assim:

```properties
make.api=${MAKE_API}
spring.profiles.active=make
```
---

## ▶️ Executando o projeto

### 💻 Localmente

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

### 🐳 Docker

Se você não quiser instalar Java 17 e MongoDB localmente, pode rodar ambos por containers via **Docker Compose**

#### 1️⃣ Defina o `PORTFOLIO_MONGODB_URI` no `.env`:

```dotenv
PORTFOLIO_MONGODB_URI=mongodb://mongo:27017/portfoliomaker
```
Isso indica que o serviço MongoDB estará disponível no container `mongo`, que será criado pelo Docker Compose

Daria para fazer um único container que rode tanto o spring quanto o mongo e deixar essa variável com o valor `mongodb://localhost:27017/portfoliomaker`, mas é uma boa prática separá-los e deixar cada container com uma responsabilidade única

#### 2️⃣ Rodando os containers

```bash
docker-compose up --build
```

O servidor estará disponível em: http://localhost:8080

#### 3️⃣ Parando os containers

```bash
docker-compose down
```

Para remover também os volumes do MongoDB:

```bash
docker-compose down -v
```

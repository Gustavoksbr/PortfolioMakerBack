# 📌 PortfolioMakerBack

API em **Spring Boot** para gerenciamento de portfólios, utilizando **MongoDB**, autenticação via **JWT (RSA)** e documentação com **Swagger**.

Cada usuário pode ter no máximo **um portfólio**, que inclui informações como descrição, habilidades, projetos, experiências e links.

---

## 🔨 Pré-requisitos

- [Java 17+](https://adoptium.net/)
- [MongoDB](https://www.mongodb.com/try/download/community) rodando em `localhost:27017`
- [Git](https://git-scm.com/)
- [Git Bash](https://gitforwindows.org/) (para gerar as chaves no Windows)

---

## 📂 Clonar o projeto

```bash
git clone https://github.com/Gustavoksbr/PortfolioMakerBack.git
cd PortfolioMakerBack
```

---


## 🔑 Configuração das chaves JWT

O projeto utiliza autenticação com chaves **RSA**.  
As chaves **não estão versionadas** por segurança.

### Gerar as chaves

Abra o **Git Bash** na raiz do projeto e execute:

```bash
# Gera a chave privada (2048 bits)
openssl genrsa -out src/main/resources/app.key.priv 2048

# Extrai a chave pública
openssl rsa -in src/main/resources/app.key.priv -pubout -out src/main/resources/app.key.pub
```

Isso criará os arquivos:

- `src/main/resources/app.key.priv` → chave privada
- `src/main/resources/app.key.pub` → chave pública

---
## ⚙️ Proprerties

Abra o arquivo `src/main/resources/application.properties`. Você pode escolher entre definir as variáveis diretamente nesse arquivo substituindo os valores `${VARIAVEL}` pelas suas configurações, ou criar um arquivo `.env` na raiz do projeto (recomendado).

Em `spring.data.mongodb.database` escolha o nome banco de dados que você criou no MongoDB (o padrão é `portfoliomaker`).

````properties
spring.application.name=portfoliomaker

spring.data.mongodb.uri=${PORTFOLIO_MONGODB_URI}

spring.data.mongodb.database=portfoliomaker

jwt.private.key=classpath:app.key.priv
jwt.public.key=classpath:app.key.pub

server.port=8080

server.error.include-stacktrace=never

spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true

spring.mail.host=${PORTFOLIO_EMAIL_HOST}
spring.mail.port=${PORTFOLIO_EMAIL_PORT}

spring.mail.username=${PORTFOLIO_EMAIL}
spring.mail.password=${PORTFOLIO_EMAIL_PASSWORD}

````


A seguir, configure as últimas quatro variáveis de acordo com o serviço de e-mail que for utilizar (Gmail, Outlook, etc).

---
## 📧 Configuração de e-mail

O sistema envia e-mails para recuperação de senha. Este vídeo mostra um passo a passo de como configurar isso no Gmail: https://youtu.be/_MwdIaMy_Ao?si=_O3NVEdCDNSwwh1u

Com isso é possível preencher essas variáveis no `application.properties`:
````properties
spring.mail.host=${PORTFOLIO_EMAIL_HOST}
spring.mail.port=${PORTFOLIO_EMAIL_PORT}

spring.mail.username=${PORTFOLIO_EMAIL}
spring.mail.password=${PORTFOLIO_EMAIL_PASSWORD}
````

---

## ▶️ Rodando o projeto

Na raiz do projeto, rode:

Caso esteja usando um arquivo `.env`, rode o comando abaixo para carregar as variáveis:
```bash
./gradlew bootRun --args='--spring.config.import=optional:file:.env[.properties]'
```
Ou, se variáveis estiverem diretamente definidas no `application.properties`, rode:

```bash
./gradlew bootRun
```

Isso vai iniciar o servidor.

---

## 📜 Documentação da API

Após iniciar, acesse o Swagger:

👉 [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

---
## ⚠️ Importante

> Nunca suba suas chaves e o .env para o repositório.  
> Adicione ao `.gitignore`:
> ```
> *.key.priv
> *.key.pub
> *.env
> ```

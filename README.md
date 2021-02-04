# API Cadastro de Clientes

## Descrição
Uma simples API que simula um cadastro básico de clientes.

## Funcionalidades

* Cadastrar cliente;
* Cadastrar Estado e Cidade;
* Remover cliente;
* Editar cliente;
* Buscar clientes e cidades;

## Powered By

* Spring Boot 2.4.2;
* MySQL 8.0.2;
* FlyWay (migrations);
* Swagger;
* Java 8;
* Docker-Compose;

## [IMPORTANTE | OBRIGATÓRIO] -  Configurar Variáveis de Ambiente
Antes de executar a aplicação, é necessário configurar as variáveis de ambiente que constam no arquivo `./env/config.env.example`. **Você deverá renomeá-lo para `config.env`**. <br>

Todas as variáveis são de preenchimento obrigatório. 

**Se a opção de execução for o Docker-Compose, o banco de dados será automaticamente criado com as variáveis de ambiente que tenham o prefixo `"MYSQL_"`**.

## Como Usar
### Com Docker-Compose
Caso tenha o Docker e Docker-Compose instalados na máquina, acesse a pasta `./docker` e rode o comando `docker-compose up -d`. Aguarde alguns segundos e tente acessar qualquer endpoint ([verificar a seção "Swagger, Endpoints e Login"](#swagger-endpoints-e-login)).

### Manualmente
Para rodar manualmente, você precisará instalar ou usar o MySQL > 8 na nuvem. 

## Swagger, Endpoints e Login

A API dispõe de acesso ao Swagger para fácil utilização. Após subir o serviço, acesse http://localhost:8080/swagger-ui.html.

Os endpoints disponíveis estão presentes nele.

Para todos os endpoints (exceto o **"Swagger-UI"**), um token JWT é necessário. <u>Para a maioria das operações de criação, remoção e edição, o usuário deve ter permissão de administrador</u>.

Faça uma requisição **POST** para `/login`, com o body JSON:

```json
    "email": "email@email.com",
    "password": "password"
```

Se o e-mail e a senha forem válidos, o token JWT será devolvido no header `"Authorization"` da response.

**Para o primeiro login, verifique ["Usuário e Senha para o Primeiro Login"](#usuário-e-senha-para-o-primeiro-login).**

## Dados de Testes

A aplicação cria alguns dados para testes, **caso o banco de dados estiver vazio**.

São dois usuários, 2 cidades e 2 Estados.

### Usuário e Senha para o Primeiro Login
#### Comum

```json
    "email": "usuario@usuario.com.br",
    "password": "123456"
```

#### Administrador

```json
    "email": "admin@admin.com.br",
    "password": "123456"
```

## Tabelas e Migrations
As tabelas usadas pelo MySQL estão no arquivo `./src/main/resources/db/migration`. Usamos o [FlyWay](https://flywaydb.org/) para criar as tabelas no banco e versionar as migrations. **O Hibernate não cria ou atualiza as tabelas.**

## Testes 
Há alguns testes básicos de controllers na pasta `./src/test/java/com/lucasbrandao/cadastroclientes/tests/controllers`.

Ao executá-los, a aplicação utilizará um segundo banco de dados [(o H2 in-memory)](https://h2database.com), independente do MySQL principal, mas que terá o mesmo schema.

O arquivo `./src/test/resources/application.properties` gerencia a conexão dos testes com o H2.
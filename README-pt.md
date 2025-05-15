# API de Doadores de Sangue

## Visão Geral

A API de Doadores de Sangue é uma aplicação Java Spring Boot projetada para gerenciar e processar dados de doadores de sangue. Ela fornece endpoints para adicionar informações de doadores, recuperar estatísticas e realizar análises avançadas de dados, como cálculo de IMC, porcentagens de obesidade e compatibilidade de doadores.

## Funcionalidades

- **Adicionar Doadores**: Adicione um ou vários doadores ao banco de dados.
- **Recuperar Dados de Doadores**: Obtenha todos os registros de doadores.
- **Estatísticas e Análises**:
  - Contar candidatos por estado.
  - Calcular IMC médio por faixa etária.
  - Determinar porcentagens de obesidade por gênero.
  - Calcular idade média por tipo sanguíneo.
  - Identificar doadores potenciais para cada tipo sanguíneo receptor.
- **Documentação Swagger**: Documentação interativa da API disponível em `/swagger-ui/index.html`.

## Pré-requisitos

Para executar a aplicação localmente, certifique-se de ter os seguintes itens instalados:

- Java 17 ou superior
- Maven 3.8+
- Docker (opcional, para executar o MySQL via Docker Compose)

## Instruções de Configuração

### 1. Clone o Repositório

```bash
# Clone o repositório
git clone <repository-url>
cd blood-donor-api
```

### 2. Configure o Banco de Dados

A aplicação utiliza MySQL como banco de dados. Você pode configurá-lo de duas maneiras:

#### Opção 1: Usando Docker Compose

Execute o seguinte comando para iniciar um container MySQL:

```bash
docker-compose up -d
```

Isso iniciará uma instância do MySQL com a configuração especificada no arquivo `docker-compose.yml`.

#### Opção 2: Configuração Manual

- Instale o MySQL localmente.
- Crie um banco de dados chamado `blood_donor_db`.
- Atualize o arquivo `src/main/resources/application.properties` com suas credenciais do MySQL:
  ```properties
  spring.datasource.url=jdbc:mysql://localhost:3306/blood_donor_db
  spring.datasource.username=<seu-usuario>
  spring.datasource.password=<sua-senha>
  ```

### 3. Compile a Aplicação

Use o Maven para compilar o projeto:

```bash
mvn clean install
```

### 4. Execute a Aplicação

Inicie a aplicação usando o seguinte comando:

```bash
mvn spring-boot:run
```

A aplicação estará acessível em `http://localhost:8080`.

### 5. Acesse a Documentação Swagger

Visite a seguinte URL para explorar os endpoints da API:

```
http://localhost:8080/swagger-ui/index.html
```

## Endpoints da API

### Gerenciamento de Doadores

- **Adicionar um Único Doador**: `POST /api/donors`
- **Adicionar Múltiplos Doadores**: `POST /api/donors/batch`
- **Obter Todos os Doadores**: `GET /api/donors`

### Estatísticas e Análises

- **Contar Candidatos por Estado**: `GET /api/donors/count-by-state`
- **IMC Médio por Faixa Etária**: `GET /api/donors/average-bmi-by-age-range`
- **Porcentagem de Obesidade por Gênero**: `GET /api/donors/obesity-percentage-by-gender`
- **Idade Média por Tipo Sanguíneo**: `GET /api/donors/average-age-by-blood-type`
- **Doadores Potenciais por Tipo Receptor**: `GET /api/donors/potential-donors-by-recipient`

## Estrutura do Projeto

- **`src/main/java`**: Contém o código principal da aplicação.
  - **`controller`**: Controladores REST para lidar com as requisições da API.
  - **`service`**: Lógica de negócios e processamento de dados.
  - **`repository`**: Repositórios JPA para interação com o banco de dados.
  - **`model`**: Classes de entidade que representam tabelas do banco de dados.
  - **`dto`**: Objetos de Transferência de Dados para comunicação com a API.
  - **`config`**: Classes de configuração (ex.: Swagger, ModelMapper).
- **`src/main/resources`**: Contém propriedades da aplicação e recursos estáticos.
- **`docker-compose.yml`**: Arquivo Docker Compose para configurar o MySQL.

## Testes

Execute os testes usando o Maven:

```bash
mvn test
```

## Solução de Problemas

- **Problemas com Lombok**: Certifique-se de que o processamento de anotações está habilitado no seu IDE.
- **Erros de Conexão com o Banco de Dados**: Verifique suas credenciais do MySQL e a URL do banco de dados no arquivo `application.properties`.
- **Conflitos de Porta**: Certifique-se de que a porta `8080` não está sendo usada por outra aplicação.

## Licença

Este projeto está licenciado sob a Licença MIT. Consulte o arquivo LICENSE para mais detalhes.

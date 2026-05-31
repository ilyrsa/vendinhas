# Vendas API - Desafio Técnico

API REST desenvolvida em Java com Spring Boot para gerenciamento de vendas e itens de vendas. Este projeto foi inicialmente desenvolvido como resolução de um desafio técnico.

## Tecnologias Utilizadas

* **Java 17**
* **Spring Boot** (Web, Data JPA, Validation, Security)
* **PostgreSQL** (Banco de dados)
* **Maven** (Gerenciador de dependências)

## Pré-requisitos

Antes de executar, certifique-se de ter instalado em sua máquina:
* [Java 17+](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
* [PostgreSQL](https://www.postgresql.org/)

## Como executar o projeto localmente

1. **Clone o repositório ou extraia o projeto:**
   ```bash
   git clone <link-do-seu-repositorio>
   cd vendinhas
   ```

2. **Configure o Banco de Dados:**
   Abra o seu PostgreSQL (via pgAdmin ou terminal) e crie um banco de dados vazio chamado `vendas_db`:
   ```sql
   CREATE DATABASE vendas_db;
   ```

3. **Configure as Credenciais:**
   Abra o arquivo `src/main/resources/application.properties` e altere as propriedades `spring.datasource.username` e `spring.datasource.password` para o usuário e senha do seu PostgreSQL local.

4. **Inicie a Aplicação:**
   No terminal, na raiz do projeto, execute o comando do Maven Wrapper:
   ```bash
   ./mvnw spring-boot:run
   ```
   A API estará disponível em `http://localhost:8080`.

## Autenticação e Como Testar

A API possui um endpoint protegido por Basic Authentication. Para realizar as requisições (usando Postman, Insomnia ou Thunder Client), configure a aba de Autenticação (Auth -> Basic) com as seguintes credenciais:

* **Usuário:** `usuario`
* **Senha:** `senha`

### Endpoint Único: Criar ou Atualizar Venda

**POST** `/api/vendas`

Exemplo de corpo da requisição (JSON) - Sucesso:

```json
{
  "cliente": "João da Silva",
  "dataVenda": "2026-05-18",
  "valorTotal": 250.00,
  "status": "ABERTA",
  "formaPagamento": "PIX",
  "observacao": "Venda realizada no balcão",
  "itens": [
    {
      "produtoId": 1001,
      "descricaoProduto": "Ração para peixe 25kg",
      "quantidade": 2,
      "valorUnitario": 100.00,
      "valorTotal": 200.00
    }
  ]
}
```

> Para atualizar uma venda existente, basta adicionar o campo `"id"` na raiz do JSON e dentro dos objetos da lista de itens.

## Autora

[Larissa](https://github.com/ilyrsa)

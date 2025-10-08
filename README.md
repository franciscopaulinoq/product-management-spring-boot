# Atividade: CRUD de Produtos com Spring Boot

## Objetivo da Aplicação

O objetivo deste projeto é criar uma aplicação web RESTful simples utilizando o framework Spring Boot. A aplicação trata-se de um CRUD (Create, Read, Update, Delete) de Produtos, com endpoints para gerenciar um inventário e realizar buscas.

---

## Como Executar Localmente

Para executar este projeto em sua máquina local, siga os passos abaixo.

### Pré-requisitos

-   [Java Development Kit (JDK)](https://www.oracle.com/java/technologies/downloads/) na versão 21.
-   [Apache Maven](https://maven.apache.org/download.cgi) para gerenciamento de dependências.

### Passos

1.  **Clone o repositório:**
    ```bash
    git clone [https://github.com/franciscopaulinoq/product-management-spring-boot.git](https://github.com/franciscopaulinoq/product-management-spring-boot.git)
    ```

2.  **Navegue até o diretório do projeto:**
    ```bash
    cd product-management-spring-boot
    ```

3.  **Execute a aplicação com o Maven:**
    ```bash
    ./mvnw spring-boot:run
    ```
    A aplicação estará disponível em `http://localhost:8080`.

---

## Endpoints da API

A API possui os seis endpoints a seguir, todos com o prefixo `/api/products`.

### 1. Listar Todos os Produtos

Retorna uma lista com todos os produtos cadastrados.

-   **Método:** `GET`
-   **URL:** `/api/products`
-   **Resposta de Sucesso (Código 200):**
    ```json
    [
        {
            "id": 1,
            "name": "Álbum de Fotos",
            "price": 50.00,
            "quantity": 1
        }
    ]
    ```
-   **Exemplo com cURL:**
    ```bash
    curl -X GET http://localhost:8080/api/products
    ```

### 2. Buscar Produto por ID

Retorna um produto específico com base no seu `id`.

-   **Método:** `GET`
-   **URL:** `/api/products/{id}`
-   **Resposta de Sucesso (Código 200):**
    ```json
    {
        "id": 1,
        "name": "Álbum de Fotos",
        "price": 50.00,
        "quantity": 1
    }
    ```
-   **Resposta de Erro (Código 404):** Retornada se o `id` não for encontrado.
-   **Exemplo com cURL:**
    ```bash
    curl -X GET http://localhost:8080/api/products/1
    ```

### 3. Adicionar um Novo Produto

Cria um novo produto. O `id` do produto deve ser único.

-   **Método:** `POST`
-   **URL:** `/api/products`
-   **Corpo da Requisição (JSON):**
    ```json
    {
        "id": 1,
        "name": "Álbum de Fotos",
        "price": 50.00,
        "quantity": 1
    }
    ```
-   **Resposta de Sucesso (Código 201):** Retorna o objeto do produto recém-criado.
-   **Resposta de Erro (Código 409):** Retornada se já existir um produto com o mesmo `id`.
-   **Exemplo com cURL:**
    ```bash
    curl -X POST -H "Content-Type: application/json" -d '{"id": 1, "name": "Álbum de Fotos", "price": 50.00, "quantity": 1}' http://localhost:8080/api/products
    ```

### 4. Atualizar um Produto

Atualiza os dados de um produto existente com base no seu `id`.

-   **Método:** `PUT`
-   **URL:** `/api/products/{id}`
-   **Corpo da Requisição (JSON):**
    ```json
    {
        "name": "Álbum de Fotos Grande",
        "price": 75.50,
        "quantity": 5
    }
    ```
-   **Resposta de Sucesso (Código 200):** Retorna o objeto do produto atualizado.
-   **Resposta de Erro (Código 404):** Retornada se o `id` não for encontrado.
-   **Exemplo com cURL:**
    ```bash
    curl -X PUT -H "Content-Type: application/json" -d '{"name": "Álbum de Fotos Grande", "price": 75.50, "quantity": 5}' http://localhost:8080/api/products/1
    ```

### 5. Deletar um Produto

Remove um produto com base no seu `id`.

-   **Método:** `DELETE`
-   **URL:** `/api/products/{id}`
-   **Resposta de Sucesso (Código 204):** Nenhuma resposta no corpo.
-   **Resposta de Erro (Código 404):** Retornada se o `id` não for encontrado.
-   **Exemplo com cURL:**
    ```bash
    curl -X DELETE http://localhost:8080/api/products/1
    ```

### 6. Buscar Produtos por Nome

Retorna uma lista de produtos cujo nome contém o texto fornecido no parâmetro de busca.

-   **Método:** `GET`
-   **URL:** `/api/products/search`
-   **Parâmetro de Consulta:** `name` (string)
-   **Resposta de Sucesso (Código 200):** Retorna uma lista de produtos que correspondem à busca.
-   **Exemplo com cURL:**
    ```bash
    curl -X GET "http://localhost:8080/api/products/search?name=Álbum"
    ```
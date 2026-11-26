# API Editora - Trabalho 01 (Framework Spring)

## Integrantes
- Kamily Biagini
- Nome do integrante 2
- Nome do integrante 3

## Como rodar
1. Ter o Java 21 instalado
2. Rodar `./mvnw spring-boot:run` (ou executar a classe `ApiApplication` pelo IntelliJ)
3. A API sobe em `http://localhost:8080`

## Endpoints

| Método | Rota | Descrição | Status |
|---|---|---|---|
| POST | /api/autores | Cadastrar autor | 201 / 409 |
| GET | /api/autores | Listar autores | 200 |
| GET | /api/autores/{id} | Buscar autor por ID | 200 / 404 |
| GET | /api/autores/{id}/artigos | Artigos de um autor | 200 / 404 |
| DELETE | /api/autores/{id} | Remover autor | 204 / 404 / 409 |
| POST | /api/artigos | Cadastrar artigo (com `autor.id`) | 201 / 404 |
| GET | /api/artigos | Listar artigos | 200 |
| GET | /api/artigos/{id} | Buscar artigo por ID | 200 / 404 |

## Exemplo de POST /api/autores

```json
{
  "nome": "Machado de Assis",
  "email": "machado@email.com",
  "biografia": "Escritor brasileiro, fundador da Academia Brasileira de Letras."
}
```

## Exemplo de POST /api/artigos

```json
{
  "titulo": "Dom Casmurro",
  "resumo": "Romance de Machado de Assis.",
  "dataPublicacao": "2020-05-10",
  "idioma": "Português",
  "autor": { "id": 1 }
}
```
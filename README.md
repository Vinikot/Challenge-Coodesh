## README
> This is a challenge by [Coodesh](https://coodesh.com/)


## What is this repository for?
### Apresentar:

1. Projeto JAVA
2. Análise do Dashboard de Monitoramento de um Website
3. Queries SQL

### Projeto Java
- Na classe Main, métodos estáticos de cada TASK estão comentados.
- Escolha um método e retire o comentário para executá-lo.

---

- Para completar a TASK 4 tive que pesquisar como salvar um arquivo no AWS s3 com Java pois foi meu primeiro contato com AWS.

Comecei pesquisando sobre AWS s3 em Java e logo encontrei uma solução que utilizava a conexão com AWS s3 como API para enviar arquivos com solicitações HTTP.

Baseei-me nesta solução para conectar ao bucket s3 que foi solicitado.

Devido às minhas permissões de acesso à AWS, não consegui encontrar uma maneira de verificar se o arquivo foi enviado com sucesso.

- Para completar a TASK 5 usei SpringBoot.

Para completar a TASK 5 com o mesmo modelo que usei com as outras TASKs tive que pesquisar sobre como inicializar o SpringBoot sem ser diretamente na Classe Main.

#### Para testar a API da TASK 5 siga as intruções abaixo:

- Execute o projeto com o método da TASK5 na classe main sem estar comentado.
- Use os JSONs abaixo para fazer as requisições HTTP:
### POST
#### URL
        http://localhost:8080/Ingrediente/create
#### JSON
        {
            "nome": "Ovo"
        }
### GET
#### URL
        http://localhost:8080/Ingrediente
### PUT
#### URL
        http://localhost:8080/Ingrediente/update
#### JSON
        {
            "ingredienteId": 1,
            "nome": "Leite"
        }
### DELETE
#### URL
        http://localhost:8080/Ingrediente/delete
#### JSON
        {
            "ingredienteId": 1
        }
#### Para verificar a entidade no banco de dados acesser a URL abaixo:
        http://localhost:8080/h2-console/        

        JDBC URL: jdbc:h2:mem:68c7153f-fc63-4d15-8b72-683188274d26
        User Name: sa

---

- Document the entire investigation process for the development of the activity (README.md in your repository); the results of these tasks are as important as your thought process and decisions as you complete them, so try to document and present your hypotheses and decisions as much as possible.

### MySQL

1. No repositório ‘teste-mysql’ está o arquivo com as Queries solicitadas.

### Análise do Dashboard de Monitoramento de um Website

- No repositorio 'teste-analise-graficos' está o [PDF contendo minha análise](https://drive.google.com/file/d/1Xozl658KWfpq3BitKuL2RP7Vqhmt6Sxy/view?usp=drive_link).

## Um "Texto" qualquer

#### Meu nome é Vinicius Almeida Kotchetkoff, tenho 20 anos, estou cursando ADS na faculdade e gosto muito de criar coisas. É daí que vem meu interesse por programação.
#### Gosto de treinar, faço academia há 2 anos e procuro continuar indo todos os dias.
#### Meu hobby é estudar filosofia, história e teologia (Cristianismo Ortodoxo). Tento todos os dias me tornar uma pessoa melhor, mesmo que só um pouco. Espero poder ajudar outras pessoas a melhorar com minha atitude.

## Finalization and Presentation Instructions

1. Add the link to the repository with your solution to the test
2. Add the link to the presentation of your project in the README.md.
3. Check if the README is good and make the final commit in your repository;
4. Send and await instructions to proceed. Success and good luck. =)

## Support

Use [our community](https://discord.gg/rdXbEvjsWu) to ask questions about the process or send a message directly to a specialist in the platform chat.

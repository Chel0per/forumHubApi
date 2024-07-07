Este projeto é um desafio proposto pela Alura para consolidar os conceitos sobre Java e Spring ensinados nos cursos https://cursos.alura.com.br/course/spring-boot-3-desenvolva-api-rest-java,
https://cursos.alura.com.br/course/spring-boot-aplique-boas-praticas-proteja-api-rest e https://cursos.alura.com.br/course/spring-boot-3-documente-teste-prepare-api-deploy. É o último
desafio do programa ONE-Oracle Next Education BACKEND.

O projeto se baseia na criação de rotas para as operações CRUD em um banco de dados das seguintes entidades:

![diagrama_banco_de_dados_forumhub](https://github.com/Chel0per/forumHubApi/assets/115203217/c1014e5d-8396-45ef-b123-3e182df37194)

A primeira controller implementada foi a de usuários com o endereço "/usuarios". Nesta rota há dois endpoints de requisição POST. a requisição "/usuarios" faz o cadastro de um usuário, é necessário enviar um corpo na requisição da seguinte forma:

```
{
  "nome":string,
  "email":string,
  "senha":string
}
```

Nenhum dos campos pode ser nulo, e o campo de e-mail é unico, ou seja, só pode ser criado um usuário por e-mail. Caso ocorra o cadastro corretamente a requisição enviará uma resposta com os dados dos usuário criado no seguinte formato:

```
{
  "id":number,
  "nome":string,
  "email":string
}
```

Como um usuário não pode ficar sem um perfil, é criada uma entidade perfil para o usuário usando o nome fornecido na requisição de cadastro.

A outra rota POST "/usuarios/login" faz a autenticação do usuário e ela requer um corpo de requisição contendo o e-mail e a senha do usuário:

```
{
  "email":string,
  "senha":string
}
```

Se o e-mail e a senha enviados forem corretos a requisição devolverá uma string contendo um token. Todas as demais requisições precisam do envio do token no cabeçalho "Authorization" para que a requisição seja autorizada. O token expira em 4 horas após sua geração, após isso, é necessário gerar um novo token usando a rota de autenticação.

A próxima controller implementada é a de cursos "/cursos". Ela possui apenas a endpoint "/cursos" para requisição POST que faz o cadastro de uma entidade do tipo curso, é necessário cadastrar um curso para cadastrar um tópico futuramente. o corpo da requisição segue o seguinte formato:

```
{
  "categoria":string,
  "nome":string
}
```

Nenhum dos campos pode ser nulo, o nome deve ser único e a categoria precisa ser uma das cadastradas(PROGRAMAÇÃO,MARKETING,DESIGN,EDIÇÃO,CARREIRA). Escreva uma categoria exatamente como escrito nos parênteses e lembre-se do token de autenticação. Caso o cadastro ocorra com sucesso a requisição retorna informações do curso cadastrado no seguinte formato:

```
{
  "id":number,
  "categoria":string,
  "nome":string
}
```

A próxima controller é o centro da aplicação, a de tópicos "/tópicos". Essa controller possui 5 endpoints, todas no endereço "/tópicos". Há uma requisição POST que é utilizada para 
cadastrar um novo tópico enviando o seguinte corpo:

```
{
  "autorId":number,
  "cursoId":number,
  "titulo":string,
  "mensagem":string
}
```

Nenhum dos campos pode ser nulo, os ids do autor e do curso precisam ser válidos. O título pode repetir mas não é possível cadastrar um tópico exatamente igual a um já cadastrado, 
com título e mensagem iguais. Se o cadastro ocorrer com sucesso a requisição envia informações do tópico cadastrado no seguinte formato:

```
{
  "id":number,
  "autorId":number,
  "cursoId":number,
  "titulo":string,
  "mensagem":string,
  "dataDeCriacao":date,
  "status":boolean
}
```

A requisição GET sem o envio do id do tópico("/topicos"), retorna uma paginação de todos os tópicos ordenados por data de criação no seguinte formato:

```
{
    "totalPages": number,
    "totalElements": number,
    "size": number,
    "content": [Topico]
    "number":number,
    "sort": {
        "empty": boolean,
        "sorted": boolean,
        "unsorted": boolean
    },
    "first": boolean,
    "last": boolean,
    "numberOfElements": number,
    "pageable": {
        "pageNumber": number,
        "pageSize": number,
        "sort": {
            "empty": boolean,
            "sorted": boolean,
            "unsorted": boolean
        },
        "offset": number,
        "paged": boolean,
        "unpaged": boolean
    },
    "empty": boolean
}
```

Os tópicos estao presentes no valor da chave "content" da resposta em json e possuem o mesmo formato da resposta do método post. O restante são detalhes de paginação. É possível 
alterar os parâmetros de paginação como tamanho da página, que é 10 por padrão. Também é possível alterar a página que a rota retorna, lembre-se que a primeira página é a página 0, a
segunda página é a página 1 e assim por diante. Então para requisitar, por exemplo, a página 3, com 5 elementos, basta enviar a requisição GET para o enderço "/topicos?size=5&page=2".

No caso de uma requisição GET que inclui uma váriável de caminho id "topicos/{id}" é obtido a informação de um tópico específico no seguinte formato:

```
{
  "id":number,
  "autorId":number,
  "cursoId":number,
  "titulo":string,
  "mensagem":string,
  "dataDeCriacao":date,
  "status":boolean,
  "resposta":[Resposta]
}
```

O id enviado precisa ser de um tópico já cadastrado. O campo de resposta retorna todas as repostas cadastradas para esse tópico. O formato dos itens da lista de respostas é informado na controller de respostas.

Há também uma requisição PUT que inclui a variável de caminho id "topicos/{id}", é necessário o envio de um corpo com os dados a serem alterados na requisição, só é possível alterar 
os campos de título e de mensagem de um tópico, lembrando que não podem haver tópicos com mensagem e título repetidos. Caso a alteração ocorra sem problemas será enviado o tópico com 
as alterações no mesmo formato da requisição GET.

Por fim a requisição DELETE que também inclui a variável de caminho id "topicos/{id}", dado um id válido, essa requisição exclui um tópico e todas as respostas cadastradas com seu id e devolve os dados do tópico excluido no mesmo formato. 

Na controller de respostas "/respostas" há apenas um método POST para cadastro e um método DELETE para exclusão. O método POST requer o seguinte corpo:

```
{
  "autorId":number,
  "topicoId":number,
  "mensagem":string,
  "solucao":string
}
```

se o cadastro for feito sem problemas a requisição retornará os dados da resposta cadastrada no seguinte formato:

```
{
  "id":number,
  "autorId":number,
  "topicoId":number,
  "mensagem":string,
  "solucao":string,
  "dataDeCriacao":date
}
```

as respostas que aparecem no método GET "topicos/{id}" possuem este formato.

A requisição DELETE exige apenas uma variável de caminho id "/respostas/{id}". Ela exclui o tópico com o id enviado e retorna sua informações no mesmo formato da requisição POST.

Por fim a controller dos perfis "/perfis" gere as informações dos perfis dos usuários. O método POST na rota "/perfis" requer um corpo com o nome do perfil e o id do usuário:

```
{
  "nome":string,
  "usuarioId":number
}
```

Nenhum dos campos pode ser nulo. Podem ser criados perfis ilimitados para um usuário porém não podem haver perfis com o mesmo nome para um mesmo usuário. Caso o cadastro do perfil
ocorra com sucesso, a requisição retorna insformações do perfil cadastrado no seguinte formato:

```
{
  "id":number,
  "nome":string,
  "usuarioId":number
}
```

O método PUT na rota "/perfis/{id}" altera o nome de um perfil identificado na variável de caminho com o valor de nome enviado no corpo da requisição. O nome só não pode ser nulo e nem igual a um outro perfil daquele usuário. Se a alteração for feita, a requisição retorna informação do perfil, com as alterações, no mesmo formato do método POST.

Há também um método DELETE na rota "/perfis/{id}". Esse método apenas deleta o perfil do id enviado e retorna as suas informações nesse mesmo formato. Um usuário não pode ficar sem um perfil, então caso esse requisição for utilizada em um perfil de um usuário que só possui um perfil, o perfil não é deletado.

A última rota é de uma requisição do tipo GET com o endereço "perfis/per_user/{id}". Ela retorna todos os perfis cadastrados de um dado usuário no seguinte formato:

```
{
    "email":string,
    "perfis":[Perfil]
}
```

O id do usuário é passado como variável de caminho e a lista de perfis utiliza o mesmo formato das outras requisições.

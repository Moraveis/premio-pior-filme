# Prémio Pior Filme
Prémio Pior Filme (uma categoria do Golden Raspberry Awards) é um projeto desenvolvido para participar do processo seletivo da empresa TexoIT para a vaga de desenvolver Java Web direcionada ao Back End.

O projeto consiste em realizar a leitura de um arquivo .csv contendo os dados do filmes e staff indicados ao prémio permitindo consultar-los e manipular-los. 

Para o denvolvimento do projeto foi desenvolvido uma API RESTful responsável pela manulipulação dos dados através dos métodos GET, POST, PUT e DELETE as operações necessária. 

- [Ferramentas Usadas](#ferramentas-usadas)
- [Como executar](#como-executar)
- [Testes Unitarios](#teste-unitario)
- [Erros](#erros)
- [Recursos](#recursos)

## Ferramentas Usadas

- IDE de Desenvolvimento: [NetBeans 8.2 com Java EE 6](https://netbeans.org)
- Gerenciador de Dependências: [Maven 3.1](https://maven.apache.org/)
- Framework para desenvolvimento da API: [Spring Boot 2.1](https://spring.io/projects/spring-boot)
- Servidor de Aplicação: [Apache Tomcat 8.0](http://tomcat.apache.org/)
- Testes da API RESTful: [Postman](https://www.getpostman.com/) 

## Como Executar

- Instalar a IDE [NetBeans 8.2 com Java EE 8](https://netbeans.org)
- Instalar o servidor de aplicação [Apache Tomcat 8.0](http://tomcat.apache.org/)

- Abrir o projeto e selecionar a opção "Limpar e contruir" para que a IDE possa baixar as dependências. 

    URL Base: http://localhost:8080/

- A porta pode ou não mudar dependendo da escolha durante a instalação do servidor de aplicação.

- O deploy do projeto pode ocorrer de duas maneira: a convencional que é gerar o ".war" e realizar o deploy em um servidor de aplicação externo ou gerar um arquivo ".jar" e utilizar o suporte do Spring encapsular Tomcat.  

## Erros

<p>Utilizei os status code do protocolo HTTP, disponíveis no header de cada response.</p>
<p><code>200 OK</code> Tudo funcionou como esperado.</p>
<p><code>201 OK</code> O Recurso foi criado com sucesso.</p>
<p><code>400 Bad Request</code> Geralmente, um problema com os parâmetros.</p>
<p><code>404 Not Found</code> O recurso acessado não existe.</p>
<p><code>500 Server errors</code> Falha minha, algum erro no servidor.</p>

## Recursos

<h3>
<a id="user-content-listagem-de-todos-os-amigos" class="anchor" href="#listagem-de-todos-os-amigos" aria-hidden="true"><svg class="octicon octicon-link" viewBox="0 0 16 16" version="1.1" width="16" height="16" aria-hidden="true"><path fill-rule="evenodd" d="M4 9h1v1H4c-1.5 0-3-1.69-3-3.5S2.55 3 4 3h4c1.45 0 3 1.69 3 3.5 0 1.41-.91 2.72-2 3.25V8.59c.58-.45 1-1.27 1-2.09C10 5.22 8.98 4 8 4H4c-.98 0-2 1.22-2 2.5S3 9 4 9zm9-3h-1v1h1c1 0 2 1.22 2 2.5S13.98 12 13 12H9c-.98 0-2-1.22-2-2.5 0-.83.42-1.64 1-2.09V6.25c-1.09.53-2 1.84-2 3.25C6 11.31 7.55 13 9 13h4c1.45 0 3-1.69 3-3.5S14.5 6 13 6z"></path></svg></a>Winners</h3>
<p>Permite obter os vencedores de um determinado ano.</p>
<p><strong>Resource URL</strong></p>
<p><code>GET http://localhost:8080/winners/</code></p>
<p><strong>Parâmetros</strong></p>
<ul>
<li><strong>Ano</strong> - Obrigatório</li>
</ul>
<p><strong>Exemplo de Requisição</strong></p>
<p><code>http://localhost:8080/winners/1980</code></p>
<p><strong>Exemplo de Resposta</strong></p>
<pre><code>[
    {
        "id": 1,
        "year": 1980,
        "title": "Can't Stop the Music",
        "studios": [
            "Associated Film Distribution"
        ],
        "producers": [
            "Allan Carr"
        ],
        "winner": true
    }
]</code></pre>
<h3>

<h3>
<a id="user-content-listagem-de-todos-os-amigos" class="anchor" href="#listagem-de-todos-os-amigos" aria-hidden="true"><svg class="octicon octicon-link" viewBox="0 0 16 16" version="1.1" width="16" height="16" aria-hidden="true"><path fill-rule="evenodd" d="M4 9h1v1H4c-1.5 0-3-1.69-3-3.5S2.55 3 4 3h4c1.45 0 3 1.69 3 3.5 0 1.41-.91 2.72-2 3.25V8.59c.58-.45 1-1.27 1-2.09C10 5.22 8.98 4 8 4H4c-.98 0-2 1.22-2 2.5S3 9 4 9zm9-3h-1v1h1c1 0 2 1.22 2 2.5S13.98 12 13 12H9c-.98 0-2-1.22-2-2.5 0-.83.42-1.64 1-2.09V6.25c-1.09.53-2 1.84-2 3.25C6 11.31 7.55 13 9 13h4c1.45 0 3-1.69 3-3.5S14.5 6 13 6z"></path></svg></a>Years</h3>
<p>Permite obter os anos que tiveram mais de um vencedor.</p>
<p><strong>Resource URL</strong></p>
<p><code>GET http://localhost:8080/years</code></p>
<p><strong>Parâmetros</strong></p>
<ul>
<li>Nenhum</li>
</ul>
<p><strong>Exemplo de Requisição</strong></p>
<p><code>http://localhost:8080/years</code></p>
<p><strong>Exemplo de Resposta</strong></p>
<pre><code>{
    "years": [
        {
            "year": 1986,
            "winnerCount": 2
        },
        {
            "year": 1990,
            "winnerCount": 2
        },
        {
            "year": 2015,
            "winnerCount": 2
        }
    ]
}</code></pre>
<h3>

<h3>
<a id="user-content-listagem-de-todos-os-amigos" class="anchor" href="#listagem-de-todos-os-amigos" aria-hidden="true"><svg class="octicon octicon-link" viewBox="0 0 16 16" version="1.1" width="16" height="16" aria-hidden="true"><path fill-rule="evenodd" d="M4 9h1v1H4c-1.5 0-3-1.69-3-3.5S2.55 3 4 3h4c1.45 0 3 1.69 3 3.5 0 1.41-.91 2.72-2 3.25V8.59c.58-.45 1-1.27 1-2.09C10 5.22 8.98 4 8 4H4c-.98 0-2 1.22-2 2.5S3 9 4 9zm9-3h-1v1h1c1 0 2 1.22 2 2.5S13.98 12 13 12H9c-.98 0-2-1.22-2-2.5 0-.83.42-1.64 1-2.09V6.25c-1.09.53-2 1.84-2 3.25C6 11.31 7.55 13 9 13h4c1.45 0 3-1.69 3-3.5S14.5 6 13 6z"></path></svg></a>Studios</h3>
<p>Permite obter dos estúdios ordernada pelo número de vitórias.</p>
<p><strong>Resource URL</strong></p>
<p><code>GET http://localhost:8080/studios</code></p>
<p><strong>Parâmetros</strong></p>
<ul>
<li>Nenhum</li>
</ul>
<p><strong>Exemplo de Requisição</strong></p>
<p><code>http://localhost:8080/studios</code></p>
<p><strong>Exemplo de Resposta</strong></p>
<pre><code>{
    "studios": [
        {
            "name": "Paramount Pictures",
            "winCount": 6
        },
        {
            "name": "Columbia Pictures",
            "winCount": 6
        },
        {
            "name": "Warner Bros.",
            "winCount": 5
        }
    ]
}</code></pre>
<h3>

<h3>
<a id="user-content-listagem-de-todos-os-amigos" class="anchor" href="#listagem-de-todos-os-amigos" aria-hidden="true"><svg class="octicon octicon-link" viewBox="0 0 16 16" version="1.1" width="16" height="16" aria-hidden="true"><path fill-rule="evenodd" d="M4 9h1v1H4c-1.5 0-3-1.69-3-3.5S2.55 3 4 3h4c1.45 0 3 1.69 3 3.5 0 1.41-.91 2.72-2 3.25V8.59c.58-.45 1-1.27 1-2.09C10 5.22 8.98 4 8 4H4c-.98 0-2 1.22-2 2.5S3 9 4 9zm9-3h-1v1h1c1 0 2 1.22 2 2.5S13.98 12 13 12H9c-.98 0-2-1.22-2-2.5 0-.83.42-1.64 1-2.09V6.25c-1.09.53-2 1.84-2 3.25C6 11.31 7.55 13 9 13h4c1.45 0 3-1.69 3-3.5S14.5 6 13 6z"></path></svg></a>Producers</h3>
<p>Obter o produtor com maior intervalo entre dois prêmios, e o que obteve dois prêmios mais rápido.</p>
<p><strong>Resource URL</strong></p>
<p><code>GET http://localhost:8080/producers</code></p>
<p><strong>Parâmetros</strong></p>
<ul>
<li>Nenhum</li>
</ul>
<p><strong>Exemplo de Requisição</strong></p>
<p><code>http://localhost:8080/producers</code></p>
<p><strong>Exemplo de Resposta</strong></p>
<pre><code>{
    "min": [
        {
            "producer": "Producer Name", "interval": 9,
            "previousWin": 2018, "followingWin": 2019
        }
    ],
    "max": [
        {
            "producer": "Producer Name", "interval": 99,
            "previousWin": 1900, "followingWin": 1999
        }
    ]
}</code></pre>
<h3>

<h3>
<a id="user-content-listagem-de-todos-os-amigos" class="anchor" href="#listagem-de-todos-os-amigos" aria-hidden="true"><svg class="octicon octicon-link" viewBox="0 0 16 16" version="1.1" width="16" height="16" aria-hidden="true"><path fill-rule="evenodd" d="M4 9h1v1H4c-1.5 0-3-1.69-3-3.5S2.55 3 4 3h4c1.45 0 3 1.69 3 3.5 0 1.41-.91 2.72-2 3.25V8.59c.58-.45 1-1.27 1-2.09C10 5.22 8.98 4 8 4H4c-.98 0-2 1.22-2 2.5S3 9 4 9zm9-3h-1v1h1c1 0 2 1.22 2 2.5S13.98 12 13 12H9c-.98 0-2-1.22-2-2.5 0-.83.42-1.64 1-2.09V6.25c-1.09.53-2 1.84-2 3.25C6 11.31 7.55 13 9 13h4c1.45 0 3-1.69 3-3.5S14.5 6 13 6z"></path></svg></a>Movies</h3>
<p>Permiter deletar um filme da lista desde que este não seja um vencedor.</p>
<p><strong>Resource URL</strong></p>
<p><code>DELETE http://localhost:8080/movies</code></p>
<p><strong>Parâmetros</strong></p>
<ul>
<li><strong>Id</strong> - Obrigário para localizar o filme a ser excluído.</li>
</ul>
<p><strong>Exemplo de Requisição</strong></p>
<p><code>http://localhost:8080/movies/1</code></p>
<p><strong>Exemplo de Resposta</strong></p>
<pre>
<p><code>204 OK</code> Tudo funcionou porém não há informação a ser retornada.</p>
<p><code>403 Not Found</code> Operação não permitida.</p>
</pre>
<h3>

## Teste Unitario

A realização dos testes unitários durante o devenvolvimento de uma API nos ajuda a entender e validar os rescursos e dados.

<strong>As seguintes validações devem ser consideradas:</strong>

- Validar os dados que retorna.
- Validar os headers da resposta
- Validar se a resposta está de acordo.
- Validar se o content-type alterado, o comportamento continua o mesmo.
- Validar se a estrutura do JSON ou XML está correta.
- Validar se quando der erro o status está de acordo com os códigos de erro.
- Validar se uma requisição com informações incompleta, qual será o comportamento da requisição.
- Testar o tempo de resposta de uma requisição

Os testes implementados para a API encontranm-se na classe [MovieControllerTest.java] e uma vez que estes são testes voltados para testar o correto funcionadmento dos recurso da API, utilizamos de informação ficticias que nos permite simular os possiveis cenários. 

Para simular os casos basta alterar as informações ali presentes e executar a classe que irá inicializar apenas os recursos necessários ao final da execução será apresentado o resultado para cada um dos testes, no caso de falha será indicado a causa.

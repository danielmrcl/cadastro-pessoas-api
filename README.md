<h2>Digital Innovation: Desenvolvendo um sistema de gerenciamento de pessoas em API REST com Spring Boot</h2>

Este é um código escrito por mim ([Daniel Marcelo](https://web.digitalinnovation.one/users/danielmarcelo_junior)) durante uma live coding realizado pelo professor [Rodrigo Peleias](https://www.linkedin.com/in/rodrigopeleias/) no bootcamp de [Java Developer](https://web.digitalinnovation.one/track/java-developer) da [Digital Innovation One](https://digitalinnovation.one).

Nesta live coding desenvolvemos um pequeno sistema para o gerenciamento de pessoas de uma empresa através de uma API REST, criada com o Spring Boot.

Durante a sessão, foram desenvolvidos e abordados os seguintes tópicos:

* Setup inicial de projeto com o Spring Boot Initialzr
* Criação de modelo de dados para o mapeamento de entidades em bancos de dados
* Desenvolvimento de operações de gerenciamento de usuários (Cadastro, leitura, atualização e remoção de pessoas de um sistema).
* Relação de cada uma das operações acima com o padrão arquitetural REST, e a explicação de cada um dos conceitos REST envolvidos durante o desenvolvimento do projeto.
* Desenvolvimento de testes unitários para validação das funcionalidades
* Implantação do sistema na nuvem através do Heroku

Para executar o projeto no terminal, digite o seguinte comando:

```shell script
mvn spring-boot:run 
```

Após executar o comando acima, basta apenas abrir o seguinte endereço e visualizar a execução do projeto:

```
http://localhost:8080/api/v1/person
```

Ou para utilizar o projeto via nuvem, basta utilizar um software Rest API Client, como o PostMan. E encaminhar requisições `GET`, `POST`, `DELETE` ou `PUT` para o endereço:

```
https://dio-restapi-cadastropessoas.herokuapp.com/api/v1/person
```
---
## Exemplos de uso:

### Cadastrar uma pessoa:
Método: `POST`  
Endereço: `[host]/api/v1/person/`  
```json
{
	"firstName":"Nome",
	"lastName":"Sobrenome",
	"cpf":"11111111111",
	"birthDate":"01-01-2000",
	"phones":[
	    {
			"type":"MOBILE",
			"number":"44931836767"
		},
		{
			"type":"COMMERCIAL",
			"number":"4431836767"
		}
	]
}
```

### Listar todas as pessoas cadastradas:  
Método: `GET`  
Endereço: `[host]/api/v1/person`  
```json
// Corpo json: vazio
```

### Procurar pessoa cadastrada por ID:  
Método: `GET`  
Endereço: `[host]/api/v1/person/[id]`  
```json
// Corpo json: vazio
```

### Deletar uma pessoa por ID:  
Método: `DELETE`  
Endereço: `[host]/api/v1/person/[id]`  
```json
// Corpo json: vazio
```

### Atualizar uma pessoa:  
Método: `PUT`  
Endereço: `[host]/api/v1/person/[id]`  
```json
{
  "id": 1, // especificar id da pessoa
  "firstName": "Nome",
  "lastName": "Sobrenome",
  "cpf": "11111111111",
  "birthDate": "01-01-2000",
  "phones":[
    {
      "id" : 1, // especificar id do tipo do telefone
      "type": "HOME",
      "number": "4431836767"
    },
    {
      "id" : 2, // especificar id do tipo do telefone
      "type": "MOBILE",
      "number": "44931836767"
    },
    {
      "id" : 3, // especificar id do tipo do telefone
      "type": "COMMERCIAL", 
      "number": "4431836767"
    }
  ]
}
```
---
Foram necessários os seguintes pré-requisitos para a execução do projeto desenvolvido durante a aula:

* Java 11 ou versões superiores.
* Maven 3.6.3 ou versões superiores.
* Intellj IDEA Community Edition ou sua IDE favorita.
* Controle de versão GIT instalado na sua máquina.
* Conta no GitHub para o armazenamento do seu projeto na nuvem.
* Conta no Heroku para o deploy do projeto na nuvem
* Muita vontade de aprender e compartilhar conhecimento :)

Abaixo, seguem links bem bacanas, sobre tópicos mencionados durante a aula:

* [SDKMan! para gerenciamento e instalação do Java e Maven](https://sdkman.io/)
* [Referência do Intellij IDEA Community, para download](https://www.jetbrains.com/idea/download)
* [Palheta de atalhos de comandos do Intellij](https://resources.jetbrains.com/storage/products/intellij-idea/docs/IntelliJIDEA_ReferenceCard.pdf)
* [Site oficial do Spring](https://spring.io/)
* [Site oficial do Spring Initialzr, para setup do projeto](https://start.spring.io/)
* [Site oficial do Heroku](https://www.heroku.com/)
* [Site oficial do GIT](https://git-scm.com/)
* [Site oficial do GitHub](http://github.com/)
* [Documentação oficial do Lombok](https://projectlombok.org/)
* [Documentação oficial do Map Struct](https://mapstruct.org/)
* [Referência para o padrão arquitetural REST](https://restfulapi.net/)

[Neste link](https://drive.google.com/file/d/1crVPOVl6ok2HeYjh3fjQuGQn2lDZVHrn/view?usp=sharing), seguem os slides apresentados como o roteiro utilizado para o desenvolvimento do projeto da nossa sessão.

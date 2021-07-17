# kotlin-springboot-proposta-cartao
Api de um sistema para receber proposta de cartão de credito de clientes, esta api interage com outros sistemas para autorização e liberação de cartão.

## Setup do Projeto 
* Linguagem de programação: Kotlin
* Tecnologia: Spring Boot 2.3.12
* Gerenciador de dependência: Gradlle
* Java 11

## Implementação utilizando as ferramentas do ecossistema Spring com Kotlin 

* Spring Web: Crie aplicativos da web, incluindo RESTful, usando Spring MVC. Usa Apache Tomcat como o contêiner integrado padrão.

* Bean Validation: é uma especificação que permite validar objetos com facilidade em diferentes camadas da aplicação. A vantagem de usar Bean Validation é que as restrições ficam inseridas nas classes de modelo.

* MongoDB: Armazene dados em documentos flexíveis do tipo JSON, o que significa que os campos podem variar de documento para documento e a estrutura de dados pode ser alterada ao longo do tempo.

* Feign: Uma maneira elegante de criar clientes HTTP em Java

* Ferramenta Postman: Para testar as requisições e criar um ambiente de produção


## Porque Kotlin
* Com o conhecimento da linguagem Kotlin, é possivel desenvolver aplicações mobile, backend e web
* Kotlin é multiplataforma
* Mesmo com seus recursos propios e sua sintaxe limpa, ele mantem uma performace equivalente ao java
* Menos sucetivel a erros, Kotlin é por padrão null safety, fazendo que exista menos erro no seu desenvolvimento
* Kotlin simplifica alguns recursos que a linguagem java precisa para funcionar com excelencia, como por exemplo gatters and setters, faça o mesmo escrevendo mesnos
* Possui integração com codigo Java

## Porque Spring
* Spring é um framework Java Kotlin para backend mais popular no mundo por pessoas e por empresas, por conta da sua velocidade, simplicidade, produtividade
* Spring tem o historico comprovado de lidar com problemas de segurança de forma rapida e responsavel. Além disso Spring Securiy facilita a integração com esquemas de segurança padrão da industria e oferece soluçoes confiaveis que são seguras por padrão
* Spring Boot transforma a maneira como se aborda as tarefas de programação, otimizando radicalmente sua experiência. Podemos combinar Spring Boot com um rico conjunto de bibliotecas de suporte
* O conjunto flexivel e abrangente de extenções e bibliotecas de terceiros, permite que os desenvolvedores criem quase todos os aplicativos imagináveis
* Com Spring, notamos inicialização rapida, desligamento rapido e execução otimizada por padrão

## MongoDB
* É um banco de dados opensource, de alta performance e flexível, sendo considerado o principal banco de dados NoSQL.
Os banco de dados NoSQL apresentam algumas vantagens sobre os outros tipos, principalmente quando precisamos de escalabilidade, flexibilidade, bom desempenho e facilidade para consultas.
* O MongoDB é orientado a documentos, ou seja, os dados são armazenados como documentos, ao contrário de bancos de dados de modelo relacional, onde trabalhamos com registros em linhas e colunas. Os documentos podem ser descritos como dados no formato de chave-valor, no caso, utilizando o formato JSON (JavaScript Object Notation).

## Criação de uma nova proposta
* Objetivo:  Realizar a criação de uma proposta, durante o processo de criação da proposta deve ser checado restrições ao solicitante da proposta.

## Necessidades
* O documento necessário deve ser o CPF/CNPJ
* email
* nome
* endereço
* salário

## Restrições
* documento do solicitante deve ser obrigatório e válido
* email não pode ser vazio, nulo ou inválido
* nome não pode ser vazio ou nulo
* endereço não pode ser vazio ou nulo
* salário bruto não pode ser vazio, nulo ou negativo

## Não pode haver mais de uma proposta para o mesmo solicitante

* Objetivo: Criamos o fluxo de geração de proposta, porém nosso cliente solicitou uma alteração que consiste em adicionar uma nova validação.
Entretanto, não podemos permitir a existência de mais de uma proposta para o mesmo solicitante, ou seja, para o mesmo CNPJ ou CPF.
* Não podemos permitir a existencia de emails já cadastrados

* Devemos retornar o status code 422, quando o solicitante já requisitou uma proposta.
* Permitir a criação de uma proposta, caso o solicitante não tenha nenhuma outra.

## Consultando dados do solicitante: Api externa 
* Objetivo: Devemos consultar alguns dados financeiros do solicitante afim de validar se sera possivel oferecer um cartão.

* Necessidades: Antes de provisionar um cartão para o solicitante, devemos verificar se o mesmo possui restrições no sistema de dados finaneiros.

## Melhorando a visibilidade da nossa aplicação para equipe de operação (Health Check)
* Objetivo: Nossa aplicação deve "mostrar" a saúde dela para algum sistema automático de monitoramento ou equipe de operação!
* Spring Boot - Actuator
* Optar por gerenciar e monitorar seu aplicativo usando terminais HTTP ou com JMX. 
* A auditoria, a integridade e a coleta de métricas também podem ser aplicadas automaticamente ao seu aplicativo.
* Liberando end point: /actuator
* Liberando end point: /health
* Liberando end point: /info

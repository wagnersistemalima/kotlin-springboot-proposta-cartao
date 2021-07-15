# kotlin-springboot-proposta-cartao
Api de um sistema para receber proposta de cartão de credito de clientes, esta api interage com outros sistemas para autorização e liberação de cartão.

## Setup do Projeto 
* Linguagem de programação: Kotlin
* Tecnologia: Spring Boot 2.3.12
* Gerenciador de dependência: Gradlle
* Java 11

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

## Criação de uma nova proposta
* Objetivo:  Realizar a criação de uma proposta, durante o processo de criação da proposta deve ser checado restrições ao solicitante da proposta.

## Não pode haver mais de uma proposta para o mesmo solicitante

* Objetivo: Criamos o fluxo de geração de proposta, porém nosso cliente solicitou uma alteração que consiste em adicionar uma nova validação.
Entretanto, não podemos permitir a existência de mais de uma proposta para o mesmo solicitante, ou seja, para o mesmo CNPJ ou CPF.
* Não podemos permitir a existencia de emails já cadastrados

package br.com.wagner.proposta.novaProposta.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.math.BigDecimal
import java.util.*

@Document(value = "proposta")
class Proposta(

    @Id
    val id: String = UUID.randomUUID().toString(),

    val nome: String,

    val documento: String,

    val email: String,

    val endereco: Endereco,

    val salario: BigDecimal
)



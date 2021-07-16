package br.com.wagner.proposta.novaProposta.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.util.*

@Document(value = "endereco")
class Endereco(

    @Id
    val id: String = UUID.randomUUID().toString(),

    val logradouro: String,

    val bairro: String,

    val complemento: String,

    val uf: String,

    val cep: String
)

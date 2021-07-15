package br.com.wagner.proposta.novaProposta.model

import javax.persistence.Embeddable
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

@Embeddable        // classe sera incorporada pela entidade Proposta
class Endereco(

    val logradouro: String,

    val bairro: String,

    val complemento: String,

    val uf: String,

    val cep: String
)

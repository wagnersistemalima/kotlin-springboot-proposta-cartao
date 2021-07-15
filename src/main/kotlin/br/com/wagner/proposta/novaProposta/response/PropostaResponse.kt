package br.com.wagner.proposta.novaProposta.response

import br.com.wagner.proposta.novaProposta.model.Proposta

data class PropostaResponse(

    val idProposta: String
){
    constructor(proposta: Proposta): this(proposta.id)
}

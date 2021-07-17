package br.com.wagner.proposta.feingClient.apiCartoes

import br.com.wagner.proposta.novaProposta.model.Proposta

data class EnviaDadosClienteRequest(
    val documento: String,
    val nome: String,
    val idProposta: String
){
    constructor(proposta: Proposta): this(proposta.documento, proposta.nome, proposta.id)
}

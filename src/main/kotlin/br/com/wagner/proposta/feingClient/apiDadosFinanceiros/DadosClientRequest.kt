package br.com.wagner.proposta.feingClient.apiDadosFinanceiros

import br.com.wagner.proposta.novaProposta.model.Proposta

data class DadosClientRequest(

    val documento: String,
    val nome: String,
    val idProposta: String
){
    constructor(proposta: Proposta): this(proposta.documento, proposta.nome, proposta.id)
}

package br.com.wagner.proposta.novaProposta.response

import br.com.wagner.proposta.novaProposta.model.Proposta

data class BuscaInformacaoPropostaResponse(
    val status: String?
) {
    constructor(proposta: Proposta): this(proposta.status)
}
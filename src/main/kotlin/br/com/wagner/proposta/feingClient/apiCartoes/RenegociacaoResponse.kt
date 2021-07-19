package br.com.wagner.proposta.feingClient.apiCartoes

import java.time.LocalDateTime

data class RenegociacaoResponse(

    val id: String?,
    val quantidade: Int?,
    val valor: Int?,
    val dataDeCriacao: LocalDateTime?
)

package br.com.wagner.proposta.feingClient.apiCartoes

import java.time.LocalDateTime

data class VencimentoResponse(

    val id: String?,
    val dia: Int?,
    val dataDeCriacao: LocalDateTime?
)

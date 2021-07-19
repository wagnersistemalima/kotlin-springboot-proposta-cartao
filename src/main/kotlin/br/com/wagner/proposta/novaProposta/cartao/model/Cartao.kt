package br.com.wagner.proposta.novaProposta.cartao.model

import br.com.wagner.proposta.feingClient.apiCartoes.RenegociacaoResponse
import br.com.wagner.proposta.feingClient.apiCartoes.VencimentoResponse
import java.time.LocalDateTime

class Cartao(


    val id: String,
    val emitidoEm: LocalDateTime,
    val titular: String,

    val bloqueios: MutableList<Any?>,

    val avisos: MutableList<Any?>,

    val carteiras: MutableList<Any?>,

    val parcelas: MutableList<Any?>,

    val limite: Int,

    val renegociacao: RenegociacaoResponse?,

    val vencimento: VencimentoResponse?,

    val idProposta: String
)

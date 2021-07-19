package br.com.wagner.proposta.feingClient.apiCartoes

import br.com.wagner.proposta.handller.exceptions.ExceptionGenericValidated
import br.com.wagner.proposta.novaProposta.cartao.model.Cartao
import br.com.wagner.proposta.novaProposta.model.Proposta
import java.time.LocalDateTime

data class RecebeDadosClienteResponse(

    val id: String,

    val emitidoEm: LocalDateTime,

    val titular: String,

    val bloqueios: MutableList<Any?>,

    val avisos: MutableList<Any?>,

    val carteiras: MutableList<Any?>,

    val parcelas: MutableList<Any?>,

    val limite: Int,

    var renegociacao: RenegociacaoResponse?,

    val vencimento: VencimentoResponse?,

    val idProposta: String
) {

    // metodo para converter objeto de resposta em entidade
    fun toModel(proposta: Proposta): Cartao {
        // validação

        if(!proposta.id.equals(idProposta)) {
            throw ExceptionGenericValidated("Erro validação id proposta")
        }
        return Cartao(id, emitidoEm, titular, bloqueios, avisos, carteiras, parcelas, limite,renegociacao,vencimento, idProposta)
    }
}

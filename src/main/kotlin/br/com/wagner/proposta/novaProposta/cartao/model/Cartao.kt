package br.com.wagner.proposta.novaProposta.cartao.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime

@Document(value = "cartao")
class Cartao(

    @Id
    val id: String,
    val emitidoEm: LocalDateTime,
    val titular: String,
    val limite: Int,
    val idProposta: String
)

package br.com.wagner.proposta.novaProposta.biometria.model

import java.time.LocalDateTime
import java.util.*

class Biometria(

    val id: String = UUID.randomUUID().toString(),
    val biometria: String,
    val idCartao: String
){
    var dataRegistro: LocalDateTime = LocalDateTime.now()
}

package br.com.wagner.proposta.novaProposta.biometria.response

import br.com.wagner.proposta.novaProposta.biometria.model.Biometria

data class CriacaoBiometriaResponse(
    val id: String
){
    constructor(biometria: Biometria): this(biometria.id)
}

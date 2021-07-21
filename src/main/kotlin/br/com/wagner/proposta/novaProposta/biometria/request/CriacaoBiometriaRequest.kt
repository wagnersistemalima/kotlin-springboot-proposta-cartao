package br.com.wagner.proposta.novaProposta.biometria.request

import br.com.wagner.proposta.novaProposta.biometria.model.Biometria
import java.util.*
import javax.validation.constraints.NotBlank

data class CriacaoBiometriaRequest(

    @field:NotBlank
    val biometria: String
) {

    // metodo para converter request em entidade

    fun toModel(idCartao: String): Biometria {

        val biometriaCodificada = Base64.getEncoder().encodeToString(biometria.toByteArray())

        return Biometria(biometria = biometriaCodificada, idCartao = idCartao)
    }
}

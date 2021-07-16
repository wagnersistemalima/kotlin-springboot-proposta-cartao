package br.com.wagner.proposta.novaProposta.request

import br.com.wagner.proposta.novaProposta.model.Endereco
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

data class EnderecoRequest(

    @field:NotBlank
    @field:Size(max = 50)
    val logradouro: String,

    @field:NotBlank
    @field:Size(max = 30)
    val bairro: String,

    @field:NotBlank
    @field:Size(max = 50)
    val complemento: String,

    @field:NotBlank
    @field:Size(max = 2)
    val uf: String,

    @field:NotBlank
    val cep: String


) {

    // metodo para converter request em entidade

    fun toModel(): Endereco {
        return Endereco(
            logradouro = logradouro,
            bairro = bairro,
            complemento = complemento,
            uf = uf.toUpperCase(),
            cep = cep)
    }
}

package br.com.wagner.proposta.novaProposta.request

import br.com.wagner.proposta.compartilhado.CpfOuCnpj
import br.com.wagner.proposta.novaProposta.model.Proposta
import java.math.BigDecimal
import javax.validation.Valid
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Positive

data class PropostaRequest(

    @field:NotBlank
    val nome: String,

    @field:NotBlank
    @field:CpfOuCnpj
    val documento: String,

    @field:NotBlank
    @field:Email
    val email: String,

    @field:NotNull
    @field:Valid
    val endereco: EnderecoRequest,

    @field:NotNull
    @field:Positive
    val salario: BigDecimal
) {

    // metodo para converter request em entidade

    fun toModel(): Proposta {

        return Proposta(nome = nome, documento = documento, email = email, endereco = endereco.toModel(), salario = salario)
    }
}

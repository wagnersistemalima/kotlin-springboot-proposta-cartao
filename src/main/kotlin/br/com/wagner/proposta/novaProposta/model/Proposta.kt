package br.com.wagner.proposta.novaProposta.model

import java.math.BigDecimal
import java.util.*
import javax.persistence.Embedded
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "tb_proposta")
class Proposta(

    @Id
    val id: String = UUID.randomUUID().toString(),

    val nome: String,

    val documento: String,

    val email: String,

    @Embedded                         // incorporar para a mesma tabela o endereço
    val endereco: Endereco,

    val salario: BigDecimal
)



package br.com.wagner.proposta.novaProposta.repository

import br.com.wagner.proposta.novaProposta.model.Proposta
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface PropostaRepository: JpaRepository<Proposta, String> {

    fun findByDocumento(documento: String): Optional<Proposta>

    fun existsByDocumento(documento: String): Boolean

    fun existsByEmail(email: String): Boolean
}
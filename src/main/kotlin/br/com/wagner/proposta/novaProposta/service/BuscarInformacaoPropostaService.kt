package br.com.wagner.proposta.novaProposta.service

import br.com.wagner.proposta.handller.exceptions.ResourceNotFoundException
import br.com.wagner.proposta.novaProposta.repository.PropostaRepository
import br.com.wagner.proposta.novaProposta.response.BuscaInformacaoPropostaResponse
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class BuscarInformacaoPropostaService(@field:Autowired val propostaRepository: PropostaRepository) {

    val logger = LoggerFactory.getLogger(BuscarInformacaoPropostaService::class.java)

    // metodo contendo a logica para buscar uma proposta po id

    @Transactional
    fun findById(id: String): BuscaInformacaoPropostaResponse {
        logger.info("---Execultando a busca pela proposta $id -------")
        val proposta = propostaRepository.findById(id).orElseThrow { ResourceNotFoundException("id da proposta não encontrado") }

        return BuscaInformacaoPropostaResponse(proposta)

    }
}
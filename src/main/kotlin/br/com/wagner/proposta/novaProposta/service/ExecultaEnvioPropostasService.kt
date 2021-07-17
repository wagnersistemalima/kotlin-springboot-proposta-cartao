package br.com.wagner.proposta.novaProposta.service

import br.com.wagner.proposta.feingClient.apiCartoes.ApiCartaoClient
import br.com.wagner.proposta.feingClient.apiCartoes.EnviaDadosClienteRequest
import br.com.wagner.proposta.novaProposta.cartao.repository.CartaoRepository
import br.com.wagner.proposta.novaProposta.repository.PropostaRepository
import feign.FeignException
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ExecultaEnvioPropostasService(
    @field:Autowired val propostaRepository: PropostaRepository,
    @field:Autowired val cartaoRepository: CartaoRepository,
    @field:Autowired val apiCartaoClient: ApiCartaoClient
) {

    val logger = LoggerFactory.getLogger(ExecultaEnvioPropostasService::class.java)

    @Transactional
    @Scheduled(fixedDelay = 20000)
    fun execultaSolicitacaoCartao() {
        logger.info("---Execultando solicitação de criação de cartao para propostas Elegiveis-----")

        try {

            val status = "ELEGIVEL"

            val listaDeClientesElegiveis = propostaRepository.findByStatus(status)

            for(proposta in listaDeClientesElegiveis) {

                // validacao
                if(proposta.cartao == null) {
                    logger.info("---Enviando propostas ELEGIVEIS que não estao associadas a um cartao------")
                    val enviaDadosClienteRequest = EnviaDadosClienteRequest(proposta)
                    val response = apiCartaoClient.solicitaCartao(enviaDadosClienteRequest).body

                    val cartao = response!!.toModel(proposta)
                    cartaoRepository.save(cartao)
                    proposta.adicionaCartao(cartao)
                    propostaRepository.save(proposta)
                    logger.info("--Proposta associada a um cartao salva com sucesso---")
                }

            }

        }
        catch (erro: FeignException) {
            logger.error("---Entrou no cath, servidor cartao indisponivel---")
        }

    }
}
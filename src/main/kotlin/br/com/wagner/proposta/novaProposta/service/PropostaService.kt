package br.com.wagner.proposta.novaProposta.service

import br.com.wagner.proposta.feingClient.apiDadosFinanceiros.ApiDadosFinanceiroClient
import br.com.wagner.proposta.feingClient.apiDadosFinanceiros.DadosClientRequest
import br.com.wagner.proposta.handller.exceptions.ExceptionGenericValidated
import br.com.wagner.proposta.novaProposta.repository.PropostaRepository
import br.com.wagner.proposta.novaProposta.request.PropostaRequest
import br.com.wagner.proposta.novaProposta.response.PropostaResponse
import feign.FeignException
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class PropostaService(
    @field:Autowired val propostaRepository: PropostaRepository,
    @field:Autowired val apiDadosFinanceiroClient: ApiDadosFinanceiroClient,
    ) {

    val logger = LoggerFactory.getLogger(PropostaService::class.java)

    // metodo contendo a logica para salvar uma proposta no banco

    @Transactional
    fun insert(request: PropostaRequest): PropostaResponse {
        logger.info("--------Execultando o cadastro de uma proposta----------")

        // validação campo unico documento

        if(propostaRepository.existsByDocumento(request.documento)) {
            logger.error("------Entrou no if, já existe proposta para este documento------")
            throw ExceptionGenericValidated("Proposta ja cadastrada para este documento")
        }

        // validacao campo unico email

        if(propostaRepository.existsByEmail(request.email)) {
            throw ExceptionGenericValidated("Proposta ja cadastrada para este documento")
        }

        val proposta = request.toModel()
        propostaRepository.save(proposta)

        // enviar dados para analize

        val dadosClienteRequest = DadosClientRequest(proposta)

        try {
            val response = apiDadosFinanceiroClient.consulta(dadosClienteRequest).body

            // validacao

            if(response!!.resultadoSolicitacao.equals("COM_RESTRICAO")) {
                logger.info("---Salvando proposta COM_RESTRICAO----")
                proposta.status = "NAO_ELEGIVEL"
                propostaRepository.save(proposta)
            }
            else if(response!!.resultadoSolicitacao.equals("SEM_RESTRICAO")) {
                logger.info("---Salvando proposta SEM_RESTRICAO")
                proposta.status = "ELEGIVEL"
                propostaRepository.save(proposta)
            }
        }
        catch (erro: FeignException) {
            logger.error("---Entrou no cat, Salvando proposta COM_RESTRICAO")
            proposta.status = "NAO_ELEGIVEL"
            propostaRepository.save(proposta)
        }


        logger.info("-----proposta salva com sucesso------")
        return PropostaResponse(proposta)
    }

}
package br.com.wagner.proposta.novaProposta.biometria.service

import br.com.wagner.proposta.handller.exceptions.ResourceNotFoundException
import br.com.wagner.proposta.novaProposta.biometria.request.CriacaoBiometriaRequest
import br.com.wagner.proposta.novaProposta.biometria.response.CriacaoBiometriaResponse
import br.com.wagner.proposta.novaProposta.repository.PropostaRepository
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class CriacaoBiometriaService(
    @field:Autowired val propostaRepository: PropostaRepository
) {

    val logger = LoggerFactory.getLogger(CriacaoBiometriaRequest::class.java)

    // metodo contendo a logica para salvar biometria

    fun insert(idCartao: String, request: CriacaoBiometriaRequest): CriacaoBiometriaResponse {
        logger.info("---Execultando o cadastro da biometria----")

        // validação do idCartao

        val proposta = propostaRepository.findByCartaoId(idCartao).orElseThrow{ ResourceNotFoundException("id do cartao nao encontrado")}

        //val cartao = Cartao(proposta)

        val biometria = request.toModel(idCartao)

        proposta.cartao!!.adicionaBiometria(biometria)

        propostaRepository.save(proposta)

        logger.info("-------Biometria Salva Com Sucesso-------")
        return CriacaoBiometriaResponse(biometria)

    }
}
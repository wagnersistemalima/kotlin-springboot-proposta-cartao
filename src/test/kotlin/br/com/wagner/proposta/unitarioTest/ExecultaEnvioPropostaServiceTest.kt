package br.com.wagner.proposta.unitarioTest

import br.com.wagner.proposta.feingClient.apiCartoes.ApiCartaoClient
import br.com.wagner.proposta.feingClient.apiCartoes.EnviaDadosClienteRequest
import br.com.wagner.proposta.novaProposta.repository.PropostaRepository
import br.com.wagner.proposta.novaProposta.service.ExecultaEnvioPropostasService
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)
class ExecultaEnvioPropostaServiceTest {

    @field:InjectMocks
    lateinit var execultaEnvioPropostaService: ExecultaEnvioPropostasService

    @field:Mock
    lateinit var propostaRepository: PropostaRepository


    @field:Mock
    lateinit var apiCartaoClient: ApiCartaoClient

    @Test
    fun `deve salvar a proposta com cartao associado`() {

        // cenario

        val statusValido = "ELEGIVEL"

        val lista = propostaRepository.findByStatus(statusValido)

        //comportamento:
        Mockito.`when`(propostaRepository.findByStatus(statusValido)).thenReturn(lista)


        // ação

        for(proposta in lista) {

            val enviaDadosClienteRequest = EnviaDadosClienteRequest(proposta)

            val response = apiCartaoClient.solicitaCartao(enviaDadosClienteRequest)

            // comportamento
            Mockito.`when`(apiCartaoClient.solicitaCartao(enviaDadosClienteRequest)).thenReturn(response)

            val cartao = response!!.body!!.toModel(proposta)

            // comportamento
            Mockito.`when`(propostaRepository.save(proposta)).thenReturn(proposta)

            proposta.adicionaCartao(cartao)
            propostaRepository.save(proposta)

        }


        // assertivas

        for(proposta in lista) {

            Assertions.assertEquals(proposta.cartao != null, proposta.cartao)
        }


    }
}
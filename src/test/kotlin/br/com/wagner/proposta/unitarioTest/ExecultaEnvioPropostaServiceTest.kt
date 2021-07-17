package br.com.wagner.proposta.unitarioTest

import br.com.wagner.proposta.feingClient.apiCartoes.ApiCartaoClient
import br.com.wagner.proposta.feingClient.apiCartoes.EnviaDadosClienteRequest
import br.com.wagner.proposta.novaProposta.cartao.repository.CartaoRepository
import br.com.wagner.proposta.novaProposta.model.Endereco
import br.com.wagner.proposta.novaProposta.model.Proposta
import br.com.wagner.proposta.novaProposta.repository.PropostaRepository
import br.com.wagner.proposta.novaProposta.service.ExecultaEnvioPropostasService
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.springframework.test.context.junit.jupiter.SpringExtension
import java.math.BigDecimal
import java.util.*

@ExtendWith(SpringExtension::class)
class ExecultaEnvioPropostaServiceTest {

    @field:InjectMocks
    lateinit var execultaEnvioPropostaService: ExecultaEnvioPropostasService

    @field:Mock
    lateinit var propostaRepository: PropostaRepository

    @field:Mock
    lateinit var cartaoRepository: CartaoRepository

    @field:Mock
    lateinit var apiCartaoClient: ApiCartaoClient

    @Test
    fun `deve enviar uma lista de propostas ELEGIVEIS para api cartao`() {

        // cenario

        val statusValido = "ELEGIVEL"

        val lista = propostaRepository.findByStatus(statusValido)

        //comportamento:
        Mockito.`when`(propostaRepository.findByStatus(statusValido)).thenReturn(lista)

        // ação

        for(proposta in lista) {

            // comportamento
            Mockito.doNothing().`when`(propostaRepository.save(proposta))

            val enviaDadosClienteRequest = EnviaDadosClienteRequest(proposta)

            val response = apiCartaoClient.solicitaCartao(enviaDadosClienteRequest)

            // comportamento
            Mockito.`when`(apiCartaoClient.solicitaCartao(enviaDadosClienteRequest)).thenReturn(response)

            val cartao = response!!.body!!.toModel(proposta)
            proposta.adicionaCartao(cartao)
            propostaRepository.save(proposta)


            // comportamento
            Mockito.doNothing().`when`(cartaoRepository.save(cartao))

        }


        // assertivas

        for(proposta in lista) {

            Assertions.assertEquals(proposta.cartao != null, proposta.cartao)
        }


    }
}
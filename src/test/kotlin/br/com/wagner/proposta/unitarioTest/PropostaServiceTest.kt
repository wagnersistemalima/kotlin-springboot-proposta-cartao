package br.com.wagner.proposta.unitarioTest

import br.com.wagner.proposta.feingClient.apiCartoes.ApiCartaoClient
import br.com.wagner.proposta.feingClient.apiCartoes.EnviaDadosClienteRequest
import br.com.wagner.proposta.feingClient.apiDadosFinanceiros.ApiDadosFinanceiroClient
import br.com.wagner.proposta.feingClient.apiDadosFinanceiros.DadosClientRequest
import br.com.wagner.proposta.handller.exceptions.ExceptionGenericValidated
import br.com.wagner.proposta.novaProposta.cartao.repository.CartaoRepository
import br.com.wagner.proposta.novaProposta.repository.PropostaRepository
import br.com.wagner.proposta.novaProposta.request.EnderecoRequest
import br.com.wagner.proposta.novaProposta.request.PropostaRequest
import br.com.wagner.proposta.novaProposta.service.PropostaService
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.springframework.test.context.junit.jupiter.SpringExtension
import java.math.BigDecimal

@ExtendWith(SpringExtension::class)
class PropostaServiceTest {

    @field:InjectMocks
    lateinit var propostaService: PropostaService

    @field:Mock
    lateinit var propostaRepository: PropostaRepository

    @field:Mock
    lateinit var apiDadosFinanceiroClient: ApiDadosFinanceiroClient

    @field:Mock
    lateinit var apiCartaoClient: ApiCartaoClient


    // 1 cenario de teste/ caminho feliz

    @Test
    fun `deve salvar uma proposta, retornar um objeto de resposta`() {

        // cenario

        val request = PropostaRequest(
            nome = "Marina",
            documento = "04394450438",
            email = "marina@gmail.com",
            endereco = EnderecoRequest(
                logradouro = "Rua das flores",
                bairro = "Catole",
                complemento = "perto da padaria",
                uf = "PB",
                cep = "58410505"
            ),
            salario = BigDecimal("1500.0")
        )

        val proposta = request.toModel()

        val dadosClientRequest = DadosClientRequest(proposta)

        val response = apiDadosFinanceiroClient.consulta(dadosClientRequest)

        val enviaDadosClienteRequest = EnviaDadosClienteRequest(proposta)

        val responseApiCartao = apiCartaoClient.solicitaCartao(enviaDadosClienteRequest)

        // ação


        // comportamento = deve retornar falso
        Mockito.`when`(propostaRepository.existsByDocumento(request.documento)).thenReturn(false)

        // comportamento = deve retornar proposta
        Mockito.`when`(propostaRepository.save(proposta)).thenReturn(proposta)

        // comportamento deve retornar false
        Mockito.`when`(propostaRepository.existsByEmail(request.email)).thenReturn(false)

        Assertions.assertEquals(response, apiDadosFinanceiroClient.consulta(dadosClientRequest))

        // nao deve lançar exceção
        Assertions.assertDoesNotThrow { propostaRepository.save(proposta) }
        Assertions.assertDoesNotThrow { propostaRepository.existsByDocumento(request.documento) }
        Assertions.assertDoesNotThrow { propostaRepository.existsByEmail(request.email) }

        // verifica se o metodo foi chamado
        Mockito.verify(propostaRepository, Mockito.times(1)).save(proposta)

    }

    // 2 cenario de teste

    @Test
    fun `deve lançar exception, quando documento ja existir cadastro`() {

        // cenario

        val request = PropostaRequest(
            nome = "Marina",
            documento = "04394450438",
            email = "marina@gmail.com",
            endereco = EnderecoRequest(
                logradouro = "Rua das flores",
                bairro = "Catole",
                complemento = "perto da padaria",
                uf = "PB",
                cep = "58410505"
            ),
            salario = BigDecimal("1500.0")
        )

        val proposta = request.toModel()

        // ação

        // comportamento = deve retornar true
        Mockito.`when`(propostaRepository.existsByDocumento(request.documento)).thenReturn(true)

        // assertiva

        // nao deve lançar exceção
       Assertions.assertThrows(ExceptionGenericValidated::class.java) {propostaService.insert(request)}

        // verifica se o metodo foi chamado
        Mockito.verify(propostaRepository, Mockito.times(0)).save(proposta)

    }

    // 3 cenario de teste

    @Test
    fun `deve lançar exception, quando email ja existir cadastro`() {

        // cenario

        val request = PropostaRequest(
            nome = "Marina",
            documento = "04394450438",
            email = "marina@gmail.com",
            endereco = EnderecoRequest(
                logradouro = "Rua das flores",
                bairro = "Catole",
                complemento = "perto da padaria",
                uf = "PB",
                cep = "58410505"
            ),
            salario = BigDecimal("1500.0")
        )

        val proposta = request.toModel()

        // ação

        // comportamento = deve retornar falso
        Mockito.`when`(propostaRepository.existsByDocumento(request.documento)).thenReturn(false)

        // comportamento = deve retornar true
        Mockito.`when`(propostaRepository.existsByEmail(request.email)).thenReturn(true)

        // assertiva

        // nao deve lançar exceção
        Assertions.assertThrows(ExceptionGenericValidated::class.java) {propostaService.insert(request)}

        // verifica se o metodo foi chamado
        Mockito.verify(propostaRepository, Mockito.times(0)).save(proposta)

    }


}
package br.com.wagner.proposta.unitarioTest

import br.com.wagner.proposta.handller.exceptions.ResourceNotFoundException
import br.com.wagner.proposta.novaProposta.model.Endereco
import br.com.wagner.proposta.novaProposta.model.Proposta
import br.com.wagner.proposta.novaProposta.repository.PropostaRepository
import br.com.wagner.proposta.novaProposta.response.BuscaInformacaoPropostaResponse
import br.com.wagner.proposta.novaProposta.service.BuscarInformacaoPropostaService
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
class BuscaInformacaoPropostaServiceTest {

    @field:InjectMocks
    lateinit var buscarInformacaoPropostaService: BuscarInformacaoPropostaService

    @field:Mock
    lateinit var propostaRepository: PropostaRepository

    // 1 cenario de teste

    @Test
    fun `deve realizar a busca de uma proposta por id, retornar objeto resposta`() {

        // cenario

        // ação

        val idValido = UUID.randomUUID().toString()

        val endereco = Endereco(
            logradouro = "Rua das flores",
            bairro = "Catole",
            complemento = "perto da padaria",
            uf = "PB",
            cep = "58410505"
        )

        val proposta = Proposta(
            nome = "Marina",
            documento = "61942755023",
            email = "marina@gmail.com",
            endereco = endereco,
            salario = BigDecimal("3000.0"),
            status = "ELEGIVEL",
            cartao = null
        )

        // comportamento:
        Mockito.`when`(propostaRepository.findById(idValido)).thenReturn(Optional.of(proposta))

        val response = BuscaInformacaoPropostaResponse(proposta)

        // assertivas

        Assertions.assertDoesNotThrow { buscarInformacaoPropostaService.findById(idValido) }
        Assertions.assertEquals(response, buscarInformacaoPropostaService.findById(idValido))
    }

    // 1 cenario de teste

    @Test
    fun `deve lançar exception ResourceNotFoundException quando nao encontrar id proposta`() {

        // cenario

        val idInvalido = UUID.randomUUID().toString()

        val endereco = Endereco(
            logradouro = "Rua das flores",
            bairro = "Catole",
            complemento = "perto da padaria",
            uf = "PB",
            cep = "58410505"
        )

        val proposta = Proposta(
            nome = "Marina",
            documento = "61942755023",
            email = "marina@gmail.com",
            endereco = endereco,
            salario = BigDecimal("3000.0"),
            status = "ELEGIVEL",
            cartao = null
        )

        // comportamento:
        Mockito.`when`(propostaRepository.findById(idInvalido)).thenReturn(Optional.empty())

        // assertivas

        Assertions.assertThrows(ResourceNotFoundException::class.java) {buscarInformacaoPropostaService.findById(idInvalido)}

    }
}
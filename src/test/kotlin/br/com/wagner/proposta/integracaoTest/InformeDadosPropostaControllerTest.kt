package br.com.wagner.proposta.integracaoTest

import br.com.wagner.proposta.novaProposta.model.Endereco
import br.com.wagner.proposta.novaProposta.model.Proposta
import br.com.wagner.proposta.novaProposta.repository.PropostaRepository
import br.com.wagner.proposta.novaProposta.response.BuscaInformacaoPropostaResponse
import br.com.wagner.proposta.novaProposta.service.BuscarInformacaoPropostaService
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.web.util.UriComponentsBuilder
import java.math.BigDecimal
import java.util.*

@SpringBootTest
@AutoConfigureDataMongo
@AutoConfigureMockMvc
@ActiveProfiles("test")
class InformeDadosPropostaControllerTest {

    @field:Autowired
    lateinit var propostaRepository: PropostaRepository

    @field:Autowired
    lateinit var mockMvc:MockMvc

    @field:Autowired
    lateinit var objectMapper: ObjectMapper

    @field:Autowired
    lateinit var buscarInformacaoPropostaService: BuscarInformacaoPropostaService

    // rodar antes de cada teste
    @BeforeEach
    internal fun setUp() {
        propostaRepository.deleteAll()
    }

    // rodar depois de cada teste
    @AfterEach
    internal fun tearDown() {
        propostaRepository.deleteAll()
    }

    // 1 cenario de testes

    @Test
    fun `deve retornar 200, com status da proposta`() {

        // cenario

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
        propostaRepository.save(proposta)

        val idValido = proposta.id

        val uri = UriComponentsBuilder.fromUriString("/propostas/{id}").buildAndExpand(idValido).toUri()

        // ação

        val response = buscarInformacaoPropostaService.findById(idValido)

        mockMvc.perform(MockMvcRequestBuilders.get(uri)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().`is`(200))
            .andExpect(MockMvcResultMatchers.content().json(toJson(response)))

        // assertiva
    }

    // 2 cenario de teste

    @Test
    fun `deve retornar 404, quando id da proposta nao encontrado`() {

        // cenario

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
        propostaRepository.save(proposta)

        val idInvalido = UUID.randomUUID().toString()

        val uri = UriComponentsBuilder.fromUriString("/propostas/{id}").buildAndExpand(idInvalido).toUri()

        // ação

        mockMvc.perform(MockMvcRequestBuilders.get(uri)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().`is`(404))

        // assertiva
    }

    // metodo para desserializar objeto de resposta

    fun toJson(response: BuscaInformacaoPropostaResponse): String {
        return objectMapper.writeValueAsString(response)
    }
}
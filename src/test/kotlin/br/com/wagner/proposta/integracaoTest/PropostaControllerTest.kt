package br.com.wagner.proposta.integracaoTest

import br.com.wagner.proposta.novaProposta.model.Endereco
import br.com.wagner.proposta.novaProposta.model.Proposta
import br.com.wagner.proposta.novaProposta.repository.PropostaRepository
import br.com.wagner.proposta.novaProposta.request.EnderecoRequest
import br.com.wagner.proposta.novaProposta.request.PropostaRequest
import br.com.wagner.proposta.novaProposta.service.PropostaService
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.transaction.annotation.Transactional
import java.math.BigDecimal
import java.net.URI

@SpringBootTest
@AutoConfigureDataMongo
@AutoConfigureMockMvc
@ActiveProfiles("test")
class PropostaControllerTest {

    @field:Autowired
    lateinit var propostaService: PropostaService

    @field:Autowired
    lateinit var propostaRepository: PropostaRepository

    @field:Autowired
    lateinit var mockMvc: MockMvc

    @field:Autowired
    lateinit var objectMapper: ObjectMapper

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

    // 1 cenario de test caminho feliz

    @Test
    fun `deve retornar 201 created`() {

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

        val uri = URI("/propostas")

        // ação

        mockMvc.perform(MockMvcRequestBuilders.post(uri)
            .contentType(MediaType.APPLICATION_JSON).content(toJson(request)))
            .andExpect(MockMvcResultMatchers.status().`is`(201))
        
    }

    // 2 cenario de test

    @Test
    fun `deve retornar 400, quando nome informado vazio`() {

        // cenario

        val request = PropostaRequest(
            nome = "",
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

        val uri = URI("/propostas")

        // ação

        mockMvc.perform(MockMvcRequestBuilders.post(uri)
            .contentType(MediaType.APPLICATION_JSON).content(toJson(request)))
            .andExpect(MockMvcResultMatchers.status().`is`(400))

        // assertivas
    }

    // 3 cenario de test

    @Test
    fun `deve retornar 400, quando documento informado invalido`() {

        // cenario

        val request = PropostaRequest(
            nome = "Marina",
            documento = "0439445043p",
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

        val uri = URI("/propostas")

        // ação

        mockMvc.perform(MockMvcRequestBuilders.post(uri)
            .contentType(MediaType.APPLICATION_JSON).content(toJson(request)))
            .andExpect(MockMvcResultMatchers.status().`is`(400))

        // assertivas
    }

    // 4 cenario de test

    @Test
    fun `deve retornar 400, quando email informado tem formato invalido`() {

        // cenario

        val request = PropostaRequest(
            nome = "Marina",
            documento = "04394450438",
            email = "marinagmail.com",
            endereco = EnderecoRequest(
                logradouro = "Rua das flores",
                bairro = "Catole",
                complemento = "perto da padaria",
                uf = "PB",
                cep = "58410505"
            ),
            salario = BigDecimal("1500.0")
        )

        val uri = URI("/propostas")

        // ação

        mockMvc.perform(MockMvcRequestBuilders.post(uri)
            .contentType(MediaType.APPLICATION_JSON).content(toJson(request)))
            .andExpect(MockMvcResultMatchers.status().`is`(400))

        // assertivas
    }

    // 5 cenario de test

    @Test
    fun `deve retornar 400, quando logradouro nao informado`() {

        // cenario

        val request = PropostaRequest(
            nome = "Marina",
            documento = "04394450438",
            email = "marina@gmail.com",
            endereco = EnderecoRequest(
                logradouro = "",
                bairro = "Catole",
                complemento = "perto da padaria",
                uf = "PB",
                cep = "58410505"
            ),
            salario = BigDecimal("1500.0")
        )

        val uri = URI("/propostas")

        // ação

        mockMvc.perform(MockMvcRequestBuilders.post(uri)
            .contentType(MediaType.APPLICATION_JSON).content(toJson(request)))
            .andExpect(MockMvcResultMatchers.status().`is`(400))

        // assertivas
    }

    // 6 cenario de test

    @Test
    fun `deve retornar 400, quando bairro nao informado`() {

        // cenario

        val request = PropostaRequest(
            nome = "Marina",
            documento = "04394450438",
            email = "marina@gmail.com",
            endereco = EnderecoRequest(
                logradouro = "rua das flores",
                bairro = "",
                complemento = "perto da padaria",
                uf = "PB",
                cep = "58410505"
            ),
            salario = BigDecimal("1500.0")
        )

        val uri = URI("/propostas")

        // ação

        mockMvc.perform(MockMvcRequestBuilders.post(uri)
            .contentType(MediaType.APPLICATION_JSON).content(toJson(request)))
            .andExpect(MockMvcResultMatchers.status().`is`(400))

        // assertivas
    }

    // 7 cenario de test

    @Test
    fun `deve retornar 400, quando complemento nao informado`() {

        // cenario

        val request = PropostaRequest(
            nome = "Marina",
            documento = "04394450438",
            email = "marina@gmail.com",
            endereco = EnderecoRequest(
                logradouro = "rua das flores",
                bairro = "Catole",
                complemento = "",
                uf = "PB",
                cep = "58410505"
            ),
            salario = BigDecimal("1500.0")
        )

        val uri = URI("/propostas")

        // ação

        mockMvc.perform(MockMvcRequestBuilders.post(uri)
            .contentType(MediaType.APPLICATION_JSON).content(toJson(request)))
            .andExpect(MockMvcResultMatchers.status().`is`(400))

        // assertivas
    }

    // 8 cenario de test

    @Test
    fun `deve retornar 400, quando uf nao informado`() {

        // cenario

        val request = PropostaRequest(
            nome = "Marina",
            documento = "04394450438",
            email = "marina@gmail.com",
            endereco = EnderecoRequest(
                logradouro = "rua das flores",
                bairro = "Catole",
                complemento = "perto da padaria",
                uf = "",
                cep = "58410505"
            ),
            salario = BigDecimal("1500.0")
        )

        val uri = URI("/propostas")

        // ação

        mockMvc.perform(MockMvcRequestBuilders.post(uri)
            .contentType(MediaType.APPLICATION_JSON).content(toJson(request)))
            .andExpect(MockMvcResultMatchers.status().`is`(400))

        // assertivas
    }

    // 9 cenario de test

    @Test
    fun `deve retornar 400, quando uf informado possui mais de duas letras`() {

        // cenario

        val request = PropostaRequest(
            nome = "Marina",
            documento = "04394450438",
            email = "marina@gmail.com",
            endereco = EnderecoRequest(
                logradouro = "rua das flores",
                bairro = "Catole",
                complemento = "perto da padaria",
                uf = "PBS",
                cep = "58410505"
            ),
            salario = BigDecimal("1500.0")
        )

        val uri = URI("/propostas")

        // ação

        mockMvc.perform(MockMvcRequestBuilders.post(uri)
            .contentType(MediaType.APPLICATION_JSON).content(toJson(request)))
            .andExpect(MockMvcResultMatchers.status().`is`(400))

        // assertivas
    }

    // 10 cenario de test

    @Test
    fun `deve retornar 400, quando cep nao informado`() {

        // cenario

        val request = PropostaRequest(
            nome = "Marina",
            documento = "04394450438",
            email = "marina@gmail.com",
            endereco = EnderecoRequest(
                logradouro = "rua das flores",
                bairro = "Catole",
                complemento = "perto da padaria",
                uf = "PB",
                cep = ""
            ),
            salario = BigDecimal("1500.0")
        )

        val uri = URI("/propostas")

        // ação

        mockMvc.perform(MockMvcRequestBuilders.post(uri)
            .contentType(MediaType.APPLICATION_JSON).content(toJson(request)))
            .andExpect(MockMvcResultMatchers.status().`is`(400))

        // assertivas
    }

    // 11 cenario de test

    @Test
    fun `deve retornar 400, quando salario zerado `() {

        // cenario

        val request = PropostaRequest(
            nome = "Marina",
            documento = "04394450438",
            email = "marina@gmail.com",
            endereco = EnderecoRequest(
                logradouro = "rua das flores",
                bairro = "Catole",
                complemento = "perto da padaria",
                uf = "PB",
                cep = "58410505"
            ),
            salario = BigDecimal("0.0")
        )

        val uri = URI("/propostas")

        // ação

        mockMvc.perform(MockMvcRequestBuilders.post(uri)
            .contentType(MediaType.APPLICATION_JSON).content(toJson(request)))
            .andExpect(MockMvcResultMatchers.status().`is`(400))

        // assertivas
    }

    // 12 cenario de test

    @Test
    fun `deve retornar 422, quando documento ja cadastrado para proposta `() {

        // cenario

        val endereco = Endereco(
            logradouro = "rua das acacias",
            bairro = "bodocongo",
            complemento = "perto da budega",
            uf = "PB",
            cep = "58410505"
        )

        val proposta = Proposta(
            nome = "Wagner",
            documento = "04394450438",
            email = "wagner@gmail.com",
            endereco = endereco,
            salario = BigDecimal("2000.0")
        )
        propostaRepository.save(proposta)

        val request = PropostaRequest(
            nome = "Marina",
            documento = "04394450438",
            email = "marina@gmail.com",
            endereco = EnderecoRequest(
                logradouro = "rua das flores",
                bairro = "Catole",
                complemento = "perto da padaria",
                uf = "PB",
                cep = "58410505"
            ),
            salario = BigDecimal("2000.0")
        )

        val uri = URI("/propostas")

        // ação

        mockMvc.perform(MockMvcRequestBuilders.post(uri)
            .contentType(MediaType.APPLICATION_JSON).content(toJson(request)))
            .andExpect(MockMvcResultMatchers.status().`is`(422))

        // assertivas
    }

    // 12 cenario de test

    @Test
    fun `deve retornar 422, quando email ja cadastrado para proposta `() {

        // cenario

        val endereco = Endereco(
            logradouro = "rua das acacias",
            bairro = "bodocongo",
            complemento = "perto da budega",
            uf = "PB",
            cep = "58410505"
        )

        val proposta = Proposta(
            nome = "Wagner",
            documento = "04394450438",
            email = "wagner@gmail.com",
            endereco = endereco,
            salario = BigDecimal("2000.0")
        )
        propostaRepository.save(proposta)

        val request = PropostaRequest(
            nome = "Marina",
            documento = "51352253070",
            email = "wagner@gmail.com",
            endereco = EnderecoRequest(
                logradouro = "rua das flores",
                bairro = "Catole",
                complemento = "perto da padaria",
                uf = "PB",
                cep = "58410505"
            ),
            salario = BigDecimal("2000.0")
        )

        val uri = URI("/propostas")

        // ação

        mockMvc.perform(MockMvcRequestBuilders.post(uri)
            .contentType(MediaType.APPLICATION_JSON).content(toJson(request)))
            .andExpect(MockMvcResultMatchers.status().`is`(422))

        // assertivas
    }


    // metodo para desserializar objeto request

    fun toJson(request: PropostaRequest): String {
        return objectMapper.writeValueAsString(request)
    }
}
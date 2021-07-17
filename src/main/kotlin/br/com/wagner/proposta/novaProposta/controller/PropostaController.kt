package br.com.wagner.proposta.novaProposta.controller

import br.com.wagner.proposta.novaProposta.request.PropostaRequest
import br.com.wagner.proposta.novaProposta.service.PropostaService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import org.springframework.web.util.UriComponentsBuilder
import javax.validation.Valid

@Validated
@RestController
@RequestMapping("/propostas")
class PropostaController(@field:Autowired val propostaService: PropostaService) {

    val logger = LoggerFactory.getLogger(PropostaController::class.java)

    // end point para inserir uma nova proposta de cartão

    @PostMapping
    fun insert(@RequestBody @Valid request: PropostaRequest): ResponseEntity<Any> {
        logger.info("-------------Iniciando o cadastro de uma proposta--------------")

        // cadastrando proposta

        val response = propostaService.insert(request)

        // solicitando cartao


        val uri = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}").buildAndExpand(response.idProposta).toUri()

        return ResponseEntity.created(uri).build()
    }
}
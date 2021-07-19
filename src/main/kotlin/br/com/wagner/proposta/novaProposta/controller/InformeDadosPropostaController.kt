package br.com.wagner.proposta.novaProposta.controller

import br.com.wagner.proposta.novaProposta.service.BuscarInformacaoPropostaService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/propostas")
class InformeDadosPropostaController(@field:Autowired val buscarInformacaoPropostaService: BuscarInformacaoPropostaService) {

    val logger = LoggerFactory.getLogger(InformeDadosPropostaController::class.java)


    // end point para pegar informaçoes do status da proposta

    @GetMapping("/{id}")
    fun findById(@PathVariable("id") id: String): ResponseEntity<Any> {
        logger.info("---Iniciando busca do status da proposta pelo id $id ----")

        val response = buscarInformacaoPropostaService.findById(id)

        logger.info("-- Busca da proposta realizada com sucesso--- $id ")
        return ResponseEntity.ok().body(response)

    }
}
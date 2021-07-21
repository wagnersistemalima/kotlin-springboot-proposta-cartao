package br.com.wagner.proposta.novaProposta.controller

import br.com.wagner.proposta.novaProposta.biometria.request.CriacaoBiometriaRequest
import br.com.wagner.proposta.novaProposta.biometria.service.CriacaoBiometriaService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import javax.validation.Valid

@Validated
@RestController
@RequestMapping("biometrias")
class CriacaoBiometriaController(@field:Autowired val criacaoBiometriaService: CriacaoBiometriaService) {

    val logger = LoggerFactory.getLogger(CriacaoBiometriaController::class.java)

    // end point para criar biometria

    @PostMapping("/{idCartao}")
    fun insert(@PathVariable("idCartao") idCartao: String,@Valid @RequestBody request: CriacaoBiometriaRequest): ResponseEntity<Any> {
        logger.info("-----Iniciando cadastro de biometria------")

        val response = criacaoBiometriaService.insert(idCartao, request)

        val uri = ServletUriComponentsBuilder
            .fromCurrentRequest().path("/{id}").buildAndExpand(response.id).toUri()

        return ResponseEntity.created(uri).build()
    }
}
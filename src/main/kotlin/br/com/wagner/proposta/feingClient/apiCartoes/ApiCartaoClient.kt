package br.com.wagner.proposta.feingClient.apiCartoes

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody

@Component
@FeignClient(name = "api-cartao", url = "http://localhost:8888/", path = "/api/cartoes")
interface ApiCartaoClient {

    // end point para solicitar cartao

    @PostMapping
    fun solicitaCartao(@RequestBody request: EnviaDadosClienteRequest): ResponseEntity<RecebeDadosClienteResponse>
}
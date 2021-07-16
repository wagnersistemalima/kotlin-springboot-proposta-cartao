package br.com.wagner.proposta.feingClient.apiDadosFinanceiros

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody

@Component
@FeignClient(name = "dados-financeiro", url = "http://localhost:9999", path = "/api/solicitacao")
interface ApiDadosFinanceiroClient {

    // end point

    @PostMapping
    fun consulta(@RequestBody request: DadosClientRequest): ResponseEntity<DadosClienteResponse>
}
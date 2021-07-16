package br.com.wagner.proposta

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients

@EnableFeignClients
@SpringBootApplication
class PropostaApplication

fun main(args: Array<String>) {
	runApplication<PropostaApplication>(*args)
}

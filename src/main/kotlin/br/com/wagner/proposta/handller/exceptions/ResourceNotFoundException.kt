package br.com.wagner.proposta.handller.exceptions

import java.lang.RuntimeException

class ResourceNotFoundException(val msg: String): RuntimeException(msg) {
}
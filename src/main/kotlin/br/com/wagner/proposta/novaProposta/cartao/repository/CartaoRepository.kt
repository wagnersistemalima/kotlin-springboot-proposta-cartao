package br.com.wagner.proposta.novaProposta.cartao.repository

import br.com.wagner.proposta.novaProposta.cartao.model.Cartao
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface CartaoRepository: MongoRepository<Cartao, String> {
}
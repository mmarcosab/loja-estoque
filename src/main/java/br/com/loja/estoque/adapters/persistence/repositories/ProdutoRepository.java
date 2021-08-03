package br.com.loja.estoque.adapters.persistence.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.com.loja.estoque.adapters.persistence.models.ProdutoData;

@Repository
public interface ProdutoRepository extends MongoRepository<ProdutoData, String> {

}

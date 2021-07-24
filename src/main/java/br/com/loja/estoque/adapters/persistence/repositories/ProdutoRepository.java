package br.com.loja.estoque.adapters.persistence.repositories;

import br.com.loja.estoque.adapters.persistence.models.ProdutoData;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends MongoRepository<ProdutoData, String> {

}

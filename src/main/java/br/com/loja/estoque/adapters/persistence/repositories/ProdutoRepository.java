package br.com.loja.estoque.adapters.persistence.repositories;

import br.com.loja.estoque.adapters.persistence.models.ProdutoData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<ProdutoData, String> {

}

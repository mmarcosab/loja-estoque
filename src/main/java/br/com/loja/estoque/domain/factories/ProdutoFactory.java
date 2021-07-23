package br.com.loja.estoque.domain.factories;

import br.com.loja.estoque.adapters.controllers.request.ProdutoInputModel;
import br.com.loja.estoque.domain.entities.Produto;


public interface ProdutoFactory {
    Produto create(ProdutoInputModel produtoInputModel);
}

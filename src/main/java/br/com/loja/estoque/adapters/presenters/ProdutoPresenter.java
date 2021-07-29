package br.com.loja.estoque.adapters.presenters;

import br.com.loja.estoque.adapters.controllers.response.OutputModel;
import br.com.loja.estoque.adapters.persistence.models.ProdutoData;


public interface ProdutoPresenter {
    OutputModel createSuccessView(ProdutoData produto);
    OutputModel createFailView(String mensagem);
}

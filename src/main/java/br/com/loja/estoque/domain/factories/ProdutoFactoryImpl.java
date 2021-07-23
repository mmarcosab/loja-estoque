package br.com.loja.estoque.domain.factories;

import br.com.loja.estoque.adapters.controllers.request.ProdutoInputModel;
import br.com.loja.estoque.domain.entities.Produto;
import br.com.loja.estoque.domain.entities.ProdutoImpl;
import org.springframework.stereotype.Component;


@Component
public class ProdutoFactoryImpl implements ProdutoFactory {

    @Override
    public Produto create(ProdutoInputModel produtoInputModel) {
        return new ProdutoImpl(produtoInputModel.getNome(), produtoInputModel.getDescricao(), produtoInputModel.getPreco());
    }
}

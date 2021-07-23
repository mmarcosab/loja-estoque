package br.com.loja.estoque.domain.usecases.impl;

import br.com.loja.estoque.adapters.controllers.request.ProdutoInputModel;
import br.com.loja.estoque.adapters.controllers.response.ProdutoOutputModel;
import br.com.loja.estoque.domain.entities.Produto;
import br.com.loja.estoque.domain.factories.ProdutoFactory;
import br.com.loja.estoque.domain.usecases.ProdutoUseCase;
import org.springframework.stereotype.Component;
import java.time.LocalDate;


@Component
public class ProdutoUseCaseImpl implements ProdutoUseCase {

    private final ProdutoFactory produtoFactory;

    public ProdutoUseCaseImpl(ProdutoFactory produtoFactory) {
        this.produtoFactory = produtoFactory;
    }

    @Override
    public ProdutoOutputModel execute(ProdutoInputModel produtoInputModel) {
        Produto produto = produtoFactory.create(produtoInputModel);
        return getResponse(produto);
    }

    private ProdutoOutputModel getResponse(Produto produto) {
        return new ProdutoOutputModel(1, produto.getNome(),
                produto.getDescricao(), produto.getPreco(), LocalDate.now());
    }

}

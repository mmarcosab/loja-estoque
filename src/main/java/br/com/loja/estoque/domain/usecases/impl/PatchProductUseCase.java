package br.com.loja.estoque.domain.usecases.impl;

import br.com.loja.estoque.adapters.controllers.request.ProdutoInputModel;
import br.com.loja.estoque.adapters.controllers.response.OutputModel;
import br.com.loja.estoque.adapters.persistence.ProdutoDsGateway;
import br.com.loja.estoque.adapters.presenters.ProdutoPresenter;
import br.com.loja.estoque.domain.usecases.PatchUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PatchProductUseCase implements PatchUseCase<ProdutoInputModel> {

    private final ProdutoDsGateway produtoDsGateway;
    private final ProdutoPresenter produtoPresenter;

    @Override
    public OutputModel execute(ProdutoInputModel produto) {
        try {
            var produtoData = produtoDsGateway.patch(produto);
            return produtoPresenter.createSuccessView(produtoData);
        } catch(Exception e) {
            return produtoPresenter.createFailView(e.getMessage());
        }
    }
}

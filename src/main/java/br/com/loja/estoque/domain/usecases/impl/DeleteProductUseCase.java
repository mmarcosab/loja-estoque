package br.com.loja.estoque.domain.usecases.impl;

import br.com.loja.estoque.adapters.controllers.response.OutputModel;
import br.com.loja.estoque.adapters.persistence.ProdutoDsGateway;
import br.com.loja.estoque.adapters.presenters.ProdutoPresenter;
import br.com.loja.estoque.domain.usecases.DeleteUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteProductUseCase implements DeleteUseCase<String, OutputModel> {

    private final ProdutoDsGateway produtoDsGateway;
    private final ProdutoPresenter produtoPresenter;

    @Override
    public OutputModel execute(String codigo) {
        try {
            produtoDsGateway.delete(codigo);
            return null;
        } catch (Exception e) {
            return produtoPresenter.createFailView(e.getMessage());
        }
    }
}

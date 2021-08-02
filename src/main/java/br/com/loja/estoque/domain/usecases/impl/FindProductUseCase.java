package br.com.loja.estoque.domain.usecases.impl;

import br.com.loja.estoque.adapters.controllers.response.OutputModel;
import br.com.loja.estoque.adapters.persistence.ProdutoDsGateway;
import br.com.loja.estoque.adapters.presenters.ProdutoPresenter;
import br.com.loja.estoque.domain.usecases.FindUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FindProductUseCase implements FindUseCase<String, OutputModel> {

    private final ProdutoDsGateway produtoDsGateway;
    private final ProdutoPresenter produtoPresenter;

    @Override
    public OutputModel execute(String codigo) {
        try {
            var produtoData = produtoDsGateway.findById(codigo);
            return produtoPresenter.createSuccessView(produtoData);
        } catch (Exception e) {
            return produtoPresenter.createFailView(e.getMessage());
        }
    }

    @Override
    public List<OutputModel> execute() {
        try {
            var produtos = produtoDsGateway.findAll();
            return produtos.stream()
                    .map(produtoData -> produtoPresenter.createSuccessView(produtoData))
                    .collect(Collectors.toList());
        } catch(Exception e) {
            return List.of(produtoPresenter.createFailView(e.getMessage()));
        }
    }

}

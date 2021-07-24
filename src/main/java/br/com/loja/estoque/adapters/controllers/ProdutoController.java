package br.com.loja.estoque.adapters.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.loja.estoque.adapters.controllers.request.ProdutoInputModel;
import br.com.loja.estoque.adapters.controllers.response.ProdutoOutputModel;
import br.com.loja.estoque.domain.usecases.CreateUseCase;

@RestController
@RequestMapping("/produtos")
public class ProdutoController extends AbstractController<ProdutoInputModel> {

    public ProdutoController(CreateUseCase<ProdutoInputModel, ProdutoOutputModel> useCase) {
        super(useCase, ProdutoInputModel.class);
    }

}

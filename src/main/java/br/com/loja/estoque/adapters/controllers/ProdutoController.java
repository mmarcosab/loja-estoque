package br.com.loja.estoque.adapters.controllers;

import br.com.loja.estoque.adapters.controllers.request.ProdutoInputModel;
import br.com.loja.estoque.domain.usecases.UseCase;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/produtos")
public class ProdutoController extends AbstractController<ProdutoInputModel>{

    public ProdutoController(UseCase useCase) {
        super(useCase);
    }

}

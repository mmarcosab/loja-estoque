package br.com.loja.estoque.domain.usecases;


import br.com.loja.estoque.adapters.controllers.request.ProdutoInputModel;
import br.com.loja.estoque.adapters.controllers.response.ProdutoOutputModel;

public interface ProdutoUseCase extends UseCase<ProdutoInputModel, ProdutoOutputModel>{
    ProdutoOutputModel execute(ProdutoInputModel produtoInputModel);
}

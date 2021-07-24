package br.com.loja.estoque.domain.usecases;


import br.com.loja.estoque.adapters.controllers.response.OutputModel;

public interface FindUseCase<T extends Object, R extends OutputModel> {
    R execute(T codigo);
}

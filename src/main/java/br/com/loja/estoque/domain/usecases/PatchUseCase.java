package br.com.loja.estoque.domain.usecases;

import br.com.loja.estoque.adapters.controllers.request.InputModel;
import br.com.loja.estoque.adapters.controllers.response.OutputModel;

public interface PatchUseCase<T extends InputModel> {
    OutputModel execute(T entity);
}

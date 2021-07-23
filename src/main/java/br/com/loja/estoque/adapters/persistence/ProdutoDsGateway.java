package br.com.loja.estoque.adapters.persistence;

import br.com.loja.estoque.adapters.controllers.request.ProdutoInputModel;
import br.com.loja.estoque.adapters.persistence.models.ProdutoData;

import java.util.List;

public interface ProdutoDsGateway {
    boolean existsById(String name);
    ProdutoData save(ProdutoInputModel requestModel);
    List<ProdutoData> getAll();
    ProdutoData update(ProdutoInputModel requestModel, String id);
    void delete(String id);
}

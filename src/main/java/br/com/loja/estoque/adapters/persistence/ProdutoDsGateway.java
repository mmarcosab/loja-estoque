package br.com.loja.estoque.adapters.persistence;

import br.com.loja.estoque.adapters.controllers.request.ProdutoInputModel;
import br.com.loja.estoque.adapters.persistence.models.ProdutoData;

import java.util.List;

public interface ProdutoDsGateway {
    List<ProdutoData> findAll();
    boolean existsById(String codigo);
    ProdutoData findById(String codigo) throws Exception;
    ProdutoData save(ProdutoInputModel requestModel) throws Exception;
    ProdutoData update(ProdutoInputModel requestModel) throws Exception;
    ProdutoData patch(ProdutoInputModel requestModel) throws Exception;
    void delete(String id) throws Exception;
}

package br.com.loja.estoque.adapters.persistence;

import br.com.loja.estoque.adapters.controllers.request.ProdutoInputModel;
import br.com.loja.estoque.adapters.persistence.models.ProdutoData;
import br.com.loja.estoque.adapters.persistence.repositories.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ProdutoJpa implements ProdutoDsGateway{

    private final ProdutoRepository repository;

    @Override
    public boolean existsById(String id) {
        return repository.existsById(id);
    }

    @Override
    public ProdutoData save(ProdutoInputModel requestModel) {
        return null;
    }

    @Override
    public List<ProdutoData> getAll() {
        return repository.findAll();
    }

    @Override
    public ProdutoData update(ProdutoInputModel requestModel, String id) {
        return null;
    }

    @Override
    public void delete(String id) {
        repository.deleteById(id);
    }
}

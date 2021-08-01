package br.com.loja.estoque.adapters.persistence;

import br.com.loja.estoque.adapters.controllers.request.ProdutoInputModel;
import br.com.loja.estoque.adapters.persistence.models.ProdutoData;
import br.com.loja.estoque.adapters.persistence.repositories.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.List;;

@Component
@RequiredArgsConstructor
public class ProdutoJpa implements ProdutoDsGateway {

    private final ProdutoRepository repository;
    private static final String NOT_FOUND = "Entidade nao encontrada a partir do id: ";

    @Override
    public boolean existsById(String id) {
        return repository.existsById(id);
    }

    @Override
    public ProdutoData findById(String codigo) throws Exception {
        return repository
                .findById(codigo)
                .orElseThrow(() -> new Exception(NOT_FOUND + codigo));
    }

    @Override
    public List<ProdutoData> findAll() {
        return repository.findAll();
    }

    @Override
    public ProdutoData save(ProdutoInputModel requestModel) throws Exception {
        if(requestModel.getCodigo() != null)
            throw new Exception("Entidade ja possui id: " + requestModel.getCodigo());
        return repository.save(requestToData(requestModel));
    }

    @Override
    public ProdutoData update(ProdutoInputModel requestModel) {
        return repository.save(requestToData(requestModel));
    }

    @Override
    public ProdutoData patch(ProdutoInputModel requestModel) throws Exception {
        findById(requestModel.getCodigo());
        return repository.save(requestToData(requestModel));
    }

    @Override
    public void delete(String id) throws Exception {
        if(repository.existsById(id)) repository.deleteById(id);
        else throw new Exception(NOT_FOUND + id);
    }

    private ProdutoData requestToData(ProdutoInputModel requestModel) {
        return ProdutoData.builder()
                .codigo(requestModel.getCodigo())
                .descricao(requestModel.getDescricao())
                .nome(requestModel.getNome())
                .preco(requestModel.getPreco())
                .build();
    }

}

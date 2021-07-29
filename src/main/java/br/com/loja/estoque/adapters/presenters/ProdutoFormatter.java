package br.com.loja.estoque.adapters.presenters;

import br.com.loja.estoque.adapters.controllers.response.OutputModel;
import br.com.loja.estoque.adapters.controllers.response.ProdutoOutputModel;
import br.com.loja.estoque.adapters.persistence.models.ProdutoData;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;

@Component
public class ProdutoFormatter implements ProdutoPresenter {

    @Override
    public OutputModel createSuccessView(ProdutoData data) {
        return ProdutoOutputModel.builder()
                .descricao(data.getDescricao())
                .dataHoraAtualizacao(LocalDate.now())
                .nome(data.getNome())
                .preco(data.getPreco())
                .codigo(data.getCodigo())
                .build();
    }

    @Override
    public OutputModel createFailView(String message) {
        throw new ResponseStatusException(HttpStatus.CONFLICT, message);
    }
}

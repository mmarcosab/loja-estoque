package br.com.loja.estoque;

import br.com.loja.estoque.adapters.persistence.models.ProdutoData;
import br.com.loja.estoque.adapters.persistence.repositories.ProdutoRepository;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class ProdutoTesteFactory {

    @Autowired
    private ProdutoRepository produtoRepository;

    ProdutoData mockProduct() {
        return ProdutoData
                .builder()
                .nome("Product test")
                .descricao("This product is being created for a test purpose only!")
                .preco(BigDecimal.TEN)
                .build();
    }

    void deleteAll() {
        //produtoRepository.deleteAll();
    }

    ProdutoData savedProduct() {
        return  null;//produtoRepository.save(mockProduct());
    }
}

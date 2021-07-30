package br.com.loja.estoque.adapters.controllers.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Builder
@Getter
@AllArgsConstructor
public class ProdutoInputModel extends AbstractInputModel {

    private String nome;
    private String descricao;
    private BigDecimal preco;

}

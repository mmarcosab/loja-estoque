package br.com.loja.estoque.adapters.controllers.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class ProdutoInputModel implements InputModel{

    private String nome;
    private String descricao;
    private BigDecimal preco;

}

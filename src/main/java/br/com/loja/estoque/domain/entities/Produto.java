package br.com.loja.estoque.domain.entities;

import java.math.BigDecimal;

public interface Produto {
    String getNome();
    BigDecimal getPreco();
    String getDescricao();
}

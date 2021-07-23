package br.com.loja.estoque.adapters.controllers.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class ProdutoOutputModel implements OutputModel{

    private int codigo;
    private String nome;
    private String descricao;
    private BigDecimal preco;
    private LocalDate dataHoraAtualizacao;

}

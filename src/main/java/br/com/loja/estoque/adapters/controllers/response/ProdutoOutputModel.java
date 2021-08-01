package br.com.loja.estoque.adapters.controllers.response;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class ProdutoOutputModel extends AbstractOutputModel {


    private String nome;
    private String descricao;
    private BigDecimal preco;
    private LocalDate dataHoraAtualizacao;

    @Builder
    public ProdutoOutputModel(String codigo, String nome, String descricao, BigDecimal preco, LocalDate dataHoraAtualizacao) {
        super(codigo);
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.dataHoraAtualizacao = dataHoraAtualizacao;
    }
}

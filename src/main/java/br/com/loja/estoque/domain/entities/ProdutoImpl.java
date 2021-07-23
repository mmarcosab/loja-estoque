package br.com.loja.estoque.domain.entities;


import java.math.BigDecimal;

public class ProdutoImpl implements Produto{

    private String nome;
    private String descricao;
    private BigDecimal preco;

    public ProdutoImpl(String nome, String descricao, BigDecimal preco) {
        validar(preco);
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
    }

    @Override
    public String getNome() {
        return nome;
    }

    @Override
    public String getDescricao() {
        return descricao;
    }

    @Override
    public BigDecimal getPreco() {
        return preco;
    }

    private void validar(BigDecimal preco){
        if(preco.compareTo(new BigDecimal(0.01)) < 0){
            throw new IllegalArgumentException("Valor doproduto invalido,favor verificar");
        }
    }


}

package br.com.loja.estoque.adapters.persistence.models;

import java.math.BigDecimal;

import org.bson.codecs.pojo.annotations.BsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@Document("Produtos")
public class ProdutoData {

    @Id
    private String codigo;

    @BsonProperty("nome")
    private String nome;

    @BsonProperty("descricao")
    private String descricao;

    @BsonProperty("preco")
    private BigDecimal preco;

}

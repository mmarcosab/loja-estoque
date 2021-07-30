package br.com.loja.estoque.adapters.controllers;

import br.com.loja.estoque.adapters.controllers.request.ProdutoInputModel;
import br.com.loja.estoque.adapters.controllers.response.ProdutoOutputModel;
import br.com.loja.estoque.domain.usecases.impl.CreateProductUseCase;
import br.com.loja.estoque.domain.usecases.impl.DeleteProductUseCase;
import br.com.loja.estoque.domain.usecases.impl.FindProductUseCase;
import br.com.loja.estoque.domain.usecases.impl.UpdateProductUseCase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;


public class ProdutoControllerTest {

    private ProdutoController produtoController;
    private FindProductUseCase findUseCase;
    private DeleteProductUseCase deleteUseCase;
    private CreateProductUseCase createUseCase;
    private UpdateProductUseCase updateUseCase;

    @BeforeEach
    public void init(){
        findUseCase = mock(FindProductUseCase.class);
        deleteUseCase = mock(DeleteProductUseCase.class);
        createUseCase = mock(CreateProductUseCase.class);
        updateUseCase = mock(UpdateProductUseCase.class);
        produtoController = new ProdutoController(findUseCase, deleteUseCase, createUseCase, updateUseCase);
    }

    @Test
    public void createProdutoWithSuccess(){
        ProdutoInputModel inputModel = ProdutoInputModel.builder()
                .nome("teste")
                .descricao("teste teste")
                .preco(new BigDecimal(200))
                .build();

        ProdutoOutputModel produtoOutputModel = ProdutoOutputModel.builder()
                .codigo("1232")
                .dataHoraAtualizacao(LocalDate.now())
                .descricao("teste teste")
                .nome("teste")
                .preco(new BigDecimal(200))
                .build();
        when(createUseCase.execute(any())).thenReturn(produtoOutputModel);

        var response = produtoController.save(inputModel);

        assertEquals(201, response.getStatusCodeValue());
        assertEquals(HttpStatus.CREATED, response.getStatusCode());

    }

    @Test
    public void createProdutoWithResponseStatusException(){
        ProdutoInputModel inputModel = ProdutoInputModel.builder()
                .nome("teste")
                .descricao("teste teste")
                .preco(new BigDecimal(200))
                .build();

        ProdutoOutputModel produtoOutputModel = ProdutoOutputModel.builder()
                .codigo("1232")
                .dataHoraAtualizacao(LocalDate.now())
                .descricao("teste teste")
                .nome("teste")
                .preco(new BigDecimal(200))
                .build();
        when(createUseCase.execute(any())).thenThrow(new ResponseStatusException(HttpStatus.CONFLICT, "Houve um erro"));

        var response = produtoController.save(inputModel);

        assertEquals(409, response.getStatusCodeValue());
        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());

    }

    @Test
    public void createProdutoWithGenericException(){
        ProdutoInputModel inputModel = ProdutoInputModel.builder()
                .nome("teste")
                .descricao("teste teste")
                .preco(new BigDecimal(200))
                .build();

        ProdutoOutputModel produtoOutputModel = ProdutoOutputModel.builder()
                .codigo("1232")
                .dataHoraAtualizacao(LocalDate.now())
                .descricao("teste teste")
                .nome("teste")
                .preco(new BigDecimal(200))
                .build();

        var response = produtoController.save(inputModel);

        assertEquals(500, response.getStatusCodeValue());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());

    }

}
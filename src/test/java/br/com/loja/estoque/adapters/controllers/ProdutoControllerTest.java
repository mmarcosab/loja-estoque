package br.com.loja.estoque.adapters.controllers;

import br.com.loja.estoque.adapters.controllers.request.ProdutoInputModel;
import br.com.loja.estoque.adapters.controllers.response.ProdutoOutputModel;
import br.com.loja.estoque.domain.usecases.impl.CreateProductUseCase;
import br.com.loja.estoque.domain.usecases.impl.DeleteProductUseCase;
import br.com.loja.estoque.domain.usecases.impl.FindProductUseCase;
import br.com.loja.estoque.domain.usecases.impl.UpdateProductUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

    @Test
    public void findOneWithSuccess(){

        ProdutoOutputModel produtoOutputModel = ProdutoOutputModel.builder()
                .codigo("1232")
                .dataHoraAtualizacao(LocalDate.now())
                .descricao("teste teste")
                .nome("teste")
                .preco(new BigDecimal(200))
                .build();
        when(findUseCase.execute(any())).thenReturn(produtoOutputModel);

        var response = produtoController.findOne("1232");

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("1232", produtoOutputModel.getCodigo());
        assertEquals("teste teste", produtoOutputModel.getDescricao());
        assertEquals("teste", produtoOutputModel.getNome());
        assertEquals(new BigDecimal(200), produtoOutputModel.getPreco());

    }

    @Test
    public void findOneWithResponseStatusException(){

        when(findUseCase.execute(any())).thenThrow(new ResponseStatusException(HttpStatus.CONFLICT, "Houve um erro"));

        var response = produtoController.findOne("1232");

        assertEquals(409, response.getStatusCodeValue());
        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());

    }

    @Test
    public void findOneWithGenericException(){

        when(findUseCase.execute(any())).thenThrow(new RuntimeException("Houve um erro"));

        var response = produtoController.findOne("1232");

        assertEquals(500, response.getStatusCodeValue());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());

    }


    @Test
    public void findAllWithSuccess(){

        ProdutoOutputModel produtoOutputModel = ProdutoOutputModel.builder()
                .codigo("1232")
                .dataHoraAtualizacao(LocalDate.now())
                .descricao("teste teste")
                .nome("teste")
                .preco(new BigDecimal(200))
                .build();

        List<ProdutoOutputModel> produtos = new ArrayList<>();
        produtos.add(produtoOutputModel);

        var response = produtoController.findAll();

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(HttpStatus.OK, response.getStatusCode());

    }

    @Test
    public void findAllWithResponseStatusException(){

        when(findUseCase.execute()).thenThrow(new ResponseStatusException(HttpStatus.CONFLICT, "Houve um erro"));

        var response = produtoController.findAll();

        assertEquals(409, response.getStatusCodeValue());
        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());

    }

    @Test
    public void findAllWithGenericException(){

        when(findUseCase.execute()).thenThrow(new RuntimeException("Houve um erro"));

        var response = produtoController.findAll();

        assertEquals(500, response.getStatusCodeValue());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());

    }

    @Test
    public void updateWithSuccess(){

        ProdutoInputModel inputModel = ProdutoInputModel.builder()
                .nome("teste")
                .descricao("teste teste")
                .preco(new BigDecimal(200))
                .build();
        inputModel.setCodigo("1232");

        ProdutoOutputModel produtoOutputModel = ProdutoOutputModel.builder()
                .codigo("1232")
                .dataHoraAtualizacao(LocalDate.now())
                .descricao("teste teste")
                .nome("teste")
                .preco(new BigDecimal(200))
                .build();

        when(updateUseCase.execute(any())).thenReturn(produtoOutputModel);

        var response = produtoController.update(inputModel);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(HttpStatus.OK, response.getStatusCode());

    }

    @Test
    public void updateWithResponseStatusException(){

        ProdutoInputModel inputModel = ProdutoInputModel.builder()
                .nome("teste")
                .descricao("teste teste")
                .preco(new BigDecimal(200))
                .build();
        inputModel.setCodigo("1232");

        when(updateUseCase.execute(any())).thenThrow(new ResponseStatusException(HttpStatus.CONFLICT, "Houve um erro"));

        var response = produtoController.update(inputModel);

        assertEquals(409, response.getStatusCodeValue());
        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());

    }

    @Test
    public void updateWithGenericException(){

        ProdutoInputModel inputModel = ProdutoInputModel.builder()
                .nome("teste")
                .descricao("teste teste")
                .preco(new BigDecimal(200))
                .build();
        inputModel.setCodigo("1232");

        when(updateUseCase.execute(any())).thenThrow(new RuntimeException("Houve um erro"));

        var response = produtoController.update(inputModel);

        assertEquals(500, response.getStatusCodeValue());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());

    }

    @Test
    public void deleteWithSuccess(){

        var response = produtoController.delete("1232");

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(HttpStatus.OK, response.getStatusCode());

    }

    @Test
    public void deleteWithResponseStatusException(){

        when(deleteUseCase.execute(any())).thenThrow(new ResponseStatusException(HttpStatus.CONFLICT, "Houve um erro"));

        var response = produtoController.delete("1232");

        assertEquals(409, response.getStatusCodeValue());
        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());

    }

    @Test
    public void deleteWithGenericException(){

        when(deleteUseCase.execute(any())).thenThrow(new RuntimeException("Houve um erro"));

        var response = produtoController.delete("1232");

        assertEquals(500, response.getStatusCodeValue());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());

    }


}
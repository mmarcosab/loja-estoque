package br.com.loja.estoque;

import br.com.loja.estoque.adapters.controllers.ProdutoController;
import br.com.loja.estoque.adapters.persistence.repositories.ProdutoRepository;
import br.com.loja.estoque.domain.usecases.impl.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.io.IOException;

import static br.com.loja.estoque.MongoRunner.isMongoRunning;
import static br.com.loja.estoque.MongoRunner.startMongo;
import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.hateoas.MediaTypes.HAL_JSON;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DisplayName("Tests for product controller")
@ExtendWith(SpringExtension.class)
@TestPropertySource("classpath:application-test.properties")
public class ProdutoControllerTests {

    @Autowired private ProdutoTesteFactory produtoTesteFactory;
    @Autowired private ProdutoRepository produtoRepository;
    @Autowired private ObjectMapper objectMapper;
    @Autowired private MockMvc mockMvc;

    private final String PRODUCTS = "/produtos";

    @BeforeEach
    public void beforeEach() throws IOException, InterruptedException {
//        if(!isMongoRunning()) startMongo();
        produtoRepository.deleteAll();
    }

    @Test
    @DisplayName("Should list all existent products.")
    public void shouldListAllProducts() throws Exception {

        final var request = MockMvcRequestBuilders
                .get(PRODUCTS)
                .contentType(HAL_JSON);

        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andDo(print());
    }

    @Test
    @DisplayName("Should find one product by its id.")
    public void shouldFindOneProductByItsId() throws Exception {

        final var savedEntity = produtoTesteFactory.savedProduct();

        assertNotNull(savedEntity);

        final var request = MockMvcRequestBuilders
                .get(PRODUCTS.concat("/").concat(savedEntity.getCodigo()))
                .contentType(HAL_JSON);

        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists())
                .andExpect(jsonPath("$.codigo").value(savedEntity.getCodigo()))
                .andDo(print());
    }


    @Test
    @DisplayName("Should create a product with all properties.")
    public void shouldCreateAProductWithPost() throws Exception {

        final var product = produtoTesteFactory.mockProduct();

        final var request = MockMvcRequestBuilders
                .post(PRODUCTS)
                .contentType(HAL_JSON)
                .content(objectMapper.writeValueAsString(product));

        mockMvc.perform(request)
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.codigo").exists())
                .andExpect(jsonPath("$.nome").value(product.getNome()))
                .andExpect(jsonPath("$.descricao").value(product.getDescricao()))
                .andExpect(jsonPath("$.preco").value(product.getPreco()))
                .andDo(print());
    }

    @Test
    @DisplayName("Should update a product using patch.")
    public void shouldUpdateAProductWithPatch() throws Exception {

        final var saved = produtoTesteFactory.savedProduct();

        assertNotNull(saved);

        final var request = MockMvcRequestBuilders
                .patch(PRODUCTS)
                .contentType(HAL_JSON)
                .content(objectMapper.writeValueAsString(saved));

        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(saved.getCodigo())))
                .andDo(print());
    }

    @Test
    @DisplayName("Should return a 4XX exception when patching some entity that doesn't exist.")
    public void shouldReturnNotFoundForPatch() throws Exception {

        final var product = produtoTesteFactory.mockProduct();
        product.setCodigo("");

        final var request = MockMvcRequestBuilders
                .patch(PRODUCTS)
                .contentType(HAL_JSON)
                .content(objectMapper.writeValueAsString(product));

        mockMvc.perform(request)
                .andExpect(status().is4xxClientError())
                .andDo(print());
    }

    @Test
    @DisplayName("Should delete a product by its id.")
    public void shouldDeleteProductById() throws Exception {

        final var created = produtoTesteFactory.savedProduct();

        assertNotNull(created);

        final var request = MockMvcRequestBuilders
                .delete(PRODUCTS.concat("/").concat(created.getCodigo()))
                .contentType(HAL_JSON);

        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andDo(print());
    }
}



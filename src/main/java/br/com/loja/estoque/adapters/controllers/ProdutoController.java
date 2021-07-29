package br.com.loja.estoque.adapters.controllers;

import br.com.loja.estoque.adapters.controllers.request.ProdutoInputModel;
import br.com.loja.estoque.adapters.controllers.response.OutputModel;
import br.com.loja.estoque.domain.usecases.impl.CreateProductUseCase;
import br.com.loja.estoque.domain.usecases.impl.DeleteProductUseCase;
import br.com.loja.estoque.domain.usecases.impl.FindProductUseCase;
import br.com.loja.estoque.domain.usecases.impl.UpdateProductUseCase;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.RepresentationModel.of;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Api
@RestController
@RequestMapping("/produtos")
@RequiredArgsConstructor
public class ProdutoController {

    private final FindProductUseCase findUseCase;
    private final DeleteProductUseCase deleteUseCase;
    private final CreateProductUseCase createUseCase;
    private final UpdateProductUseCase updateUseCase;

    @GetMapping("/{codigo}")
    public ResponseEntity<?> findOne(@PathVariable String codigo) {
        try {
            var produto = findUseCase.execute(codigo);
            return ResponseEntity
                    .ok(build(produto, methodOn(ProdutoController.class).findAll()));
        } catch (ResponseStatusException e) {
            return ResponseEntity
                    .status(e.getStatus())
                    .body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        try {
            List<OutputModel> produtos = findUseCase.execute();
            return ResponseEntity
                    .ok(produtos
                            .stream()
                            .map(produto -> build(produto, methodOn(ProdutoController.class)
                                    .findOne(produto.getCodigo())))
                            .collect(Collectors.toList()));
        } catch (ResponseStatusException e) {
            return ResponseEntity
                    .status(e.getStatus())
                    .body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody ProdutoInputModel produto) {
        try {
            var created = createUseCase.execute(produto);
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(build(created, methodOn(ProdutoController.class).findOne(created.getCodigo())));
        } catch (ResponseStatusException e) {
            return ResponseEntity
                    .status(e.getStatus())
                    .body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody ProdutoInputModel produto) {
        try {
            var updated = updateUseCase.execute(produto);
            return ResponseEntity
                    .ok(build(updated, methodOn(ProdutoController.class).findOne(updated.getCodigo())));
        } catch (ResponseStatusException e) {
            return ResponseEntity
                    .status(e.getStatus())
                    .body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
    }

    @DeleteMapping("/{codigo}")
    public ResponseEntity<?> delete(@RequestParam String codigo) {
        try {
            deleteUseCase.execute(codigo);
            return ResponseEntity
                    .ok(linkTo(methodOn(ProdutoController.class)
                            .findAll())
                            .withSelfRel());
        } catch (ResponseStatusException e) {
            return ResponseEntity
                    .status(e.getStatus())
                    .body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
    }

    private RepresentationModel<?> build(OutputModel product, ResponseEntity<?> method) {
        return of(product).add(linkTo(method).withSelfRel());
    }
}

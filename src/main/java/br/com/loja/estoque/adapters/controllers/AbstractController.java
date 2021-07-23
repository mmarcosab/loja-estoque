package br.com.loja.estoque.adapters.controllers;

import br.com.loja.estoque.adapters.controllers.request.InputModel;
import br.com.loja.estoque.domain.usecases.UseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public  class AbstractController<T extends InputModel> {

    private final UseCase useCase;

    public AbstractController(UseCase useCase) {
        this.useCase = useCase;
    }

    @PostMapping
    public ResponseEntity create(@RequestBody T request){
        try {
            var response = useCase.execute(request);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

}

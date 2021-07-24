package br.com.loja.estoque.adapters.controllers;

import static org.springframework.hateoas.RepresentationModel.of;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.loja.estoque.adapters.controllers.request.InputModel;
import br.com.loja.estoque.adapters.controllers.response.OutputModel;
import br.com.loja.estoque.domain.usecases.CreateUseCase;

public class AbstractController<T extends InputModel> {

	private final CreateUseCase<T, ? extends OutputModel> useCase;
	private final Class<T> entity;

	public AbstractController(CreateUseCase<T, ? extends OutputModel> useCase, Class<T> entity) {
		this.useCase = useCase;
		this.entity = entity;
	}

	@GetMapping("/{codigo}")
	private ResponseEntity<?> findOne(@PathVariable String codigo) {
		try {
			Class<T>[] clazz = null;
			var entity = this.entity.getDeclaredConstructor(clazz).newInstance();

			entity.setCodigo(codigo);
			var response = useCase.execute(entity);

			return ResponseEntity
					.ok(of(response).add(linkTo(methodOn(AbstractController.class).findAll()).withSelfRel()));
		} catch (Exception e) {
			return ResponseEntity.status(500).build();
		}
	}

	private ResponseEntity<?> findAll() {
		try {
			Class<T>[] clazz = null;
			var entity = this.entity.getDeclaredConstructor(clazz).newInstance();

			var response = useCase.execute(entity);

			return ResponseEntity.ok(of(response)
					.add(linkTo(methodOn(AbstractController.class).findOne(response.getCodigo())).withSelfRel()));
		} catch (Exception e) {
			return ResponseEntity.status(500).build();
		}
	}

	@PostMapping
	public ResponseEntity<?> create(@RequestBody T request) {
		try {
			var response = useCase.execute(request);
			return ResponseEntity.status(HttpStatus.CREATED)
					.body(of(linkTo(methodOn(AbstractController.class).findOne(response.getCodigo()))));
		} catch (Exception e) {
			return ResponseEntity.status(500).build();
		}
	}
}

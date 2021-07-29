package br.com.loja.estoque.adapters.controllers.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public abstract class AbstractOutputModel implements OutputModel {

	String codigo;	
}

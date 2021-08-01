package br.com.loja.estoque.adapters.controllers.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public abstract class AbstractOutputModel implements OutputModel {

	String codigo;	
}

package br.com.loja.estoque.domain.usecases;

public interface DeleteUseCase<T extends Object> {
    void execute(T codigo);
}

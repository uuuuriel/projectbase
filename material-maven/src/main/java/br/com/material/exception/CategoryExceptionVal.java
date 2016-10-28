package br.com.material.exception;

public class CategoryExceptionVal extends MainException {

	public CategoryExceptionVal() {
		this.generateObject("Categoria já existente. Tente novamente.",
				"Gentileza informar uma categoria que não esteja cadastro em sistema.");
	}

	private static final long serialVersionUID = 1L;

}

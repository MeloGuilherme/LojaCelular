package br.unitins.lojacelular.model;

import javax.validation.constraints.NotEmpty;

public class Produto {

	private Integer id;
	
	@NotEmpty(message = "O Nome do Produto não pode ser vazio!")
	private String nome;
	
	@NotEmpty(message = "O Preço do Produto não pode ser vazio!")
	private Float preco;
	
	@NotEmpty(message = "Favor dar uma descrição...")
	private String descricao;
	
	@NotEmpty(message = "SELECIONE A MARCA!")
	private MarcaProduto marcaProduto;
	
	@NotEmpty(message = "SELECIONE A COR!")
	private CorProduto corProduto;

	public Produto() {
		super();
	}

	public Produto(Integer id, String nome, Float preco, String descricao, MarcaProduto marcaProduto,
			CorProduto corProduto) {
		super();
		this.id = id;
		this.nome = nome;
		this.preco = preco;
		this.descricao = descricao;
		this.marcaProduto = marcaProduto;
		this.corProduto = corProduto;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Float getPreco() {
		return preco;
	}

	public void setPreco(Float preco) {
		this.preco = preco;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public MarcaProduto getMarcaProduto() {
		return marcaProduto;
	}

	public void setMarcaProduto(MarcaProduto marcaProduto) {
		this.marcaProduto = marcaProduto;
	}

	public CorProduto getCorProduto() {
		return corProduto;
	}

	public void setCorProduto(CorProduto corProduto) {
		this.corProduto = corProduto;
	}
}

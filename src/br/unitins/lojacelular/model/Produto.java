package br.unitins.lojacelular.model;

public class Produto {

	private Integer id;

	private String nome;
	private Double preco;
	private String descricao;
	private MarcaProduto marcaProduto;
	private CorProduto corProduto;

	public Produto() {
		super();
	}

	public Produto(Integer id, String nome, Double preco, String descricao, MarcaProduto marcaProduto,
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

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
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

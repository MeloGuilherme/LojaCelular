package br.unitins.lojacelular.model;

public class ItemPedido {

	private Integer id;

	private Double valor;
	private Integer quantidade;

//	public ItemPedido() {
//		super();
//	}
//
//	public ItemPedido(Integer id, Double valor, Integer quantidade) {
//		super();
//		this.id = id;
//		this.valor = valor;
//		this.quantidade = quantidade;
//	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

}

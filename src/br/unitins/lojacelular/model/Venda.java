package br.unitins.lojacelular.model;

import java.time.LocalDate;
import java.util.List;

public class Venda {

	private Integer id;
	private LocalDate data;
	private Usuario usuario;

//	private Double precoTotal;
//	private LocalDate horaFinalPedido;
//	private LocalDate dataFinalPedido;
//	private StatusPedido statusPedido;

	private List<ItemVenda> listaItemVenda;
	
	// campo calculado
	private Float totalVenda = 0.0f;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<ItemVenda> getListaItemVenda() {
		return listaItemVenda;
	}

	public void setListaItemVenda(List<ItemVenda> listaItemVenda) {
		this.listaItemVenda = listaItemVenda;
	}
	
	public Float getTotalVenda() {
		return totalVenda;
	}

	public void setTotalVenda(Float totalVenda) {
		this.totalVenda = totalVenda;
	}

}
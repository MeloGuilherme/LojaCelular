package br.unitins.lojacelular.model;

import java.time.LocalDate;

public class Pedido {
	
	private Integer id;
	
	private LocalDate dataPedido;
	private Double precoTotal;
	private LocalDate horaFinalPedido;
	private LocalDate dataFinalPedido;

	private ItemPedido itemPedido;
	private StatusPedido statusPedido;
	
	public Pedido() {
		super();
	}

	public Pedido(Integer id, LocalDate dataPedido, Double precoTotal, LocalDate horaFinalPedido, LocalDate dataFinalPedido,
			ItemPedido itemPedido, StatusPedido statusPedido) {
		super();
		this.setId(id);
		this.dataPedido = dataPedido;
		this.precoTotal = precoTotal;
		this.horaFinalPedido = horaFinalPedido;
		this.dataFinalPedido = dataFinalPedido;
		this.itemPedido = itemPedido;
		this.statusPedido = statusPedido;
	}

	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public LocalDate getDataPedido() {
		return dataPedido;
	}

	public void setDataPedido(LocalDate dataPedido) {
		this.dataPedido = dataPedido;
	}

	public Double getPrecoTotal() {
		return precoTotal;
	}

	public void setPrecoTotal(Double precoTotal) {
		this.precoTotal = precoTotal;
	}

	public LocalDate getHoraFinalPedido() {
		return horaFinalPedido;
	}

	public void setHoraFinalPedido(LocalDate horaFinalPedido) {
		this.horaFinalPedido = horaFinalPedido;
	}

	public LocalDate getDataFinalPedido() {
		return dataFinalPedido;
	}

	public void setDataFinalPedido(LocalDate dataFinalPedido) {
		this.dataFinalPedido = dataFinalPedido;
	}

	public ItemPedido getItemPedido() {
		return itemPedido;
	}

	public void setItemPedido(ItemPedido itemPedido) {
		this.itemPedido = itemPedido;
	}

	public StatusPedido getStatusPedido() {
		return statusPedido;
	}

	public void setStatusPedido(StatusPedido statusPedido) {
		this.statusPedido = statusPedido;
	}


}

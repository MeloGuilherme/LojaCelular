package br.unitins.lojacelular.model;

import javax.validation.constraints.NotEmpty;

public class Telefone {
	
	private Integer id;
	
	@NotEmpty(message = "O DDD não pode ser vazio")
	private String codigoArea;
	
	@NotEmpty(message = "Favor digitar numero do telefone!")
	private String numero;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCodigoArea() {
		return codigoArea;
	}

	public void setCodigoArea(String codigoArea) {
		this.codigoArea = codigoArea;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	@Override
	public String toString() {
		return "Telefone [id=" + id + ", codigoArea=" + codigoArea + ", numero=" + numero + "]";
	}
	
}

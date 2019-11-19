package br.unitins.lojacelular.model;

public class Telefone {
	
	private Integer id;
	
	private String codigoArea;
	private String numero;

//	public Telefone() {
//		super();
//	}
//
//	public Telefone(Integer id, String codigoArea, String numero) {
//		super();
//		this.id = id;
//		this.codigoArea = codigoArea;
//		this.numero = numero;
//	}

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

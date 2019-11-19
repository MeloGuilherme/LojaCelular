package br.unitins.lojacelular.model;

public class Endereco {
	
	private Integer id; 
	
	private String cidade;
	private String estado;
	private String cep;
	private String logradouro;
	
//	public Endereco() {
//		super();
//	}
//
//	public Endereco(Integer id, String cidade, String estado, String cep, String logradouro) {
//		super();
//		this.id = id;
//		this.cidade = cidade;
//		this.estado = estado;
//		this.cep = cep;
//		this.logradouro = logradouro;
//	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	@Override
	public String toString() {
		return "Endereco [id=" + id + ", cidade=" + cidade + ", estado=" + estado + ", cep=" + cep + ", logradouro="
				+ logradouro + "]";
	}
	
}

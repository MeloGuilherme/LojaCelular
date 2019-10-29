package br.unitins.lojacelular.model;

public enum CorProduto {
	
	BRANCO(1, "Branco"),
	PRETO(2, "Preto"),
	ROSA(3, "Rosa"),
	GRAFITE(4, "Grafite"),
	CINZA(5, "Cinza"),
	DOURADO(6, "Dourado");
	
	private int value;
	private String label;
	
	CorProduto(int value, String label) {
		this.value = value;
		this.label = label;
	}

	public int getValue() {
		return value;
	}

	public String getLabel() {
		return label;
	}
	
	public static CorProduto valueOf(int value) {
		
		for (CorProduto corProduto : CorProduto.values()) {
			
			if (corProduto.getValue() == value) 
				return corProduto;
		}
		
		return null;
	}
}
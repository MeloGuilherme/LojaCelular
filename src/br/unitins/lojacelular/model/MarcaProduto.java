package br.unitins.lojacelular.model;

public enum MarcaProduto {
	
	APPLE(1, "Apple"),
	SAMSUNG(2, "Samsung"),
	XIAOMI(3, "Xiaomi"),
	MOTOROLA(4, "Motorola"),
	LG(5, "LG"),
	ASUS(6, "Asus"),
	SONY(7, "Sony");

	private int value;
	private String label;
	
	MarcaProduto(int value, String label) {
		this.value = value;
		this.label = label;
	}

	public int getValue() {
		return value;
	}

	public String getLabel() {
		return label;
	}
	
	public static MarcaProduto valueOf(int value) {
		
		for (MarcaProduto marcaProduto : MarcaProduto.values()) {
			
			if (marcaProduto.getValue() == value) 
				return marcaProduto;
		}
		
		return null;
	}
}

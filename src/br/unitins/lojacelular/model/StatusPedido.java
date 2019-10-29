package br.unitins.lojacelular.model;

public enum StatusPedido {
	
	ABERTO(1, "Aberto"),
	FECHADO(2, "Fechado");

	private int value;
	private String label;
	
	StatusPedido(int value, String label) {
		this.value = value;
		this.label = label;
	}

	public int getValue() {
		return value;
	}

	public String getLabel() {
		return label;
	}
	
	public static StatusPedido valueOf(int value) {
		
		for (StatusPedido statusPedido : StatusPedido.values()) {
			
			if (statusPedido.getValue() == value) 
				return statusPedido;
		}
		
		return null;
	}
}
package com.desafio.entities.enums;

public enum Status {

	AGUARDANDO_ENTREGA(1, "aguardando_entrega"), ENTREGUE(2, "entregue"), PARCEIROS(3, "aguardando_retirada_parceiro");

	private int cod;
	private String descricao;

	private Status() {
	}

	private Status(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}

	public int getCod() {
		return cod;
	}

	public String getDescricao() {
		return descricao;
	}

	public static Status toEnum(Integer cod) {
		if (cod == null) {
			return null;
		}

		for (Status x : Status.values()) {
			if (cod.equals(x.getCod())) {
				return x;
			}
		}
		throw new IllegalArgumentException("Id inválido: " + cod);
	}
}

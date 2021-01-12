package com.desafio.entities.enums;

public enum CanalVendas {

	E_COMMERCE(1, "e_commerce"), LOJA_FISICA(2, "loja_fisica"), PARCEIROS(3, "parceiros");

	private int cod;
	private String descricao;

	private CanalVendas() {
	}

	private CanalVendas(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}

	public int getCod() {
		return cod;
	}

	public String getDescricao() {
		return descricao;
	}

	public static CanalVendas toEnum(Integer cod) {
		if (cod == null) {
			return null;
		}

		for (CanalVendas x : CanalVendas.values()) {
			if (cod.equals(x.getCod())) {
				return x;
			}
		}
		throw new IllegalArgumentException("Id inválido: " + cod);
	}
}

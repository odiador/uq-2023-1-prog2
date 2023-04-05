package co.edu.uniquindio.centroimpresion.model.centro;

import java.io.Serializable;

public enum EstadoImpresora implements Serializable {
	ACTIVO("Activo"), INACTIVO("Inactivo"), DESCONECTADO("Desconectado"), MANTENIMIENTO("En Mantenimiento");
	private String texto;

	private EstadoImpresora(String texto) {
		this.texto = texto;
	}

	public String getTexto() {
		return texto;
	}

}

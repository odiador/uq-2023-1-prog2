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

	public static EstadoImpresora obtenerEstado(String texto) {
		EstadoImpresora[] estados = EstadoImpresora.values();
		for (EstadoImpresora estado : estados)
			if (estado.getTexto().equals(texto))
				return estado;

		return null;
	}

	public static String[] stringValues() {
		EstadoImpresora[] values = EstadoImpresora.values();
		String[] arr = new String[values.length];
		for (int i = 0; i < arr.length; i++)
			arr[i] = values[i].getTexto();
		return arr;
	}

}

package co.edu.uniquindio.centroimpresion.model;

import co.edu.uniquindio.centroimpresion.exceptions.ObjetoFaltanteException;

public enum EstadoImpresora {
	ACTIVO("Activo"), INACTIVO("Inactivo"), DESCONECTADO("Desconectado"), MANTENIMIENTO("En Mantenimiento");

	private String texto;

	/**
	 * Es el constructor del estado de la impresora
	 * 
	 * @param texto
	 */
	private EstadoImpresora(String texto) {
		this.texto = texto;
	}

	public String getTexto() {
		return texto;
	}

	/**
	 * Obtiene el estado de una impresora a partir de su texto
	 * 
	 * @param estadoString
	 * @return
	 * @throws ObjetoFaltanteException en caso de que no se encuentre
	 */
	public static EstadoImpresora obtenerEstadoThrows(String estadoString) throws ObjetoFaltanteException {
		EstadoImpresora estadoImpresora = obtenerEstado(estadoString);
		if (estadoImpresora == null)
			throw new ObjetoFaltanteException("El estado no fue encontrado", estadoString);
		return estadoImpresora;
	}

	/**
	 * 
	 * Obtiene el estado de una impresora a partir de su texto, si no se encuentra
	 * retorna un null
	 * 
	 * @param texto
	 * @return
	 */
	public static EstadoImpresora obtenerEstado(String texto) {
		EstadoImpresora[] estados = EstadoImpresora.values();
		for (EstadoImpresora estado : estados)
			if (estado.getTexto().equals(texto))
				return estado;

		return null;
	}

	/**
	 * 
	 * Obtiene los valores del texto de los estados de impresora
	 * 
	 * @return
	 */
	public static String[] stringValues() {
		EstadoImpresora[] values = EstadoImpresora.values();
		String[] arr = new String[values.length];
		for (int i = 0; i < arr.length; i++)
			arr[i] = values[i].getTexto();
		return arr;
	}

}

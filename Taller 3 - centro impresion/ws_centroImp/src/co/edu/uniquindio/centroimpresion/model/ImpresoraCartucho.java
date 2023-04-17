package co.edu.uniquindio.centroimpresion.model;

import java.time.LocalDateTime;

import co.edu.uniquindio.centroimpresion.exceptions.ImpresoraException;

public class ImpresoraCartucho extends Impresora {

	/**
	 *
	 */
	private static final long serialVersionUID = -616372429177701679L;
	private final double desgasteCartucho;
	private final double capacidadCartucho;
	private double nivelCartucho;

	/**
	 * Es el constructor de la impresora de cartucho
	 * 
	 * @param code
	 * @param marca
	 * @param estado
	 * @param esAColor
	 * @param paginasPorMinuto
	 * @param capacidadCartucho
	 * @param desgasteCartucho
	 */
	public ImpresoraCartucho(String code, String marca, EstadoImpresora estado, boolean esAColor,
			double paginasPorMinuto, double capacidadCartucho, double desgasteCartucho) {
		super(code, marca, estado, esAColor, paginasPorMinuto);
		this.capacidadCartucho = capacidadCartucho;
		this.desgasteCartucho = desgasteCartucho;
		recargar();
	}

	/**
	 * Es el constructor de la impresora de cartucho sin parametros
	 */
	public ImpresoraCartucho() {
		super();
		this.desgasteCartucho = -1;
		this.capacidadCartucho = -1;
	}

	/**
	 * Obtiene el nivel del cartucho actual
	 * 
	 * @return
	 */
	public double getNivelCartucho() {
		return nivelCartucho;
	}

	/**
	 * Cambia el nivel del cartucho actual
	 * 
	 * @param nivelCartucho
	 */
	public void setNivelCartucho(double nivelCartucho) {
		this.nivelCartucho = nivelCartucho;
	}

	/**
	 * Obtiene el desgaste del cartucho
	 * 
	 * @return
	 */
	public double getDesgasteCartucho() {
		return desgasteCartucho;
	}

	/**
	 * Obtiene la capacidad del cartucho
	 * 
	 * @return
	 */
	public double getCapacidadCartucho() {
		return capacidadCartucho;
	}

	/**
	 * Baja el nivel del cartucho, muestra un error en caso de que no haya
	 * suficiente capacidad en la impresora
	 * 
	 * @throws ImpresoraException
	 */
	public void bajarNivelCartucho() throws ImpresoraException {
		double resultado = capacidadCartucho - desgasteCartucho;
		if (resultado < 0)
			throw new ImpresoraException("No hay suficiente capacidad en la impresora");
		setNivelCartucho(resultado);
	}

	@Override
	public void imprimirDocumento(LocalDateTime dateTime, Documento documento) throws ImpresoraException {
		throwIfNotActive();
		bajarNivelCartucho();
		documento.setFechaImpresion(dateTime);
		getListaDocumentos().add(documento);
		documentosImpresos++;
	}

	@Override
	public String toString() {
		return "ImpresoraCartucho [desgasteCartucho=" + desgasteCartucho + ", capacidadCartucho=" + capacidadCartucho
				+ ", nivelCartucho=" + nivelCartucho + ", code=" + code + ", marca=" + marca + ", estado=" + estado
				+ ", listaDocumentos=" + listaDocumentos + ", letrasPorSegundo=" + letrasPorSegundo + ", esAColor="
				+ esAColor + ", documentosImpresos=" + documentosImpresos + "]";
	}

	@Override
	protected void recargar() {
		setNivelCartucho(getCapacidadCartucho());
	}

}

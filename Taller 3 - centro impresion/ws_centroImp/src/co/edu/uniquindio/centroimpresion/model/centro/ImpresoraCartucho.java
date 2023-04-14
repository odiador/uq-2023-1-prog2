package co.edu.uniquindio.centroimpresion.model.centro;

import java.time.LocalDateTime;

import co.edu.uniquindio.centroimpresion.exceptions.NoHayCapacidadException;

public class ImpresoraCartucho extends Impresora {

	/**
	 *
	 */
	private static final long serialVersionUID = -616372429177701679L;
	private final double desgasteCartucho;
	private final double capacidadCartucho;
	private double nivelCartucho;

	public ImpresoraCartucho(String code, String marca, EstadoImpresora estado, boolean esAColor,
			double paginasPorMinuto, double capacidadCartucho, double desgasteCartucho) {
		super(code, marca, estado, esAColor, paginasPorMinuto);
		this.capacidadCartucho = capacidadCartucho;
		this.desgasteCartucho = desgasteCartucho;
		this.nivelCartucho = capacidadCartucho;
	}

	public void recargarCartucho() {
		setNivelCartucho(getCapacidadCartucho());
	}

	public double getNivelCartucho() {
		return nivelCartucho;
	}

	public void setNivelCartucho(double nivelCartucho) {
		this.nivelCartucho = nivelCartucho;
	}

	public double getDesgasteCartucho() {
		return desgasteCartucho;
	}

	public double getCapacidadCartucho() {
		return capacidadCartucho;
	}

	public void bajarNivelCartucho() throws NoHayCapacidadException {
		double resultado = capacidadCartucho - desgasteCartucho;
		if (resultado < 0)
			throw new NoHayCapacidadException();
		setNivelCartucho(resultado);
	}

	@Override
	public boolean exists() {
		return super.exists() && capacidadCartucho >= 0 && desgasteCartucho > 0 && nivelCartucho >= 0;
	}

	@Override
	public double imprimirDocumento(LocalDateTime dateTime, Documento documento) throws NoHayCapacidadException {
		bajarNivelCartucho();
		documento.setFechaImpresion(dateTime);
		getListaDocumentos().add(documento);
		return obtenerMilisegundosImpresion();
	}

	@Override
	public String toString() {
		return "ImpresoraCartucho [desgasteCartucho=" + desgasteCartucho + ", capacidadCartucho=" + capacidadCartucho
				+ ", nivelCartucho=" + nivelCartucho + ", code=" + code + ", marca=" + marca + ", estado=" + estado
				+ ", listaDocumentos=" + listaDocumentos + ", paginasPorMinuto=" + paginasPorMinuto + ", esAColor="
				+ esAColor + ", paginasImpresas=" + paginasImpresas + "]";
	}

}

package co.edu.uniquindio.centroimpresion.model.centro;

import java.time.LocalDateTime;

import co.edu.uniquindio.centroimpresion.exceptions.NoHayCapacidadException;

public class ImpresoraLaser extends Impresora {

	/**
	 *
	 */
	private static final long serialVersionUID = 2336828568323072760L;
	private final int duracionToner;
	private int nivelToner;

	public ImpresoraLaser(String code, String marca, EstadoImpresora estado, boolean esAColor, double paginasPorMinuto,
			int duracionToner) {
		super(code, marca, estado, esAColor, paginasPorMinuto);
		this.duracionToner = duracionToner;
	}

	public int getDuracionToner() {
		return duracionToner;
	}

	public int getNivelToner() {
		return nivelToner;
	}

	public void setNivelToner(int nivelToner) {
		this.nivelToner = nivelToner;
	}

	public void bajarNivelToner() throws NoHayCapacidadException {
		if (nivelToner <= 0)
			throw new NoHayCapacidadException();
		setNivelToner(nivelToner - 1);
	}

	public void recargarToner() {
		setNivelToner(getDuracionToner());
	}

	@Override
	public void imprimirDocumento(LocalDateTime dateTime, Documento documento) throws NoHayCapacidadException {
		bajarNivelToner();
		documento.setFechaImpresion(dateTime);
		getListaDocumentos().add(documento);
	}

}

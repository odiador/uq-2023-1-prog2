package co.edu.uniquindio.centroimpresion.model;

import java.time.LocalDateTime;

import co.edu.uniquindio.centroimpresion.exceptions.ImpresoraException;

public class ImpresoraLaser extends Impresora {

	/**
	 *
	 */
	private static final long serialVersionUID = 2336828568323072760L;
	private final int duracionToner;
	private int nivelToner;

	public ImpresoraLaser(String code, String marca, EstadoImpresora estado, boolean esAColor, double letrasPorSegundo,
			int duracionToner) {
		super(code, marca, estado, esAColor, letrasPorSegundo);
		this.duracionToner = duracionToner;
		recargar();
	}

	/**
	 * Obtiene la duracion del toner
	 * 
	 * @return
	 */
	public int getDuracionToner() {
		return duracionToner;
	}

	/**
	 * Obtiene el nivel del toner actual
	 * 
	 * @return
	 */
	public int getNivelToner() {
		return nivelToner;
	}

	/**
	 * Cambia el nivel del toner actual
	 * 
	 * @param nivelToner
	 */
	public void setNivelToner(int nivelToner) {
		this.nivelToner = nivelToner;
	}

	/**
	 * Baja el nivel del toner, muestra un error en caso de que no haya suficiente
	 * capacidad en la impresora
	 * 
	 * @throws ImpresoraException
	 */
	public void bajarNivelToner() throws ImpresoraException {
		if (nivelToner <= 0)
			throw new ImpresoraException("No hay suficiente capacidad en la impresora");
		setNivelToner(nivelToner - 1);
	}

	@Override
	public void imprimirDocumento(LocalDateTime dateTime, Documento documento) throws ImpresoraException {
		throwIfNotActive();
		bajarNivelToner();
		documento.setFechaImpresion(dateTime);
		getListaDocumentos().add(documento);
		documentosImpresos++;
	}

	@Override
	public String toString() {
		return String.format(
				"ImpresoraLaser [code=%s, marca=%s, estado=%s, listaDocumentos=%s, letrasPorSegundo=%s, esAColor=%s, documentosImpresos=%s, duracionToner=%s, nivelToner=%s]",
				code, marca, estado, listaDocumentos, letrasPorSegundo, esAColor, documentosImpresos, duracionToner,
				nivelToner);
	}

	@Override
	protected void recargar() {
		setNivelToner(getDuracionToner());
	}

}

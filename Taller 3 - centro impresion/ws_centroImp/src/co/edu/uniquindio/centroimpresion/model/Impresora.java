package co.edu.uniquindio.centroimpresion.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import co.edu.uniquindio.centroimpresion.exceptions.ImpresoraException;

public abstract class Impresora implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 5292404516001955132L;
	protected final String code;
	protected String marca;
	protected EstadoImpresora estado;
	protected final List<Documento> listaDocumentos = new ArrayList<Documento>();
	protected double letrasPorSegundo;
	protected boolean esAColor;
	protected int documentosImpresos;

	/**
	 * Es el constructor de la clase impresora (no instanciable naturalmente)
	 * 
	 * @param code
	 * @param marca
	 * @param estado
	 * @param esAColor
	 * @param letrasPorSegundo
	 */
	public Impresora(String code, String marca, EstadoImpresora estado, boolean esAColor, double letrasPorSegundo) {
		this.code = code;
		this.marca = marca;
		this.estado = estado;
		this.esAColor = esAColor;
		this.letrasPorSegundo = letrasPorSegundo;
	}

	/**
	 * Es el constructor de la clase impresora sin parametros (no instanciable
	 * naturalmente)
	 */
	public Impresora() {
		code = "";
	}

	/**
	 * Suelta un error en caso de que la impresora no este activa
	 * 
	 * @throws ImpresoraException
	 */
	protected void throwIfNotActive() throws ImpresoraException {
		if (!estaActiva())
			throw new ImpresoraException("La impresora no esta activa (Estado: " + estado.getTexto() + ")");
	}

	/**
	 * Imprime un documento en una fecha especifica
	 * 
	 * @param dateTime
	 * @param documento
	 * @throws ImpresoraException en caso de que no se pueda realizar la accion
	 */
	public abstract void imprimirDocumento(LocalDateTime dateTime, Documento documento) throws ImpresoraException;

	/**
	 * Obtiene el codigo de la impresora
	 * 
	 * @return
	 */
	public String getCode() {
		return code;
	}

	/**
	 * Obtiene la marca de la impresora
	 * 
	 * @return
	 */
	public String getMarca() {
		return marca;
	}

	/**
	 * Cambia la marca de la impresora
	 * 
	 * @param marca
	 */
	public void setMarca(String marca) {
		this.marca = marca;
	}

	/**
	 * Obtiene el estado de la impresora
	 * 
	 * @return
	 */
	public EstadoImpresora getEstado() {
		return estado;
	}

	/**
	 * Cambia el estado de la impresora
	 * 
	 * @param estado
	 */
	public void setEstado(EstadoImpresora estado) {
		this.estado = estado;
	}

	/**
	 * Determina si la impresora esta activa o no
	 * 
	 * @return
	 */
	public boolean estaActiva() {
		return getEstado() == EstadoImpresora.ACTIVO;
	}

	/**
	 * Obtiene la lista de documentos de la impresora
	 * 
	 * @return
	 */
	public List<Documento> getListaDocumentos() {
		return listaDocumentos;
	}

	/**
	 * Obtiene la velocidad de la impresora (letras/seg)
	 * 
	 * @return
	 */
	public double getLetrasPorSegundo() {
		return letrasPorSegundo;
	}

	/**
	 * Cambia la velocidad de la impresora (letras/seg)
	 * 
	 * @param letrasPorSegundo
	 */
	public void setLetrasPorSegundo(double letrasPorSegundo) {
		this.letrasPorSegundo = letrasPorSegundo;
	}

	/**
	 * Obtiene si la impresora es a color o no
	 * 
	 * @return
	 */
	public boolean esAColor() {
		return esAColor;
	}

	/**
	 * Cambia si la impresora es a color o no
	 * 
	 * @param esAColor
	 */
	public void setEsAColor(boolean esAColor) {
		this.esAColor = esAColor;
	}

	/**
	 * Obtiene las paginas impresas de la impresora
	 * 
	 * @return
	 */
	public int getPaginasImpresas() {
		return documentosImpresos;
	}

	/**
	 * Cambia las paginas impresas de la impresora
	 * 
	 * @param paginasImpresas
	 */
	public void setPaginasImpresas(int paginasImpresas) {
		this.documentosImpresos = paginasImpresas;
	}

	/**
	 * Recarga la impresora
	 */
	protected abstract void recargar();

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Impresora other = (Impresora) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Impresora [code=" + code + ", marca=" + marca + ", estado=" + estado + ", listaDocumentos="
				+ listaDocumentos + ", letrasPorSegundo=" + letrasPorSegundo + ", esAColor=" + esAColor
				+ ", paginasImpresas=" + documentosImpresos + "]";
	}

}

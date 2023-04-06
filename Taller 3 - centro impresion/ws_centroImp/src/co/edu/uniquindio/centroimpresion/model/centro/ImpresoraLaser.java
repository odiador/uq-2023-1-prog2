package co.edu.uniquindio.centroimpresion.model.centro;

public class ImpresoraLaser extends Impresora {

	/**
	 *
	 */
	private static final long serialVersionUID = 2336828568323072760L;
	private int duracionToner;
	private int nivelToner;

	public ImpresoraLaser(String code, String marca, EstadoImpresora estado, boolean esAColor, double paginasPorMinuto,
			int duracionToner) {
		super(code, marca, estado, esAColor, paginasPorMinuto);
		this.duracionToner = duracionToner;
	}

	public int getDuracionToner() {
		return duracionToner;
	}

	public void setDuracionToner(int duracionToner) {
		this.duracionToner = duracionToner;
	}

	public int getNivelToner() {
		return nivelToner;
	}

	public void setNivelToner(int nivelToner) {
		this.nivelToner = nivelToner;
	}

	public void recargarToner() {
		setNivelToner(getDuracionToner());
	}

	@Override
	public boolean imprimirDocumento() {
		return false;
	}

	@Override
	public boolean imprimirDocumento(Documento documento) {
		// TODO Auto-generated method stub
		return false;
	}

}

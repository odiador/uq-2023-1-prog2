package co.edu.uniquindio.centroimpresion.model;

public class ImpresoraCartucho extends Impresora {

	private double desgasteCartucho;
	private double capacidadCartucho;
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

	public void setDesgasteCartucho(double desgasteCartucho) {
		this.desgasteCartucho = desgasteCartucho;
	}

	public double getCapacidadCartucho() {
		return capacidadCartucho;
	}

	public void setCapacidadCartucho(double capacidadCartucho) {
		this.capacidadCartucho = capacidadCartucho;
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

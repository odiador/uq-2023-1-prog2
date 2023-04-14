package co.edu.uniquindio.centroimpresion.model.centro;

public class Relacion<T1, T2> {
	private T1 t1;
	private T2 t2;

	public Relacion(T1 t1, T2 t2) {
		this.t1 = t1;
		this.t2 = t2;
	}

	public T1 obtenerCampo1() {
		return t1;
	}

	public T2 obtenerCampo2() {
		return t2;
	}
}

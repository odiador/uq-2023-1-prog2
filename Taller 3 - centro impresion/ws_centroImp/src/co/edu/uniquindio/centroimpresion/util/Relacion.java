package co.edu.uniquindio.centroimpresion.util;

/**
 * Genera una relacion entre 2 objetos, en este proyecto es usada para
 * relacionar impresora-documento al momento de imprimir
 * 
 * @author juan
 *
 * @param <T1>
 * @param <T2>
 */
public class Relacion<T1, T2> {
	private T1 t1;
	private T2 t2;

	public Relacion(T1 t1, T2 t2) {
		this.t1 = t1;
		this.t2 = t2;
	}

	/**
	 * Obtiene el primer campo de la relacion
	 * 
	 * @return
	 */
	public T1 obtenerCampo1() {
		return t1;
	}

	/**
	 * Obtiene el segundo campo de la relacion
	 * 
	 * @return
	 */
	public T2 obtenerCampo2() {
		return t2;
	}
}

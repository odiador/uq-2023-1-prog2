package co.edu.uniquindio.centroimpresion.services;

/**
 * Esta interface ejecuta un metodo cuando se este imprimiendo, exactamente cada
 * vez que se quiera agregar un caracter a la impresion
 * 
 * @author juan
 *
 */
public interface PuedeAgregarCaracter {
	/**
	 * Se ejecuta este metodo cuando se este imprimiendo, exactamente cada vez que
	 * se quiera agregar un caracter a la impresion
	 * 
	 * @param caracter
	 */
	public void agregarCaracter(char caracter);
}
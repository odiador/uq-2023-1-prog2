package co.edu.uniquindio.taller2.model;

public class Util {

	/**
	 * Obtiene una cadena con un formato especificado
	 *
	 * @param format
	 * @param args
	 * @return
	 */
	public static String darFormato(String format, Object... args) {
		return String.format(format, args).replace(',', '.');
	}

	/**
	 * Obtiene una cadena con formato de dinero
	 * <li>Ejemplo: Para un decimal 1536.73 se obtiene como resultado lo
	 * siguiente: $1536.73
	 *
	 * @param args
	 * @return
	 */
	public static String darFormatoDinero(Object... args) {
		return "$" + darFormato("%.2f", args);
	}
}

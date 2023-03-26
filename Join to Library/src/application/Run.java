package application;

import javax.swing.JOptionPane;

import view.AdminWindow;
import view.MainWindow;

public class Run {
	public static void main (String[] args) {
		String message = "<html>Bienvenid@ al CRAI, por favor elige una opcion a usar:" + "<br>"
				+ generateColorfulString("1. Ir a inicio de sesion de personas", "0000FF") + "<br>"
				+ generateColorfulString("2. Ir a las herramientas de administración de los estudiantes y libros",
						"006000")
				+ "<br>" + generateColorfulString("0. Cerrar", "FF0000") + "</html>";

		int opt = -1;
		while (opt == -1) {
			try {
				opt = Integer.parseInt(JOptionPane.showInputDialog(message));
			} catch (NumberFormatException e) {
				continue;
			}
			switch (opt) {
				case 1 -> MainWindow.openView();
				case 2 -> AdminWindow.openView();
				case 0 -> System.exit(0);
				default -> {
					opt = -1;
					continue;
				}
			}

		}
	}

	public static String generateColorfulString (String s, String hexColor) {
		return "<font color = #" + hexColor + ">" + s + "</font>";
	}
}

package customized;

import java.awt.Color;
import javax.swing.JLabel;

public class CustomLabel extends JLabel {

	private Color colCambio = Herramientas.white;

	public CustomLabel(String texto) {
		setText(texto);
		setFont(Herramientas.FUENTE_COOLVETICA);
		setHorizontalAlignment(10);
		setForeground(Herramientas.white);
	}

	public Color getColCambio() {
		return colCambio;
	}

	public void actualizarColor(Color colCambio) {
		this.colCambio = colCambio;
		setBackground(Herramientas.black);
		setForeground(getColCambio());
	}
	public void setFontSize(float size) {
		setFont(getFont().deriveFont(size));
	}
}

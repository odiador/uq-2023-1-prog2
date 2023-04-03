package co.edu.uniquindio.centroimpresion.view.principal;

import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

public class Header extends BorderPane {
	private String nombre;
	private Label lblNombre;

	/**
	 * Es el constructor de la clase Header
	 *
	 * @param nombre
	 */
	public Header(String nombre) {
		this.nombre = nombre;
		initComp();
	}

	/**
	 * Inicializa los componentes del panel y directamente también los agrega
	 */
	private void initComp() {
		lblNombre = new Label(nombre);
		setCenter(lblNombre);
		setId("header");
	}

	public Label getLblNombre() {
		return lblNombre;
	}

	public void setLblNombre(Label lblNombre) {
		this.lblNombre = lblNombre;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String name) {
		this.nombre = name;
	}
}

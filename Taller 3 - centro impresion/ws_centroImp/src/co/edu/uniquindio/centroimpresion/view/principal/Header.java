package co.edu.uniquindio.centroimpresion.view.principal;

import co.edu.uniquindio.centroimpresion.model.TipoEmpleado;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class Header extends BorderPane {
	private String nombre;
	private Label lblNombre, lblEmpleado;
	private TipoEmpleado tipoEmpleado;

	/**
	 * Es el constructor de la clase Header
	 *
	 * @param nombre
	 */
	public Header(String nombre, TipoEmpleado tipoEmpleado) {
		this.nombre = nombre;
		this.tipoEmpleado = tipoEmpleado;
		initComp();
	}

	/**
	 * Inicializa los componentes del panel y directamente también los agrega
	 */
	private void initComp() {
		lblNombre = new Label(nombre);
		lblEmpleado = new Label(tipoEmpleado.getText());
		setId("header");
		VBox vbox = new VBox(lblNombre, lblEmpleado);
		vbox.setId("centered-box");
		VBox.setMargin(lblNombre, new Insets(20));
		setCenter(vbox);
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

	public TipoEmpleado getTipoEmpleado() {
		return tipoEmpleado;
	}

	public void setTipoEmpleado(TipoEmpleado tipoEmpleado) {
		this.tipoEmpleado = tipoEmpleado;
	}
}

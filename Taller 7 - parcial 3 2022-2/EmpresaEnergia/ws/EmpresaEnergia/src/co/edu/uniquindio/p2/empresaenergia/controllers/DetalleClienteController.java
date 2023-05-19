package co.edu.uniquindio.p2.empresaenergia.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class DetalleClienteController {
	@FXML
	private TextField lblNombre;

	@FXML
	private Label lblAskIdentificacion;

	@FXML
	private Label lblAskInfoSecundaria1;

	@FXML
	private TextField lblIdentificacion;

	@FXML
	private TextField lblInfoSecundaria2;

	@FXML
	private Label lblAskNombre;

	@FXML
	private TextField lblInfoSecundaria1;

	@FXML
	private Label lblAskInfoSecundaria2;

	@FXML
	private BorderPane mainPane;

	@FXML
	void cerrarEvent(ActionEvent event) {
		cerrarAction();
	}

	private void cerrarAction() {
		((Stage) mainPane.getScene().getWindow()).close();
	}

	/**
	 * @return the lblNombre
	 */
	public TextField getLblNombre() {
		return lblNombre;
	}

	/**
	 * @return the lblAskIdentificacion
	 */
	public Label getLblAskIdentificacion() {
		return lblAskIdentificacion;
	}

	/**
	 * @return the lblAskInfoSecundaria1
	 */
	public Label getLblAskInfoSecundaria1() {
		return lblAskInfoSecundaria1;
	}

	/**
	 * @return the lblIdentificacion
	 */
	public TextField getLblIdentificacion() {
		return lblIdentificacion;
	}

	/**
	 * @return the lblInfoSecundaria2
	 */
	public TextField getLblInfoSecundaria2() {
		return lblInfoSecundaria2;
	}

	/**
	 * @return the lblAskNombre
	 */
	public Label getLblAskNombre() {
		return lblAskNombre;
	}

	/**
	 * @return the lblInfoSecundaria1
	 */
	public TextField getLblInfoSecundaria1() {
		return lblInfoSecundaria1;
	}

	/**
	 * @return the lblAskInfoSecundaria2
	 */
	public Label getLblAskInfoSecundaria2() {
		return lblAskInfoSecundaria2;
	}

}

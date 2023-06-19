package co.edu.uniquindio.p2.diplomado.controllers;

import co.edu.uniquindio.p2.diplomado.exceptions.AtributosFaltantesException;
import co.edu.uniquindio.p2.diplomado.exceptions.EstudianteException;
import co.edu.uniquindio.p2.diplomado.exceptions.NullException;
import co.edu.uniquindio.p2.diplomado.model.Estudiante;
import co.edu.uniquindio.p2.diplomado.utility.FxUtility;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class AdicionNotaController {
	@FXML
	private Button textoBoton;

	@FXML
	private TextField txtNota;

	@FXML
	private Label textoLabel;

	private Estudiante estudiante;

	private Runnable runnableCerrar;

	private Runnable runnableActualizarTabla;

	@FXML
	void initialize() {
		textoBoton.setText("Agregar");
		textoLabel.setText("Adicion");
		FxUtility.setRegexTextFieldTo(txtNota, "[0-9\\.]", "Este campo solo puede tener numeros y decimales");
	}

	@FXML
	void volverEvent(ActionEvent event) {
		runnableCerrar.run();
	}

	@FXML
	void botonPresionadoEvent(ActionEvent event) {
		adicionNotaAction();
	}

	private void adicionNotaAction() {
		Float nota = obtenerNotaTextField();
		if (nota < 0) {
			FxUtility.mostrarMensaje("Error", "La nota no pudo ser agregada", "Escribe una nota válida",
					AlertType.ERROR);
			return;
		}
		estudiante.agregarNota(nota);
		try {
			ModelFactoryController.getInstance().actualizarEstudiante(estudiante);
			runnableCerrar.run();
			runnableActualizarTabla.run();
			FxUtility.mostrarMensaje("Informacion", "La nota ha sido agregada con éxito",
					"La nota ha sido agregada con éxito", AlertType.CONFIRMATION);
		} catch (EstudianteException | NullException | AtributosFaltantesException e) {
			FxUtility.mostrarMensaje("Error", "La nota no pudo ser agregada", e.getMessage(), AlertType.ERROR);
		}
	}

	private Float obtenerNotaTextField() {
		float numero = -1f;
		try {
			numero = Float.parseFloat(txtNota.getText());
		} catch (NumberFormatException e) {
		}
		return new Float(numero);
	}

	public AdicionNotaController(Estudiante estudiante, Runnable runnableCerrar, Runnable runnableActualizarTabla) {
		this.estudiante = estudiante;
		this.runnableCerrar = runnableCerrar;
		this.runnableActualizarTabla = runnableActualizarTabla;
	}
}

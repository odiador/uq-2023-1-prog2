package co.edu.uniquindio.p2.diplomado.controllers;

import co.edu.uniquindio.p2.diplomado.exceptions.AtributosFaltantesException;
import co.edu.uniquindio.p2.diplomado.exceptions.EstudianteException;
import co.edu.uniquindio.p2.diplomado.exceptions.NotaException;
import co.edu.uniquindio.p2.diplomado.exceptions.NullException;
import co.edu.uniquindio.p2.diplomado.model.Estudiante;
import co.edu.uniquindio.p2.diplomado.utility.FxUtility;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class EdicionNotaController {
	@FXML
	private Button textoBoton;

	@FXML
	private TextField txtNota;

	@FXML
	private Label textoLabel;

	private Estudiante estudiante;

	private Runnable runnableCerrar;

	private Runnable runnableActualizarTabla;

	private int index;

	private float nota;

	@FXML
	void initialize() {
		textoBoton.setText("Editar");
		textoLabel.setText("Edicion");
		FxUtility.setRegexTextFieldTo(txtNota, "[0-9\\.]", "Este campo solo puede tener numeros y decimales");
	}

	@FXML
	void volverEvent(ActionEvent event) {
		runnableCerrar.run();
	}

	@FXML
	void botonPresionadoEvent(ActionEvent event) {
		edicionNotaAction();
	}

	private void edicionNotaAction() {
		Float nota = obtenerNotaTextField();
		if (nota < 0) {
			FxUtility.mostrarMensaje("Error", "La nota no pudo ser editada", "Escribe una nota válida",
					AlertType.ERROR);
			return;
		}
		try {
			estudiante.cambiarNotaPos(index, this.nota, nota);
		} catch (NotaException e) {
			FxUtility.mostrarMensaje("Error", "La nota no pudo ser editada", e.getMessage(), AlertType.ERROR);
			return;
		}
		try {
			ModelFactoryController.getInstance().actualizarEstudiante(estudiante);
			runnableCerrar.run();
			runnableActualizarTabla.run();
			FxUtility.mostrarMensaje("Informacion", "La nota ha sido editada con éxito",
					"La nota ha sido editada con éxito", AlertType.CONFIRMATION);
		} catch (EstudianteException | NullException | AtributosFaltantesException e) {
			FxUtility.mostrarMensaje("Error", "La nota no pudo ser editada", e.getMessage(), AlertType.ERROR);
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

	public EdicionNotaController(int index, float nota, Estudiante estudiante, Runnable runnableCerrar,
			Runnable runnableActualizarTabla) {
		this.index = index;
		this.nota = nota;
		this.estudiante = estudiante;
		this.runnableCerrar = runnableCerrar;
		this.runnableActualizarTabla = runnableActualizarTabla;
	}
}

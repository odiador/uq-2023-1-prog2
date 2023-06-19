package co.edu.uniquindio.p2.diplomado.controllers;

import co.edu.uniquindio.p2.diplomado.exceptions.EstudianteException;
import co.edu.uniquindio.p2.diplomado.exceptions.NullException;
import co.edu.uniquindio.p2.diplomado.utility.FxUtility;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;

public class EliminacionEstudianteController {
    @FXML
    private BorderPane mainPane;

    @FXML
    private TextField txtIdentificacion;

    private Runnable cerrarRunnable;

    private Runnable actualizarTablaRunnable;

    @FXML
    void cerrarEvent(ActionEvent event) {
        cerrarAction();
    }

    @FXML
    void eliminarEvent(ActionEvent event) {
        eliminarAction();

    }

    private void cerrarAction() {
        cerrarRunnable.run();
    }

    private void eliminarAction() {
        try {
            ModelFactoryController.getInstance().eliminarEstudiante(txtIdentificacion.getText());
            FxUtility.mostrarMensaje(null, null, null, null);
            actualizarTablaRunnable.run();
            FxUtility.mostrarMensaje("Informacion", "El estudiante fue eliminado:",
                    "El estudiante fue eliminado con éxito", AlertType.INFORMATION);
        } catch (EstudianteException | NullException e) {
            FxUtility.mostrarMensaje("Error", "El estudiante no se pudo eliminar:",
                    e.getMessage(), AlertType.ERROR);
        }
    }

    public EliminacionEstudianteController(Runnable cerrarRunnable, Runnable actualizarTablaRunnable) {
        this.cerrarRunnable = cerrarRunnable;
        this.actualizarTablaRunnable = actualizarTablaRunnable;
    }

}

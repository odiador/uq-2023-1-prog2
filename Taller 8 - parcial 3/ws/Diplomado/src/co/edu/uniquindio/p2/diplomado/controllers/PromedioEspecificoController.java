package co.edu.uniquindio.p2.diplomado.controllers;

import co.edu.uniquindio.p2.diplomado.exceptions.EstudianteException;
import co.edu.uniquindio.p2.diplomado.utility.FxUtility;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

public class PromedioEspecificoController {

    @FXML
    private BorderPane mainPane;

    @FXML
    private TextField txtIdentificacion;

    private Runnable cerrarEvent;

    @FXML
    void cerrarEvent(ActionEvent event) {
        cerrarAction();
    }

    @FXML
    void calcularPromedioEvent(ActionEvent event) {
        calcularPromedioAction();
    }

    public PromedioEspecificoController(Runnable cerrarEvent) {
        this.cerrarEvent = cerrarEvent;

    }

    private void cerrarAction() {
        cerrarEvent.run();
    }

    private void calcularPromedioAction() {
        try {
            FxUtility.mostrarMensaje("Informacion", "El promeido del estudiante es",
                    ModelFactoryController.getInstance().obtenerPromedioEstudiante(txtIdentificacion.getText()) + "",
                    AlertType.INFORMATION);
        } catch (EstudianteException e) {
            FxUtility.mostrarMensaje("Error", "Error", e.getMessage(), AlertType.ERROR);
        }
    }
}

package co.edu.uniquindio.p2.diplomado.controllers;

import java.io.IOException;

import co.edu.uniquindio.p2.diplomado.exceptions.NotPresentException;
import co.edu.uniquindio.p2.diplomado.utility.FxUtility;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class EstadisticasController {

    @FXML
    void promEstudiantesEvent(ActionEvent event) {
        promEstudiantesAction();
    }

    @FXML
    void promEstEspecificoEvent(ActionEvent event) {
        promEstEspecificoAction();
    }

    @FXML
    void estudiantePromAltoEvent(ActionEvent event) {
        estudiantePromAltoAction();
    }

    private void promEstudiantesAction() {
        try {
            FxUtility.mostrarMensaje("Informacion", "El promedio de los estudiantes es:",
                    ModelFactoryController.getInstance().obtenerPromedioTodosEstudiantes() + "", AlertType.INFORMATION);
        } catch (NotPresentException e) {
            FxUtility.mostrarMensaje("Error", "Error", e.getMessage(), AlertType.ERROR);
        }
    }

    private void promEstEspecificoAction() {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../view/PromedioEspecifico.fxml"));
        loader.setController(new PromedioEspecificoController(() -> stage.close()));
        try {
            stage.getIcons().add(new Image("/resources/images/Logo Window.png"));
            stage.setTitle("Promedio de estudiante especifico | "
                    + ModelFactoryController.getInstance().obtenerNombreDiplomado() + " | J Amador Roa");
            stage.setScene(new Scene(loader.load()));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void estudiantePromAltoAction() {
        try {
            FxUtility.mostrarMensaje("Informacion", "El promedio de los estudiantes es:",
                    ModelFactoryController.getInstance().obtenerPromedioMasAltoEstudiantes() + "",
                    AlertType.INFORMATION);
        } catch (NotPresentException e) {
            FxUtility.mostrarMensaje("Error", "Error", e.getMessage(), AlertType.ERROR);
        }
    }

}

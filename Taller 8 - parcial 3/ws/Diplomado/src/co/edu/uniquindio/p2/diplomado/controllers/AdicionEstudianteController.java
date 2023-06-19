package co.edu.uniquindio.p2.diplomado.controllers;

import co.edu.uniquindio.p2.diplomado.exceptions.AtributosFaltantesException;
import co.edu.uniquindio.p2.diplomado.exceptions.CuposLlenosException;
import co.edu.uniquindio.p2.diplomado.exceptions.EstudianteException;
import co.edu.uniquindio.p2.diplomado.exceptions.NullException;
import co.edu.uniquindio.p2.diplomado.model.Estudiante;
import co.edu.uniquindio.p2.diplomado.model.Genero;
import co.edu.uniquindio.p2.diplomado.utility.FxUtility;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;

public class AdicionEstudianteController {
    @FXML
    private TextField txtNombre;

    @FXML
    private BorderPane mainPane;

    @FXML
    private TextField txtIdentificacion;

    @FXML
    private ComboBox<String> comboGenero;

    private Runnable cerrarRunnable;

    private Runnable actualizarTablaRunnable;

    @FXML
    void initialize() {
        comboGenero.setItems(FXCollections.observableArrayList(Genero.textValues()));
    }

    @FXML
    void agregarEstudianteEvent(ActionEvent event) {
        agregarEstudianteAction();
    }

    @FXML
    void volverEvent(ActionEvent event) {
        volverAction();
    }

    private void volverAction() {
        cerrarRunnable.run();
    }

    public AdicionEstudianteController(Runnable cerrarRunnable, Runnable actualizarTablaRunnable) {
        this.cerrarRunnable = cerrarRunnable;
        this.actualizarTablaRunnable = actualizarTablaRunnable;
    }

    private void agregarEstudianteAction() {
        Estudiante estudiante = obtenerEstudianteCampos();
        if (estudiante == null) {
            FxUtility.mostrarMensaje("Error", "El estudiante no pudo ser agregado",
                    "Te hacen falta campos por llenar", AlertType.ERROR);
            return;
        }
        try {
            ModelFactoryController.getInstance().agregarEstudiante(estudiante);
            actualizarTablaRunnable.run();
            cerrarRunnable.run();
            FxUtility.mostrarMensaje("Informacion", "El estudiante ha sido agregado con éxito",
                    "El estudiante ha sido agregado con éxito", AlertType.CONFIRMATION);
        } catch (EstudianteException | NullException | AtributosFaltantesException | CuposLlenosException e) {
            FxUtility.mostrarMensaje("Error", "El estudiante no pudo ser agregado",
                    e.getMessage(), AlertType.ERROR);
        }
    }

    private Estudiante obtenerEstudianteCampos() {
        String identificacion = txtIdentificacion.getText();
        String nombre = txtNombre.getText();
        String generoString = comboGenero.getSelectionModel().getSelectedItem();

        if (identificacion.isEmpty())
            return null;
        if (nombre.isEmpty())
            return null;
        if (generoString == null)
            return null;

        Genero genero = Genero.of(generoString);
        if (genero == null)
            return null;

        return new Estudiante(nombre, genero, identificacion);
    }

}

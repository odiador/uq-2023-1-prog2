package co.edu.uniquindio.p2.empresaenergia.view;

import java.io.IOException;

import co.edu.uniquindio.p2.empresaenergia.controllers.ControlMenuPrincipal;
import co.edu.uniquindio.p2.empresaenergia.model.Empleado;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

public class EscenaPrincipal extends Scene {

	public EscenaPrincipal(Empleado empleado) throws IOException {
		super(new BorderPane(), 1280, 720);
		ControlMenuPrincipal controlMenuPrincipal = new ControlMenuPrincipal();

		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("MenuPrncipal.fxml"));
		loader.setController(controlMenuPrincipal);

		Parent load = loader.load();
		controlMenuPrincipal.getLblNombreEmpleado().setText("Bienvenid@, " + empleado.getNombre());
		setRoot(load);
	}

}

package co.edu.uniquindio.p2.empresaenergia.application;

import java.net.URL;

import co.edu.uniquindio.p2.empresaenergia.controllers.ModelFactoryController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader();
			URL resource = getClass().getResource("../view/InicioSesion.fxml");
			loader.setLocation(resource);
			Scene scene = new Scene(loader.load(), 600, 400);
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.setTitle("Inicio de Sesion | " + obtenerNombreEmpresa() + " ©");
			primaryStage.getIcons().add(new Image("/resources/images/logoEmpresaEnergia.png"));
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

	/**
	 * Obtiene el nombre de la empresa de energía
	 * 
	 * @return
	 */
	public static String obtenerNombreEmpresa() {
		return ModelFactoryController.getInstance().getEmpresaEnergia().getNombre();
	}
}

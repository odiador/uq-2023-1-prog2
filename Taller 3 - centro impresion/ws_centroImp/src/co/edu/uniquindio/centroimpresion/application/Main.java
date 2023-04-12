package co.edu.uniquindio.centroimpresion.application;

import java.net.URL;

import co.edu.uniquindio.centroimpresion.model.scenes.EscenaMenu;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
	public static URL css;

	@Override
	public void start(Stage primaryStage) {
		try {
			EscenaMenu scene = EscenaMenu.obtenerEscenaJm(primaryStage);
			css = getClass().getResource("application.css");
			scene.getStylesheets().add(css.toExternalForm());
			primaryStage.setTitle("Centro de Impresion - Amador");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}

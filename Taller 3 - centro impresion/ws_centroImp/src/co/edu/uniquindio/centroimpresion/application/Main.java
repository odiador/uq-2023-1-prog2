package co.edu.uniquindio.centroimpresion.application;

import co.edu.uniquindio.centroimpresion.model.scenes.EscenaMenu;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			EscenaMenu scene = EscenaMenu.obtenerEscenaJm(primaryStage);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
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

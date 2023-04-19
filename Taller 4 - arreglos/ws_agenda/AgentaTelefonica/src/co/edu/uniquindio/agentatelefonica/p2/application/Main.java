package co.edu.uniquindio.agentatelefonica.p2.application;

import co.edu.uniquindio.agentatelefonica.p2.views.scenes.EscenaPrincipal;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			Scene scene = new EscenaPrincipal("Juan Manuel Amador Roa");
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

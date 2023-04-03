package co.edu.uniquindio.centroimpresion.application;

import co.edu.uniquindio.centroimpresion.view.principal.OpcionPrincipal;
import co.edu.uniquindio.centroimpresion.view.principal.PanelPrincipal;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			PanelPrincipal root = new PanelPrincipal("Juan Manuel Amador Roa", OpcionPrincipal.values());
			Scene scene = new Scene(root, 800, 500);
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

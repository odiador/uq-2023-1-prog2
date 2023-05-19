package co.edu.uniquindio.p2.universidad.application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
import javafx.stage.Stage;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/co/edu/uniquindio/p2/universidad/views/PanelGestionEstudiantes.fxml"));
			SplitPane anchor = (SplitPane) loader.load();
			Scene scene = new Scene(anchor, 700, 600);
			primaryStage.setMinHeight(600);
			primaryStage.setMinWidth(600);
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

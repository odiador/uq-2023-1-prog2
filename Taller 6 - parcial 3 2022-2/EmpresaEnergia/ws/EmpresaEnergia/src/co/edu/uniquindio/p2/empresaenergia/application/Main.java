package co.edu.uniquindio.p2.empresaenergia.application;

import java.net.URL;

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
			URL resource = getClass().getResource("../view/MenuPrncipal.fxml");
			System.out.println(resource);
			loader.setLocation(resource);
			SplitPane root = loader.load();
			Scene scene = new Scene(root, 1280, 720);
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

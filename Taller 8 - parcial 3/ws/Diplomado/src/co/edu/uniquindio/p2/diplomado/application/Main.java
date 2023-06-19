package co.edu.uniquindio.p2.diplomado.application;

import co.edu.uniquindio.p2.diplomado.controllers.MenuPrincipalController;
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
			loader.setLocation(getClass().getResource("../view/MenuPrncipal.fxml"));
			loader.setController(new MenuPrincipalController());
			Scene scene = new Scene(loader.load(), 1280, 720);
			primaryStage.getIcons().add(new Image("/resources/images/Logo Window.png"));
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

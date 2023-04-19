package co.edu.uniquindio.agentatelefonica.p2.controllers;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.image.Image;

public class CtrlImages {

	public static Image generarUserImage() {
		FileInputStream stream = null;
		try {
			stream = new FileInputStream("src/resources/NoImage.png");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return new Image(stream);
	}

}

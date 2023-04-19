package co.edu.uniquindio.agentatelefonica.p2.controllers;

import co.edu.uniquindio.agentatelefonica.p2.views.principal.Header;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.value.ChangeListener;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;

public class CtrlResponsive {

	public static ChangeListener<? super Number> generarResponsiveListenerHeaderStage(Scene scene, Header header) {
		return (observable, oldValue, newValue) -> {
			// obtiene la altura de la escena
			double height = scene.getHeight();
			double headerHeight = height * 0.2;
			if (headerHeight < 80)
				headerHeight = 80;
			header.setPrefHeight(headerHeight);
		};
	}

	public static void escalarImagenHeaderResponsive(Header header, ImageView imgView) {
		DoubleBinding multiply = header.heightProperty().multiply(0.85).subtract(67.9d);
	
		imgView.fitHeightProperty().bind(multiply);
		imgView.fitWidthProperty().bind(multiply);
	}

}

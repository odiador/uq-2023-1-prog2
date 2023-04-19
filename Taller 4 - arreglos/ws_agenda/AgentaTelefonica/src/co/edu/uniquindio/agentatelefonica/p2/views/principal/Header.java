package co.edu.uniquindio.agentatelefonica.p2.views.principal;

import co.edu.uniquindio.agentatelefonica.p2.controllers.CtrlImages;
import co.edu.uniquindio.agentatelefonica.p2.controllers.CtrlResponsive;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class Header extends VBox {
	private String name;

	public Header(String name) {
		this.name = name;
		initComponents();
	}

	public void initComponents() {
		Label labelTitulo = new Label("Agenda Telefonica");
		ImageView imgView = new ImageView(CtrlImages.generarUserImage());

		Label label = new Label(name);

		CtrlResponsive.escalarImagenHeaderResponsive(this, imgView);
		setId("header");
		imgView.setId("img-header");
		label.setId("lbl-header");
		labelTitulo.setId("titulo-header");

		VBox.setMargin(labelTitulo, new Insets(10, 0, 0, 0));
		VBox.setMargin(imgView, new Insets(0, 0, 0, 0));
		VBox.setMargin(label, new Insets(0, 0, 20, 0));

		getChildren().add(labelTitulo);
		getChildren().add(imgView);
		getChildren().add(label);

	}

}

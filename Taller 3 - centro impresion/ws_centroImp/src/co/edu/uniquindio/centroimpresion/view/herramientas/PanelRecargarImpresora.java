package co.edu.uniquindio.centroimpresion.view.herramientas;

import co.edu.uniquindio.centroimpresion.view.custom.Boton;
import co.edu.uniquindio.centroimpresion.view.custom.PanelConVolver;
import co.edu.uniquindio.centroimpresion.view.util.Utility;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class PanelRecargarImpresora extends PanelConVolver {
	public PanelRecargarImpresora() {
		initComp();
	}

	@Override
	public void initComp() {
		super.initComp();
		VBox vbox = new VBox(30);
		TextField tfCodigo = new TextField();
		Boton bAceptar = new Boton("Aceptar", evt -> {

		});

		vbox.setId("centered-box");
		tfCodigo.setId("textfield");

		vbox.getChildren().add(Utility.generarHBox("Escribe el codigo de la impresora", tfCodigo));
		vbox.getChildren().add(bAceptar);
		setCenter(vbox);
	}

	@Override
	public void volverPresionado() {
		// TODO Auto-generated method stub

	}

}

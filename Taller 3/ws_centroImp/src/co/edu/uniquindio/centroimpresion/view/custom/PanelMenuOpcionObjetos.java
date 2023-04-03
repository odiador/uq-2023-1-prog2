package co.edu.uniquindio.centroimpresion.view.custom;

import co.edu.uniquindio.centroimpresion.model.OpcionObjeto;
import co.edu.uniquindio.centroimpresion.model.TipoAccion;
import co.edu.uniquindio.centroimpresion.model.TipoEmpleado;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public abstract class PanelMenuOpcionObjetos extends BorderPane implements EventHandler<Event> {

	private OpcionObjeto[] opciones;
	private String[] opcionesString;
	private TipoAccion accion;

	public PanelMenuOpcionObjetos(TipoAccion accion, TipoEmpleado tipoEmpleado) {
		this.accion = accion;
		this.opciones = generarOpciones(tipoEmpleado);
		this.opcionesString = new String[opciones.length];

		llenarStringsAcciones();
		initComp();
	}

	private void llenarStringsAcciones() {
		for (int i = 0; i < opciones.length; i++) {
			opcionesString[i] = accion.getText() + " " + opciones[i].getText();
		}
	}

	public abstract OpcionObjeto[] generarOpciones(TipoEmpleado tipoEmpleado);

	public void initComp() {
		VBox box = new VBox();
		box.setAlignment(Pos.CENTER);
		for (int i = 0; i < opciones.length; i++) {
			Label label = new Label(opcionesString[i]);
			label.setId("boton-opcion");
			label.setOnMouseReleased(this);
			box.getChildren().add(label);
			VBox.setMargin(label, new Insets(20));
		}
		setCenter(box);
	}

	public OpcionObjeto obtenerOpcion(String msg) {
		for (int i = 0; i < opcionesString.length; i++) {
			if (opcionesString[i].equals(msg))
				return opciones[i];
		}
		return null;
	}

	public abstract void btnDocPresionado();

	public abstract void btnDocEspPresionado();

	public abstract void btnImpCartuPresionado();

	public abstract void btnImpLaserPresionado();

	public abstract void btnImpPresionado();

	@Override
	public void handle(Event event) {
		Label label = (Label) event.getSource();
		OpcionObjeto opcion = obtenerOpcion(label.getText());
		switch (opcion) {
		case DOCUMENTO:
			btnDocPresionado();
			break;
		case IMPRESORA_CARTUCHO:
			btnImpCartuPresionado();
			break;
		case IMPRESORA_LASER:
			btnImpLaserPresionado();
			break;
		case DOCUMENTO_ESPEFICO:
			btnDocEspPresionado();
			break;
		case IMPRESORA:
			btnImpPresionado();
			break;
		default:
			break;
		}

	}

	public OpcionObjeto[] getOpciones() {
		return opciones;
	}

	public void setOpciones(OpcionObjeto[] opciones) {
		this.opciones = opciones;
	}
}

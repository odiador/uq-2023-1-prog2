package co.edu.uniquindio.centroimpresion.view;

import java.util.ArrayList;
import java.util.List;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

public class PanelPrincipalIzq extends VBox implements EventHandler<Event> {
	private final List<TabComunicationListener> listaListeners = new ArrayList<TabComunicationListener>();
	private OpcionPrincipal[] opciones;

	public PanelPrincipalIzq(String nombre, OpcionPrincipal opciones[]) {
		this.opciones = opciones;
		getChildren().add(new Header(nombre));
		for (OpcionPrincipal opcion : opciones) {
			Label labelOpciones = new Label(opcion.getText());
			labelOpciones.setMaxWidth(Double.MAX_VALUE);
			labelOpciones.setId("label-opciones-principal");
			labelOpciones.setOnMouseReleased(this);
			getChildren().add(labelOpciones);
		}
		setId("vbox-principal");
	}

	@Override
	public void handle(Event event) {
		MouseEvent evento = (MouseEvent) event;
		Label label = (Label) event.getSource();
		if (evento.getX() >= 0 && evento.getY() >= 0 && evento.getX() <= label.getWidth()
				&& evento.getY() <= label.getHeight())
			executeTabComunicationListeners(label.getText());
	}

	public OpcionPrincipal obtenerOpcion(String source) {
		for (OpcionPrincipal opcion : opciones) {
			if (opcion.getText().equals(source)) {
				return opcion;
			}
		}
		return null;
	}

	public void executeTabComunicationListeners(String source) {
		OpcionPrincipal opcionEncontrada = obtenerOpcion(source);
		if (opcionEncontrada != null) {
			for (TabComunicationListener tabComunicationListener : listaListeners) {
				tabComunicationListener.movementPerformed(opcionEncontrada);
			}
		}
	}

	public void addTabComunicationListener(TabComunicationListener l) {
		listaListeners.add(l);
	}
}

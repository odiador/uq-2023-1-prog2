package co.edu.uniquindio.centroimpresion.view.principal;

import java.util.ArrayList;
import java.util.List;

import co.edu.uniquindio.centroimpresion.model.TipoAccion;
import co.edu.uniquindio.centroimpresion.model.TipoEmpleado;
import co.edu.uniquindio.centroimpresion.services.TabComunicationListener;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

public class PanelPrincipalIzq extends VBox implements EventHandler<Event> {
	private final List<TabComunicationListener> listaListeners = new ArrayList<TabComunicationListener>();
	private TipoEmpleado tipoEmpleado;

	public PanelPrincipalIzq(String nombre, TipoEmpleado tipoEmpleado) {
		this.tipoEmpleado = tipoEmpleado;
		getChildren().add(new Header(nombre, tipoEmpleado));
		initComp();
		setFillWidth(true);
		setId("vbox-principal");
	}

	public void initComp() {
		Label labelOpcion;

		labelOpcion = new Label(TipoAccion.AGREGAR.getText());
		labelOpcion.setId("label-opciones-principal");
		labelOpcion.setOnMouseReleased(this);
		getChildren().add(labelOpcion);

		labelOpcion = new Label(TipoAccion.IMPRIMIR.getText());
		labelOpcion.setId("label-opciones-principal");
		labelOpcion.setOnMouseReleased(this);
		getChildren().add(labelOpcion);

		labelOpcion = new Label(TipoAccion.ACTUALIZAR.getText());
		labelOpcion.setId("label-opciones-principal");
		labelOpcion.setOnMouseReleased(this);
		getChildren().add(labelOpcion);

		if (tipoEmpleado.puedeEliminarDocumentos() || tipoEmpleado.puedeEliminarImpresoras()) {
			labelOpcion = new Label(TipoAccion.ELIMINAR.getText());
			labelOpcion.setId("label-opciones-principal");
			labelOpcion.setOnMouseReleased(this);
			getChildren().add(labelOpcion);
		}
		if (tipoEmpleado.puedeVerDocs() || tipoEmpleado.puedeVerImpresoras()) {

			labelOpcion = new Label(TipoAccion.VER.getText());
			labelOpcion.setId("label-opciones-principal");
			labelOpcion.setOnMouseReleased(this);
			getChildren().add(labelOpcion);
		}
		labelOpcion = new Label(TipoAccion.ACERCA_DE.getText());
		labelOpcion.setId("label-opciones-principal");
		labelOpcion.setOnMouseReleased(this);
		getChildren().add(labelOpcion);

	}

	@Override
	public void handle(Event event) {
		MouseEvent evento = (MouseEvent) event;
		Label label = (Label) event.getSource();
		if (evento.getX() >= 0 && evento.getY() >= 0 && evento.getX() <= label.getWidth()
				&& evento.getY() <= label.getHeight())
			executeTabComunicationListeners(label.getText());
	}

	public TipoAccion obtenerOpcion(String source) {
		for (TipoAccion opcion : TipoAccion.values()) {
			if (opcion.getText().equals(source)) {
				return opcion;
			}
		}
		return null;
	}

	public void executeTabComunicationListeners(String source) {
		TipoAccion opcionEncontrada = obtenerOpcion(source);
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

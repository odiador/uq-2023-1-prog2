package co.edu.uniquindio.agentatelefonica.p2.views.principal;

import co.edu.uniquindio.agentatelefonica.p2.views.internal.PanelAnadirContacto;
import co.edu.uniquindio.agentatelefonica.p2.views.internal.PanelBuscarContacto;
import co.edu.uniquindio.agentatelefonica.p2.views.internal.PanelEliminarContacto;
import co.edu.uniquindio.agentatelefonica.p2.views.internal.PanelHerramientas;
import co.edu.uniquindio.centroimpresion.util.Boton;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class PanelInternoPrincipal extends BorderPane {
	public PanelInternoPrincipal() {
		initComponents();
	}

	public void initComponents() {
		VBox box = new VBox();
		EventHandler<? super MouseEvent> eventoVolver = evtVolver -> initComponents();

		Boton anadirContactos = new Boton("Añadir contacto", e -> {
			setCenter(new PanelAnadirContacto(eventoVolver));
		});
		Boton buscarContacto = new Boton("Buscar contacto", e -> {
			setCenter(new PanelBuscarContacto(eventoVolver));
		});
		Boton eliminarContacto = new Boton("Eliminar contacto", e -> {
			setCenter(new PanelEliminarContacto(eventoVolver));
		});

		Boton btnHerramientas = new Boton("Herramientas", e -> {
			setCenter(new PanelHerramientas(eventoVolver));
		});

		box.getChildren().add(anadirContactos);
		box.getChildren().add(buscarContacto);
		box.getChildren().add(eliminarContacto);
		box.getChildren().add(btnHerramientas);

		VBox.setMargin(anadirContactos, new Insets(20));
		VBox.setMargin(buscarContacto, new Insets(20));
		VBox.setMargin(eliminarContacto, new Insets(20));
		VBox.setMargin(btnHerramientas, new Insets(20));

		box.setId("centered-box");
		setCenter(box);

	}
}

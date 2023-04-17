package co.edu.uniquindio.centroimpresion.view.see;

import co.edu.uniquindio.centroimpresion.model.Impresora;
import co.edu.uniquindio.centroimpresion.model.ImpresoraCartucho;
import co.edu.uniquindio.centroimpresion.model.ImpresoraLaser;
import co.edu.uniquindio.centroimpresion.view.custom.Boton;
import co.edu.uniquindio.centroimpresion.view.herramientas.PanelUpdateImpCartucho;
import co.edu.uniquindio.centroimpresion.view.herramientas.PanelUpdateImpLaser;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

public class PanelSeeSelectedImp extends BorderPane {
	private Impresora impresora;
	private EventHandler<? super MouseEvent> eventoVolver;

	public PanelSeeSelectedImp(Impresora impresora, EventHandler<? super MouseEvent> eventoVolver) {
		this.impresora = impresora;
		this.eventoVolver = eventoVolver;
		initComponents();
	}

	private void initComponents() {
		if (impresora instanceof ImpresoraCartucho) {
			setCenter(new PanelUpdateImpCartucho((ImpresoraCartucho) impresora, false));
		}
		if (impresora instanceof ImpresoraLaser) {
			setCenter(new PanelUpdateImpLaser((ImpresoraLaser) impresora, false));
		}
		setBottom(new Boton("Volver", eventoVolver, "btn-volver"));
	}
}

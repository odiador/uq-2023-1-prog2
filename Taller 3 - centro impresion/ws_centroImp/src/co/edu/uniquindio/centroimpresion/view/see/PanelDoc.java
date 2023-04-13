package co.edu.uniquindio.centroimpresion.view.see;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import co.edu.uniquindio.centroimpresion.model.centro.Documento;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class PanelDoc extends VBox {
	private Documento documento;

	public PanelDoc(Documento documento) {
		this.documento = documento;
		initComp();
	}

	public void initComp() {
		setId("centered-box");

		Label codigo = new Label("Codigo: " + documento.getCode());
		Label titulo = new Label("Titulo: " + documento.getTitulo());
		Label prioridad = new Label("Prioridad: " + String.valueOf(documento.getPrioridad()));
		Label contenido = new Label("Ver Contenido");
		LocalDateTime momentoAgregado = documento.getFechaAgregado();
		DateTimeFormatter pattern = DateTimeFormatter.ofPattern("HH:mm:ss, dd/MM/yy");
		Label fechaAgregado = new Label("Fecha agregado: " + momentoAgregado.format(pattern));
		LocalDateTime fechaImpresion = documento.getFechaImpresion();
		Label yaFueImpreso = new Label(
				"Fecha de Impresion: " + (fechaImpresion != null ? fechaImpresion.format(pattern) : "N/A"));

		contenido.setId("btn-tabla");
		contenido.setOnMouseReleased(e -> new Alert(AlertType.INFORMATION, documento.getContenido()).show());

		getChildren().addAll(codigo, titulo, prioridad, contenido, fechaAgregado, yaFueImpreso);
	}

}

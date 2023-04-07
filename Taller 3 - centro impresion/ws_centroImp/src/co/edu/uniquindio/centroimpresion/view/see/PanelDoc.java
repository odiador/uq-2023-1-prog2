package co.edu.uniquindio.centroimpresion.view.see;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import co.edu.uniquindio.centroimpresion.model.centro.Documento;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class PanelDoc extends VBox {
	private Documento documento;

	public PanelDoc(Documento documento) {
		this.documento = documento;
		initComp();
	}

	public void initComp() {
		Label codigo = new Label(documento.getCode());
		Label titulo = new Label(documento.getTitulo());
		Label prioridad = new Label(String.valueOf(documento.getPrioridad()));
		Label contenido = new Label(documento.getContenido());
		LocalDateTime momentoAgregado = documento.getFechaAgregado();
		Label fechaAgregado = new Label(momentoAgregado.format(DateTimeFormatter.ofPattern("HH:mm:ss, dd:MM:yy")));
		Label yaFueImpreso = new Label(documento.getFechaImpresion() != null ? "Si" : "No");
		getChildren().addAll(codigo, titulo, prioridad, contenido, fechaAgregado, yaFueImpreso);
	}
}

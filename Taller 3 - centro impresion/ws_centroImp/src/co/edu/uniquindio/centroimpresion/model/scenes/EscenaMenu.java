package co.edu.uniquindio.centroimpresion.model.scenes;

import co.edu.uniquindio.centroimpresion.model.centro.TipoEmpleado;
import co.edu.uniquindio.centroimpresion.view.principal.PanelPrincipal;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class EscenaMenu extends Scene {

	public EscenaMenu(Stage stage, String name, TipoEmpleado tipoEmpleado) {
		super(new PanelPrincipal(stage, name, tipoEmpleado), 1200, 800);
	}

	public static EscenaMenu obtenerEscenaJm(Stage stage) {
		return new EscenaMenu(stage, "Juan Manuel Amador Roa", TipoEmpleado.ADMINISTRADOR);
	}

}

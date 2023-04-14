package co.edu.uniquindio.centroimpresion.view.menu;

import java.util.ArrayList;
import java.util.Arrays;

import co.edu.uniquindio.centroimpresion.model.centro.OpcionObjeto;
import co.edu.uniquindio.centroimpresion.model.centro.TipoAccion;
import co.edu.uniquindio.centroimpresion.model.centro.TipoEmpleado;
import co.edu.uniquindio.centroimpresion.view.custom.PanelMenuOpcionObjetos;
import co.edu.uniquindio.centroimpresion.view.print.PanelPrintDoc;
import co.edu.uniquindio.centroimpresion.view.print.PanelPrintEspDoc;
import javafx.stage.Stage;

public class PanelMenuPrint extends PanelMenuOpcionObjetos {

	private Stage stage;

	public PanelMenuPrint(TipoEmpleado tipoEmpleado, Stage stage) {
		super(TipoAccion.IMPRIMIR, tipoEmpleado);
		this.stage = stage;
	}

	public OpcionObjeto[] generarOpciones(TipoEmpleado tipoEmpleado) {
		ArrayList<OpcionObjeto> listaOpciones = new ArrayList<OpcionObjeto>(Arrays.asList(OpcionObjeto.values()));
		listaOpciones.remove(OpcionObjeto.IMPRESORA);
		listaOpciones.remove(OpcionObjeto.IMPRESORA_CARTUCHO);
		listaOpciones.remove(OpcionObjeto.IMPRESORA_LASER);
		if (!tipoEmpleado.puedeImprimirDocEspecifico()) {
			listaOpciones.remove(OpcionObjeto.DOCUMENTO_ESPEFICO);
		}
		return listaOpciones.toArray(new OpcionObjeto[listaOpciones.size()]);
	}

	@Override
	public void btnDocPresionado() {
		setCenter(new PanelPrintDoc(this, stage));
	}

	@Override
	public void btnImpCartuPresionado() {
	}

	@Override
	public void btnImpLaserPresionado() {
	}

	@Override
	public void btnDocEspPresionado() {
		setCenter(new PanelPrintEspDoc(this));
	}

	@Override
	public void btnImpPresionado() {
	}

}

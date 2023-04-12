package co.edu.uniquindio.centroimpresion.view.menu;

import java.util.ArrayList;
import java.util.Arrays;

import co.edu.uniquindio.centroimpresion.model.centro.OpcionObjeto;
import co.edu.uniquindio.centroimpresion.model.centro.TipoAccion;
import co.edu.uniquindio.centroimpresion.model.centro.TipoEmpleado;
import co.edu.uniquindio.centroimpresion.view.add.PanelAddDoc;
import co.edu.uniquindio.centroimpresion.view.add.PanelAddImpCartucho;
import co.edu.uniquindio.centroimpresion.view.add.PanelAddImpLaser;
import co.edu.uniquindio.centroimpresion.view.custom.PanelMenuOpcionObjetos;
import javafx.stage.Stage;

public class PanelMenuAdd extends PanelMenuOpcionObjetos {

	private Stage stage;

	public PanelMenuAdd(Stage stage,TipoEmpleado tipoEmpleado) {
		super(TipoAccion.AGREGAR, tipoEmpleado);
		this.stage = stage;
	}

	public OpcionObjeto[] generarOpciones(TipoEmpleado tipoEmpleado) {
		ArrayList<OpcionObjeto> listaOpciones = new ArrayList<OpcionObjeto>(Arrays.asList(OpcionObjeto.values()));
		listaOpciones.remove(OpcionObjeto.DOCUMENTO_ESPEFICO);
		listaOpciones.remove(OpcionObjeto.IMPRESORA);
		if (!tipoEmpleado.puedeAgregarImpresora()) {
			listaOpciones.remove(OpcionObjeto.IMPRESORA_LASER);
			listaOpciones.remove(OpcionObjeto.IMPRESORA_CARTUCHO);
		}
		return listaOpciones.toArray(new OpcionObjeto[listaOpciones.size()]);
	}

	@Override
	public void btnDocPresionado() {
		setCenter(new PanelAddDoc(stage,this));
	}

	@Override
	public void btnImpCartuPresionado() {
		setCenter(new PanelAddImpCartucho(this));
	}

	@Override
	public void btnImpLaserPresionado() {
		setCenter(new PanelAddImpLaser(this));
	}

	@Override
	public void btnDocEspPresionado() {
	}

	@Override
	public void btnImpPresionado() {

	}

}

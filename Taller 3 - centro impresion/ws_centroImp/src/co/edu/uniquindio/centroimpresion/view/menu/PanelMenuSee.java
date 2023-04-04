package co.edu.uniquindio.centroimpresion.view.menu;

import java.util.ArrayList;
import java.util.Arrays;

import co.edu.uniquindio.centroimpresion.model.OpcionObjeto;
import co.edu.uniquindio.centroimpresion.model.TipoAccion;
import co.edu.uniquindio.centroimpresion.model.TipoEmpleado;
import co.edu.uniquindio.centroimpresion.view.add.PanelAddDoc;
import co.edu.uniquindio.centroimpresion.view.custom.PanelMenuOpcionObjetos;

public class PanelMenuSee extends PanelMenuOpcionObjetos {

	public PanelMenuSee(TipoEmpleado tipoEmpleado) {
		super(TipoAccion.VER, tipoEmpleado);
	}

	public OpcionObjeto[] generarOpciones(TipoEmpleado tipoEmpleado) {
		ArrayList<OpcionObjeto> listaOpciones = new ArrayList<OpcionObjeto>(Arrays.asList(OpcionObjeto.values()));
		listaOpciones.remove(OpcionObjeto.DOCUMENTO_ESPEFICO);
		listaOpciones.remove(OpcionObjeto.IMPRESORA_CARTUCHO);
		listaOpciones.remove(OpcionObjeto.IMPRESORA_LASER);
		if (!tipoEmpleado.puedeVerDocs()) {
			listaOpciones.remove(OpcionObjeto.DOCUMENTO);
		}
		if (!tipoEmpleado.puedeVerImpresoras()) {
			listaOpciones.remove(OpcionObjeto.IMPRESORA);
		}
		return listaOpciones.toArray(new OpcionObjeto[listaOpciones.size()]);
	}

	@Override
	public void btnDocPresionado() {
		setCenter(new PanelAddDoc(this));
	}

	@Override
	public void btnImpCartuPresionado() {
	}

	@Override
	public void btnImpLaserPresionado() {
	}

	@Override
	public void btnDocEspPresionado() {
	}

	@Override
	public void btnImpPresionado() {

	}

}

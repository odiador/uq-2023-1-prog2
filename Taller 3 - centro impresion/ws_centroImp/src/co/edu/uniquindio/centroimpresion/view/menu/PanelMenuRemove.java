package co.edu.uniquindio.centroimpresion.view.menu;

import java.util.ArrayList;
import java.util.Arrays;

import co.edu.uniquindio.centroimpresion.model.centro.OpcionObjeto;
import co.edu.uniquindio.centroimpresion.model.centro.TipoAccion;
import co.edu.uniquindio.centroimpresion.model.centro.TipoEmpleado;
import co.edu.uniquindio.centroimpresion.view.custom.PanelMenuOpcionObjetos;
import co.edu.uniquindio.centroimpresion.view.remove.PanelRemoveDoc;
import co.edu.uniquindio.centroimpresion.view.remove.PanelRemoveImp;

public class PanelMenuRemove extends PanelMenuOpcionObjetos {

	public PanelMenuRemove(TipoEmpleado tipoEmpleado) {
		super(TipoAccion.ELIMINAR, tipoEmpleado);
	}

	public OpcionObjeto[] generarOpciones(TipoEmpleado tipoEmpleado) {
		ArrayList<OpcionObjeto> listaOpciones = new ArrayList<OpcionObjeto>(Arrays.asList(OpcionObjeto.values()));
		listaOpciones.remove(OpcionObjeto.DOCUMENTO_ESPEFICO);
		listaOpciones.remove(OpcionObjeto.IMPRESORA_CARTUCHO);
		listaOpciones.remove(OpcionObjeto.IMPRESORA_LASER);
		if (!tipoEmpleado.puedeEliminarDocumentos()) {
			listaOpciones.remove(OpcionObjeto.DOCUMENTO);
		}
		if (!tipoEmpleado.puedeEliminarImpresoras()) {
			listaOpciones.remove(OpcionObjeto.IMPRESORA);
		}
		return listaOpciones.toArray(new OpcionObjeto[listaOpciones.size()]);
	}

	@Override
	public void btnDocPresionado() {
		setCenter(new PanelRemoveDoc(this));
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
		setCenter(new PanelRemoveImp(this));
	}

}

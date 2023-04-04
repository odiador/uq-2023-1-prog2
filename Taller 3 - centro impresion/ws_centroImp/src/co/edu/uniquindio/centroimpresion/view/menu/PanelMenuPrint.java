package co.edu.uniquindio.centroimpresion.view.menu;

import java.util.ArrayList;
import java.util.Arrays;

import co.edu.uniquindio.centroimpresion.model.OpcionObjeto;
import co.edu.uniquindio.centroimpresion.model.TipoAccion;
import co.edu.uniquindio.centroimpresion.model.TipoEmpleado;
import co.edu.uniquindio.centroimpresion.view.custom.PanelMenuOpcionObjetos;
import co.edu.uniquindio.centroimpresion.view.print.PanelPrintDoc;
import co.edu.uniquindio.centroimpresion.view.print.PanelPrintEspDoc;

public class PanelMenuPrint extends PanelMenuOpcionObjetos {

	public PanelMenuPrint(TipoEmpleado tipoEmpleado) {
		super(TipoAccion.IMPRIMIR, tipoEmpleado);
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
		setCenter(new PanelPrintDoc(this));
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

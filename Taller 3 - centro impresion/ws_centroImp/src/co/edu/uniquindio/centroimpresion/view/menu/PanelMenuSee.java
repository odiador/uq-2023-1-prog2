package co.edu.uniquindio.centroimpresion.view.menu;

import java.util.ArrayList;

import co.edu.uniquindio.centroimpresion.model.centro.OpcionObjeto;
import co.edu.uniquindio.centroimpresion.model.centro.TipoAccion;
import co.edu.uniquindio.centroimpresion.model.centro.TipoEmpleado;
import co.edu.uniquindio.centroimpresion.view.custom.PanelMenuOpcionObjetos;
import co.edu.uniquindio.centroimpresion.view.see.PanelSeeDocs;
import co.edu.uniquindio.centroimpresion.view.see.PanelSeeImpCartucho;
import co.edu.uniquindio.centroimpresion.view.see.PanelSeeImpLaser;

public class PanelMenuSee extends PanelMenuOpcionObjetos {

	public PanelMenuSee(TipoEmpleado tipoEmpleado) {
		super(TipoAccion.VER, tipoEmpleado);
	}

	public OpcionObjeto[] generarOpciones(TipoEmpleado tipoEmpleado) {
		ArrayList<OpcionObjeto> listaOpciones = new ArrayList<OpcionObjeto>();
		if (tipoEmpleado.puedeVerDocs()) {
			listaOpciones.add(OpcionObjeto.DOCUMENTO);
		}
		if (tipoEmpleado.puedeVerImpresoras()) {
			listaOpciones.add(OpcionObjeto.IMPRESORA_LASER);
			listaOpciones.add(OpcionObjeto.IMPRESORA_CARTUCHO);
		}
		return listaOpciones.toArray(new OpcionObjeto[listaOpciones.size()]);
	}

	@Override
	public void btnDocPresionado() {
		setCenter(new PanelSeeDocs(this));
	}

	@Override
	public void btnImpCartuPresionado() {
		setCenter(new PanelSeeImpCartucho(this));
	}

	@Override
	public void btnImpLaserPresionado() {
		setCenter(new PanelSeeImpLaser(this));
	}

	@Override
	public void btnDocEspPresionado() {
	}

	@Override
	public void btnImpPresionado() {

	}

}

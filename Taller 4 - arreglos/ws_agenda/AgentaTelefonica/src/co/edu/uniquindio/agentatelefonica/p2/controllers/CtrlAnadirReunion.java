package co.edu.uniquindio.agentatelefonica.p2.controllers;

import java.util.HashSet;
import java.util.Set;

import co.edu.uniquindio.agentatelefonica.p2.exceptions.CampoException;
import co.edu.uniquindio.agentatelefonica.p2.exceptions.ContactoException;
import co.edu.uniquindio.agentatelefonica.p2.model.Contacto;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class CtrlAnadirReunion {
	public Set<Contacto> listaContactos = new HashSet<>();

	public CtrlAnadirReunion() {
	}

	public void anadirContacto(String nombre, String telefono) {
		try {
			Contacto contacto = CtrlContacto.buscarContactoThrows(nombre, telefono);
			agregarContactoThrows(contacto);
		} catch (CampoException | ContactoException e) {
			new Alert(AlertType.WARNING, e.getMessage()).show();
		}
	}

	public void agregarContactoThrows(Contacto contacto) {
		listaContactos.add(contacto);
	}

	public static void goToAnadirReunion() {

	}
}

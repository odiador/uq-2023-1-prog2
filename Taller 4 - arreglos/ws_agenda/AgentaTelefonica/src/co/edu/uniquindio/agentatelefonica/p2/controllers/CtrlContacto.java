package co.edu.uniquindio.agentatelefonica.p2.controllers;

import co.edu.uniquindio.agentatelefonica.p2.exceptions.CampoException;
import co.edu.uniquindio.agentatelefonica.p2.exceptions.ContactoException;
import co.edu.uniquindio.agentatelefonica.p2.exceptions.ObjetoNoExisteException;
import co.edu.uniquindio.agentatelefonica.p2.model.Contacto;
import co.edu.uniquindio.centroimpresion.util.Utility;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class CtrlContacto {
	public static void agregarContacto(String nombre, String alias, String direccion, String telefono, String email) {
		try {
			agregarContactoThrows(nombre, alias, direccion, telefono, email);
			new Alert(AlertType.WARNING, "El contacto ha sido agregado exitosamente").show();
		} catch (CampoException | ObjetoNoExisteException | ContactoException e) {
			new Alert(AlertType.WARNING, e.getMessage()).show();
		}
	}

	public static void buscarContacto(String nombre, String telefono) {
		try {
			Contacto contactoThrows = buscarContactoThrows(nombre, telefono);
			new Alert(AlertType.WARNING, contactoThrows.toString()).show();
		} catch (CampoException | ContactoException e) {
			new Alert(AlertType.WARNING, e.getMessage()).show();
		}
	}

	private static void agregarContactoThrows(String nombre, String alias, String direccion, String telefono,
			String email) throws CampoException, ObjetoNoExisteException, ContactoException {
		Utility.throwIfEmpty(nombre);
		Utility.throwIfEmpty(alias);
		Utility.throwIfEmpty(direccion);
		Utility.throwIfEmpty(telefono);
		Utility.throwIfEmpty(email);
		Contacto contacto = new Contacto(nombre, alias, direccion, telefono, email);
		SerializedData data = new SerializedData();
		data.getAgenda().aniadirContacto(contacto);
		data.actualizarAgenda();
	}

	private static Contacto buscarContactoThrows(String nombre, String telefono)
			throws CampoException, ContactoException {
		Utility.throwIfEmpty(nombre);
		Utility.throwIfEmpty(telefono);
		SerializedData data = new SerializedData();
		Contacto contacto = new Contacto(nombre, telefono);
		Contacto buscarContactoThrows = data.getAgenda().buscarContactoThrows(contacto);
		return buscarContactoThrows;
	}
}

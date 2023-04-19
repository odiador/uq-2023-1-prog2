package co.edu.uniquindio.agentatelefonica.p2.controllers;

import co.edu.uniquindio.agentatelefonica.p2.exceptions.ArregloLlenoException;
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
		} catch (CampoException | ObjetoNoExisteException | ContactoException | ArregloLlenoException e) {
			new Alert(AlertType.WARNING, e.getMessage()).show();
		}
	}

	public static void agregarContactoThrows(String nombre, String alias, String direccion, String telefono,
			String email) throws CampoException, ObjetoNoExisteException, ContactoException, ArregloLlenoException {
		Utility.throwIfEmpty(nombre);
		Utility.throwIfEmpty(alias);
		Utility.throwIfEmpty(direccion);
		Utility.throwIfEmpty(telefono);
		Utility.throwIfEmpty(email);
		Contacto contacto = new Contacto(nombre, alias, direccion, telefono, email);
		SerializedData data = new SerializedData();
		data.getAgenda().aniadirContacto(contacto);
	}

}

package co.edu.uniquindio.agentatelefonica.p2.controllers;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

import co.edu.uniquindio.agentatelefonica.p2.exceptions.ArregloLlenoException;
import co.edu.uniquindio.agentatelefonica.p2.exceptions.CampoException;
import co.edu.uniquindio.agentatelefonica.p2.exceptions.ContactoException;
import co.edu.uniquindio.agentatelefonica.p2.exceptions.ObjetoNoExisteException;
import co.edu.uniquindio.agentatelefonica.p2.exceptions.ReunionException;
import co.edu.uniquindio.agentatelefonica.p2.model.Contacto;
import co.edu.uniquindio.agentatelefonica.p2.model.Reunion;
import co.edu.uniquindio.centroimpresion.util.Utility;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class CtrlAnadirReunion {
	private Set<Contacto> listaContactos = new HashSet<>();

	public void anadirContacto(String nombre, String telefono) {
		try {
			Contacto contacto = CtrlContacto.buscarContactoThrows(nombre, telefono);
			agregarContactoThrows(contacto);
			new Alert(AlertType.CONFIRMATION,
					"El contacto ha sido agregado, recuerda agregar la reunion para guardarlo").show();
		} catch (CampoException | ContactoException e) {
			new Alert(AlertType.WARNING, e.getMessage()).show();
		}
	}

	public void agregarContactoThrows(Contacto contacto) {
		listaContactos.add(contacto);
	}

	public static void goToAnadirReunion() {

	}

	public Set<Contacto> getListaContactos() {
		return listaContactos;
	}

	public void setListaContactos(Set<Contacto> listaContactos) {
		this.listaContactos = listaContactos;
	}

	public static void anadirReunion(String nombre, String descripcion, Set<Contacto> listaContactos, LocalDate fecha,
			String horas, String minutos) {
		try {
			anadirReunionThrows(nombre, descripcion, listaContactos, fecha, horas, minutos);
			new Alert(AlertType.CONFIRMATION, "La reunion ha sido agregada con exito").show();
		} catch (CampoException | ObjetoNoExisteException | ReunionException | ArregloLlenoException e) {
			new Alert(AlertType.WARNING, e.getMessage()).show();
		}
	}

	public static void anadirReunionThrows(String nombre, String descripcion, Set<Contacto> listaContactos,
			LocalDate fecha, String horas, String minutos)
			throws CampoException, ObjetoNoExisteException, ReunionException, ArregloLlenoException {
		Contacto arrContactos[] = listaContactos.toArray(new Contacto[listaContactos.size()]);
		int horasNum = Utility.pasarEnteroThrows(horas);
		int minutosNum = Utility.pasarEnteroThrows(minutos);
		try {
			LocalTime localTime = LocalTime.of(horasNum, minutosNum);
			LocalDateTime fechaHora = LocalDateTime.of(fecha, localTime);
			Reunion reunion = new Reunion(nombre, descripcion, fechaHora, arrContactos);
			SerializedData data = new SerializedData();
			data.getAgenda().agregarReunion(reunion);
		} catch (DateTimeException e) {
			throw new ObjetoNoExisteException("Organiza bien la fecha y la hora");
		}
	}
}

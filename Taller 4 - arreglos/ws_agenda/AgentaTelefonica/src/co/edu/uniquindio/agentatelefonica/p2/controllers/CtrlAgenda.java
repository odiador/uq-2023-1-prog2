package co.edu.uniquindio.agentatelefonica.p2.controllers;

import java.util.Optional;

import co.edu.uniquindio.agentatelefonica.p2.application.Main;
import co.edu.uniquindio.agentatelefonica.p2.exceptions.CampoException;
import co.edu.uniquindio.agentatelefonica.p2.model.Agenda;
import co.edu.uniquindio.agentatelefonica.p2.views.scenes.EscenaCrearAgenda;
import co.edu.uniquindio.centroimpresion.util.Utility;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

public class CtrlAgenda {

	public static void crearAgenda(Stage stage, Stage stageMain, String nombre, String cantContactosString,
			String cantGruposString, String cantReunionesString) {
		try {
			crearAgendaThrows(nombre, cantContactosString, cantGruposString, cantReunionesString);
			stage.close();
			stageMain.show();
		} catch (CampoException e) {
			new Alert(AlertType.WARNING, e.getMessage()).show();
		}
	}

	public static void huecosLibres() {
		SerializedData data = new SerializedData();
		int huecosLibres = data.getAgenda().huecosLibres();
		int ocupados = data.getAgenda().cantidadOcupados();
		StringBuilder sringBuilder = new StringBuilder();
		sringBuilder.append("La cantidad de contactos ocupados son: ");
		sringBuilder.append(ocupados);
		sringBuilder.append(" y de libres son: ");
		sringBuilder.append(huecosLibres);
		Alert alert = new Alert(AlertType.INFORMATION, sringBuilder.toString());
		alert.show();
	}

	private static void crearAgendaThrows(String nombre, String cantContactosString, String cantGruposString,
			String cantReunionesString) throws CampoException {
		Utility.throwIfEmpty(nombre);
		Utility.throwIfEmpty(cantContactosString);
		Utility.throwIfEmpty(cantGruposString);
		Utility.throwIfEmpty(cantReunionesString);
		int cantContactos = pasarEnteroThrows(cantContactosString);
		int cantGrupos = pasarEnteroThrows(cantGruposString);
		int cantReuniones = pasarEnteroThrows(cantReunionesString);
		SerializedData data = new SerializedData();
		data.setAgenda(new Agenda(nombre, cantContactos, cantReuniones, cantGrupos));
		data.actualizarAgenda();
	}

	private static int pasarEnteroThrows(String cadena) throws CampoException {
		try {
			return Integer.parseInt(cadena);
		} catch (NumberFormatException e) {
			throw new CampoException("Recuerda solo colocar numeros en campos numericos");
		}
	}

	public static void crearData(Stage stage) {
		SerializedData data = new SerializedData();
		try {
			data.leerObjeto();
			preguntarCrearAgenda(stage, "La agenda ya existe, deseas sobreescribirla?");

		} catch (Exception e) {
			CtrlAgenda.irACrearAgenda(stage);
		}
	}

	private static void preguntarCrearAgenda(Stage stage, String msg) {
		Alert alert = new Alert(AlertType.INFORMATION, msg, ButtonType.YES, ButtonType.NO);
		Optional<ButtonType> crearAgenda = alert.showAndWait();
		ButtonType resultadoCrear = crearAgenda.orElse(null);
		if (resultadoCrear == ButtonType.YES)
			CtrlAgenda.irACrearAgenda(stage);
		else
			stage.show();
	}

	static void irACrearAgenda(Stage stageMain) {
		Stage stage = new Stage();
		EscenaCrearAgenda escenaCrearAgenda = new EscenaCrearAgenda(stage, stageMain);
		stage.setTitle("Agenda - agregar agenda | Juan Manuel Amador Roa");
		stage.setScene(escenaCrearAgenda);
		escenaCrearAgenda.getStylesheets().add(Main.applicationCss);
		stage.show();
	}

}

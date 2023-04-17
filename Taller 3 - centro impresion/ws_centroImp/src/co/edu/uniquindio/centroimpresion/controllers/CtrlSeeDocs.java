package co.edu.uniquindio.centroimpresion.controllers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import co.edu.uniquindio.centroimpresion.model.Documento;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.util.Callback;

public class CtrlSeeDocs {
	/**
	 * Obtiene la lista de documentos en cola del centro de impresion
	 * 
	 * @return
	 */
	public static ArrayList<Documento> obtenerListaCola() {
		return (ArrayList<Documento>) new SerializedData().getCentroImpresion().getListaDocumentos();
	}

	/**
	 * Obtiene la lista de documentos en cola del centro de impresion
	 * 
	 * @return
	 */
	public static ArrayList<Documento> obtenerListaImpresos() {
		return (ArrayList<Documento>) new SerializedData().getCentroImpresion().getListaDocumentosImpresos();
	}

	/**
	 * Obtiene el callback del contenido del documento, a cada celda de la columna
	 * se agrega un boton que muestra el contenido del doc
	 * 
	 * @return
	 */
	public static Callback<TableColumn<Documento, String>, TableCell<Documento, String>> obtenerCallbackContenido() {
		return new Callback<TableColumn<Documento, String>, TableCell<Documento, String>>() {

			final @Override public TableCell<Documento, String> call(TableColumn<Documento, String> param) {
				TableCell<Documento, String> cell = new TableCell<Documento, String>() {

					@Override
					protected void updateItem(String arg0, boolean arg1) {
						super.updateItem(arg0, arg1);
						if (arg1) {
							setText(null);
						} else {
							setId("btn-tabla");
							setOnMouseReleased(evt -> new Alert(AlertType.INFORMATION,
									getTableView().getItems().get(getIndex()).getContenido()).show());
							setText("Ver contenido");
						}
					}
				};
				return cell;
			}
		};
	}

	/**
	 * Hace que en toda la columna, en las celdas de le asigne el titulo
	 * 
	 * @return
	 */
	public static Callback<CellDataFeatures<Documento, String>, ObservableValue<String>> obtenerCallbackTitulo() {
		return data -> new ReadOnlyStringWrapper(data.getValue().getTitulo());
	}

	/**
	 * Hace que en toda la columna, en las celdas de le asigne el codigo
	 * 
	 * @return
	 */
	public static Callback<CellDataFeatures<Documento, String>, ObservableValue<String>> obtenerCallbackCodigo() {
		return data -> new ReadOnlyStringWrapper(data.getValue().getCode());
	}

	/**
	 * Hace que en toda la columna, en las celdas de le asigne la prioridad
	 * 
	 * @return
	 */
	public static Callback<CellDataFeatures<Documento, String>, ObservableValue<String>> obtenerCallbackPrioridad() {
		return data -> new ReadOnlyStringWrapper(data.getValue().getPrioridad() + "");
	}

	/**
	 * Hace que en toda la columna, en las celdas de le asigne la fecha de
	 * agregacion
	 * 
	 * @return
	 */
	public static Callback<CellDataFeatures<Documento, String>, ObservableValue<String>> obtenerCallbackFechaAgregado() {
		return data -> new ReadOnlyStringWrapper(
				data.getValue().getFechaAgregado().format(DateTimeFormatter.ofPattern("HH:mm:ss, dd/MM/yy")));
	}

	/**
	 * Hace que en toda la columna, en las celdas de le asigne la fecha de impresion
	 * 
	 * @return
	 */
	public static Callback<CellDataFeatures<Documento, String>, ObservableValue<String>> obtenerCallbackFechaImpresion() {
		return data -> {
			LocalDateTime fechaImpresion = data.getValue().getFechaImpresion();
			return new ReadOnlyStringWrapper(
					fechaImpresion != null ? fechaImpresion.format(DateTimeFormatter.ofPattern("HH:mm:ss, dd/MM/yy"))
							: "N/A");
		};
	}

	/**
	 * Hace que en cada fila se haga una verificacion de que si el doc fue impreso
	 * para cambiar el diseño a las filas
	 * 
	 * @return
	 */
	public static Callback<TableView<Documento>, TableRow<Documento>> obtenerDisenioFilas() {
		return arg0 -> {
			return new TableRow<Documento>() {
				@Override
				protected void updateItem(Documento item, boolean empty) {
					super.updateItem(item, empty);
					if (item == null) {
						setStyle("");
						return;
					}
					if (item.fueImpreso())
						setId("tabla-true");
					else
						setId("tabla-false");

				}
			};
		};
	}

}

package co.edu.uniquindio.centroimpresion.controllers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import co.edu.uniquindio.centroimpresion.model.centro.Documento;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Alert;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.util.Callback;

public class CtrlSeeDocs {
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

	public static Callback<CellDataFeatures<Documento, String>, ObservableValue<String>> obtenerCallbackTitulo() {
		return data -> new ReadOnlyStringWrapper(data.getValue().getTitulo());
	}

	public static Callback<CellDataFeatures<Documento, String>, ObservableValue<String>> obtenerCallbackCodigo() {
		return data -> new ReadOnlyStringWrapper(data.getValue().getCode());
	}

	public static Callback<CellDataFeatures<Documento, String>, ObservableValue<String>> obtenerCallbackPrioridad() {
		return data -> new ReadOnlyStringWrapper(data.getValue().getPrioridad() + "");
	}

	public static Callback<CellDataFeatures<Documento, String>, ObservableValue<String>> obtenerCallbackFechaAgregado() {
		return data -> new ReadOnlyStringWrapper(
				data.getValue().getFechaAgregado().format(DateTimeFormatter.ofPattern("HH:mm:ss, dd/MM/yy")));
	}

	public static Callback<CellDataFeatures<Documento, String>, ObservableValue<String>> obtenerCallbackFechaImpresion() {
		return data -> {
			LocalDateTime fechaImpresion = data.getValue().getFechaImpresion();
			return new ReadOnlyStringWrapper(
					fechaImpresion != null ? fechaImpresion.format(DateTimeFormatter.ofPattern("HH:mm:ss, dd/MM/yy"))
							: "N/A");
		};
	}

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
					if (isSelected()) {
						setStyle("");
						return;
					}
					if (item.getFechaImpresion() != null)
						setStyle("-fx-background-color: #babaff;");
					else
						setStyle("");
	
				}
			};
		};
	}
}

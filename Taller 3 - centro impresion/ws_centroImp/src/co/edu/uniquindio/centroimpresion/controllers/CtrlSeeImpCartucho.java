package co.edu.uniquindio.centroimpresion.controllers;

import co.edu.uniquindio.centroimpresion.model.centro.ImpresoraCartucho;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.util.Callback;

public class CtrlSeeImpCartucho {

	public static Callback<CellDataFeatures<ImpresoraCartucho, String>, ObservableValue<String>> obtenerCallbackCode() {
		return data -> new ReadOnlyStringWrapper(data.getValue().getCode());
	}

	public static Callback<CellDataFeatures<ImpresoraCartucho, String>, ObservableValue<String>> obtenerCallbackMarca() {
		return data -> new ReadOnlyStringWrapper(data.getValue().getMarca());
	}

	public static Callback<CellDataFeatures<ImpresoraCartucho, String>, ObservableValue<String>> obtenerCallbackEsAColor() {
		return data -> new ReadOnlyStringWrapper(data.getValue().isEsAColor() ? "Si" : "No");
	}

	public static Callback<CellDataFeatures<ImpresoraCartucho, String>, ObservableValue<String>> obtenerCallbackVelocidad() {
		return data -> new ReadOnlyStringWrapper(String.format("%.2f", data.getValue().getLetrasPorSegundo()) + " l/s");
	}

	public static Callback<CellDataFeatures<ImpresoraCartucho, String>, ObservableValue<String>> obtenerCallbackCapacidad() {
		return data -> new ReadOnlyStringWrapper(String.format("%.2f", data.getValue().getCapacidadCartucho()) + " ml");
	}

	public static Callback<CellDataFeatures<ImpresoraCartucho, String>, ObservableValue<String>> obtenerCallbackNivel() {
		return data -> new ReadOnlyStringWrapper(String.format("%.2f", data.getValue().getNivelCartucho()) + " ml");
	}

	public static Callback<CellDataFeatures<ImpresoraCartucho, String>, ObservableValue<String>> obtenerCallbackDesgaste() {
		return data -> new ReadOnlyStringWrapper(String.format("%.2f", data.getValue().getDesgasteCartucho()) + " ml");
	}

	public static Callback<CellDataFeatures<ImpresoraCartucho, String>, ObservableValue<String>> obtenerCallbackEstado() {
		return data -> new ReadOnlyStringWrapper(data.getValue().getEstado().getTexto());
	}

	public static Callback<CellDataFeatures<ImpresoraCartucho, String>, ObservableValue<String>> obtenerCallbackCantidad() {
		return data -> new ReadOnlyStringWrapper(data.getValue().getPaginasImpresas() + "");
	}

	public static Callback<TableView<ImpresoraCartucho>, TableRow<ImpresoraCartucho>> obtenerDisenioFilas() {
		return arg0 -> {
			return new TableRow<ImpresoraCartucho>() {
				@Override
				protected void updateItem(ImpresoraCartucho item, boolean empty) {
					super.updateItem(item, empty);
					if (item == null) {
						setStyle("");
						return;
					}
					if (isSelected()) {
						setStyle("");
						return;
					}
					if (item.estaActiva())
						setId("tabla-true");
					else
						setId("tabla-false");

				}
			};
		};
	}
}

package co.edu.uniquindio.centroimpresion.controllers;

import co.edu.uniquindio.centroimpresion.model.Impresora;
import co.edu.uniquindio.centroimpresion.model.ImpresoraCartucho;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.util.Callback;

public class CtrlSeeImpCartucho {
	/**
	 * Hace que en toda la columna, en las celdas se le asigne el codigo
	 * 
	 * @return
	 */
	public static Callback<CellDataFeatures<ImpresoraCartucho, String>, ObservableValue<String>> obtenerCallbackCode() {
		return data -> new ReadOnlyStringWrapper(data.getValue().getCode());
	}

	/**
	 * Hace que en toda la columna, en las celdas se le asigne la marca
	 * 
	 * @return
	 */
	public static Callback<CellDataFeatures<ImpresoraCartucho, String>, ObservableValue<String>> obtenerCallbackMarca() {
		return data -> new ReadOnlyStringWrapper(data.getValue().getMarca());
	}

	/**
	 * Hace que en toda la columna, en las celdas se le asigne si es a color o no
	 * 
	 * @return
	 */
	public static Callback<CellDataFeatures<ImpresoraCartucho, String>, ObservableValue<String>> obtenerCallbackEsAColor() {
		return data -> new ReadOnlyStringWrapper(data.getValue().esAColor() ? "Si" : "No");
	}

	/**
	 * Hace que en toda la columna, en las celdas se le asigne la velocidad
	 * 
	 * @return
	 */
	public static Callback<CellDataFeatures<ImpresoraCartucho, String>, ObservableValue<String>> obtenerCallbackVelocidad() {
		return data -> new ReadOnlyStringWrapper(String.format("%.2f", data.getValue().getLetrasPorSegundo()) + " l/s");
	}

	/**
	 * Hace que en toda la columna, en las celdas se le asigne la capacidad
	 * 
	 * @return
	 */
	public static Callback<CellDataFeatures<ImpresoraCartucho, String>, ObservableValue<String>> obtenerCallbackCapacidad() {
		return data -> new ReadOnlyStringWrapper(String.format("%.2f", data.getValue().getCapacidadCartucho()) + " ml");
	}

	/**
	 * Hace que en toda la columna, en las celdas se le asigne el nivel
	 * 
	 * @return
	 */
	public static Callback<CellDataFeatures<ImpresoraCartucho, String>, ObservableValue<String>> obtenerCallbackNivel() {
		return data -> new ReadOnlyStringWrapper(String.format("%.2f", data.getValue().getNivelCartucho()) + " ml");
	}

	/**
	 * Hace que en toda la columna, en las celdas se le asigne el desgaste
	 * 
	 * @return
	 */
	public static Callback<CellDataFeatures<ImpresoraCartucho, String>, ObservableValue<String>> obtenerCallbackDesgaste() {
		return data -> new ReadOnlyStringWrapper(String.format("%.2f", data.getValue().getDesgasteCartucho()) + " ml");
	}

	/**
	 * Hace que en toda la columna, en las celdas se le asigne el estado
	 * 
	 * @return
	 */
	public static Callback<CellDataFeatures<ImpresoraCartucho, String>, ObservableValue<String>> obtenerCallbackEstado() {
		return data -> new ReadOnlyStringWrapper(data.getValue().getEstado().getTexto());
	}

	/**
	 * Hace que en toda la columna, en las celdas se le asigne la cantidad de
	 * paginas impresas
	 * 
	 * @return
	 */
	public static Callback<CellDataFeatures<ImpresoraCartucho, String>, ObservableValue<String>> obtenerCallbackCantidad() {
		return data -> new ReadOnlyStringWrapper(data.getValue().getPaginasImpresas() + "");
	}

	/**
	 * Cambia el diseño de las filas dependiendo de que si la impresora esta activa
	 * o tiene otro estado
	 * 
	 * @return
	 */
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

	/**
	 * Hace que en toda la columna, en las celdas se le asigne un boton que muestre
	 * la tabla de documentos
	 * 
	 * @param stage
	 * @param eventoVolver
	 * @return
	 */
	public static Callback<TableColumn<ImpresoraCartucho, String>, TableCell<ImpresoraCartucho, String>> obtenerCallbackDocumentos(
			Stage stage, EventHandler<? super MouseEvent> eventoVolver) {
		return new Callback<TableColumn<ImpresoraCartucho, String>, TableCell<ImpresoraCartucho, String>>() {

			final @Override public TableCell<ImpresoraCartucho, String> call(
					TableColumn<ImpresoraCartucho, String> param) {
				TableCell<ImpresoraCartucho, String> cell = new TableCell<ImpresoraCartucho, String>() {

					@Override
					protected void updateItem(String arg0, boolean arg1) {
						super.updateItem(arg0, arg1);
						if (arg1) {
							setText(null);
						} else {
							setId("btn-tabla");
							Impresora impresora = getTableView().getItems().get(getIndex());
							setOnMouseReleased(
									evt -> CtrlSeeImps.abrirDocumentosImpresora(stage, eventoVolver, impresora));
							setText("Ver Documentos");
						}
					}

				};
				return cell;
			}
		};
	}
}

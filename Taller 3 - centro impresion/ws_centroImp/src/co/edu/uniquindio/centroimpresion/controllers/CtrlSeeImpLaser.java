package co.edu.uniquindio.centroimpresion.controllers;

import co.edu.uniquindio.centroimpresion.model.centro.Impresora;
import co.edu.uniquindio.centroimpresion.model.centro.ImpresoraLaser;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Callback;

public class CtrlSeeImpLaser {

	public static Callback<CellDataFeatures<ImpresoraLaser, String>, ObservableValue<String>> obtenerCallbackCode() {
		return data -> new ReadOnlyStringWrapper(data.getValue().getCode());
	}

	public static Callback<CellDataFeatures<ImpresoraLaser, String>, ObservableValue<String>> obtenerCallbackMarca() {
		return data -> new ReadOnlyStringWrapper(data.getValue().getMarca());
	}

	public static Callback<CellDataFeatures<ImpresoraLaser, String>, ObservableValue<String>> obtenerCallbackEsAColor() {
		return data -> new ReadOnlyStringWrapper(data.getValue().esAColor() ? "Si" : "No");
	}

	public static Callback<CellDataFeatures<ImpresoraLaser, String>, ObservableValue<String>> obtenerCallbackVelocidad() {
		return data -> new ReadOnlyStringWrapper(String.format("%.2f", data.getValue().getLetrasPorSegundo()) + " l/s");
	}

	public static Callback<CellDataFeatures<ImpresoraLaser, String>, ObservableValue<String>> obtenerCallbackDuracionToner() {
		return data -> new ReadOnlyStringWrapper(data.getValue().getDuracionToner() + "");
	}

	public static Callback<CellDataFeatures<ImpresoraLaser, String>, ObservableValue<String>> obtenerCallbackNivelToner() {
		return data -> new ReadOnlyStringWrapper(data.getValue().getNivelToner() + "");
	}

	public static Callback<CellDataFeatures<ImpresoraLaser, String>, ObservableValue<String>> obtenerCallbackEstado() {
		return data -> new ReadOnlyStringWrapper(data.getValue().getEstado().getTexto());
	}

	public static Callback<CellDataFeatures<ImpresoraLaser, String>, ObservableValue<String>> obtenerCallbackCantidad() {
		return data -> new ReadOnlyStringWrapper(data.getValue().getPaginasImpresas() + "");
	}

	public static Callback<TableView<ImpresoraLaser>, TableRow<ImpresoraLaser>> obtenerDisenioFilas() {
		return arg0 -> {
			return new TableRow<ImpresoraLaser>() {
				@Override
				protected void updateItem(ImpresoraLaser item, boolean empty) {
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

	public static Callback<TableColumn<ImpresoraLaser, String>, TableCell<ImpresoraLaser, String>> obtenerCallbackDocumentos(
			Stage stage, EventHandler<? super MouseEvent> eventoVolver) {
		return new Callback<TableColumn<ImpresoraLaser, String>, TableCell<ImpresoraLaser, String>>() {

			final @Override public TableCell<ImpresoraLaser, String> call(TableColumn<ImpresoraLaser, String> param) {
				TableCell<ImpresoraLaser, String> cell = new TableCell<ImpresoraLaser, String>() {

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

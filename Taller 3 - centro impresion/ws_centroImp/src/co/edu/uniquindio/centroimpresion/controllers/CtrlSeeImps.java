package co.edu.uniquindio.centroimpresion.controllers;

import java.util.Set;

import co.edu.uniquindio.centroimpresion.application.Main;
import co.edu.uniquindio.centroimpresion.exceptions.CentroImpresionException;
import co.edu.uniquindio.centroimpresion.model.Documento;
import co.edu.uniquindio.centroimpresion.model.Impresora;
import co.edu.uniquindio.centroimpresion.view.custom.Boton;
import co.edu.uniquindio.centroimpresion.view.menu.PanelMenuSee;
import co.edu.uniquindio.centroimpresion.view.see.PanelSeeDocs;
import co.edu.uniquindio.centroimpresion.view.see.PanelSeeSelectedImp;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Callback;

public class CtrlSeeImps {
	/**
	 * Muestra la impresora seleccionada, muestra alertas en caso de que no se den
	 * las cosas correctamente
	 * 
	 * @param panelMenuSee
	 * @param eventoVolver
	 */
	public static void irAVerImpresoraSeleccionada(PanelMenuSee panelMenuSee,
			EventHandler<? super MouseEvent> eventoVolver) {
		SerializedData data = new SerializedData();
		try {
			Impresora impresora = data.getCentroImpresion().obtenerPrimerElementoImpresoraThrows();
			panelMenuSee.setCenter(new PanelSeeSelectedImp(impresora, eventoVolver));
		} catch (CentroImpresionException e) {
			new Alert(AlertType.WARNING, e.getMessage()).show();
		}

	}

	/**
	 * Hace que en toda la columna, en las celdas se le asigne el codigo
	 * 
	 * @return
	 */
	public static Callback<CellDataFeatures<Impresora, String>, ObservableValue<String>> obtenerCallbackCode() {
		return data -> new ReadOnlyStringWrapper(data.getValue().getCode());
	}

	/**
	 * Hace que en toda la columna, en las celdas se le asigne
	 * 
	 * @return
	 */
	public static Callback<CellDataFeatures<Impresora, String>, ObservableValue<String>> obtenerCallbackMarca() {
		return data -> new ReadOnlyStringWrapper(data.getValue().getMarca());
	}

	/**
	 * Hace que en toda la columna, en las celdas se le asigne si es a color o no
	 * 
	 * @return
	 */
	public static Callback<CellDataFeatures<Impresora, String>, ObservableValue<String>> obtenerCallbackEsAColor() {
		return data -> new ReadOnlyStringWrapper(data.getValue().esAColor() ? "Si" : "No");
	}

	/**
	 * Hace que en toda la columna, en las celdas se le asigne la velocidad
	 * 
	 * @return
	 */
	public static Callback<CellDataFeatures<Impresora, String>, ObservableValue<String>> obtenerCallbackVelocidad() {
		return data -> new ReadOnlyStringWrapper(String.format("%.2f", data.getValue().getLetrasPorSegundo()) + " l/s");
	}

	/**
	 * Hace que en toda la columna, en las celdas se le asigne el estado
	 * 
	 * @return
	 */
	public static Callback<CellDataFeatures<Impresora, String>, ObservableValue<String>> obtenerCallbackEstado() {
		return data -> new ReadOnlyStringWrapper(data.getValue().getEstado().getTexto());
	}

	/**
	 * Hace que en toda la columna, en las celdas se le asigne la cantidad de
	 * paginas imrpesas
	 * 
	 * @return
	 */
	public static Callback<CellDataFeatures<Impresora, String>, ObservableValue<String>> obtenerCallbackCantidad() {
		return data -> new ReadOnlyStringWrapper(data.getValue().getPaginasImpresas() + "");
	}

	/**
	 * Cambia el diseño de las filas dependiendo de que si la impresora esta activa
	 * o tiene cualquier otro estado
	 * 
	 * @return
	 */
	public static Callback<TableView<Impresora>, TableRow<Impresora>> obtenerDisenioFilas() {
		return arg0 -> {
			return new TableRow<Impresora>() {
				@Override
				protected void updateItem(Impresora item, boolean empty) {
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
	public static Callback<TableColumn<Impresora, String>, TableCell<Impresora, String>> obtenerCallbackDocumentos(
			Stage stage, EventHandler<? super MouseEvent> eventoVolver) {
		return new Callback<TableColumn<Impresora, String>, TableCell<Impresora, String>>() {

			final @Override public TableCell<Impresora, String> call(TableColumn<Impresora, String> param) {
				TableCell<Impresora, String> cell = new TableCell<Impresora, String>() {

					@Override
					protected void updateItem(String arg0, boolean arg1) {
						super.updateItem(arg0, arg1);
						if (arg1) {
							setText(null);
						} else {
							setId("btn-tabla");
							Impresora impresora = getTableView().getItems().get(getIndex());
							setOnMouseReleased(evt -> abrirDocumentosImpresora(stage, eventoVolver, impresora));
							setText("Ver Documentos");
						}
					}

				};
				return cell;
			}
		};
	}

	static void abrirDocumentosImpresora(Stage stage, EventHandler<? super MouseEvent> eventoVolver,
			Impresora impresora) {
		Set<Documento> listaDocumentos = impresora.getListaDocumentos();
		BorderPane borderPane = new BorderPane(new PanelSeeDocs(listaDocumentos));
		borderPane.setBottom(new Boton("Volver", eventoVolver, "btn-volver"));
		Scene escena = new Scene(borderPane, 800, 400);
		escena.getStylesheets().add(Main.css.toExternalForm());
		stage.setScene(escena);
	}
}

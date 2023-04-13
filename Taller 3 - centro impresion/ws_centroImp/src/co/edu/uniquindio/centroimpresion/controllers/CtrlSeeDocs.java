package co.edu.uniquindio.centroimpresion.controllers;

import co.edu.uniquindio.centroimpresion.model.centro.Documento;
import javafx.scene.control.Alert;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.Alert.AlertType;
import javafx.util.Callback;

public class CtrlSeeDocs {
	public static Callback<TableColumn<Documento, String>, TableCell<Documento, String>> obtenerValoresContenido() {
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
}

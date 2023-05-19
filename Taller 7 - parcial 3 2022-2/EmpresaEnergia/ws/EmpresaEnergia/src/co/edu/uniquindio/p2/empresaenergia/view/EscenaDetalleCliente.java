package co.edu.uniquindio.p2.empresaenergia.view;

import java.io.IOException;

import co.edu.uniquindio.p2.empresaenergia.controllers.DetalleClienteController;
import co.edu.uniquindio.p2.empresaenergia.model.Cliente;
import co.edu.uniquindio.p2.empresaenergia.model.ClienteJuridico;
import co.edu.uniquindio.p2.empresaenergia.model.ClienteNatural;
import co.edu.uniquindio.p2.empresaenergia.model.TipoCliente;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

public class EscenaDetalleCliente extends Scene {

	public EscenaDetalleCliente(Cliente cliente) throws IOException {
		super(new BorderPane(), 600, 400);
		DetalleClienteController controller = new DetalleClienteController();

		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("DetalleCliente.fxml"));
		loader.setController(controller);

		BorderPane load = (BorderPane) loader.load();

		getStylesheets().add(getClass().getResource("application.css").toExternalForm());

		TipoCliente tipoCliente = cliente.getTipoCliente();
		controller.getLblIdentificacion().setText(cliente.getId());
		controller.getLblNombre().setText(cliente.getNombre());
		switch (tipoCliente) {
		case JURIDICO:
			ClienteJuridico clienteJuridico = (ClienteJuridico) cliente;
			controller.getLblAskIdentificacion().setText("NIT del cliente:");
			controller.getLblAskInfoSecundaria1().setText("Telefono del cliente:");
			controller.getLblAskInfoSecundaria2().setText("Tipo de empresa:");
			controller.getLblInfoSecundaria1().setText(clienteJuridico.getTelefono());
			controller.getLblInfoSecundaria2().setText(clienteJuridico.getTipoEmpresa());
			break;
		case NATURAL:
			ClienteNatural clienteNatural = (ClienteNatural) cliente;
			controller.getLblAskIdentificacion().setText("Cedula del cliente:");
			controller.getLblAskInfoSecundaria1().setText("Apellidos del cliente:");
			controller.getLblAskInfoSecundaria2().setText("Estrato del cliente:");
			controller.getLblInfoSecundaria1().setText(clienteNatural.getApellidos());
			controller.getLblInfoSecundaria2().setText(clienteNatural.getEstrato() + "");
			break;
		}
		setRoot(load);
	}

}

package ventanas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import customized.BotonListener;
import customized.CustomBoton;
import customized.CustomLabel;
import customized.CustomTextfield;
import customized.Herramientas;
import customized.ParteSuperior;
import threads.HiloEsperarTime;

public class VentanaBuscarAlumno extends ParteSuperior implements BotonListener {

	private CustomLabel labelBuscar, labelInferior;
	private CustomTextfield tfBuscar;
	private CustomBoton botonBuscar, botonVolver;
	private VentanaPrincipal ventanaPrincipal;

	public VentanaBuscarAlumno(VentanaPrincipal ventanaPrincipal) {
		setStellarVisible(false);

		setVentanaPrincipal(ventanaPrincipal);

		setLabelBuscar(new CustomLabel("Buscar:"));
		setLabelInferior(new CustomLabel("Acción relizada"));
		setTfBuscar(new CustomTextfield("Escribe un ID"));

		setBotonBuscar(new CustomBoton("Buscar"));
		setBotonVolver(new CustomBoton("Volver"));

		getBotonBuscar().addBotonListener(this);
		getBotonVolver().addBotonListener(this);

		getLabelBuscar().setBounds(posXCentrada(400), 60, 300, 50);
		getLabelInferior().setBounds(10, 250, getWidth() - 250, 50);

		getTfBuscar().setBounds(posXCentrada(400), 100, 400, 35);

		getBotonBuscar().setBounds(getWidth() - 220, 150, 200, 50);
		getBotonVolver().setBounds(getWidth() - 220, 220, 200, 50);

		getTfBuscar().setFontSize(25f);

		getLabelInferior().setHorizontalAlignment(4);
		getLabelInferior().setVisible(false);

		agregar(getBotonBuscar());
		agregar(getBotonVolver());
		agregar(getLabelBuscar());
		agregar(getLabelInferior());
		agregar(getTfBuscar());
	}

	@Override
	public void conFigurarVentana() {
		setSize(500, 290);
		cambiarTitulo("Buscar Alumno", getFont());
	}

	@Override
	public void cambioDeColor() {
		getTfBuscar().actualizarColor(col);
		getLabelInferior().actualizarColor(col);
		getLabelBuscar().actualizarColor(col);
		getBotonBuscar().actualizarColor(col);
		getBotonVolver().actualizarColor(col);
	}

	@Override
	public void cambiarMaximizado(boolean esGrande) {

	}

	@Override
	public void BotonPresionado(CustomBoton boton) {
		if (boton.equals(getBotonBuscar())) {
			getLabelInferior().setText("Acción realizada");
			String txtBuscar = getTfBuscar().getText();
			if (txtBuscar.equals("Escribe un ID") || txtBuscar.equals("")) {
				getLabelInferior().setText("Acción no realizada");
			}
			if (getLabelInferior().getText().equals("Acción realizada")) {
				try {
					Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/base_odiadora", "root", "");
					PreparedStatement pst = c.prepareStatement("SELECT * FROM alumnos WHERE ID = ?");
					pst.setString(1, txtBuscar.trim());
					ResultSet rs = pst.executeQuery();
					if (rs.next()) {
						String mensaje = "<html>Nombre: " + rs.getString("NAME") + "<br>Grupo: " + rs.getString("GRUPO")+"</html>";
						Herramientas.abrirPopUp("Informacion", mensaje);
					} else
						getLabelInferior().setText("No se encontraron datos");

				} catch (SQLException e) {
					e.printStackTrace();
					getLabelInferior().setText("Error en la base");
				}
			}
			new HiloEsperarTime(getLabelInferior(), 1).start();
		}

	}

	public VentanaPrincipal getVentanaPrincipal() {
		return ventanaPrincipal;
	}

	public void setVentanaPrincipal(VentanaPrincipal ventanaPrincipal) {
		this.ventanaPrincipal = ventanaPrincipal;
	}

	public CustomLabel getLabelBuscar() {
		return labelBuscar;
	}

	public void setLabelBuscar(CustomLabel labelBuscar) {
		this.labelBuscar = labelBuscar;
	}

	public CustomLabel getLabelInferior() {
		return labelInferior;
	}

	public void setLabelInferior(CustomLabel labelInferior) {
		this.labelInferior = labelInferior;
	}

	public CustomBoton getBotonVolver() {
		return botonVolver;
	}

	public void setBotonVolver(CustomBoton botonVolver) {
		this.botonVolver = botonVolver;
	}

	public CustomBoton getBotonBuscar() {
		return botonBuscar;
	}

	public void setBotonBuscar(CustomBoton botonBuscar) {
		this.botonBuscar = botonBuscar;
	}

	public CustomTextfield getTfBuscar() {
		return tfBuscar;
	}

	public void setTfBuscar(CustomTextfield tfBuscar) {
		this.tfBuscar = tfBuscar;
	}

}

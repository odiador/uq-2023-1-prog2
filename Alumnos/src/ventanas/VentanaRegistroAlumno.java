package ventanas;

import customized.BotonListener;
import customized.CustomBoton;
import customized.CustomLabel;
import customized.CustomTextfield;
import customized.Herramientas;
import customized.ParteSuperior;
import threads.HiloEsperarTime;

import java.sql.*;

import javax.swing.SwingConstants;

public class VentanaRegistroAlumno extends ParteSuperior implements BotonListener {

	private CustomLabel labelNombre, labelGrupo, labelInferior;
	private CustomTextfield tfNombre, tfGrupo;

	private CustomBoton botonRegistrar, botonBorrar, botonModificar, botonVolver;
	private VentanaPrincipal ventanaPrincipal;

	public VentanaRegistroAlumno(VentanaPrincipal ventanaPrincipal) {
		setRedimensionable(false);
		setStellarVisible(false);
		setVentanaPrincipal(ventanaPrincipal);

		setBotonRegistrar(new CustomBoton("Registrar"));
		setBotonBorrar(new CustomBoton("Borrar"));
		setBotonModificar(new CustomBoton("Modificar"));
		setBotonVolver(new CustomBoton("Volver"));

		setLabelNombre(new CustomLabel("Nombre:"));
		setLabelGrupo(new CustomLabel("Grupo:"));
		setLabelInferior(new CustomLabel(""));

		setTfNombre(new CustomTextfield("Escribe un nombre"));
		setTfGrupo(new CustomTextfield("Escribe un grupo"));

		getBotonRegistrar().addBotonListener(this);
		getBotonModificar().addBotonListener(this);
		getBotonBorrar().addBotonListener(this);
		getBotonVolver().addBotonListener(this);

		getBotonRegistrar().setBounds(posXCentrada(640), 220, 200, 50);
		getBotonModificar().setBounds(posXCentrada(640) + 220, 220, 200, 50);
		getBotonBorrar().setBounds(posXCentrada(640) + 440, 220, 200, 50);
		getBotonVolver().setBounds(getWidth() - 220, 300, 200, 50);

		getTfNombre().setBounds(posXCentrada(820), 150, 400, 35);
		getTfGrupo().setBounds(posXCentrada(820) + 420, 150, 400, 35);

		getLabelNombre().setBounds(posXCentrada(820), 100, 200, 35);
		getLabelGrupo().setBounds(posXCentrada(820) + 420, 100, 200, 35);
		getLabelInferior().setBounds(10, 300, getWidth() - 250, 50);

		getLabelNombre().setFontSize(25f);
		getTfNombre().setFontSize(25f);
		getLabelGrupo().setFontSize(25f);
		getTfGrupo().setFontSize(25f);

		getLabelInferior().setHorizontalAlignment(4);
		getLabelInferior().setVisible(false);

		agregar(getBotonRegistrar());
		agregar(getBotonModificar());
		agregar(getBotonBorrar());
		agregar(getBotonVolver());

		agregar(getLabelNombre());
		agregar(getLabelGrupo());
		agregar(getLabelInferior());

		agregar(getTfNombre());
		agregar(getTfGrupo());
	}

	@Override
	public void conFigurarVentana() {
		setSize(900, 370);
		cambiarTitulo("Registrar alumno", Herramientas.FUENTE_TITULO_DEFAULT);

	}

	@Override
	public void cambioDeColor() {
		getBotonBorrar().actualizarColor(col);
		getBotonModificar().actualizarColor(col);
		getBotonRegistrar().actualizarColor(col);
		getBotonVolver().actualizarColor(col);

		getLabelNombre().actualizarColor(col);
		getLabelGrupo().actualizarColor(col);
		getLabelInferior().actualizarColor(col);

		getTfNombre().actualizarColor(col);
		getTfGrupo().actualizarColor(col);
	}

	public void vaciar() {
		getTfNombre().setText("Escribe un nombre");
		getTfGrupo().setText("Escribe un grupo");
		getbNavegacion().requestFocus();
	}

	public void BotonPresionado(CustomBoton boton) {
		if (boton.equals(getBotonVolver())) {
			ventanaPrincipal.actualizarColores();
			ventanaPrincipal.setVisible(true);
			setVisible(false);
		}
		if (boton.equals(getBotonBorrar())) {
			vaciar();
		}
		if (boton.equals(getBotonRegistrar())) {
			getLabelInferior().setText("Acción realizada");

			String txtNombre = getTfNombre().getText();
			String txtGrupo = getTfGrupo().getText();

			if (txtGrupo.equals("") || txtGrupo.equals("Escribe un grupo")) {
				getLabelInferior().setText("Acción no realizada: Escribe un grupo");
			}

			if (txtNombre.equals("") || txtNombre.equals("Escribe un nombre")) {
				getLabelInferior().setText("Acción no realizada: Escribe un nombre");
			}

			if (getLabelInferior().getText().equals("Acción realizada")) {
				try {
					Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/base_odiadora", "root", "");
					PreparedStatement pst = c.prepareStatement("insert into alumnos values(?,?,?)");
					pst.setString(1, "0");
					pst.setString(2, txtNombre.trim());
					pst.setString(3, txtGrupo.trim());
					pst.executeUpdate();
					vaciar();

				} catch (SQLException e) {
					e.printStackTrace();
					getLabelInferior().setText("Error en la base");
				}
			}
			new HiloEsperarTime(getLabelInferior(), 1.5f).start();
		}
		if (boton.equals(getBotonModificar())) {

		}
	}

	@Override
	public void cambiarMaximizado(boolean esGrande) {
	}

	public CustomTextfield getTfNombre() {
		return tfNombre;
	}

	public void setTfNombre(CustomTextfield tfNombre) {
		this.tfNombre = tfNombre;
	}

	public CustomLabel getLabelNombre() {
		return labelNombre;
	}

	public void setLabelNombre(CustomLabel labelNombre) {
		this.labelNombre = labelNombre;
	}

	public CustomLabel getLabelGrupo() {
		return labelGrupo;
	}

	public void setLabelGrupo(CustomLabel labelGrupo) {
		this.labelGrupo = labelGrupo;
	}

	public CustomBoton getBotonBorrar() {
		return botonBorrar;
	}

	public void setBotonBorrar(CustomBoton botonBorrar) {
		this.botonBorrar = botonBorrar;
	}

	public CustomBoton getBotonRegistrar() {
		return botonRegistrar;
	}

	public void setBotonRegistrar(CustomBoton botonRegistrar) {
		this.botonRegistrar = botonRegistrar;
	}

	public CustomBoton getBotonModificar() {
		return botonModificar;
	}

	public void setBotonModificar(CustomBoton botonModificar) {
		this.botonModificar = botonModificar;
	}

	public CustomTextfield getTfGrupo() {
		return tfGrupo;
	}

	public void setTfGrupo(CustomTextfield tfGrupo) {
		this.tfGrupo = tfGrupo;
	}

	public VentanaPrincipal getVentanaPrincipal() {
		return ventanaPrincipal;
	}

	public void setVentanaPrincipal(VentanaPrincipal ventanaPrincipal) {
		this.ventanaPrincipal = ventanaPrincipal;
	}

	public CustomBoton getBotonVolver() {
		return botonVolver;
	}

	public void setBotonVolver(CustomBoton botonVolver) {
		this.botonVolver = botonVolver;
	}

	public CustomLabel getLabelInferior() {
		return labelInferior;
	}

	public void setLabelInferior(CustomLabel labelConfirmar) {
		this.labelInferior = labelConfirmar;
	}
}

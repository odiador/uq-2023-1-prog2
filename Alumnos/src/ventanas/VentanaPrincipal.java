package ventanas;

import customized.BotonListener;
import customized.CustomBoton;
import customized.ParteSuperior;

public class VentanaPrincipal extends ParteSuperior implements BotonListener {

	private CustomBoton botonRegistrar, botonBuscar;

	public VentanaPrincipal() {
		setResizable(false);
		setStellarVisible(false);

		setBotonRegistrar(new CustomBoton("Registrar alumno"));
		setBotonBuscar(new CustomBoton("Buscar alumno"));

		getBotonRegistrar().setBounds(100, 100, 300, 50);
		getBotonBuscar().setBounds(100, 200, 300, 50);

		getBotonRegistrar().setFontSize(30f);
		getBotonBuscar().setFontSize(30f);

		getBotonRegistrar().addBotonListener(this);
		getBotonBuscar().addBotonListener(this);

		agregar(getBotonBuscar());
		agregar(getBotonRegistrar());
	}

	public void conFigurarVentana() {
		setSize(800, 400);
		cambiarTitulo("Ventana Principal", getFont());
	}

	@Override
	public void cambioDeColor() {
		getBotonBuscar().actualizarColor(col);
		getBotonRegistrar().actualizarColor(col);
	}

	@Override
	public void cambiarMaximizado(boolean esGrande) {
		// TODO Auto-generated method stub

	}

	public static void main(String[] args) {
		new VentanaPrincipal().setVisible(true);

	}

	public CustomBoton getBotonRegistrar() {
		return botonRegistrar;
	}

	public void setBotonRegistrar(CustomBoton botonRegistrar) {
		this.botonRegistrar = botonRegistrar;
	}

	public CustomBoton getBotonBuscar() {
		return botonBuscar;
	}

	public void setBotonBuscar(CustomBoton botonBuscar) {
		this.botonBuscar = botonBuscar;
	}

	public void BotonPresionado(CustomBoton customComboBox) {
		if (customComboBox.equals(getBotonBuscar())) {
			VentanaBuscarAlumno ventana = new VentanaBuscarAlumno(this);
			ventana.actualizarColores();
			ventana.setVisible(true);
			setVisible(false);
		}
		if (customComboBox.equals(getBotonRegistrar())) {
			VentanaRegistroAlumno ventana = new VentanaRegistroAlumno(this);
			ventana.actualizarColores();
			ventana.setVisible(true);
			setVisible(false);
		}
	}
}

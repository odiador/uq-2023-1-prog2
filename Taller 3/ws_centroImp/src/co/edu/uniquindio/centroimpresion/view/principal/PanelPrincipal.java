package co.edu.uniquindio.centroimpresion.view.principal;

import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

public class PanelPrincipal extends BorderPane implements TabComunicationListener {

	private PanelPrincipalIzq panelIzq;
	private BorderPane tabPane;
	private Label label;
	private String nombre;
	private OpcionPrincipal[] opciones;

	/**
	 * Es el constructor del Panel principal, este contiene su panel izquierdo y
	 * su panel de pestañas
	 *
	 * @param nombre
	 *            es el nombre de perfil del empleado
	 * @param opciones
	 *            son las opciones que tiene el empleado de elegir
	 */
	public PanelPrincipal(String nombre, OpcionPrincipal[] opciones) {
		this.nombre = nombre;
		this.opciones = opciones;

		initComp();
	}

	/**
	 * Inicializa los componentes del panel principal y directamente también los
	 * agrega
	 */
	public void initComp() {
		panelIzq = new PanelPrincipalIzq(nombre, opciones);
		label = new Label("Elige un boton");
		tabPane = new BorderPane(label);
		setLeft(panelIzq);
		panelIzq.addTabComunicationListener(this);
		setCenter(tabPane);
	}

	@Override
	public void movementPerformed(OpcionPrincipal source) {
		label.setText(source.getText());
	}

	public PanelPrincipalIzq getPanelIzq() {
		return panelIzq;
	}

	public void setPanelIzq(PanelPrincipalIzq panelIzq) {
		this.panelIzq = panelIzq;
	}

	public BorderPane getTabPane() {
		return tabPane;
	}

	public void setTabPane(BorderPane tabPane) {
		this.tabPane = tabPane;
	}

	public Label getLabel() {
		return label;
	}

	public void setLabel(Label label) {
		this.label = label;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public OpcionPrincipal[] getOpciones() {
		return opciones;
	}

	public void setOpciones(OpcionPrincipal[] opciones) {
		this.opciones = opciones;
	}

}

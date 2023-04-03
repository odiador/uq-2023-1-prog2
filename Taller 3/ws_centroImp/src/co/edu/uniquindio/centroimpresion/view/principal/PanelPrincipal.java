package co.edu.uniquindio.centroimpresion.view.principal;

import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

public class PanelPrincipal extends BorderPane implements TabComunicationListener {

	private PanelPrincipalIzq panelIzq;
	private TabPanelPrincipal tabPane;
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
		tabPane = new TabPanelPrincipal(opciones);
		setLeft(panelIzq);
		panelIzq.addTabComunicationListener(this);
		setCenter(tabPane);
	}

	@Override
	public void movementPerformed(OpcionPrincipal source) {
		tabPane.updateView(source);
	}

	public PanelPrincipalIzq getPanelIzq() {
		return panelIzq;
	}

	public void setPanelIzq(PanelPrincipalIzq panelIzq) {
		this.panelIzq = panelIzq;
	}

	public TabPanelPrincipal getTabPane() {
		return tabPane;
	}

	public void setTabPane(TabPanelPrincipal tabPane) {
		this.tabPane = tabPane;
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

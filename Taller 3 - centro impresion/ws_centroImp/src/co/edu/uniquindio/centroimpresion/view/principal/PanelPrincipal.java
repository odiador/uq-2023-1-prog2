package co.edu.uniquindio.centroimpresion.view.principal;

import co.edu.uniquindio.centroimpresion.model.centro.TipoAccion;
import co.edu.uniquindio.centroimpresion.model.centro.TipoEmpleado;
import javafx.scene.layout.BorderPane;

public class PanelPrincipal extends BorderPane implements TabComunicationListener {

	private PanelPrincipalIzq panelIzq;
	private TabPanelPrincipal tabPane;
	private String nombre;
	private TipoEmpleado tipoEmpleado;

	/**
	 * Es el constructor del Panel principal, este contiene su panel izquierdo y
	 * su panel de pestañas
	 *
	 * @param nombre
	 *            es el nombre de perfil del empleado
	 * @param opciones
	 *            son las opciones que tiene el empleado de elegir
	 */
	public PanelPrincipal(String nombre, TipoEmpleado tipoEmpleado) {
		this.nombre = nombre;
		this.tipoEmpleado = tipoEmpleado;

		initComp();
	}

	/**
	 * Inicializa los componentes del panel principal y directamente también los
	 * agrega
	 */
	public void initComp() {
		panelIzq = new PanelPrincipalIzq(nombre, tipoEmpleado);
		tabPane = new TabPanelPrincipal(tipoEmpleado);
		setLeft(panelIzq);
		panelIzq.addTabComunicationListener(this);
		setCenter(tabPane);
	}

	@Override
	public void movementPerformed(TipoAccion source) {
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

	public TipoEmpleado getOpciones() {
		return tipoEmpleado;
	}

	public void setOpciones(TipoEmpleado tipoEmpleado) {
		this.tipoEmpleado = tipoEmpleado;
	}

}

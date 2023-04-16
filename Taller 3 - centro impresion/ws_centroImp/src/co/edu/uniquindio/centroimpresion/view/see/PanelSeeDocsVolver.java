package co.edu.uniquindio.centroimpresion.view.see;

import java.util.Collection;

import co.edu.uniquindio.centroimpresion.model.centro.Documento;
import co.edu.uniquindio.centroimpresion.view.custom.PanelConVolver;
import co.edu.uniquindio.centroimpresion.view.menu.PanelMenuSee;

public class PanelSeeDocsVolver extends PanelConVolver {
	private PanelMenuSee panel;
	private boolean verImpresos;
	private Collection<Documento> listaDocs = null;

	public PanelSeeDocsVolver(PanelMenuSee panelMenuSee) {
		this(panelMenuSee, false);
	}

	public PanelSeeDocsVolver(PanelMenuSee panelMenuSee, boolean verImpresos) {
		this(panelMenuSee, verImpresos, null);
	}

	public PanelSeeDocsVolver(PanelMenuSee panelMenuSee, Collection<Documento> listaDocs) {
		this(panelMenuSee, false, listaDocs);
	}

	private PanelSeeDocsVolver(PanelMenuSee panelMenuSee, boolean verImpresos, Collection<Documento> listaDocs) {
		this.panel = panelMenuSee;
		this.verImpresos = verImpresos;
		this.listaDocs = listaDocs;
		initComp();
	}

	public void initComp() {
		super.initComp();
		setCenter(new PanelSeeDocs(verImpresos, listaDocs));
	}

	@Override
	public void volverPresionado() {
		panel.initComponents();
	}

}

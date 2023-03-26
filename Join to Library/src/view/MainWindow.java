package view;

import view.custom.Constants;

public class MainWindow extends Template {

	public MainWindow() {
		setBodyBg(Constants.defaultBgColor);
		addToBody(new MainPanel(this));
	}

	@Override
	public void configSize () {
		setSize(1000, 600);
	}

	public static void openView () {
		new MainWindow().setVisible(true);
	}
}

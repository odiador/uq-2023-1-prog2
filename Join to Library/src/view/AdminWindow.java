package view;

import static controller.AdminController.goToMain;

import view.custom.Constants;

public class AdminWindow extends Template {

	public static void main (String[] args) {
		new AdminWindow().setVisible(true);
	}

	public AdminWindow() {
		setBodyBg(Constants.defaultBgColor);
		goToMain(this);
	}

	@Override
	public void configSize () {
		setSize(1000, 700);

	}

	public static void openView () {
		new AdminWindow().setVisible(true);
	}

}

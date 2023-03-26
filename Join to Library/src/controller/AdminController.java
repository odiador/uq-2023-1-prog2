package controller;

import view.AddBookPanel;
import view.AddStudentPanel;
import view.AdminWindow;
import view.BorrowBookPanel;
import view.MainAdminPanel;

public class AdminController {

	public static void goToMain (AdminWindow frame) {
		frame.getPanelBody().removeAll();
		frame.addToBody(new MainAdminPanel(frame));
		frame.revalidate();
	}

	public static void goToAddStudent (AdminWindow frame) {
		frame.getPanelBody().removeAll();
		frame.addToBody(new AddStudentPanel(frame));
		frame.revalidate();
	}

	public static void goToAddBook (AdminWindow frame) {
		frame.getPanelBody().removeAll();
		frame.addToBody(new AddBookPanel(frame));
		frame.revalidate();
	}

	public static void goToBorrowBook (AdminWindow frame) {
		frame.getPanelBody().removeAll();
		frame.addToBody(new BorrowBookPanel(frame));
		frame.revalidate();
	}

}

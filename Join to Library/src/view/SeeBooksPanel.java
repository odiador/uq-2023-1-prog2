package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import controller.MainController;
import model.Student;
import view.custom.Panel;
import view.custom.Text;
import view.custom.Utilities;

public class SeeBooksPanel extends Panel {

	public SeeBooksPanel(MainWindow frame, Student student) {
		Text backBtn = new Text("Volver");

		backBtn.setPreferredSize(300, 40);
		backBtn.configAsButton(e -> MainController.goToShowInfo(student, frame));

		int tam = student.getBorrowedBooks().size();
		int width = Utilities.conseguirNumeroTabla(tam);
		int height = tam / width;

		Panel centralPanel = new Panel();

		centralPanel.setLayout(new GridLayout(height, width));
		for (int i = 0; i < tam; i++) centralPanel.add(new BookPanel(student.getBorrowedBooks().get(i)));

		add(centralPanel);
		add(backBtn, BorderLayout.SOUTH);
	}
}
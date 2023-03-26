package view;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.SwingConstants;

import controller.AdminController;
import view.custom.Panel;
import view.custom.Text;

public class MainAdminPanel extends Panel {

	public MainAdminPanel(AdminWindow frame) {
		Text title = new Text("Herramientas de administración");
		Text addStudentBtn = new Text("Agregar Estudiante");
		Text addBookBtn = new Text("Agregar Libro");
		Text borrowBookBtn = new Text("Prestar Libro");

		title.setPreferredSize(100, 60);
		addStudentBtn.setPreferredSize(300, 40);
		addBookBtn.setPreferredSize(300, 40);
		borrowBookBtn.setPreferredSize(300, 40);

		title.setVerticalAlignment(SwingConstants.BOTTOM);

		addStudentBtn.configAsButton(e -> AdminController.goToAddStudent(frame));
		addBookBtn.configAsButton(e -> AdminController.goToAddBook(frame));
		borrowBookBtn.configAsButton(e -> AdminController.goToBorrowBook(frame));

		Panel centralPanel = new Panel();
		centralPanel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(0, 5, 5, 0);
		gbc.fill = GridBagConstraints.BOTH;

		gbc.gridx = 0;
		gbc.gridy = 0;
		centralPanel.add(addStudentBtn, gbc);

		gbc.gridx++;
		centralPanel.add(addBookBtn, gbc);

		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 2;
		centralPanel.add(borrowBookBtn, gbc);

		add(title, BorderLayout.NORTH);
		add(centralPanel);
	}
}

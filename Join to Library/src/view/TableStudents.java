package view;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import controller.AdminController;
import model.Student;
import view.custom.CustomLineBorder;
import view.custom.Panel;
import view.custom.TableText;
import view.custom.Text;

public class TableStudents extends Panel {

	public TableStudents(AdminWindow frame, Student student) {
		Text backBtn = new Text("Volver");

		backBtn.setPreferredSize(300, 40);
		backBtn.configAsButton(e -> AdminController.goToMain(frame));

		Panel centralPanel = new Panel();

		centralPanel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.insets = new Insets(0, 5, 5, 0);
		gbc.weightx = 1;
		gbc.gridx = 0;
		gbc.gridy = 0;

		String headerTableNames[] = "Id-Nombre-Autor".split("-");
		for (int i = 0; i < headerTableNames.length; i++) {
			TableText header = new TableText(headerTableNames[i], CustomLineBorder.TODOS);
			header.setBackground(Text.brighter);

			gbc.gridx++;
		}

		add(centralPanel);
		add(backBtn, BorderLayout.SOUTH);
	}
}
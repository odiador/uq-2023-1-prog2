package view;

import static javax.swing.JOptionPane.showMessageDialog;
import static model.DataBaseMethods.addStudent;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.SwingConstants;

import controller.AdminController;
import model.Student;
import view.custom.ClicListener;
import view.custom.CustomException;
import view.custom.Evento;
import view.custom.NumberTextField;
import view.custom.Panel;
import view.custom.Text;
import view.custom.TextField;

public class AddStudentPanel extends Panel implements ClicListener {

	private NumberTextField tfCC;
	private TextField tfName;
	private TextField tfMail;
	private TextField tfProgram;
	private NumberTextField tfSemester;
	private Text lblWriteCC;
	private Text lblWriteSemester;
	private Text goBackBtn;
	private Text acceptBtn;
	private AdminWindow frame;

	public AddStudentPanel(AdminWindow frame) {
		setFrame(frame);

		lblWriteCC = new Text("Escribe la cédula de la persona:", SwingConstants.LEFT);
		tfCC = new NumberTextField(99999999999d);
		tfName = new TextField("Escribe el nombre del estudiante: ");
		tfMail = new TextField("Escribe el correo del estudiante: ");
		tfProgram = new TextField("Escribe el programa del estudiante: ");
		lblWriteSemester = new Text("Escribe el numero de semestre:", SwingConstants.LEFT);
		tfSemester = new NumberTextField(99);

		acceptBtn = new Text("Aceptar");
		goBackBtn = new Text("Volver");

		lblWriteCC.setPreferredSize(500, 40);
		tfCC.setPreferredSize(500, 40);
		tfName.setPreferredSize(500, 40);
		tfMail.setPreferredSize(500, 40);
		tfProgram.setPreferredSize(500, 40);
		lblWriteSemester.setPreferredSize(400, 40);
		tfSemester.setPreferredSize(100, 40);

		acceptBtn.setPreferredSize(300, 40);
		goBackBtn.setPreferredSize(300, 40);

		goBackBtn.configAsButton(this);
		acceptBtn.configAsButton(this);

		Panel southPanel = new Panel();

		southPanel.setLayout(new GridLayout(1, 2));
		southPanel.add(goBackBtn);
		southPanel.add(acceptBtn);

		Panel centralPanel = new Panel();
		centralPanel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(0, 5, 5, 0);
		gbc.fill = GridBagConstraints.BOTH;

		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 2;
		centralPanel.add(lblWriteCC, gbc);

		gbc.gridy++;
		centralPanel.add(tfCC, gbc);

		gbc.gridy++;
		centralPanel.add(tfName, gbc);

		gbc.gridy++;
		centralPanel.add(tfMail, gbc);

		gbc.gridy++;
		centralPanel.add(tfProgram, gbc);

		gbc.gridy++;
		gbc.gridwidth = 1;
		centralPanel.add(lblWriteSemester, gbc);

		gbc.gridx++;
		centralPanel.add(tfSemester, gbc);

		add(centralPanel);
		add(southPanel, BorderLayout.SOUTH);

	}

	@Override
	public void botonPresionado (Evento e) {
		if (e.getSource() == acceptBtn) {
			try {
				addStudent(new Student(tfCC.getText(), tfName.getText(), tfMail.getText(), tfProgram.getText(),
						tfSemester.getIntNumber()));
				showMessageDialog(null, "El estudiante ha sido agregado");
			} catch (CustomException e1) {
				showMessageDialog(null, e1.getCausa());
			}

		}
		if (e.getSource() == goBackBtn) AdminController.goToMain(getFrame());
	}

	public AdminWindow getFrame () {
		return frame;
	}

	public void setFrame (AdminWindow frame) {
		this.frame = frame;
	}
}

package view;

import static controller.MainController.goToMain;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.SwingConstants;

import controller.MainController;
import model.Student;
import view.custom.Panel;
import view.custom.Text;

public class StudentInfoPanel extends Panel {

	private Student student;

	public StudentInfoPanel(MainWindow frame, Student student) {
		setStudent(student);
		Text lblWelcome = new Text("¡Hola " + getStudent().getName() + " un gusto verte!");
		Text lblCC = new Text("Cédula: " + getStudent().getCC());
		Text lblMail = new Text("Correo: " + getStudent().getMail());
		Text lblProgram = new Text("<html><center>Programa cursando actualmente: " + getStudent().getProgram()
				+ " en el semestre número " + getStudent().getSemester() + "</center></html>");
		Text backBtn = new Text("Volver");
		int amountOfBooks = getStudent().getBorrowedBooks().size();

		Text lblCantBooks = new Text("Actualmente cuentas con " + amountOfBooks + " "
				+ (amountOfBooks == 1 ? "libro prestado" : "libros prestados"));
		Text seeBooksBtn = new Text(amountOfBooks == 1 ? "Ver libro" : "Ver libros");

		lblWelcome.setFont(lblWelcome.getFont().deriveFont(40f));

		lblWelcome.setPreferredSize(300, 60);
		lblCC.setPreferredSize(300, 40);
		lblMail.setPreferredSize(300, 40);
		lblProgram.setPreferredSize(300, 40);
		backBtn.setPreferredSize(300, 40);
		seeBooksBtn.setPreferredSize(300, 40);

		lblWelcome.setVerticalAlignment(SwingConstants.BOTTOM);

		backBtn.configAsButton(e -> goToMain(frame));
		seeBooksBtn.configAsButton(e -> MainController.goToSeeBooks(frame, student));

		setLayout(new BorderLayout());

		Panel centralPanel = new Panel();
		centralPanel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(0, 5, 5, 0);
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1;

		gbc.gridx = 0;
		gbc.gridy = 0;
		centralPanel.add(lblCC, gbc);

		gbc.gridy++;
		centralPanel.add(lblMail, gbc);

		gbc.gridy++;
		centralPanel.add(lblProgram, gbc);

		gbc.gridy++;
		centralPanel.add(lblProgram, gbc);

		gbc.gridy++;
		centralPanel.add(lblCantBooks, gbc);

		gbc.gridy++;
		if (amountOfBooks > 0) {
			gbc.weightx = 0;
			gbc.fill = GridBagConstraints.NONE;
			centralPanel.add(seeBooksBtn, gbc);
		}

		add(lblWelcome, BorderLayout.NORTH);
		add(centralPanel);
		add(backBtn, BorderLayout.SOUTH);
	}

	public Student getStudent () {
		return student;
	}

	public void setStudent (Student student) {
		this.student = student;
	}

}

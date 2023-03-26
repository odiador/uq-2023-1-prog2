package view;

import static controller.AdminController.goToMain;
import static javax.swing.JOptionPane.showMessageDialog;
import static model.DataBaseMethods.addBorrowedBookToStudent;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.SwingConstants;

import model.Book;
import model.DataBaseMethods;
import view.custom.ClicListener;
import view.custom.CustomException;
import view.custom.Evento;
import view.custom.NumberTextField;
import view.custom.Panel;
import view.custom.Text;

public class BorrowBookPanel extends Panel implements ClicListener {

	private NumberTextField tfCC;
	private NumberTextField tfId;
	private Text lblWriteId;
	private Text lblWriteCC;
	private Text goBackBtn;
	private Text acceptBtn;
	private AdminWindow frame;

	public BorrowBookPanel(AdminWindow frame) {
		setFrame(frame);

		lblWriteCC = new Text("Escribe la cedula de la persona:", SwingConstants.LEFT);
		tfCC = new NumberTextField(99999999999d);
		lblWriteId = new Text("Escribe el id del libro:", SwingConstants.LEFT);
		tfId = new NumberTextField(Integer.MAX_VALUE);

		acceptBtn = new Text("Aceptar");
		goBackBtn = new Text("Volver");

		tfCC.setPreferredSize(500, 40);
		lblWriteCC.setPreferredSize(500, 40);
		lblWriteId.setPreferredSize(500, 40);
		tfId.setPreferredSize(500, 40);

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

		gbc.gridy++;
		centralPanel.add(lblWriteCC, gbc);

		gbc.gridy++;
		centralPanel.add(tfCC, gbc);

		gbc.gridy++;
		centralPanel.add(lblWriteId, gbc);

		gbc.gridy++;
		centralPanel.add(tfId, gbc);

		add(centralPanel);
		add(southPanel, BorderLayout.SOUTH);

	}

	@Override
	public void botonPresionado (Evento e) {
		if (e.getSource() == acceptBtn) {
			try {
				Book book = DataBaseMethods.getBook(tfId.getIntNumber());
				addBorrowedBookToStudent(tfCC.getText(), book);
				showMessageDialog(null, "El libro ha sido prestado satisfactoriamente");
			} catch (CustomException e1) {
				showMessageDialog(null, e1.getCausa());
			}

		}
		if (e.getSource() == goBackBtn) goToMain(getFrame());
	}

	public AdminWindow getFrame () {
		return frame;
	}

	public void setFrame (AdminWindow frame) {
		this.frame = frame;
	}
}

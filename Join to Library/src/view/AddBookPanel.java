package view;

import static controller.AdminController.goToMain;
import static javax.swing.JOptionPane.showMessageDialog;
import static model.DataBaseMethods.addBook;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.SwingConstants;

import model.Book;
import view.custom.ClicListener;
import view.custom.CustomException;
import view.custom.Evento;
import view.custom.NumberTextField;
import view.custom.Panel;
import view.custom.Text;
import view.custom.TextField;

public class AddBookPanel extends Panel implements ClicListener {

	private NumberTextField tfId;
	private TextField tfName;
	private TextField tfAuthor;
	private Text lblWriteId;
	private Text goBackBtn;
	private Text acceptBtn;
	private AdminWindow frame;

	public AddBookPanel(AdminWindow frame) {
		setFrame(frame);

		lblWriteId = new Text("Escribe el id del libro:", SwingConstants.LEFT);
		tfId = new NumberTextField(Integer.MAX_VALUE);
		tfName = new TextField("Escribe el nombre del libro: ");
		tfAuthor = new TextField("Escribe el autor del libro: ");

		acceptBtn = new Text("Aceptar");
		goBackBtn = new Text("Volver");

		lblWriteId.setPreferredSize(500, 40);
		tfId.setPreferredSize(500, 40);
		tfName.setPreferredSize(500, 40);
		tfAuthor.setPreferredSize(500, 40);

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
		centralPanel.add(lblWriteId, gbc);

		gbc.gridy++;
		centralPanel.add(tfId, gbc);

		gbc.gridy++;
		centralPanel.add(tfName, gbc);

		gbc.gridy++;
		centralPanel.add(tfAuthor, gbc);

		add(centralPanel);
		add(southPanel, BorderLayout.SOUTH);

	}

	@Override
	public void botonPresionado (Evento e) {
		if (e.getSource() == acceptBtn) {
			try {
				addBook(new Book(tfId.getIntNumber(), tfName.getText(), tfAuthor.getText()));
				showMessageDialog(null, "El libro ha sido agregado");
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

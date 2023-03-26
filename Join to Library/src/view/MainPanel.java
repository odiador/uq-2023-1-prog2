package view;

import static controller.MainController.goToShowInfo;
import static javax.swing.JOptionPane.showMessageDialog;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Panel;

import javax.swing.SwingConstants;

import view.custom.Constants;
import view.custom.CustomException;
import view.custom.NumberTextField;
import view.custom.Text;

public class MainPanel extends Panel {
	public MainPanel(MainWindow frame) {
		Font f = Constants.defaultFont.deriveFont(30f);
		Text lblTitle = new Text("¡Bienvenid@ a la biblioteca CRAI!");
		Text lblWriteCC = new Text("Escribe tu cédula:", f);
		Text acceptBtn = new Text("Entrar");
		NumberTextField tfCC = new NumberTextField(99999999999d);

		lblTitle.setFont(f.deriveFont(40f));
		tfCC.setFont(f);

		lblTitle.setPreferredSize(500, 60);
		tfCC.setPreferredSize(500, 50);
		acceptBtn.setPreferredSize(300, 40);

		lblTitle.setVerticalAlignment(SwingConstants.BOTTOM);
		lblWriteCC.setHorizontalAlignment(SwingConstants.LEFT);

		acceptBtn.configAsButton(e -> {
			try {
				goToShowInfo(tfCC.getText(), frame);
			} catch (CustomException e1) {
				showMessageDialog(null, e1.getCausa());
			}
		});

		setLayout(new BorderLayout());

		Panel centerPanel = new Panel();
		centerPanel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		gbc.insets = new Insets(0, 5, 5, 0);

		gbc.gridx = 0;
		gbc.gridy = 0;
		centerPanel.add(lblWriteCC, gbc);

		gbc.gridy = 1;
		centerPanel.add(tfCC, gbc);

		add(lblTitle, BorderLayout.NORTH);
		add(centerPanel);
		add(acceptBtn, BorderLayout.SOUTH);
	}

}

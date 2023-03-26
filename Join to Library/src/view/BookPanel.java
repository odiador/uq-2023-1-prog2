package view;

import java.awt.GridLayout;

import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import model.Book;
import view.custom.Constants;
import view.custom.Panel;
import view.custom.Text;

public class BookPanel extends Panel {

	public BookPanel(Book book) {
		Text lblName = new Text(addHtmlto("Nombre: " + book.getName()));
		Text lblAuthor = new Text(addHtmlto("Autor: " + book.getAuthor()));

		lblName.setVerticalAlignment(SwingConstants.BOTTOM);
		lblAuthor.setVerticalAlignment(SwingConstants.TOP);

		setBorder(new LineBorder(Constants.defaultLineColor));
		setLayout(new GridLayout(2, 1, 1, 1));
		add(lblName);
		add(lblAuthor);
	}

	public static String addHtmlto (String s) {
		return "<html><center>" + s + "</center></html>";
	}

}

package co.edu.uniquindio.p2.universidad.controllers;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import co.edu.uniquindio.p2.universidad.model.Universidad;

public class ModelFactoryController {
	private static final String RUTA = "data.dat";
	private static Universidad universidad = null;

	/**
	 * @return the universidad
	 */
	public static Universidad getInstance() {
		if (universidad == null)
			readInstance();
		return universidad;
	}

	public static void readInstance() {
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(RUTA));
			universidad = (Universidad) ois.readObject();
			ois.close();
		} catch (IOException | ClassNotFoundException e) {
			universidad = new Universidad("Universidad del quindío", "Cll 9N");
			saveInstance();
		}
	}

	public static void saveInstance() {
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(RUTA));
			oos.writeObject(universidad);
			oos.close();
		} catch (IOException e) {
		}
	}
}

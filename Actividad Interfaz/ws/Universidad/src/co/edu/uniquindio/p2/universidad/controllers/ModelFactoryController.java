package co.edu.uniquindio.p2.universidad.controllers;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import co.edu.uniquindio.p2.universidad.model.Universidad;

public class ModelFactoryController {
	private static final String RUTA = "data.dat";
	private Universidad universidad = null;

	private static class SingletonHolder {
		private final static ModelFactoryController eINSTANCE = new ModelFactoryController();
	}

	public static ModelFactoryController getInstance() {
		return SingletonHolder.eINSTANCE;
	}

	/**
	 * @return the universidad
	 */
	public Universidad getUniversidad() {
		if (universidad == null)
			readUniversidad();
		return universidad;
	}

	public void readUniversidad() {
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(RUTA));
			universidad = (Universidad) ois.readObject();
			ois.close();
		} catch (IOException | ClassNotFoundException e) {
			universidad = new Universidad("Universidad del quindío", "Cll 9N");
			saveInstance();
		}
	}

	public void saveInstance() {
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(RUTA));
			oos.writeObject(universidad);
			oos.close();
		} catch (IOException e) {
		}
	}
}

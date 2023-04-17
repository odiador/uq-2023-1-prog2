package co.edu.uniquindio.centroimpresion.controllers;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import co.edu.uniquindio.centroimpresion.model.CentroImpresion;

public class SerializedData {
	private static final String RUTA = "src/co/edu/uniquindio/centroimpresion/controllers/info.dat";
	private CentroImpresion centroImpresion;

	/**
	 * Es el constructor de la clase, obtiene el centro de impresion, si no se
	 * encuentra, crea uno nuevo y lo guarda automaticamente en una ruta especifica:
	 * src/co/edu/uniquindio/centroimpresion/controllers/info.dat
	 */
	public SerializedData() {
		try {
			leerObjeto();
		} catch (Exception e) {
			try {
				centroImpresion = new CentroImpresion();
				escribirObjeto();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}

	/**
	 * Intenta leer el centro de impresion
	 * 
	 * @throws Exception
	 */
	public void leerObjeto() throws Exception {
		ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(RUTA));
		try {
			this.centroImpresion = (CentroImpresion) objectInputStream.readObject();
			objectInputStream.close();
		} catch (Exception e) {
			objectInputStream.close();
			throw e;
		}
	}

	/**
	 * Intenta escribrir el centro de impresion
	 * 
	 * @throws Exception
	 */
	public void escribirObjeto() throws Exception {
		ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(RUTA));
		try {
			objectOutputStream.writeObject(centroImpresion);
			objectOutputStream.close();
		} catch (Exception e) {
			objectOutputStream.close();
			throw e;
		}
	}

	/**
	 * Actualiza el centro de impresion, primero intentando guardar, y si no se
	 * encuentra, intenta leer el objeto
	 */
	public void updateCentroImpresion() {
		try {
			escribirObjeto();
		} catch (Exception e) {
			try {
				leerObjeto();
			} catch (Exception e1) {
			}
		}
	}

	/**
	 * Cambia el centro de impresion y luego lo guarda
	 * 
	 * @see {@link #updateCentroImpresion()}
	 * @param centroImpresion
	 */
	public void updateCentroImpresion(CentroImpresion centroImpresion) {
		setCentroImpresion(centroImpresion);
		updateCentroImpresion();
	}

	/**
	 * Obtiene el centro de impresion
	 * 
	 * @return
	 */
	public CentroImpresion getCentroImpresion() {
		return centroImpresion;
	}

	/**
	 * Cambia el centro de impresion
	 * 
	 * @param centroImpresion
	 */
	private void setCentroImpresion(CentroImpresion centroImpresion) {
		this.centroImpresion = centroImpresion;
	}

	@Override
	public String toString() {
		return "SerializedData [centroImpresion=" + centroImpresion + "]";
	}

}

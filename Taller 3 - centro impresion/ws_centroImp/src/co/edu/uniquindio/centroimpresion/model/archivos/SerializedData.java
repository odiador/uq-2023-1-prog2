package co.edu.uniquindio.centroimpresion.model.archivos;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import co.edu.uniquindio.centroimpresion.model.centro.CentroImpresion;

public class SerializedData {
	private static final String RUTA = "src/co/edu/uniquindio/centroimpresion/model/archivos/info.dat";
	private CentroImpresion centroImpresion;

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

	public void leerObjeto() throws Exception {
		ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(RUTA));
		try {
			this.centroImpresion = (CentroImpresion) objectInputStream.readObject();
			objectInputStream.close();
		} catch (Exception e) {
			objectInputStream.close();
			e.printStackTrace();
			throw e;
		}
	}

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

	public void updateCentroImpresion() {
		try {
			escribirObjeto();
		} catch (Exception e) {
			try {
				leerObjeto();
			} catch (Exception e1) {
			}
		}
		System.out.println(getCentroImpresion()); //TODO
	}

	public void updateCentroImpresion(CentroImpresion centroImpresion) {
		setCentroImpresion(centroImpresion);
		updateCentroImpresion();
	}

	public CentroImpresion getCentroImpresion() {
		return centroImpresion;
	}

	public void setCentroImpresion(CentroImpresion centroImpresion) {
		this.centroImpresion = centroImpresion;
	}

	@Override
	public String toString() {
		return "SerializedData [centroImpresion=" + centroImpresion + "]";
	}

}

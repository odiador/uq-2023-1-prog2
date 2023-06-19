package co.edu.uniquindio.p2.diplomado.controllers;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

import co.edu.uniquindio.p2.diplomado.exceptions.AtributosFaltantesException;
import co.edu.uniquindio.p2.diplomado.exceptions.CuposLlenosException;
import co.edu.uniquindio.p2.diplomado.exceptions.EstudianteException;
import co.edu.uniquindio.p2.diplomado.exceptions.NotPresentException;
import co.edu.uniquindio.p2.diplomado.exceptions.NullException;
import co.edu.uniquindio.p2.diplomado.model.Diplomado;
import co.edu.uniquindio.p2.diplomado.model.Estudiante;

public class ModelFactoryController {
	/**
	 * Es la ruta en la que está almacenada la información de la universidad
	 */
	private static final String RUTA = "data.dat";
	private Diplomado diplomado = null;

	/**
	 * Es la clase singleton que controla la universidad
	 * 
	 * @author juana
	 *
	 */
	private static class SingletonHolder {
		private final static ModelFactoryController eINSTANCE = new ModelFactoryController();
	}

	/**
	 * Obtiene un objeto de clase {@link #ModelFactoryController()} que permite usar
	 * métodos de la universidad
	 * 
	 * @return
	 */
	public static ModelFactoryController getInstance() {
		return SingletonHolder.eINSTANCE;
	}

	/**
	 * Obtiene la universidad del ModelFactory, si no se encuentra (es null) se
	 * intenta leer {@link #readDiplomado()}, y si no se puede leer se crea un nuevo
	 * diplomado y se guarda en el archivo ({@link #saveDiplomado()})
	 * 
	 * @return la universidad
	 */
	public Diplomado getDiplomado() {
		if (diplomado == null)
			readDiplomado();
		return diplomado;
	}

	/**
	 * Lee la universidad de un archivo de ruta {@link #RUTA}, si no se encuentra,
	 * se crea una nueva universidad y se guarda en la ruta
	 * ({@link #saveDiplomado()}
	 */
	public void readDiplomado() {
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(RUTA));
			diplomado = (Diplomado) ois.readObject();
			ois.close();
		} catch (IOException | ClassNotFoundException e) {
			diplomado = new Diplomado("Uniquindio", 100);
			saveDiplomado();
		}
	}

	/**
	 * Guarda el diplomado en un archivo de una ruta específica
	 * {@link #RUTA}
	 */
	public void saveDiplomado() {
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(RUTA));
			oos.writeObject(getDiplomado());
			oos.close();
		} catch (IOException e) {
		}
	}

	public void agregarEstudiante(Estudiante estudiante)
			throws EstudianteException, NullException, AtributosFaltantesException, CuposLlenosException {
		getDiplomado().agregarEstudiante(estudiante);
		saveDiplomado();
	}

	/**
	 * Elimina un estudiante de la lista de estudiantes
	 * 
	 * @param id
	 * @throws EstudianteException
	 * @throws NullException
	 */
	public void eliminarEstudiante(String id) throws EstudianteException, NullException {
		getDiplomado().eliminarEstudiante(id);
		saveDiplomado();
	}

	/**
	 * @return {@link Diplomado#listarEstudiantes()}
	 */
	public List<Estudiante> listarEstudiantes() {
		return getDiplomado().listarEstudiantes();
	}

	/**
	 * Obtiene el nombre de la empresa de energía
	 * 
	 * @return
	 */
	public String obtenerNombreDiplomado() {
		return getDiplomado().getNombre();
	}

	public Float obtenerPromedioTodosEstudiantes() throws NotPresentException {
		return getDiplomado().obtenerPromedioTodosEstudiantes();
	}

	public Float obtenerPromedioMasAltoEstudiantes() throws NotPresentException {
		return getDiplomado().obtenerPromedioMasAltoEstudiantes();
	}

	public float obtenerPromedioEstudiante(String text) throws EstudianteException {
		return getDiplomado().obtenerPromedioEstudiante(text);
	}

	public void actualizarEstudiante(Estudiante estudiante)
			throws EstudianteException, NullException, AtributosFaltantesException {
		getDiplomado().actualizarEstudiante(estudiante);
		saveDiplomado();
	}

	public List<Float> listarNotasEstudiante(String id) throws EstudianteException {
		return getDiplomado().listarNotasEstudiante(id);
	}

}

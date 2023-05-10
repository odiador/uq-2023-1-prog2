package co.edu.uniquindio.p2.universidad.controllers;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

import co.edu.uniquindio.p2.universidad.exceptions.EstudianteException;
import co.edu.uniquindio.p2.universidad.exceptions.NullException;
import co.edu.uniquindio.p2.universidad.model.Estudiante;
import co.edu.uniquindio.p2.universidad.model.Universidad;

public class ModelFactoryController {
	/**
	 * Es la ruta en la que está almacenada la información de la universidad
	 */
	private static final String RUTA = "data.dat";
	private Universidad universidad = null;

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
	 * intenta leer {@link #readUniversidad()}, y si no se puede leer se crea una
	 * nueva universidad y se guarda en el archivo ({@link #saveUniversidad()})
	 * 
	 * @return la universidad
	 */
	public Universidad getUniversidad() {
		if (universidad == null)
			readUniversidad();
		return universidad;
	}

	/**
	 * Lee la universidad de un archivo de ruta {@link #RUTA}, si no se encuentra,
	 * se crea una nueva universidad y se guarda en la ruta
	 * ({@link #saveUniversidad()}
	 */
	public void readUniversidad() {
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(RUTA));
			universidad = (Universidad) ois.readObject();
			ois.close();
		} catch (IOException | ClassNotFoundException e) {
			universidad = new Universidad("Universidad del quind�o", "Cll 9N");
			saveUniversidad();
		}
	}

	/**
	 * Guarda la universidad en un archivo de una ruta específica {@link #RUTA}
	 */
	public void saveUniversidad() {
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(RUTA));
			oos.writeObject(universidad);
			oos.close();
		} catch (IOException e) {
		}
	}

	/**
	 * Obtiene la lista de estudiantes de la universidad
	 * 
	 * @return
	 */
	public List<Estudiante> getListaEstudiantes() {
		return getUniversidad().getListaEstudiantes();
	}

	/**
	 * Agrega un estudiante, usa el método de universidad:
	 * {@link Universidad#agregarEstudiante(Estudiante)}, da errores si la
	 * universidad suelta errores
	 * 
	 * @param estudiante
	 * @throws NullException
	 * @throws EstudianteException
	 */
	public void agregarEstudiante(Estudiante estudiante) throws NullException, EstudianteException {
		getUniversidad().agregarEstudiante(estudiante);
		saveUniversidad();
	}

	/**
	 * Elimina un estudiante, usa el método de universidad:
	 * {@link Universidad#eliminarEstudiante(Estudiante)}, da errores si la
	 * universidad suelta errores
	 * 
	 * @param estudiante
	 * @throws NullException
	 * @throws EstudianteException
	 */
	public void eliminarEstudiante(Estudiante estudiante) throws NullException, EstudianteException {
		getUniversidad().eliminarEstudiante(estudiante);
		saveUniversidad();
	}

	/**
	 * Actualiza un estudiante, usa el método de universidad:
	 * {@link Universidad#actualizarEstudiante(Estudiante)}, da errores si la
	 * universidad suelta errores
	 * 
	 * @param estudiante
	 * @throws NullException
	 * @throws EstudianteException
	 */
	public void actualizarEstudiante(Estudiante estudiante) throws NullException, EstudianteException {
		getUniversidad().actualizarEstudiante(estudiante);
		saveUniversidad();
	}
}

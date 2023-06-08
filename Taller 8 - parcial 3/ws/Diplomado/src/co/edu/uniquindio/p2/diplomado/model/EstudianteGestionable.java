package co.edu.uniquindio.p2.diplomado.model;

import java.util.List;

import co.edu.uniquindio.p2.diplomado.exceptions.AtributosFaltantesException;
import co.edu.uniquindio.p2.diplomado.exceptions.CuposLlenosException;
import co.edu.uniquindio.p2.diplomado.exceptions.EstudianteException;
import co.edu.uniquindio.p2.diplomado.exceptions.NullException;

public interface EstudianteGestionable {
	public void agregarEstudiante(Estudiante estudiante)
			throws EstudianteException, NullException, AtributosFaltantesException, CuposLlenosException;

	public void actualizarEstudiante(Estudiante estudiante)
			throws EstudianteException, NullException, AtributosFaltantesException;

	public void eliminarEstudiante(String id) throws EstudianteException, NullException;

	public Estudiante buscarEstudiante(String id);

	public default boolean validarEstudiante(String id) {
		return buscarEstudiante(id) != null;
	}

	public List<Estudiante> listarEstudiantes();

}

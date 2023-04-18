package co.edu.uniquindio.agentatelefonica.p2.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

import co.edu.uniquindio.agentatelefonica.p2.exceptions.ArregloLlenoException;
import co.edu.uniquindio.agentatelefonica.p2.exceptions.ContactoException;
import co.edu.uniquindio.agentatelefonica.p2.exceptions.ObjetoNoExisteException;

public class Agenda implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nombre;
	private Contacto[] listaContactos;
	private Reunion[] listaReuniones;
	private Grupo[] listaGrupos;

	public Agenda(String nombre, int listaContactosLength, int listaReunionesLength, int listaGruposLength) {
		super();
		this.nombre = nombre;
		this.listaContactos = new Contacto[listaContactosLength];
		this.listaReuniones = new Reunion[listaReunionesLength];
		this.listaGrupos = new Grupo[listaGruposLength];
	}

	public Agenda(String nombre) {
		this(nombre, 0, 0, 0);
	}

	/**
	 * Añade un contacto al arreglo de contactos, en caso de que este lleno se
	 * 
	 * @param c
	 * @throws ObjetoNoExisteException en caso de que el contacto {@code c} sea null
	 * @throws ContactoException       en caso de que el contacto ya exista
	 * @throws ArregloLlenoException   en caso de que el arreglo de contactos este
	 *                                 lleno
	 */
	public void aniadirContacto(Contacto c) throws ObjetoNoExisteException, ContactoException, ArregloLlenoException {
		throwifNull(c);
		if (existeContacto(c))
			throw new ContactoException("El contacto no se pudo agregar (ya existe)");
		int posLibre = buscarPosLibreContacto();
		if (posLibre == -1)
			throw new ArregloLlenoException("La lista de contactos esta llena");
		listaContactos[posLibre] = c;
	}

	private void throwifNull(Contacto c) throws ObjetoNoExisteException {
		if (c == null)
			throw new ObjetoNoExisteException("El contacto no existe");
	}

	/**
	 * Determina si el contacto existe o no dependiendo de que si la posicion del
	 * contacto no sea null
	 * 
	 * @see {@link #buscarPosContacto(Contacto)}
	 * @param c
	 * @return true si existe
	 */
	public boolean existeContacto(Contacto c) {
		return buscarPosContacto(c) != -1;
	}

	/**
	 * Busca la posicion en la que se encuentra un determinado contacto, usa el
	 * metodo indexOf del arraylist, el cual usa la condicion (c==null ?
	 * get(i)==null : c.equals(get(i))), o -1 en caso de que no se encuentre
	 * 
	 * @param c
	 * @return el indice de la posicion del contacto o -1 si no se encuentra
	 */
	private int buscarPosContacto(Contacto c) {
		return ((ArrayList<Contacto>) Arrays.asList(listaContactos)).indexOf(c);
	}

	/**
	 * Busca un contacto a partir del nombre, si no se encuentra se retorna un null
	 * 
	 * @param nombre
	 * @return el contacto, si no se encuentra null
	 */
	public Contacto buscarContacto(String nombre) {
		for (Contacto contacto : listaContactos)
			if (contacto.getNombre().equals(nombre))
				return contacto;
		return null;
	}

	/**
	 * Obtiene la primera posicion libre de un contacto, si no se encuentra se
	 * retorna un -1
	 * 
	 * @see {@link #buscarPosContacto(Contacto)}
	 * @return -1 si no se encuentra, cualquier otro numero en caso de que si se
	 *         encuentre
	 */
	private int buscarPosLibreContacto() {
		return buscarPosContacto(null);
	}

	/**
	 * Elimina el contacto de la agenda, muestra errores en caso de que no se pueda
	 * eliminar
	 * 
	 * @param c
	 * @throws ObjetoNoExisteException en caso de que el contacto enviado sea null
	 * @throws ContactoException       en caso de que no exista el contacto
	 */
	public void eliminarContacto(Contacto c) throws ObjetoNoExisteException, ContactoException {
		throwifNull(c);
		for (int i = 0; i < listaContactos.length; i++)
			if (listaContactos[i].equals(c)) {
				listaContactos[i] = null;
				return;
			}
		throw new ContactoException("El contacto no pudo ser eliminado (no existe)");
	}

	/**
	 * Determina si la agenda esta llena o no
	 * 
	 * @return
	 */
	public boolean agendaLlena() {
		return !existeContacto(null);
	}

	/**
	 * Indica cuantos contactos más podemos meter.
	 * 
	 * @return la cantidad de huecos libres
	 */
	public int huecosLibres() {
		int contador = 0;
		for (Contacto contacto : listaContactos)
			if (contacto == null)
				contador++;
		return contador;
	}

	/**
	 * Obtiene el nombre de la agenda
	 * 
	 * @return
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Cambia el nombre de la agenda
	 * 
	 * @param nombre
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Obtiene la lista de contactos de la agenda
	 * 
	 * @return
	 */
	public Contacto[] getListaContactos() {
		return listaContactos;
	}

	/**
	 * Cambia la lista de contactos de la agenda
	 * 
	 * @param listaContactos
	 */
	public void setListaContactos(Contacto[] listaContactos) {
		this.listaContactos = listaContactos;
	}

	/**
	 * Obtiene la lista de reuniones de la agenda
	 * 
	 * @return
	 */
	public Reunion[] getListaReuniones() {
		return listaReuniones;
	}

	/**
	 * Obtiene la lista de reuniones de la agenda
	 * 
	 * @param listaReuniones
	 */
	public void setListaReuniones(Reunion[] listaReuniones) {
		this.listaReuniones = listaReuniones;
	}

	/**
	 * Obtiene la lista de grupos de la agenda
	 * 
	 * @return
	 */
	public Grupo[] getListaGrupos() {
		return listaGrupos;
	}

	/**
	 * 
	 * Cambia la lista de grupos de la agenda
	 * 
	 * @param listaGrupos
	 */
	public void setListaGrupos(Grupo[] listaGrupos) {
		this.listaGrupos = listaGrupos;
	}

	@Override
	public int hashCode() {
		return Objects.hash(nombre);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Agenda other = (Agenda) obj;
		return Objects.equals(nombre, other.nombre);
	}

	@Override
	public String toString() {
		return String.format("Agenda [nombre=%s, listaContactos=%s, listaReuniones=%s, listaGrupos=%s]", nombre,
				Arrays.toString(listaContactos), Arrays.toString(listaReuniones), Arrays.toString(listaGrupos));
	}

}

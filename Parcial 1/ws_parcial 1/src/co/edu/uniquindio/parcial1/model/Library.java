package co.edu.uniquindio.parcial1.model;

import java.util.ArrayList;
import java.util.List;

public class Library {

	private String name;
	private String address;
	private String phoneNumber;

	private final List<Student> studentList = new ArrayList<Student>();
	private final List<Lending> lendingList = new ArrayList<Lending>();
	private final List<Book> bookList = new ArrayList<Book>();
	private final List<Employer> employerList = new ArrayList<Employer>();

	/**
	 * Es el constructor de la biblioteca
	 * 
	 * @param name        es el nombre
	 * @param address     es la dirección
	 * @param phoneNumber es el número de teléfono
	 */
	public Library(String name, String address, String phoneNumber) {
		this.name = name;
		this.address = address;
		this.phoneNumber = phoneNumber;
	}

	/**
	 * Es el constructor de la biblioteca sin parámetros
	 */
	public Library() {
	}

	/**
	 * Obtiene la lista de estudiantes de la biblioteca
	 * 
	 * @return la lista de estudiantes
	 */
	public List<Student> getStudentList() {
		return studentList;
	}

	/**
	 * Obtiene la lista de libros de la biblioteca
	 * 
	 * @return la lista de libros
	 */
	public List<Book> getBookList() {
		return bookList;
	}

	/**
	 * Obtiene la lista de préstamos de la biblioteca
	 * 
	 * @return la lista de préstamos
	 */
	public List<Lending> getLendingList() {
		return lendingList;
	}

	/**
	 * Obtiene la lista de empleados de la biblioteca
	 * 
	 * @return la lista de empleados
	 */
	public List<Employer> getEmployerList() {
		return employerList;
	}

	/**
	 * Obtiene el nombre de la biblioteca
	 * 
	 * @return el nombre
	 */
	public String getName() {
		return name;
	}

	/**
	 * Cambia el nombre de la biblioteca
	 * 
	 * @param name el nombre
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Obtiene la dirección de la biblioteca
	 * 
	 * @return la dirección
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * Cambia la dirección de la biblioteca
	 * 
	 * @param address la dirección
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * Obtiene el número de teléfono de la biblioteca
	 * 
	 * @return el número de teléfono
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * Cambia el número de teléfono de la biblioteca
	 * 
	 * @param phoneNumber
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
}

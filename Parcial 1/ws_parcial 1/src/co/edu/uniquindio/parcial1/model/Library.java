package co.edu.uniquindio.parcial1.model;

import java.time.LocalDate;
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

	/**
	 * Busca un empleado a partir de su nombre, si no se encuentra se retorna un
	 * empleado con constructor vacío
	 * 
	 * @param name es el nombre a buscar del empleado
	 * @return el empleado encontrado
	 */
	public Employer searchEmployer(String name) {
		return getEmployerList().stream().filter(employer -> employer.getName().equals(name)).findFirst()
				.orElse(new Employer());
	}

	public Employer searchEmployerOrThrow(String name) throws LibraryException {
		Employer employer = searchEmployer(name);
		if (!employer.getExists()) {
			throw new LibraryException("No se encontró el empleado con nombre " + name);
		}
		return employer;
	}

	public String addBook(String title, String author, String isbn) throws LibraryException {
		if (validateBook(isbn)) {
			throw new LibraryException("El libro ya existe  (" + isbn + ")");
		}
		getBookList().add(new Book(isbn, title, author));
		return "El libro ha sido agregado (" + isbn + ")";
	}

	public Book searchBookOrThrow(String isbn) throws LibraryException {
		Book book = searchBook(isbn);
		if (!book.getExists()) {
			throw new LibraryException("No se encontró el empleado con nombre " + name);
		}
		return book;
	}

	public Book searchBook(String isbn) {
		return getBookList().stream().filter(book -> book.getIsbn().equals(isbn)).findFirst()
				.orElse(new Book());
	}

	public boolean validateBook(String isbn) {
		return searchBook(isbn).getExists();
	}

	/**
	 * Valida si un empleado existe o no a partir de su nombre
	 * 
	 * @param name es el nombre
	 * @return true si se encuentra, false si no
	 */
	public boolean validateEmployer(String name) {
		return searchEmployer(name).getExists();
	}

	/**
	 * 
	 * @param name
	 * @param salary
	 * @param appointment
	 * @return El empleado ha sido agregado ({@code name})
	 * @throws LibraryException
	 */
	public String addEmployer(String name, Double salary, String appointment) throws LibraryException {
		if (validateEmployer(name)) {
			throw new LibraryException("El empleado ya existe (" + name + ")");
		}
		getEmployerList().add(new Employer(name, salary, appointment));
		return "El empleado ha sido agregado (" + name + ")";
	}

	public String addLending(LocalDate date, LocalDate deliveryDate, String code, Employer employer)
			throws LibraryException {
		if (validateLending(code))
			throw new LibraryException("El préstamo ya existe (" + code + ")");

		getLendingList().add(new Lending(date, deliveryDate, code, employer));
		return "El préstamo ha sido agregado (" + code + ")";
	}

	private boolean validateLending(String code) {
		return searchLending(code).getExists();
	}

	public Lending searchLending(String code) {
		return getLendingList().stream().filter(lending -> lending.getCode().equals(code)).findFirst()
				.orElse(new Lending());
	}

	/**
	 * Obtiene del empleado que ha realizado un préstamo de un Libro dado el isbn
	 * del libro.
	 * 
	 * @param isbn es el isbn del libro
	 * @return el empleado en caso de que exista
	 * @throws LibraryException en caso de que no exista, muestra un error con
	 *                          mensaje: "No se pudo obtener la información del
	 *                          empleado que ha realizado un préstamo de un Libro
	 *                          dado el ISBN del libro."
	 */
	public Employer getEmployerByISBNBookofLending(String isbn) throws LibraryException {
		Employer employer = new Employer();
		for (Lending eachlending : getLendingList()) {
			employer = eachlending.getEmployerByIsbn(isbn);
			if (employer.getExists())
				break;
		}
		if (!employer.getExists()) {
			throw new LibraryException(
					"No se pudo obtener la información del empleado que ha realizado un préstamo de un Libro dado el ISBN del libro.");
		}

		return employer;
	}

	public String addLendingDetail(String lendingCode, Double unitaryValue, Integer quantity, Book book)
			throws LibraryException {
		Lending lending = searchLending(lendingCode);
		if (!lending.getExists()) {
			throw new LibraryException("El préstamo no se ha encontrado (" + lendingCode + ")");
		}
		lending.addLendingDetail(lendingCode, unitaryValue, quantity, book);
		return "El detalle del préstamo ha sido agregado (" + lendingCode + ")";
	}
}

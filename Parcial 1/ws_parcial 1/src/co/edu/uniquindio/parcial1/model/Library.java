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
	public Library(final String name, final String address, final String phoneNumber) {
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
	public void setName(final String name) {
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
	public void setAddress(final String address) {
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
	public void setPhoneNumber(final String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * Busca un empleado a partir de su nombre, si no se encuentra se retorna un
	 * empleado con constructor vacío
	 * 
	 * @param name es el nombre a buscar del empleado
	 * @return el empleado encontrado
	 */
	public Employer searchEmployer(final String name) {
		return getEmployerList().stream().filter(employer -> employer.getName().equals(name)).findFirst()
				.orElse(new Employer());
	}

	public Employer searchEmployerOrThrow(final String name) throws LibraryException {
		final Employer employer = searchEmployer(name);
		if (!employer.getExists()) {
			throw new LibraryException("No se encontró el empleado con nombre " + name);
		}
		return employer;
	}

	public String addBook(final String title, final String author, final String isbn) throws LibraryException {
		if (validateBook(isbn)) {
			throw new LibraryException("El libro ya existe  (" + isbn + ")");
		}
		getBookList().add(new Book(isbn, title, author));
		return "El libro ha sido agregado (" + isbn + ")";
	}

	public Book searchBookOrThrow(final String isbn) throws LibraryException {
		final Book book = searchBook(isbn);
		if (!book.getExists()) {
			throw new LibraryException("No se encontró el empleado con nombre " + name);
		}
		return book;
	}

	public Book searchBook(final String isbn) {
		return getBookList().stream().filter(book -> book.getIsbn().equals(isbn)).findFirst()
				.orElse(new Book());
	}

	public boolean validateBook(final String isbn) {
		return searchBook(isbn).getExists();
	}

	/**
	 * Valida si un empleado existe o no a partir de su nombre
	 * 
	 * @param name es el nombre
	 * @return true si se encuentra, false si no
	 */
	public boolean validateEmployer(final String name) {
		return searchEmployer(name).getExists();
	}

	/**
	 * 
	 * @param id
	 * @param name
	 * @param salary
	 * @param appointment
	 * @param aniosExperiencia
	 * @return El empleado ha sido agregado ({@code name})
	 * @throws LibraryException
	 */
	public String addEmployer(final String id, final String name, final Double salary, final String appointment,
			final Integer aniosExperiencia) throws LibraryException {
		if (validateEmployer(name)) {
			throw new LibraryException("El empleado ya existe (" + name + ")");
		}
		getEmployerList().add(new Employer(id, name, salary, appointment, aniosExperiencia));
		return "El empleado ha sido agregado (" + name + ")";
	}

	public String addLending(final LocalDate date, final LocalDate deliveryDate, final String code,
			final Employer employer, final Student student)
			throws LibraryException {
		if (validateLending(code))
			throw new LibraryException("El préstamo ya existe (" + code + ")");

		getLendingList().add(new Lending(date, deliveryDate, code, employer, student));
		return "El préstamo ha sido agregado (" + code + ")";
	}

	private boolean validateLending(final String code) {
		return searchLending(code).getExists();
	}

	public Lending searchLending(final String code) {
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
	public Employer getEmployerByISBNBookofLending(final String isbn) throws LibraryException {
		Employer employer = new Employer();
		for (final Lending eachlending : getLendingList()) {
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

	/**
	 * Agrega un detalle de préstamo, a partir del código del préstamo, muestra un
	 * error si el préstamo
	 * 
	 * @param lendingCode  es el código del préstamo
	 * @param unitaryValue
	 * @param quantity
	 * @param book
	 * @return
	 * @throws LibraryException
	 */
	public String addLendingDetail(final String lendingCode, final Double unitaryValue, final Integer quantity,
			final Book book)
			throws LibraryException {
		final Lending lending = searchLending(lendingCode);
		if (!lending.getExists()) {
			throw new LibraryException("El préstamo no se ha encontrado (" + lendingCode + ")");
		}
		return lending.addLendingDetail(lendingCode, unitaryValue, quantity, book);
	}
}
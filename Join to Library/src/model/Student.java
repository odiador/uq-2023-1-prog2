package model;

import java.util.ArrayList;

public class Student {
	private String cc;
	private String name;
	private String mail;
	private String program;
	private int semester;
	private ArrayList<Book> borrowedBooks = new ArrayList<Book>();

	public Student(String cc, String name, String mail, String program, int semester) {
		setCC(cc);
		setName(name);
		setMail(mail);
		setProgram(program);
		setSemester(semester);
	}

	public Student(String cc, String name, String mail, String program, int semester, ArrayList<Book> borrowedBooks) {
		this(cc, name, mail, program, semester);
		setBorrowedBooks(borrowedBooks);
	}

	public boolean getExists () {
		boolean result = true;
		if (getCC() == null) result = false;
		else if (getName() == null) result = false;
		else if (getMail() == null) result = false;
		else if (getProgram() == null) result = false;
		else if (getSemester() == -1) result = false;
		return result;
	}

	public String getCC () {
		return cc;
	}

	public void setCC (String cc) {
		this.cc = cc;
	}

	public String getMail () {
		return mail;
	}

	public void setMail (String mail) {
		this.mail = mail;
	}

	public String getName () {
		return name;
	}

	public void setName (String name) {
		this.name = name;
	}

	public String getProgram () {
		return program;
	}

	public void setProgram (String program) {
		this.program = program;
	}

	public int getSemester () {
		return semester;
	}

	public void setSemester (int semester) {
		this.semester = semester;
	}

	public static Student none () {
		return new Student(null, null, null, null, -1);
	}

	public ArrayList<Book> getBorrowedBooks () {
		return borrowedBooks;
	}

	public void addNewBorrowedBook (int id, String name, String author) {
		getBorrowedBooks().add(new Book(id, name, author));
	}

	public void clearNewBorrowedBooks () {
		getBorrowedBooks().clear();
	}

	public void addNewBorrowedBook (Book book) {
		getBorrowedBooks().add(book);
	}

	public void setBorrowedBooks (ArrayList<Book> borrowedBooks) {
		this.borrowedBooks = borrowedBooks;
	}

	public static ArrayList<Book> parseBorrowedBooks (String s) {
		if (s.equals("{}")) return new ArrayList<Book>();
		StringBuilder sb = new StringBuilder(s);
		sb.deleteCharAt(0);
		sb.deleteCharAt(sb.length() - 1);
		String borrowedBooksArr[] = sb.toString().split("-");
		ArrayList<Book> result = new ArrayList<Book>();
		for (String eachNonParsedBook : borrowedBooksArr) result.add(Book.parse(eachNonParsedBook));
		return result;
	}

	public String getBorrowedBooksString () {
		String result = "{";
		ArrayList<Book> borrowedBooks = getBorrowedBooks();
		for (int i = 0; i < borrowedBooks.size(); i++) {
			Book eachBook = borrowedBooks.get(i);

			result += eachBook.toString();
			if (i == borrowedBooks.size() - 1) continue;
			result += "-";
		}
		result += "}";
		return result;
	}

	public static Student parse (String s) {
		if (s.equals("?")) return none();
		StringBuilder sb = new StringBuilder(s);
		sb.deleteCharAt(0);
		sb.deleteCharAt(sb.length() - 1);
		String studentInfoArray[] = sb.toString().split("?");
		String cc = studentInfoArray[0];
		String name = studentInfoArray[1];
		String mail = studentInfoArray[2];
		String program = studentInfoArray[3];
		int semester = Integer.parseInt(studentInfoArray[4]);
		ArrayList<Book> borrowedBookList = parseBorrowedBooks(studentInfoArray[5]);

		return new Student(cc, name, mail, program, semester, borrowedBookList);
	}

	@Override
	public String toString () {
		return !getExists() ? "?"
				: "<" + getCC() + "?" + getName() + "?" + getMail() + "?" + getProgram() + "?" + getSemester()
						+ getBorrowedBooksString();
	}
}

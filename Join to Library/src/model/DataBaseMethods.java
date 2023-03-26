package model;

import static java.sql.DriverManager.getConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import view.custom.CustomException;

public class DataBaseMethods {

	public static final String ruta = "jdbc:mysql://localhost:3306/crai_library?connectTimeout=5000&socketTimeout=5000";

	public static Student getStudent (String cc) throws CustomException {
		Student student = Student.none();
		try {
			Connection c = getConnection(ruta, "root", "");
			PreparedStatement state = c.prepareStatement("SELECT * FROM `students` WHERE `CC`= ?");
			state.setString(1, cc);

			ResultSet rs = state.executeQuery();
			if (rs.next()) student = new Student(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
					rs.getInt(5), Student.parseBorrowedBooks(rs.getString(6)));
			c.close();
			if (!student.getExists()) {
				throw new CustomException(CustomException.STUDENT_NOT_FOUND,
						"Estudiante con cédula: " + cc + " no encontrado");
			}
		} catch (SQLException e) {
			String message = e.getMessage();
			if (message == null) message = "";
			throw new CustomException(e.getErrorCode(), message);
		}
		return student;
	}

	public static void addBorrowedBookToStudent (String cc, Book book) throws CustomException {

		Student student = getStudent(cc);
		student.addNewBorrowedBook(book);
		updateStudent(student);
	}

	public static void clearBorrowedBooksToStudent (String cc, Book book) throws CustomException {
		Student student = getStudent(cc);
		student.clearNewBorrowedBooks();
		updateStudent(student);
	}

	public static void updateStudent (Student student) throws CustomException {
		try {
			Connection c = DriverManager.getConnection(ruta, "root", "");
			PreparedStatement state = c.prepareStatement("UPDATE `students` SET `NAME` = ?, `MAIL` = ?, `PROGRAM` = ?, "
					+ "`SEMESTER` = ?, `BRRW_BOOKS` = ? WHERE `students`.`CC` = ?");
			state.setString(1, student.getName());
			state.setString(2, student.getMail());
			state.setString(3, student.getProgram());
			state.setInt(4, student.getSemester());
			state.setString(5, student.getBorrowedBooksString());
			state.setString(6, student.getCC());
			state.executeUpdate();
			c.close();
		} catch (SQLException e) {
			String message = e.getMessage();
			if (message == null) message = "";
			throw new CustomException(e.getErrorCode(), message);
		}
	}

	public static void addStudent (Student student) throws CustomException {
		try {
			Connection c = getConnection(ruta, "root", "");
			PreparedStatement pst = c.prepareStatement("insert into students values(?,?,?,?,?,?)");
			pst.setString(1, student.getCC());
			pst.setString(2, student.getName());
			pst.setString(3, student.getMail());
			pst.setString(4, student.getProgram());
			pst.setInt(5, student.getSemester());
			pst.setString(6, student.getBorrowedBooksString());
			pst.executeUpdate();
			c.close();
		} catch (SQLException e) {
			if (e.getErrorCode() == 1062) {
				throw new CustomException(CustomException.STUDENT_ALREADY_EXISTS, "El estudiante ya existe");
			}
			String message = e.getMessage();
			if (message == null) message = "";
			throw new CustomException(e.getErrorCode(), message);
		}
	}

	public static void addBook (Book book) throws CustomException {
		try {
			Connection c = getConnection(ruta, "root", "");
			PreparedStatement pst = c.prepareStatement("insert into books values(?,?,?)");
			pst.setInt(1, book.getId());
			pst.setString(2, book.getName());
			pst.setString(3, book.getAuthor());
			pst.executeUpdate();
			c.close();
		} catch (SQLException e) {
			if (e.getErrorCode() == 1062) {
				throw new CustomException(CustomException.BOOK_ALREADY_EXISTS, "El libro ya existe");
			}
			String message = e.getMessage();
			if (message == null) message = "";
			throw new CustomException(e.getErrorCode(), message);
		}
	}

	public static Book getBook (int id) throws CustomException {
		Book book = Book.none();
		try {
			Connection c = getConnection(ruta, "root", "");
			Statement state = c.createStatement();
			ResultSet rs = state.executeQuery("SELECT * FROM `books` WHERE `ID`=" + id);
			if (rs.next()) book = new Book(rs.getInt(1), rs.getString(2), rs.getString(3));
			c.close();
			if (!book.getExists()) {
				throw new CustomException(CustomException.STUDENT_NOT_FOUND,
						"El libro con id: " + id + " no encontrado");
			}
		} catch (SQLException e) {
			String message = e.getMessage();
			if (message == null) message = "";
			throw new CustomException(e.getErrorCode(), message);
		}
		return book;
	}

	public static void updateBook (Book book) throws CustomException {
		try {
			Connection c = DriverManager.getConnection(ruta, "root", "");
			PreparedStatement state = c
					.prepareStatement("UPDATE `books` SET `NAME` = ?, `AUTHOR` = ? WHERE `books`.`ID` = ?");
			state.setString(4, book.getName());
			state.setString(5, book.getAuthor());
			state.setInt(6, book.getId());
			state.executeUpdate();
			c.close();
		} catch (SQLException e) {
			String message = e.getMessage();
			if (message == null) message = "";
			throw new CustomException(e.getErrorCode(), message);
		}
	}

	public static Book[] getAllBooks () throws CustomException {
		Book books[] = null;
		try {
			books = new Book[getCantOfClients()];
			Connection c = DriverManager.getConnection(ruta, "root", "");
			Statement state = c.createStatement();
			ResultSet rs = state.executeQuery("SELECT * FROM `books`");
			for (int i = 0; i < books.length; i++) {
				rs.next();
				books[i] = new Book(rs.getInt(1), rs.getString(2), rs.getString(3));
			}
			c.close();
		} catch (SQLException e) {
			String message = e.getMessage();
			if (message == null) message = "";
			throw new CustomException(e.getErrorCode(), message);
		}
		return books;
	}

	public static int getCantOfClients () throws SQLException {
		Connection c = DriverManager.getConnection(ruta, "root", "");
		Statement state = c.createStatement();
		ResultSet rs = state.executeQuery("SELECT COUNT(*) AS count FROM clients");
		int ret = -1;
		while (rs.next()) ret = rs.getInt("count");
		return ret;
	}

	public static int countBooks () throws CustomException {
		int ret = -1;
		try {
			Connection c = DriverManager.getConnection(ruta, "root", "");
			Statement state = c.createStatement();
			ResultSet rs = state.executeQuery("SELECT COUNT(*) AS count FROM books");
			while (rs.next()) ret = rs.getInt("count");
			c.close();
		} catch (SQLException e) {
			String message = e.getMessage();
			if (message == null) message = "";
			throw new CustomException(e.getErrorCode(), message);
		}
		return ret;
	}

	public static int countStudents () throws CustomException {
		int ret = -1;
		try {
			Connection c = DriverManager.getConnection(ruta, "root", "");
			Statement state = c.createStatement();
			ResultSet rs = state.executeQuery("SELECT COUNT(*) AS count FROM students");
			while (rs.next()) ret = rs.getInt("count");
			c.close();
		} catch (SQLException e) {
			String message = e.getMessage();
			if (message == null) message = "";
			throw new CustomException(e.getErrorCode(), message);
		}
		return ret;
	}
}

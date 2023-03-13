package co.edu.uniquindio.parcial1.model;

public class Book {

    private String isbn;
    private String title;
    private String author;

    /**
     * Es el constructor del libro
     * 
     * @param isbn
     * @param title
     * @param author
     */
    public Book(String isbn, String title, String author) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;

    }

    /**
     * Es el constructor del libro sin parámetros
     */
    public Book() {
    }

    /**
     * Obtiene el ISBN del libro
     * 
     * @return el ISBN
     */
    public String getIsbn() {
        return isbn;
    }

    /**
     * Cambia el ISBN del libro
     * 
     * @param isbn el ISBN
     */
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    /**
     * Obtiene título del libro
     * 
     * @return el título
     */
    public String getTitle() {
        return title;
    }

    /**
     * Cambia título del libro
     * 
     * @param title el título
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Obtiene el nombre del autor del libro
     * 
     * @return el nombre del autor
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Cambia el nombre del autor del libro
     * 
     * @param author el nombre del autor
     */
    public void setAuthor(String author) {
        this.author = author;
    }
}

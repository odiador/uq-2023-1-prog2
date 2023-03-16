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
    public Book(final String isbn, final String title, final String author) {
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
    public void setIsbn(final String isbn) {
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
    public void setTitle(final String title) {
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
    public void setAuthor(final String author) {
        this.author = author;
    }

    /**
     * Pregunta si el isbn es el mismo que el del libro
     * 
     * @param isbn es el isbn del libro
     * @return true si tiene el mismo isbn
     */
    public boolean hasIsbn(final String isbn) {
        return getIsbn().equals(isbn);
    }

    /**
     * Determina si el libro existe o no dependiendo de que su autor, isbn y titulo
     * no sean null
     * 
     * @return true si ninguno es null
     */
    public boolean getExists() {
        return getAuthor() != null && getIsbn() != null && getTitle() != null;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((isbn == null) ? 0 : isbn.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Book other = (Book) obj;
        if (isbn == null) {
            if (other.isbn != null)
                return false;
        } else if (!isbn.equals(other.isbn))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return getExists() ? "Book [isbn=" + isbn + ", title=" + title + ", author=" + author + "]"
                : "Book [Doesn't exists]";
    }
}

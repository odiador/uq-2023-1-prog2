package model;

public class Book {
	private int id = -1;
	private String name;
	private String creator;

	public Book(int id, String name, String author) {
		setId(id);
		setName(name);
		setAuthor(author);
	}

	public Book() {}

	public boolean getExists () {
		boolean result = true;
		if (getId() < 0) result = false;
		else if (getName() == null) result = false;
		else if (getAuthor() == null) result = false;

		return result;
	}

	public static Book none () {
		return new Book();
	}

	public int getId () {
		return id;
	}

	public void setId (int id) {
		this.id = id;
	}

	public String getName () {
		return name;
	}

	public void setName (String name) {
		this.name = name;
	}

	public String getAuthor () {
		return creator;
	}

	public void setAuthor (String creator) {
		this.creator = creator;
	}

	public static Book parse (String s) {
		if (s.equals("?")) return none();
		StringBuilder sb = new StringBuilder(s);
		sb.deleteCharAt(0);
		sb.deleteCharAt(sb.length() - 1);
		String bookInfoArr[] = sb.toString().split(",");
		int id = Integer.parseInt(bookInfoArr[0]);
		String name = bookInfoArr[1];
		String author = bookInfoArr[2];
		return new Book(id, name, author);
	}

	@Override
	public String toString () {
		return !getExists() ? "?" : "<" + getId() + "," + getName() + "," + getAuthor() + ">";
	}
}

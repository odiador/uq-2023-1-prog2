package view.custom;

/**
 * 0: error inesperado <br>
 * 10: el cliente no existe <br>
 * 2: la tarjeta ya existe <br>
 * 1: la tarjeta no existe <br>
 * -1: no puede ser negativo
 * 
 * @author juana
 *
 */
public class CustomException extends java.lang.Exception {
	public static final int STUDENT_NOT_FOUND = -1;
	public static final int STUDENT_ALREADY_EXISTS = 1;
	public static final int BOOK_ALREADY_EXISTS = 2;
	public static final int UNEXPECTED_ERROR = 0;

	private int code;
	private String causa;

	public CustomException() {
		this("Unexpected Error", UNEXPECTED_ERROR);
	}

	public CustomException(int code, String causa) {
		this(causa, code);
	}

	public CustomException(String causa, int code) {
		super(causa);
		setCausa(causa);
		setCode(code);
	}

	public int getErrorCode () {
		return code;
	}

	public void setCode (int code) {
		this.code = code;
	}

	public String getCausa () {
		return causa;
	}

	public void setCausa (String causa) {
		this.causa = causa;
	}
}

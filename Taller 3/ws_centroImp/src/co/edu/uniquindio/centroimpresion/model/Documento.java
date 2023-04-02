package co.edu.uniquindio.centroimpresion.model;

public class Documento implements Comparable<Documento> {
	private String code;
	private String titulo;
	private int prioridad;
	private String cuerpo;

	/**
	 *
	 * @param code
	 * @param titulo
	 * @param prioridad
	 * @param cuerpo
	 */
	public Documento(String code, String titulo, int prioridad, String cuerpo) {
		this.code = code;
		this.titulo = titulo;
		this.prioridad = prioridad;
		this.cuerpo = cuerpo;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public int getPrioridad() {
		return prioridad;
	}

	public void setPrioridad(int prioridad) {
		this.prioridad = prioridad;
	}

	public String getCuerpo() {
		return cuerpo;
	}

	public void setCuerpo(String cuerpo) {
		this.cuerpo = cuerpo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
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
		Documento other = (Documento) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		return true;
	}

	public int compararPorTitulo(Documento o) {
		return getTitulo().compareTo(o.getTitulo());
	}

	@Override
	public int compareTo(Documento o) {
		return o.getPrioridad() - getPrioridad();
	}

}

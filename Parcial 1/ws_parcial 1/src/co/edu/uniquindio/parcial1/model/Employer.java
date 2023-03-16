package co.edu.uniquindio.parcial1.model;

import java.util.ArrayList;
import java.util.List;

public class Employer {
    private String name;
    private Double salary;
    private String appointment;
    private final List<Lending> lendingList = new ArrayList<Lending>();
    private Integer aniosExperiencia;
    private String id;

    /**
     * Es el constructor del empleado
     * 
     * @param id
     * @param name
     * @param salary
     * @param appointment
     * @param aniosExperiencia
     */
    public Employer(final String id, final String name, final Double salary, final String appointment,
            final Integer aniosExperiencia) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.appointment = appointment;
        this.aniosExperiencia = aniosExperiencia;
    }

    /**
     * Es el constructor del empleado sin parámetros
     */
    public Employer() {

    }

    /**
     * Obtiene la lista de préstamos del empleado
     * 
     * @return la lista de préstamos
     */
    public List<Lending> getLendingList() {
        return lendingList;
    }

    /**
     * Obtiene la identificación del empleado
     * 
     * @return la identificación
     */
    public String getId() {
        return id;
    }

    /**
     * Cambia la identificación del empleado
     * 
     * @param id es la identificación
     */
    public void setId(final String id) {
        this.id = id;
    }

    /**
     * Obtiene el nombre del empleado
     * 
     * @return el nombre
     */
    public String getName() {
        return name;
    }

    /**
     * Cambia el nombre del empleado
     * 
     * @param name el nombre
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * Obtiene el salario del empleado
     * 
     * @return el salario
     */
    public Double getSalary() {
        return salary;
    }

    /**
     * Cambia el salario del empleado
     * 
     * @param salary el salario
     */
    public void setSalary(final Double salary) {
        this.salary = salary;
    }

    /**
     * Obtiene el cargo del empleado
     * 
     * @return el cargo
     */
    public String getAppointment() {
        return appointment;
    }

    /**
     * Determina si el empleado existe o no dependiendo de que su identificación,
     * nombre, cargo, salario no sea null
     * 
     * @return true si existe
     */
    public boolean getExists() {
        return getId() != null && getName() != null && getAppointment() != null && getSalary() != null
                && getAniosExperiencia() != null;
    }

    /**
     * Cambia el cargo del empleado
     * 
     * @param appointment el cargo
     */
    public void setAppointment(final String appointment) {
        this.appointment = appointment;
    }

    /**
     * Obtiene los años de experiencia del empleado
     * 
     * @return los años de experiencia
     */
    public Integer getAniosExperiencia() {
        return aniosExperiencia;
    }

    /**
     * Cambia los años de experiencia del empleado
     * 
     * @param aniosExperiencia son los años de experiencia
     */
    public void setAniosExperiencia(final Integer aniosExperiencia) {
        this.aniosExperiencia = aniosExperiencia;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final Employer other = (Employer) obj;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Employer [name=" + name + ", salary=" + salary + ", appointment=" + appointment + ", lendingList="
                + lendingList + ", aniosExperiencia=" + aniosExperiencia + ", id=" + id + "]";
    }
}

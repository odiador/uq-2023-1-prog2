package co.edu.uniquindio.parcial1.model;

import java.util.ArrayList;
import java.util.List;

public class Employer {
    private String name;
    private Double salary;
    private String appointment;
    private final List<Lending> lendingList = new ArrayList<Lending>();

    /**
     * Es el constructor del empleado
     * 
     * @param name
     * @param salary
     * @param appointment
     */
    public Employer(String name, Double salary, String appointment) {
        this.name = name;
        this.salary = salary;
        this.appointment = appointment;
    }

    /**
     * Es el constructor del empleado
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
    public void setName(String name) {
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
    public void setSalary(Double salary) {
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
     * Determina si el empleado existe o no dependiendo de que su nombre, cargo y
     * salario no sea null
     * 
     * @return true si existe
     */
    public boolean getExists() {
        return getName() != null && getAppointment() != null && getSalary() != null;
    }

    /**
     * Cambia el cargo del empleado
     * 
     * @param appointment el cargo
     */
    public void setAppointment(String appointment) {
        this.appointment = appointment;
    }

    @Override
    public String toString() {
        return "Employer [name=" + name + ", salary=" + salary + ", appointment=" + appointment + ", lendingList="
                + lendingList + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
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
        Employer other = (Employer) obj;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        return true;
    }
}

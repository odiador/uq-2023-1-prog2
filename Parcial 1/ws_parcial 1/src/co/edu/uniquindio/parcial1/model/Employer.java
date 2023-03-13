package co.edu.uniquindio.parcial1.model;

public class Employer {
    private String name;
    private Double salary;
    private String appointment;

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
     * Cambia el cargo del empleado
     * 
     * @param appointment el cargo
     */
    public void setAppointment(String appointment) {
        this.appointment = appointment;
    }
}

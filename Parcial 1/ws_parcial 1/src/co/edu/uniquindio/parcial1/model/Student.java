package co.edu.uniquindio.parcial1.model;

public class Student {
    private String name;
    private String lastName;
    private Integer age;
    private String program;
    private String state;
    private String id;

    /**
     * Es el constructor de la clase estudiante
     * 
     * @param name     el nombre del estudiante
     * @param lastName el apellido del estudiante
     * @param age      la edad del estudiante
     * @param program  el programa del estudiante
     * @param state    el estado del estudiante
     * @param id       la identificación del estudiante
     */
    public Student(String name, String lastName, Integer age, String program, String state, String id) {
        this.name = name;
        this.lastName = lastName;
        this.age = age;
        this.program = program;
        this.state = state;
        this.id = id;
    }

    /**
     * Es el constructor de la clase estudiante sin parámetros
     */
    public Student() {
    }

    /**
     * Obtiene el nombre del estudiante
     * 
     * @return el nombre
     */
    public String getName() {
        return name;
    }

    /**
     * Cambia el nombre del estudiante
     * 
     * @param name el nombre
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Obtiene el apellido del estudiante
     * 
     * @return el apellido
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Cambia el apellido del estudiante
     * 
     * @param lastName el apellido
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Obtiene la edad del estudiante
     * 
     * @return la edad
     */
    public Integer getAge() {
        return age;
    }

    /**
     * Cambia la edad del estudiante
     * 
     * @param age la edad
     */
    public void setAge(Integer age) {
        this.age = age;
    }

    /**
     * Obtiene el programa del estudiante
     * 
     * @return el programa
     */
    public String getProgram() {
        return program;
    }

    /**
     * Cambia el programa del estudiante
     * 
     * @param program el programa
     */
    public void setProgram(String program) {
        this.program = program;
    }

    /**
     * Obtiene el estado del estudiante
     * 
     * @return el estado
     */
    public String getState() {
        return state;
    }

    /**
     * Cambia el estado del estudiante
     * 
     * @param state el estado
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * Obtiene la identificación del estudiante
     * 
     * @return la identificación
     */
    public String getId() {
        return id;
    }

    /**
     * Cambia la identificación del estudiante
     * 
     * @param id la identificación
     */
    public void setId(String id) {
        this.id = id;
    }
}

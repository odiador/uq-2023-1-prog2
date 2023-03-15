package co.edu.uniquindio.parcial1.model;

import java.util.ArrayList;
import java.util.List;

public class Student {
    private String name;
    private String lastName;
    private Integer age;
    private String program;
    private String state;
    private String id;
    private final List<Lending> lendingList = new ArrayList<Lending>();

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
    public Student(final String name, final String lastName, final Integer age, final String program, final String state, final String id) {
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
     * Obtiene la lista de préstamos del estudiante
     * 
     * @return la lista de préstamos
     */
    public List<Lending> getLendingList() {
        return lendingList;
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
    public void setName(final String name) {
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
    public void setLastName(final String lastName) {
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
    public void setAge(final Integer age) {
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
    public void setProgram(final String program) {
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
    public void setState(final String state) {
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
    public void setId(final String id) {
        this.id = id;
    }
}

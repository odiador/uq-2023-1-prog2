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
    public Student(final String name, final String lastName, final Integer age, final String program,
            final String state, final String id) {
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
     * Busca un préstamo a partir de su código {@code code}, si no se encuentra se
     * retorna un préstamo sin parámetros (que no existe)
     * 
     * @param code es el código del préstamo
     * @return el préstamo
     */
    public Lending searchLending(final String code) {
        return getLendingList().stream().filter(lending -> lending.hasCode(code)).findFirst().orElse(new Lending());
    }

    /**
     * Busca un préstamo a partir de su código {@code code}
     * 
     * @param code es el código del préstamo
     * @return el préstamo
     * @throws LibraryException si no se encuentra el préstamo: "El préstamo no fue
     *                          encontrado ( + {@code code} + )"
     * @see {@link #searchLending(String)}
     */
    public Lending searchLendingOrThrow(final String code) throws LibraryException {
        final Lending lending = searchLending(code);
        if (!lending.getExists()) {
            throw new LibraryException("El préstamo no fue encontrado (" + code + ")");
        }
        return lending;
    }

    /**
     * Valida si un préstamo existe o no a partir de su código {@code code}
     * 
     * @param code es el código del préstamo
     * @return true si se encuentra
     */
    public boolean validateLending(final String code) {
        return searchLending(code).getExists();
    }

    /**
     * Agrega un préstamo ya terminado a la lista de préstamos del estudiante
     * 
     * @param lending
     * @return
     * @throws LibraryException
     */
    public String addLending(final Lending lending)
            throws LibraryException {
        final String code = lending.getCode();
        if (validateLending(code)) {
            throw new LibraryException("El préstamo ya existe (" + code + ")");
        }
        if (!lending.isEnded()) {
            throw new LibraryException("El préstamo no ha sido términado (" + code + ")");
        }
        getLendingList().add(lending);
        return "Se agregó un nuevo préstamo (" + code + ")";
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

    /**
     * Determina si un estudiante existe dependiendo de que su id, nombre, apellido,
     * edad, programa y estado no sean null
     * 
     * @return true si ninguno es null
     * @see {@link #getId()}
     *      <li>{@link #getName()}
     *      <li>
     */
    public boolean getExists() {
        return getId() != null && getName() != null && getLastName() != null && getAge() != null && getProgram() != null
                && getState() != null;
    }

    /**
     * Determina si el estudiante tiene una identificación especificada
     * 
     * @param id es la identificación a comparar
     * @return true si son las mismas ids
     */
    public boolean hasId(String id) {
        return getId().equals(id);
    }
}
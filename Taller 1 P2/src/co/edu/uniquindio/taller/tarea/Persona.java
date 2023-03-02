package co.edu.uniquindio.taller.tarea;

public class Persona {

    private String id;
    private String name;
    private String lastName;
    private Integer age;

    /**
     * Es el constructor de una persona
     * 
     * @param id
     * @param name
     * @param lastName
     * @param age
     */
    public Persona(String id, String name, String lastName, Integer age) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.age = age;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}

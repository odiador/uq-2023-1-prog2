package co.edu.uniquindio.taller.punto1;

public abstract class Persona {
    private String name;
    private Integer age;

    public Persona(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    /**
     * Obtiene el nombre de la persona
     * 
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * Cambia el nombre de la persona
     * 
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Obtiene la edad de la persona
     * 
     * @return
     */
    public Integer getAge() {
        return age;
    }

    /**
     * Cambia la edad de la persona
     * 
     * @param age
     */
    public void setAge(Integer age) {
        this.age = age;
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
        Persona other = (Persona) obj;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Persona [name=" + name + ", age=" + age + "]";
    }
}

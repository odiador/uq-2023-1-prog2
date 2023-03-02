package co.edu.uniquindio.taller.punto1;

import java.util.ArrayList;
import java.util.List;

public class Directivo extends Empleado {
    private String categoria;
    private List<Empleado> listaSubordinados;

    /**
     * Es el constructor del directivo
     * 
     * @param categoria
     * @param name
     * @param age
     * @param sueldoBruto
     */
    public Directivo(String name, Integer age, Double sueldoBruto, String categoria) {
        super(name, age, sueldoBruto);
        this.categoria = categoria;
        this.listaSubordinados = new ArrayList<Empleado>();
    }

    public List<Empleado> getListaSubordinados() {
        return listaSubordinados;
    }

    public void setListaSubordinados(List<Empleado> listaSubordinados) {
        this.listaSubordinados = listaSubordinados;
    }

    /**
     * Obtiene la categoría del directivo
     * 
     * @return
     */
    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

}

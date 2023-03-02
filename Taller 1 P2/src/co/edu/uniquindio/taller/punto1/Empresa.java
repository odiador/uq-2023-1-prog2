package co.edu.uniquindio.taller.punto1;

import java.util.ArrayList;
import java.util.List;

public class Empresa {

    private List<Persona> listaPersonas;

    private String name;

    public Empresa(String name) {
        this.name = name;
        this.listaPersonas = new ArrayList<Persona>();

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Persona> getListaPersonas() {
        return listaPersonas;
    }

    public void setListaPersonas(List<Persona> listaPersonas) {
        this.listaPersonas = listaPersonas;
    }
}

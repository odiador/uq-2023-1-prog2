package co.edu.uniquindio.taller.punto1;

import java.util.List;

public class Empleado extends Persona {

    private Double sueldoBruto;
    private List<Directivo> listaDirectivos;

    public Empleado(String name, Integer age, Double sueldoBruto) {
        super(name, age);
        this.sueldoBruto = sueldoBruto;
    }

    public Double getSueldoBruto() {
        return sueldoBruto;
    }

    public void setSueldoBruto(Double sueldoBruto) {
        this.sueldoBruto = sueldoBruto;
    }

    public List<Directivo> getListaDirectivos() {
        return listaDirectivos;
    }

    public void setListaDirectivos(List<Directivo> listaDirectivos) {
        this.listaDirectivos = listaDirectivos;
    }

}

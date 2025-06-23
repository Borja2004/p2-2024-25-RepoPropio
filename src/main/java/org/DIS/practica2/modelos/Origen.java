package org.DIS.practica2.modelos;

public class Origen {
    private String provincia;
    private String comunidad;

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getComunidad() {
        return comunidad;
    }

    public void setComunidad(String comunidad) {
        this.comunidad = comunidad;
    }

    public Origen(String comunidad, String provincia) {
        this.comunidad = comunidad;
        this.provincia = provincia;
    }

    @Override
    public String toString() {
        return "Origen{" +
                "provincia='" + provincia + '\'' +
                ", comunidad='" + comunidad + '\'' +
                '}';
    }
}
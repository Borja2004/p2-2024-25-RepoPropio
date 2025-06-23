package org.DIS.practica2.modelos;

public class Periodo {
    private String fecha_inicio;
    private String period;
    private String fecha_fin;

    public String getPeriodo() {
        return period;
    }

    public void setPeriodo(String periodo) {
        this.period = periodo;
    }

    public String getFecha_inicio() {
        return fecha_inicio;
    }

    public void setFecha_inicio(String fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public String getFecha_fin() {
        return fecha_fin;
    }

    public void setFecha_fin(String fecha_fin) {
        this.fecha_fin = fecha_fin;
    }

    public Periodo(String fecha_inicio, String fecha_fin) {
        this.fecha_inicio = fecha_inicio;
        this.fecha_fin = fecha_fin;
        String[] fecha_inicio_splited = fecha_inicio.split("-");
        this.period = fecha_inicio_splited[0] + "M" + fecha_inicio_splited[1];
    }

    @Override
    public String toString() {
        return "Periodo{" +
                "fecha_inicio='" + fecha_inicio + '\'' +
                ", periodo='" + period + '\'' +
                ", fecha_fin='" + fecha_fin + '\'' +
                '}';
    }
}
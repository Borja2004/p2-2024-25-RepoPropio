package org.DIS.practica2.modelos;

import java.time.LocalDate;

public class Periodo {
    private String fecha_inicio;
    private String fecha_fin;
    private String period;

    public Periodo(String fecha_inicio, String fecha_fin) {
        this.fecha_inicio = fecha_inicio;
        this.fecha_fin = fecha_fin;

        // Calcular el periodo en formato "YYYYMM"
        String[] partes = fecha_inicio.split("-");
        if (partes.length >= 2) {
            this.period = partes[0] + "M" + partes[1];
        } else {
            this.period = "";
        }
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

    public String getPeriodo() {
        return period;
    }

    public void setPeriodo(String period) {
        this.period = period;
    }

    // Métodos auxiliares para trabajar con LocalDate
    public LocalDate getLocalDateInicio() {
        return LocalDate.parse(fecha_inicio);
    }

    public LocalDate getLocalDateFin() {
        return LocalDate.parse(fecha_fin);
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

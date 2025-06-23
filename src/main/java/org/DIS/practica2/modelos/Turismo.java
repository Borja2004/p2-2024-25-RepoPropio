package org.DIS.practica2.modelos;

import com.google.gson.annotations.SerializedName;
import org.DIS.practica2.modelos.Destino;
import org.DIS.practica2.modelos.Origen;
import org.DIS.practica2.modelos.Periodo;

import java.util.UUID;

public class Turismo {

    private int total;

    @SerializedName("_id")
    private String id;

    @SerializedName("destino")
    private Destino destino;

    @SerializedName("periodo")
    private Periodo periodo;

    @SerializedName("origen")
    private Origen origen;

    public Turismo(Origen origen, Destino destino, Periodo periodo, int total) {
        this.origen = origen;
        this.destino = destino;
        this.periodo = periodo;
        this.id = UUID.randomUUID().toString();
        this.total = total;
    }

    public Turismo(Origen origen, Destino destino, Periodo periodo, int total, String id) {
        this.origen = origen;
        this.destino = destino;
        this.periodo = periodo;
        this.id = id;
        this.total = total;
    }

    // Getters
    public String getId() {
        return id;
    }

    public Origen getOrigen() {
        return origen;
    }

    public Destino getDestino() {
        return destino;
    }

    public Periodo getPeriodo() {
        return periodo;
    }

    public int getTotal() {
        return total;
    }

    // Setters
    public void setId(String id) {
        this.id = id;
    }

    public void setOrigen(Origen origen) {
        this.origen = origen;
    }

    public void setDestino(Destino destino) {
        this.destino = destino;
    }

    public void setPeriodo(Periodo periodo) {
        this.periodo = periodo;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "Turismo{" +
                "id='" + id + '\'' +
                ", origen=" + origen +
                ", destino=" + destino +
                ", periodo=" + periodo +
                ", total=" + total +
            '}';
    }
}

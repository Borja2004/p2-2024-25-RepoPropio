package dis.ufv.practica2.api.models;

import java.util.UUID;

/**
 * Clase que representa un viaje turístico con origen, destino, periodo y total de viajeros.
 */
public class Turismo {

    private String _id;
    private Origen from;           // Origen del viaje
    private Destino to;           // Destino del viaje
    private Periodo timeRange;    // Periodo del viaje
    private int total;            // Total de personas

    // Constructor: se genera un ID aleatorio automáticamente
    public Turismo(Origen from, Destino to, Periodo timeRange, int total) {
        this.from = from;
        this.to = to;
        this.timeRange = timeRange;
        this.total = total;
        this._id = UUID.randomUUID().toString();
    }

    // Getters
    public String get_id() {
        return _id;
    }

    public Origen getOrigen() {
        return from;
    }

    public Destino getDestino() {
        return to;
    }

    public Periodo getPeriodo() {
        return timeRange;
    }

    public int getTotal() {
        return total;
    }

    // Setters
    public void set_id(String _id) {
        this._id = _id;
    }

    public void setOrigen(Origen from) {
        this.from = from;
    }

    public void setDestino(Destino to) {
        this.to = to;
    }

    public void setPeriodo(Periodo timeRange) {
        this.timeRange = timeRange;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "Turismo{" +
                "_id='" + _id + '\'' +
                ", origen=" + from +
                ", destino=" + to +
                ", periodo=" + timeRange +
                ", total=" + total +
            '}';
}
}

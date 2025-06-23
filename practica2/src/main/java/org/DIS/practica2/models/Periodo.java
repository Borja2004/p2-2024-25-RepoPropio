package dis.ufv.practica2.api.models;

/**
 * Clase que representa el periodo de un viaje turístico.
 */
public class Periodo {
    private String fecha_inicio;
    private String fecha_fin;
    private String periodo;  // Texto descriptivo adicional (opcional)

    // Constructor
    public Periodo(String fecha_inicio, String fecha_fin, String periodo) {
        this.fecha_inicio = fecha_inicio;
        this.fecha_fin = fecha_fin;
        this.periodo = periodo;
    }

    // Getters
    public String getFecha_inicio() {
        return fecha_inicio;
    }

    public String getFecha_fin() {
        return fecha_fin;
    }

    public String getPeriodo() {
        return periodo;
    }

    // Setters
    public void setFecha_inicio(String fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public void setFecha_fin(String fecha_fin) {
        this.fecha_fin = fecha_fin;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    @Override
    public String toString() {
        return "Periodo{" +
                "fecha_inicio='" + fecha_inicio + '\'' +
                ", fecha_fin='" + fecha_fin + '\'' +
                ", periodo='" + periodo + '\'' +
            '}';
}
}

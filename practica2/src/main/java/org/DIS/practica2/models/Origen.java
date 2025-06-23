package dis.ufv.practica2.api.models;

/**
 * Clase que representa el origen de un viaje turístico.
 */
public class Origen {
    private String provincia;
    private String comunidad;

    // Constructor
    public Origen(String provincia, String comunidad) {
        this.provincia = provincia;
        this.comunidad = comunidad;
    }

    // Getters
    public String getProvincia() {
        return provincia;
    }

    public String getComunidad() {
        return comunidad;
    }

    // Setters
    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public void setComunidad(String comunidad) {
        this.comunidad = comunidad;
    }

    @Override
    public String toString() {
        return "Origen{" +
                "provincia='" + provincia + '\'' +
                ", comunidad='" + comunidad + '\'' +
            '}';
}
}

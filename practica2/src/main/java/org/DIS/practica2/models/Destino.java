package dis.ufv.practica2.api.models;

/**
 * Clase que representa el destino de un viaje turístico.
 */
public class Destino {
    private String comunidad;
    private String provincia;

    // Constructor
    public Destino(String comunidad, String provincia) {
        this.comunidad = comunidad;
        this.provincia = provincia;
    }

    // Getters
    public String getComunidad() {
        return comunidad;
    }

    public String getProvincia() {
        return provincia;
    }

    // Setters
    public void setComunidad(String comunidad) {
        this.comunidad = comunidad;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    @Override
    public String toString() {
        return "Destino{" +
                "comunidad='" + comunidad + '\'' +
                ", provincia='" + provincia + '\'' +
            '}';
}
}

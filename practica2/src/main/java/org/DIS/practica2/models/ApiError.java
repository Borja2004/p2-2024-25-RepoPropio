package dis.ufv.practica2.api.models;

/**
 * Clase para representar errores personalizados en la API.
 */
public class ApiError {
    private int statusCode;
    private String message;

    public ApiError(int statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }

    // Getters
    public int getStatusCode() {
        return statusCode;
    }

    public String getMessage() {
        return message;
    }

    // Setters
    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Error 404 - Recurso no encontrado.
     */
    public static ApiError notFound(String mensaje) {
        return new ApiError(404, mensaje);
}
}

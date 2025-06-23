package dis.ufv.practica2.api.services;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import dis.ufv.practica2.api.models.Turismo;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

/**
 * Servicio encargado de gestionar la lectura y escritura de datos turísticos desde un fichero JSON local.
 */
@Service
public class LocalJSONAPIService {

    private static final String TURISMO_JSON_PATH = "src/main/resources/TurismoComunidades.json";

    // Añadir un nuevo turismo al fichero (Create)
    public Turismo crearTurismo(Turismo turismo) {
        turismo.set_id(UUID.randomUUID().toString());

        ArrayList<Turismo> lista = leeFicheroJson();
        lista.add(turismo);

        if (escribirJson(TURISMO_JSON_PATH, lista)) {
            System.out.println("Turismo añadido correctamente.");
            return turismo;
        }
        return null;
    }

    // Leer el fichero JSON (Read)
    public ArrayList<Turismo> leeFicheroJson() {
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(TURISMO_JSON_PATH))) {
            return new Gson().fromJson(reader, new TypeToken<ArrayList<Turismo>>() {}.getType());
        } catch (IOException e) {
            System.out.println("Error leyendo el archivo JSON: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    // Leer un fichero específico JSON
    public ArrayList<Turismo> leerFicheroJson(String path) {
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(path))) {
            return new Gson().fromJson(reader, new TypeToken<ArrayList<Turismo>>() {}.getType());
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    // Guardar lista actualizada en el JSON
    private boolean escribirJson(String path, ArrayList<Turismo> lista) {
        try (Writer writer = Files.newBufferedWriter(Paths.get(path))) {
            new Gson().toJson(lista, writer);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Buscar turismo por ID
    public Turismo getTurismo(String id) {
        for (Turismo turismo : leeFicheroJson()) {
            if (turismo.get_id().equals(id)) {
                return turismo;
            }
        }
        return null;
    }

    // Actualizar un turismo existente (Update)
    public Turismo actualizarTurismo(String id, Turismo actualizado) {
        ArrayList<Turismo> lista = leeFicheroJson();
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).get_id().equals(id)) {
                actualizado.set_id(id);  // Mantener el mismo ID
                lista.set(i, actualizado);
                escribirJson(TURISMO_JSON_PATH, lista);
                System.out.println("Turismo actualizado.");
                return actualizado;
            }
        }
        System.out.println("No se encontró el turismo con ID: " + id);
        return null;
    }

    // Eliminar un turismo por ID (Delete)
    public boolean eliminarTurismo(String id) {
        ArrayList<Turismo> lista = leeFicheroJson();
        boolean eliminado = lista.removeIf(t -> t.get_id().equals(id));

        if (eliminado && escribirJson(TURISMO_JSON_PATH, lista)) {
            System.out.println("Turismo eliminado.");
            return true;
        } else {
            System.out.println("No se pudo eliminar el turismo con ID: " + id);
            return false;
        }
    }

    // Leer lista de comunidades desde un archivo y devolverlas ordenadas
    public ArrayList<String> leerComunidades(String path) {
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(path))) {
            JsonObject json = JsonParser.parseReader(reader).getAsJsonObject();
            ArrayList<String> comunidades = new ArrayList<>(json.keySet());
            Collections.sort(comunidades);
            return comunidades;
        } catch (IOException e) {
            throw new RuntimeException("Error leyendo comunidades: " + e.getMessage());
        }
    }

    // Obtener turismos pertenecientes a una comunidad específica
    public ArrayList<Turismo> getTurismoByComunidad(String comunidad, String path) {
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(path))) {
            JsonObject json = JsonParser.parseReader(reader).getAsJsonObject();
            JsonArray array = json.getAsJsonArray(comunidad);

            if (array == null) return new ArrayList<>();

            ArrayList<Turismo> resultado = new ArrayList<>();
            for (JsonElement elemento : array) {
                resultado.add(new Gson().fromJson(elemento, Turismo.class));
            }
            return resultado;
        } catch (IOException e) {
            throw new RuntimeException("Error obteniendo turismos por comunidad: " + e.getMessage());
 }
}
}

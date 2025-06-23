package dis.ufv.practica2.api.controllers;

import dis.ufv.practica2.api.models.Turismo;
import dis.ufv.practica2.api.models.ApiError;
import dis.ufv.practica2.api.services.LocalJSONAPIService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api")
public class LocalJSONController {

    private final LocalJSONAPIService servicio;

    public LocalJSONController(LocalJSONAPIService servicio) {
        this.servicio = servicio;
    }

    // Obtener todos los registros desde el fichero JSON
    @GetMapping("/db")
    public ResponseEntity<?> obtenerTodos() {
        ArrayList<Turismo> turismos = servicio.leeFicheroJson();

        if (turismos != null) {
            return ResponseEntity.ok(turismos);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ApiError.notFound("No se pudo leer el archivo JSON"));
        }
    }

    // Obtener un turismo por ID
    @GetMapping("/db/{id}")
    public ResponseEntity<?> obtenerPorId(@PathVariable String id) {
        Turismo turismo = servicio.getTurismo(id);

        if (turismo != null) {
            return ResponseEntity.ok(turismo);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ApiError.notFound("Turismo no encontrado con ID: " + id));
        }
    }

    // Eliminar un turismo por ID
    @DeleteMapping("/db/{id}")
    public ResponseEntity<?> eliminar(@PathVariable String id) {
        boolean eliminado = servicio.eliminarTurismo(id);

        if (eliminado) {
            return ResponseEntity.ok(servicio.leeFicheroJson());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ApiError.notFound("No se encontró turismo con ID: " + id));
        }
    }

    // Crear un nuevo turismo
    @PostMapping(path = "/db",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Turismo> crear(@RequestBody Turismo nuevo) {
        Turismo creado = servicio.crearTurismo(nuevo);
        return new ResponseEntity<>(creado, HttpStatus.CREATED);
    }

    // Actualizar un turismo por ID
    @PutMapping(path = "/db/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> actualizar(@PathVariable String id, @RequestBody Turismo actualizado) {
        Turismo turismo = servicio.actualizarTurismo(id, actualizado);

        if (turismo != null) {
            return ResponseEntity.ok(turismo);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ApiError.notFound("No se encontró turismo con ID: " + id));
        }
    }

    // Obtener lista de comunidades desde un archivo local
    @GetMapping("/comunidades")
    public ResponseEntity<ArrayList<String>> obtenerComunidades() {
        ArrayList<String> comunidades = servicio.leerComunidades("src/main/resources/Comunidades_Agrupadas.json");
        return ResponseEntity.ok(comunidades);
    }

    // Obtener turismos por comunidad desde archivo local
    @GetMapping("/comunidades/{comunidad}")
    public ResponseEntity<ArrayList<Turismo>> obtenerPorComunidad(@PathVariable String comunidad) {
        ArrayList<Turismo> resultado = servicio.getTurismoByComunidad(comunidad, "src/main/resources/Comunidades_Agrupadas.json");
        return ResponseEntity.ok(resultado);
}
}
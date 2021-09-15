package ar.com.ada.api.noaa.controllers;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import ar.com.ada.api.noaa.entities.Boya;
import ar.com.ada.api.noaa.models.request.FaroColorRequest;
import ar.com.ada.api.noaa.models.response.GenericResponse;
import ar.com.ada.api.noaa.services.BoyaService;

@RestController

public class BoyaController {
    @Autowired
    private BoyaService service;

    @PostMapping("/api/boyas")
    public ResponseEntity<GenericResponse> crearBoya(@RequestBody Boya boya) {
        GenericResponse r = new GenericResponse();

        service.crearBoya(boya);

        r.isOk = true;
        r.id = boya.getBoyaId();
        r.message = "La Boya se ha creado correctamente";

        return ResponseEntity.ok(r);
    }

    @GetMapping("api/boyas")
    public ResponseEntity<List<Boya>> listarBoyas() {
        return ResponseEntity.ok(service.obtenerBoyas());
    }

    @GetMapping("api/boyas/{id}")
    public ResponseEntity<?> traerBoyaById(@PathVariable Integer id) {

        GenericResponse r = new GenericResponse();

        if (!service.validarBoyaExiste(id)) {
            r.isOk = false;
            r.message = "El Id de la boya ingresada no es válido.";
            return ResponseEntity.badRequest().body(r);
        }
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PutMapping(("api/boyas/{id}"))
    public ResponseEntity<GenericResponse> putActualizarFaroColor(@PathVariable Integer id,
            @RequestBody FaroColorRequest faroColor) {

        GenericResponse r = new GenericResponse();
        r.isOk = true;

        Boya boya = service.buscarPorId(id);
        boya.setColor(faroColor.color);
        service.actualizar(boya);

        r.message = "actualizado correctamente";
        return ResponseEntity.ok(r);
    }

}

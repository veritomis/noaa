package ar.com.ada.api.noaa.controllers;


import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import ar.com.ada.api.noaa.entities.Boya;
import ar.com.ada.api.noaa.models.GenericResponse;
import ar.com.ada.api.noaa.models.request.NivelDelMarRequest;
import ar.com.ada.api.noaa.services.BoyaService;

@RestController

public class BoyaController {
    @Autowired
    private BoyaService service;

    @PostMapping("/api/boyas")
    public ResponseEntity<GenericResponse> crearBoya(@RequestBody Boya boya)  {
        GenericResponse respuesta = new GenericResponse();

           
             service.crearBoya(boya);

            respuesta.isOk = true;
            respuesta.id = boya.getBoyaId();
            respuesta.message = "Boya creada correctamente";

            return ResponseEntity.ok(respuesta);
    }

    @GetMapping("api/boyas")
    public ResponseEntity<List<Boya>> listarBoyas() {
        return ResponseEntity.ok(service.obtenerBoyas());
    }

    @GetMapping("api/boyas/{id}")
    public ResponseEntity<?> traerBoyaById(@PathVariable Integer id) {
        
        GenericResponse respuesta = new GenericResponse();

        if (!service.validarBoyaExiste(id)) {
            respuesta.isOk = false;
            respuesta.message = "El Id de la boya ingresada no es válido.";
            return ResponseEntity.badRequest().body(respuesta);
        }
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PutMapping(("/boyas/{id}"))
    public ResponseEntity<GenericResponse> putActualizarNivelDelMar(@PathVariable Integer id,
            @RequestBody NivelDelMarRequest nivelDeMar) {

        GenericResponse r = new GenericResponse();
        r.isOk = true;

       Boya boya = service.buscarPorId(id);
        boya.setColor(nivelDeMar.color);
        service.actualizar(boya);

        r.message = "actualizado";
        return ResponseEntity.ok(r);
    }

    
}

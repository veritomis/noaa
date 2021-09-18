package ar.com.ada.api.noaa.controllers;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ar.com.ada.api.noaa.entities.Muestra;
import ar.com.ada.api.noaa.models.request.InfoMuestra;
import ar.com.ada.api.noaa.models.response.*;
import ar.com.ada.api.noaa.services.*;

@RestController
public class MuestraController {

    @Autowired
    private MuestraService service;

    @PostMapping("api/muestras")
    public ResponseEntity<?> registrarMuestra(@RequestBody InfoMuestra info) {
        MuestraResponse r = new MuestraResponse();

        Muestra muestra = service.registrarMuestra(info.boyaId, info.fecha, info.matricula, info.longitudActual,
                info.latitudActual, info.altura);

        // respuesta.isOk = true;
        r.id = muestra.getMuestraId();
        r.color = muestra.getBoya().getColor();
        r.message = "La muestra se registró correctamente";

        return ResponseEntity.ok(r);
    }

    @GetMapping("api/muestras/boyas/{boyaId}")
    public ResponseEntity<?> traerMuestrasByBoyaId(@PathVariable Integer boyaId) {

        return ResponseEntity.ok(service.muestrasPorBoyaId(boyaId));
    }


    @GetMapping("api/muestras/colores/{color}")
    public ResponseEntity<List<ColorResponse>> buscarByColor(@PathVariable String color) {

        return ResponseEntity.ok(service.buscarByColor(color));
    }


    @GetMapping("muestras/minima/{boyaId}")
    public ResponseEntity<AlturaMinima> alturamin(@PathVariable Integer boyaId) {

        Muestra muestra = service.alturamin(boyaId);
        AlturaMinima alturamin = new AlturaMinima();

        alturamin.color = muestra.getBoya().getColor();
        alturamin.altura = muestra.getAltura();
        alturamin.horario = muestra.getHorario();

        return ResponseEntity.ok(alturamin);
    }

    @DeleteMapping("api/muestras/{id}")
    public ResponseEntity<GenericResponse> eliminar(@PathVariable Integer id, Integer boyaId) {

        GenericResponse r = new GenericResponse();
        if (service.validarMuestraExiste(id)) {
            service.eliminarMuestraPorId(id, boyaId);

            r.isOk = true;
            r.message = "La muestra ha sido reseteada. El color de la boya es Azul.";
            return ResponseEntity.ok(r);

        } else {
            r.isOk = false;
            r.message = "La muestra no existe";
            return ResponseEntity.badRequest().body(r);

        }

    }

}

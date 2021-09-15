package ar.com.ada.api.noaa.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ar.com.ada.api.noaa.entities.Boya;
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
        MuestraResponse respuesta = new MuestraResponse();

        Muestra muestra = service.registrarMuestra(info.boyaId, info.fecha, info.matricula, info.longitudActual,
                info.latitudActual, info.altura);

        // respuesta.isOk = true;
        respuesta.id = muestra.getMuestraId();
        respuesta.color = muestra.getBoya().getColor();
        respuesta.message = "La muestra se registró correctamente";

        return ResponseEntity.ok(respuesta);
    }

    @GetMapping("api/muestras/boyas/{boyaId}")
    public ResponseEntity<List<Muestra>> traerMuestrasByBoyaId(@PathVariable Integer boyaId) {

        return ResponseEntity.ok(service.muestrasPorBoyaId(boyaId));
    }

    @GetMapping("api/muestras/colores/{color}")
    public ResponseEntity<List<Muestra>> traerMuestrasByColor(@PathVariable String color) {
        // Muestra muestraColor = service.traerMuestrasByColor(color, null, null)
        /*
         * GenericResponse r = new GenericResponse();
         * 
         * if(muestraColor == null) { r.isOk = false; r.message =
         * "No se reconoce el color ingresado"; return
         * ResponseEntity.badRequest().body(r); }else {
         */
        return ResponseEntity.ok(service.muestrasByColor(color));
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

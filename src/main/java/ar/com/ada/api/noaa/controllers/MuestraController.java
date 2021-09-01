package ar.com.ada.api.noaa.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ar.com.ada.api.noaa.entities.Muestra;
import ar.com.ada.api.noaa.models.request.InfoMuestra;
import ar.com.ada.api.noaa.models.response.MuestraResponse;
import ar.com.ada.api.noaa.services.MuestraService;

@RestController
public class MuestraController {

    @Autowired
    private MuestraService service;

    @PostMapping("api/muestras")
    public ResponseEntity<?> registrarMuestra(@RequestBody InfoMuestra info)  {
        MuestraResponse respuesta = new MuestraResponse();

           
           Muestra muestra = service.registrarMuestra(info.boyaId, info.fecha, info.matricula, info.longitudActual, info.latitudActual, info.altura);

            //respuesta.isOk = true;
            respuesta.id = muestra.getMuestraId();
            respuesta.color = muestra.getBoya().getColor();
           // respuesta.message = "La muestra se registró correctamente";

            return ResponseEntity.ok(respuesta);
    }

    
}

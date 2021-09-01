package ar.com.ada.api.noaa.services;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.ada.api.noaa.entities.Boya;
import ar.com.ada.api.noaa.entities.Muestra;
import ar.com.ada.api.noaa.entities.Boya.FaroColorEnum;
import ar.com.ada.api.noaa.repos.MuestraRepository;

@Service

public class MuestraService {

    @Autowired
    MuestraRepository repository;

    @Autowired
    BoyaService boyaService;

    public Muestra registrarMuestra(Integer boyaId, Date fecha, String matricula, Double longitudActual,
            Double latitudActual, Double altura) {

        Muestra muestra = new Muestra();

        Boya boya = boyaService.buscarPorId(boyaId);

        muestra.setFecha(fecha);
        muestra.setMatricula(matricula);
        muestra.setLongitudActual(longitudActual);
        muestra.setLatitudActual(latitudActual);
        muestra.setAltura(altura);

        if (muestra.getAltura() < -100 || muestra.getAltura() > 100) {
            boya.setColor(FaroColorEnum.ROJO);
        }
        if (muestra.getAltura() < -50 || muestra.getAltura() > 50) {
            boya.setColor(FaroColorEnum.AMARILLO);
        } else {
            boya.setColor(FaroColorEnum.VERDE);
        }

        boya.agregarMuestra(muestra);

        
        return repository.save(muestra);
    }

    public Muestra buscarPorId(Integer id) {
        return repository.findByMuestraId(id);
    }



    /*
     * altura > 50 AMARILLO altura > 100 ROJO ALTURA < -50 AMARILLO ALTURA < -100
     * ROJO ALTURA = 50 VERDE ok
     */

}

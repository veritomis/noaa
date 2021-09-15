package ar.com.ada.api.noaa.services;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.ada.api.noaa.entities.*;
import ar.com.ada.api.noaa.models.response.ColorResponse;
import ar.com.ada.api.noaa.repos.BoyaRepository;
import ar.com.ada.api.noaa.repos.MuestraRepository;

@Service

public class MuestraService {

    @Autowired
    MuestraRepository repository;

    @Autowired
    BoyaRepository boyaRepository;

    @Autowired
    BoyaService boyaService;

    public Muestra registrarMuestra(Integer boyaId, Date horario, String matricula, Double longitudActual,
            Double latitudActual, Double altura) {

        Muestra muestra = new Muestra();

        Boya boya = boyaService.buscarPorId(boyaId);

        muestra.setHorario(horario);
        muestra.setMatricula(matricula);
        muestra.setLongitudActual(longitudActual);
        muestra.setLatitudActual(latitudActual);
        muestra.setAltura(altura);

        if (muestra.getAltura() < -100 || muestra.getAltura() > 100) {
            boya.setColor("ROJO");
        }
        if (muestra.getAltura() < -50 || muestra.getAltura() > 50) {
            boya.setColor("AMARILLO");
        } else {
            boya.setColor("VERDE");
        }

        boya.agregarMuestra(muestra);

        return repository.save(muestra);
    }

    public Muestra buscarPorId(Integer id) {
        return repository.findByMuestraId(id);
    }

    public List<Muestra> muestrasPorBoyaId(Integer boyaId) {
        Boya boya = boyaService.buscarPorId(boyaId);
        return boya.getMuestras();
    }

    public void eliminarMuestraPorId(Integer id, Integer boyaId) {
        repository.deleteById(id);
        Boya boya = boyaService.buscarPorId(boyaId);
        boya.setColor("AZUL");

    }

    public boolean validarMuestraExiste(Integer id) {
        Muestra muestra = repository.findByMuestraId(id);
        if (muestra != null) {
            return true;
        } else
            return false;
    }

    public List<Muestra> muestrasByColor(String color) {
        Boya boya = boyaService.buscarPorColor(color);
        return boya.getMuestras();
    }

}

// List<ColorResponse>colorResponse = new ArrayList<>();
/*
 * ColorResponse colorMuestra = new ColorResponse();
 * 
 * for (Muestra muestra : repository.findAll()){
 * if(muestra.getBoya().getColor().equals(color)){
 * 
 * colorMuestra.setAltura(altura); colorMuestra.setBoyaId(boyaId);
 * 
 * colorMuestras.add(colorMuestra); } } return colorMuestras;
 * 
 * }
 */

/*
 * public boolean validarColor(Integer color) { Boya boya =
 * repository.findByColor(color); if(boya.getColor().equals(color)){ return
 * true; } else return false; }
 */

/*
 * altura > 50 AMARILLO altura > 100 ROJO ALTURA < -50 AMARILLO ALTURA < -100
 * ROJO ALTURA = 50 VERDE ok
 */

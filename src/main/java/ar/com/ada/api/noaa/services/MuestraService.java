package ar.com.ada.api.noaa.services;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.ada.api.noaa.entities.Boya;
import ar.com.ada.api.noaa.entities.Muestra;
import ar.com.ada.api.noaa.repos.MuestraRepository;

@Service

public class MuestraService {

    @Autowired
    MuestraRepository repository;

    @Autowired
    BoyaService boyaService;

    //public void registrarMuestra(Muestra muestra) {

    //}

    public Integer registrarMuestra(Integer boyaId, Date fecha, String matricula, Double longitudActual, Double latitudActual, Double altura){

        Muestra muestra = new Muestra();

        Boya boya = boyaService.buscarPorId(boyaId);
        muestra.setFecha(fecha);
        muestra.setMatricula(matricula);
        muestra.setLongitudActual(longitudActual);
        muestra.setLatitudActual(latitudActual);
        muestra.setAltura(altura);

        boya.agregarMuestra(muestra);

        repository.save(muestra);
        return muestra.getMuestraId();
    
}

public Muestra buscarPorId(Integer id) {
    return repository.findByMuestraId(id);
}





}

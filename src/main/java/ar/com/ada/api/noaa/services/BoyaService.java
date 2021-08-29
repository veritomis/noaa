package ar.com.ada.api.noaa.services;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.ada.api.noaa.entities.Boya;
import ar.com.ada.api.noaa.entities.Boya.NivelDelMarEnum;
import ar.com.ada.api.noaa.repos.BoyaRepository;

@Service
public class BoyaService {

    @Autowired
    BoyaRepository repository;

    public void crearBoya(Boya boya) {
        boya.setColor(NivelDelMarEnum.VERDE);
        repository.save(boya);
    }

    public Boya crearBoya(Double longitud, Double latitud) {

        Boya boya = new Boya();
        boya.setLongitud(longitud);
        boya.setLatitud(latitud);

        crearBoya(boya);

        return boya;

    }

    public List<Boya> obtenerBoyas() {
        return repository.findAll();
    }

    public boolean validarBoyaExiste(Integer id) {
        if (buscarPorId(id) != null) {
            return true;
        } else
            return false;
    }

    public Boya buscarPorId(Integer boyaId) {
        return repository.findByBoyaId(boyaId);
    }

    public void actualizar(Boya boya) {
        repository.save(boya);
    }

}

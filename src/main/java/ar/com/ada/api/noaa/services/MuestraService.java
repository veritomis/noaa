package ar.com.ada.api.noaa.services;

import java.util.*;

import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.ada.api.noaa.entities.*;
import ar.com.ada.api.noaa.models.response.*;
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
        } else if (muestra.getAltura() < -50 || muestra.getAltura() > 50) {
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

    public Muestra alturamin(Integer boyaId) {
        Boya boya = boyaService.buscarPorId(boyaId);
        Muestra alturamin = boya.getMuestras().get(0);
        for (Muestra muestra : boya.getMuestras()) {
            if (muestra.getAltura() < alturamin.getAltura()) {
                alturamin = muestra;

            }
        }
        return alturamin;

    }

    public List<ColorResponse> buscarByColor(String color) {
        List<ColorResponse> cRs = new ArrayList();

        for (Muestra muestra : repository.findAll()) {
            ColorResponse cR = new ColorResponse();

            if (muestraColor(muestra).equals(color)) {

                cR.boyaId = muestra.getBoya().getBoyaId();
                cR.horario = muestra.getHorario();
                cR.altura = muestra.getAltura();

                cRs.add(cR);

            }
        }
        return cRs;
    }

    public String muestraColor(Muestra muestra) {

        if (muestra.getAltura() < -100 || muestra.getAltura() > 100) {
            return "ROJO";
        } else if (muestra.getAltura() < -50 || muestra.getAltura() > 50) {
            return "AMARILLO";
        } else {
            return "VERDE";
        }
    }

    public AnomaliaResponse tipoDeAlerta(Integer boyaId) {
        AnomaliaResponse aR = new AnomaliaResponse();
        Boya boya = boyaService.buscarPorId(boyaId);
        Muestra m = boya.getMuestras().get(0);
        for (Muestra muestra : muestrasPorBoyaId(boyaId)) {

            if ((muestra.getAltura() - m.getAltura()) == 500) {
                aR.alturaNivelDelMarActual = muestra.getAltura();
                aR.horarioInicioAnomalia = muestra.getHorario();
                aR.horarioInicioFinAnomalia = muestra.getHorario();
                aR.tipoAlerta = "ALERTA DE IMPACTO";

            } else if ((Math.abs(muestra.getAltura()) - Math.abs(m.getAltura())) == 200) {
                aR.alturaNivelDelMarActual = muestra.getAltura();
                aR.horarioInicioAnomalia = muestra.getHorario();
                aR.horarioInicioFinAnomalia = muestra.getHorario();
                aR.tipoAlerta = "ALERTA DE KAIJU";
            }

        }
        return aR;

    }

}

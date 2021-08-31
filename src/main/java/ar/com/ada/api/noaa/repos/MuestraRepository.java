package ar.com.ada.api.noaa.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import ar.com.ada.api.noaa.entities.Muestra;

public interface MuestraRepository extends JpaRepository<Muestra, Integer>{

    Muestra findByMuestraId(Integer id);

    
}

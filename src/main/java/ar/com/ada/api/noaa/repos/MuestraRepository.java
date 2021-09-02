package ar.com.ada.api.noaa.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.com.ada.api.noaa.entities.Muestra;

@Repository
public interface MuestraRepository extends JpaRepository<Muestra, Integer>{

    Muestra findByMuestraId(Integer id);



    
}

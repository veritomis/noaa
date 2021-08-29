package ar.com.ada.api.noaa.entities;

import java.util.*;

import javax.persistence.*;

@Entity
@Table(name = "muestra")
public class Muestra {
    
    @Id
    @Column(name = "muestra_id")
    private Integer muestraId;

    private Date fecha;

    private String matricula;

    private double longitudActual;

    private double latitudActual;

    private double altura;


    @ManyToOne
    @JoinColumn(name = "boya_id", referencedColumnName = "boya_id")
    private Boya boya;

    public Integer getMuestraId() {
        return muestraId;
    }

    public void setMuestraId(Integer muestraId) {
        this.muestraId = muestraId;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public double getLongitudActual() {
        return longitudActual;
    }

    public void setLongitudActual(double longitudActual) {
        this.longitudActual = longitudActual;
    }

    public double getLatitudActual() {
        return latitudActual;
    }

    public void setLatitudActual(double latitudActual) {
        this.latitudActual = latitudActual;
    }

    public double getAltura() {
        return altura;
    }

    public void setAltura(double altura) {
        this.altura = altura;
    }

    
    
}

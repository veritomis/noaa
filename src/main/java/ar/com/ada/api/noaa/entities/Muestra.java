package ar.com.ada.api.noaa.entities;

import java.util.*;

import javax.persistence.*;

@Entity
@Table(name = "muestra")
public class Muestra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "muestra_id")
    private Integer muestraId;

    private Date horario;

    private String matricula;

    @Column(name = "longitud_actual")
    private Double longitudActual;

    @Column(name = "latitud_actual")
    private Double latitudActual;

    private Double altura;

    @ManyToOne
    @JoinColumn(name = "boya_id", referencedColumnName = "boya_id")
    private Boya boya;

    public Integer getMuestraId() {
        return muestraId;
    }

    public void setMuestraId(Integer muestraId) {
        this.muestraId = muestraId;
    }

    public Date getHorario() {
        return horario;
    }

    public void setHorario(Date horario) {
        this.horario = horario;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public Double getLongitudActual() {
        return longitudActual;
    }

    public void setLongitudActual(Double longitudActual) {
        this.longitudActual = longitudActual;
    }

    public Double getLatitudActual() {
        return latitudActual;
    }

    public void setLatitudActual(Double latitudActual) {
        this.latitudActual = latitudActual;
    }

    public Double getAltura() {
        return altura;
    }

    public void setAltura(Double altura) {
        this.altura = altura;
    }

    public Boya getBoya() {
        return boya;
    }

    public void setBoya(Boya boya) {
        this.boya = boya;
    }

    public void getBoyaId() {
    }

}

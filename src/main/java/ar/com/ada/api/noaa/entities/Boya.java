package ar.com.ada.api.noaa.entities;

import java.util.*;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "boya")
public class Boya {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "boya_id")
    private Integer boyaId;

    
    private Integer color;

    private Double longitud;

    private Double latitud;

    @OneToMany(mappedBy = "boya", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Muestra> muestras = new ArrayList<>();

    public enum NivelDelMarEnum { 
        ROJO(1), AMARILLO(2), VERDE(3), AZUL(4), NULL(5);

    private final Integer value;

    private NivelDelMarEnum(Integer value) {
            this.value = value;
        }

    public Integer getValue() {
        return value;
    }

    public static NivelDelMarEnum parse(Integer id) {
        NivelDelMarEnum status = null;
        for (NivelDelMarEnum item : NivelDelMarEnum.values()) {
            if (item.getValue().equals(id)) {
                status = item;
                break;
            }
        }
        return status;
    }
}

    public Integer getBoyaId() {
        return boyaId;
    }

    public void setBoyaId(Integer boyaId) {
        this.boyaId = boyaId;
    }

    public List<Muestra> getMuestras() {
        return muestras;
    }

    public void setMuestras(List<Muestra> muestras) {
        this.muestras = muestras;
    }

    public Double getLongitud() {
        return longitud;
    }

    public void setLongitud(Double longitud) {
        this.longitud = longitud;
    }

    public Double getLatitud() {
        return latitud;
    }

    public void setLatitud(Double latitud) {
        this.latitud = latitud;
    }

    public NivelDelMarEnum getcolor() { // 20:13
        return NivelDelMarEnum.parse(color);
    }

    public void setColor(NivelDelMarEnum color) {
        this.color = color.getValue();
    }

    /*
     * public void agregarMuestra(Muestra muestra) { this.muestras.add(muestra);
     * muestra.setBoya(this); }
     */

}

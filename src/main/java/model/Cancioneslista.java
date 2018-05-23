package main.java.model;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;
import javax.persistence.FetchType;

@Entity
public class Cancioneslista {
    private int idCancLista;
    private Date fechaIntroduccion;
    private Listarep listarepByListaRep;
    private Cancion cancionByIdCancion;

    @Id
    @Column(name = "idCancLista")
    public int getIdCancLista() {
        return idCancLista;
    }

    public void setIdCancLista(int idCancLista) {
        this.idCancLista = idCancLista;
    }

    @Basic
    @Column(name = "fechaIntroduccion")
    public Date getFechaIntroduccion() {
        return fechaIntroduccion;
    }

    public void setFechaIntroduccion(Date fechaIntroduccion) {
        this.fechaIntroduccion = fechaIntroduccion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cancioneslista that = (Cancioneslista) o;
        return idCancLista == that.idCancLista &&
                Objects.equals(fechaIntroduccion, that.fechaIntroduccion);
    }

    @Override
    public int hashCode() {

        return Objects.hash(idCancLista, fechaIntroduccion);
    }

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "listaRep", referencedColumnName = "idLista", nullable = false)
    public Listarep getListarepByListaRep() {
        return listarepByListaRep;
    }

    public void setListarepByListaRep(Listarep listarepByListaRep) {
        this.listarepByListaRep = listarepByListaRep;
    }

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "idCancion", referencedColumnName = "idCancion", nullable = false)
    public Cancion getCancionByIdCancion() {
        return cancionByIdCancion;
    }

    public void setCancionByIdCancion(Cancion cancionByIdCancion) {
        this.cancionByIdCancion = cancionByIdCancion;
    }
}

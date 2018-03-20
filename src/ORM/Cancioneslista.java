package ORM;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
public class Cancioneslista {
    private int idCancLista;
    private int listaRep;
    private int idCancion;
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
    @Column(name = "listaRep")
    public int getListaRep() {
        return listaRep;
    }

    public void setListaRep(int listaRep) {
        this.listaRep = listaRep;
    }

    @Basic
    @Column(name = "idCancion")
    public int getIdCancion() {
        return idCancion;
    }

    public void setIdCancion(int idCancion) {
        this.idCancion = idCancion;
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
                listaRep == that.listaRep &&
                idCancion == that.idCancion &&
                Objects.equals(fechaIntroduccion, that.fechaIntroduccion);
    }

    @Override
    public int hashCode() {

        return Objects.hash(idCancLista, listaRep, idCancion, fechaIntroduccion);
    }

    @ManyToOne
    @JoinColumn(name = "listaRep", referencedColumnName = "idLista", nullable = false)
    public Listarep getListarepByListaRep() {
        return listarepByListaRep;
    }

    public void setListarepByListaRep(Listarep listarepByListaRep) {
        this.listarepByListaRep = listarepByListaRep;
    }

    @ManyToOne
    @JoinColumn(name = "idCancion", referencedColumnName = "idCancion", nullable = false)
    public Cancion getCancionByIdCancion() {
        return cancionByIdCancion;
    }

    public void setCancionByIdCancion(Cancion cancionByIdCancion) {
        this.cancionByIdCancion = cancionByIdCancion;
    }
}

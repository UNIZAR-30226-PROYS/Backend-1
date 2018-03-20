package ORM;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@IdClass(CancioneslistaPK.class)
public class Cancioneslista {
    private String listaRep;
    private int idCancion;
    private Date fechaIntroduccion;
    private Listarep listarepByListaRep;
    private Cancion cancionByIdCancion;

    @Id
    @Column(name = "listaRep")
    public String getListaRep() {
        return listaRep;
    }

    public void setListaRep(String listaRep) {
        this.listaRep = listaRep;
    }

    @Id
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
        return idCancion == that.idCancion &&
                Objects.equals(listaRep, that.listaRep) &&
                Objects.equals(fechaIntroduccion, that.fechaIntroduccion);
    }

    @Override
    public int hashCode() {

        return Objects.hash(listaRep, idCancion, fechaIntroduccion);
    }

    @ManyToOne
    @JoinColumn(name = "listaRep", referencedColumnName = "idLista", nullable = false, insertable=false, updatable=false)
    public Listarep getListarepByListaRep() {
        return listarepByListaRep;
    }

    public void setListarepByListaRep(Listarep listarepByListaRep) {
        this.listarepByListaRep = listarepByListaRep;
    }

    @ManyToOne
    @JoinColumn(name = "idCancion", referencedColumnName = "idCancion", nullable = false, insertable=false, updatable=false)
    public Cancion getCancionByIdCancion() {
        return cancionByIdCancion;
    }

    public void setCancionByIdCancion(Cancion cancionByIdCancion) {
        this.cancionByIdCancion = cancionByIdCancion;
    }
}

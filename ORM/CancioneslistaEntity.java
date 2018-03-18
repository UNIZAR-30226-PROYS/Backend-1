package ORM;

import javax.persistence.*;

@Entity
@Table(name = "cancioneslista", schema = "wolfic", catalog = "")
@IdClass(CancioneslistaEntityPK.class)
public class CancioneslistaEntity {
    private String listaRep;
    private int idCancion;
    private Date fechaIntroduccion;

    @Id
    @Column(name = "listaRep")
    public java.lang.String getListaRep() {
        return listaRep;
    }

    public void setListaRep(java.lang.String listaRep) {
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
    public java.sql.Date getFechaIntroduccion() {
        return fechaIntroduccion;
    }

    public void setFechaIntroduccion(java.sql.Date fechaIntroduccion) {
        this.fechaIntroduccion = fechaIntroduccion;
    }

    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;

        CancioneslistaEntity that = (CancioneslistaEntity) object;

        if (idCancion != that.idCancion) return false;
        if (listaRep != null ? !listaRep.equals(that.listaRep) : that.listaRep != null) return false;
        if (fechaIntroduccion != null ? !fechaIntroduccion.equals(that.fechaIntroduccion) : that.fechaIntroduccion != null)
            return false;

        return true;
    }

    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (listaRep != null ? listaRep.hashCode() : 0);
        result = 31 * result + idCancion;
        result = 31 * result + (fechaIntroduccion != null ? fechaIntroduccion.hashCode() : 0);
        return result;
    }
}

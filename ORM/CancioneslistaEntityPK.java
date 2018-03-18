package ORM;

import javax.persistence.Column;
import javax.persistence.Id;

public class CancioneslistaEntityPK implements Serializable {
    private String listaRep;
    private int idCancion;

    @Column(name = "listaRep")
    @Id
    public java.lang.String getListaRep() {
        return listaRep;
    }

    public void setListaRep(java.lang.String listaRep) {
        this.listaRep = listaRep;
    }

    @Column(name = "idCancion")
    @Id
    public int getIdCancion() {
        return idCancion;
    }

    public void setIdCancion(int idCancion) {
        this.idCancion = idCancion;
    }

    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;

        CancioneslistaEntityPK that = (CancioneslistaEntityPK) object;

        if (idCancion != that.idCancion) return false;
        if (listaRep != null ? !listaRep.equals(that.listaRep) : that.listaRep != null) return false;

        return true;
    }

    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (listaRep != null ? listaRep.hashCode() : 0);
        result = 31 * result + idCancion;
        return result;
    }
}

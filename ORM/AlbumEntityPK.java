package ORM;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class AlbumEntityPK implements Serializable {
    private String nombre;
    private String idUser;

    @Column(name = "nombre")
    @Id
    public java.lang.String getNombre() {
        return nombre;
    }

    public void setNombre(java.lang.String nombre) {
        this.nombre = nombre;
    }

    @Column(name = "idUser")
    @Id
    public java.lang.String getIdUser() {
        return idUser;
    }

    public void setIdUser(java.lang.String idUser) {
        this.idUser = idUser;
    }

    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;

        AlbumEntityPK that = (AlbumEntityPK) object;

        if (nombre != null ? !nombre.equals(that.nombre) : that.nombre != null) return false;
        if (idUser != null ? !idUser.equals(that.idUser) : that.idUser != null) return false;

        return true;
    }

    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (nombre != null ? nombre.hashCode() : 0);
        result = 31 * result + (idUser != null ? idUser.hashCode() : 0);
        return result;
    }
}

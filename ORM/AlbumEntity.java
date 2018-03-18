package ORM;

import javax.persistence.*;

@Entity
@Table(name = "album", schema = "wolfic", catalog = "")
@IdClass(AlbumEntityPK.class)
public class AlbumEntity {
    private String nombre;
    private String idUser;
    private Date fechaCreacion;

    @Id
    @Column(name = "nombre")
    public java.lang.String getNombre() {
        return nombre;
    }

    public void setNombre(java.lang.String nombre) {
        this.nombre = nombre;
    }

    @Id
    @Column(name = "idUser")
    public java.lang.String getIdUser() {
        return idUser;
    }

    public void setIdUser(java.lang.String idUser) {
        this.idUser = idUser;
    }

    @Basic
    @Column(name = "fechaCreacion")
    public java.sql.Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(java.sql.Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;

        AlbumEntity that = (AlbumEntity) object;

        if (nombre != null ? !nombre.equals(that.nombre) : that.nombre != null) return false;
        if (idUser != null ? !idUser.equals(that.idUser) : that.idUser != null) return false;
        if (fechaCreacion != null ? !fechaCreacion.equals(that.fechaCreacion) : that.fechaCreacion != null)
            return false;

        return true;
    }

    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (nombre != null ? nombre.hashCode() : 0);
        result = 31 * result + (idUser != null ? idUser.hashCode() : 0);
        result = 31 * result + (fechaCreacion != null ? fechaCreacion.hashCode() : 0);
        return result;
    }
}

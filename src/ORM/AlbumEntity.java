package ORM;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "album", schema = "wolfic")
@IdClass(AlbumEntityPK.class)
public class AlbumEntity {
    private String nombre;
    private String idUser;
    private Date fechaCreacion;

    @Id
    @Column(name = "nombre")
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Id
    @Column(name = "idUser")
    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    @Basic
    @Column(name = "fechaCreacion")
    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AlbumEntity that = (AlbumEntity) o;
        return Objects.equals(nombre, that.nombre) &&
                Objects.equals(idUser, that.idUser) &&
                Objects.equals(fechaCreacion, that.fechaCreacion);
    }

    @Override
    public int hashCode() {

        return Objects.hash(nombre, idUser, fechaCreacion);
    }
}

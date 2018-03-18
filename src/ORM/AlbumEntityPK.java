package ORM;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class AlbumEntityPK implements Serializable {
    private String nombre;
    private String idUser;

    @Column(name = "nombre")
    @Id
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Column(name = "idUser")
    @Id
    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AlbumEntityPK that = (AlbumEntityPK) o;
        return Objects.equals(nombre, that.nombre) &&
                Objects.equals(idUser, that.idUser);
    }

    @Override
    public int hashCode() {

        return Objects.hash(nombre, idUser);
    }
}

package ORM;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class ListarepEntityPK implements Serializable {
    private String idUser;
    private String nombre;

    @Column(name = "idUser")
    @Id
    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    @Column(name = "nombre")
    @Id
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ListarepEntityPK that = (ListarepEntityPK) o;
        return Objects.equals(idUser, that.idUser) &&
                Objects.equals(nombre, that.nombre);
    }

    @Override
    public int hashCode() {

        return Objects.hash(idUser, nombre);
    }
}

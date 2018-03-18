package ORM;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class ComentarioEntityPK implements Serializable {
    private int idCancion;
    private String idUser;
    private int idComentario;

    @Column(name = "idCancion")
    @Id
    public int getIdCancion() {
        return idCancion;
    }

    public void setIdCancion(int idCancion) {
        this.idCancion = idCancion;
    }

    @Column(name = "idUser")
    @Id
    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    @Column(name = "idComentario")
    @Id
    public int getIdComentario() {
        return idComentario;
    }

    public void setIdComentario(int idComentario) {
        this.idComentario = idComentario;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ComentarioEntityPK that = (ComentarioEntityPK) o;
        return idCancion == that.idCancion &&
                idComentario == that.idComentario &&
                Objects.equals(idUser, that.idUser);
    }

    @Override
    public int hashCode() {

        return Objects.hash(idCancion, idUser, idComentario);
    }
}

package ORM;

import javax.persistence.Column;
import javax.persistence.Id;

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
    public java.lang.String getIdUser() {
        return idUser;
    }

    public void setIdUser(java.lang.String idUser) {
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

    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;

        ComentarioEntityPK that = (ComentarioEntityPK) object;

        if (idCancion != that.idCancion) return false;
        if (idComentario != that.idComentario) return false;
        if (idUser != null ? !idUser.equals(that.idUser) : that.idUser != null) return false;

        return true;
    }

    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + idCancion;
        result = 31 * result + (idUser != null ? idUser.hashCode() : 0);
        result = 31 * result + idComentario;
        return result;
    }
}

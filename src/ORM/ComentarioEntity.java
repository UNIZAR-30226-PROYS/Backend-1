package ORM;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "comentario", schema = "wolfic")
@IdClass(ComentarioEntityPK.class)
public class ComentarioEntity {
    private int idCancion;
    private String idUser;
    private int idComentario;
    private String cuerpo;
    private Date fechaSubida;

    @Id
    @Column(name = "idCancion")
    public int getIdCancion() {
        return idCancion;
    }

    public void setIdCancion(int idCancion) {
        this.idCancion = idCancion;
    }

    @Id
    @Column(name = "idUser")
    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    @Id
    @Column(name = "idComentario")
    public int getIdComentario() {
        return idComentario;
    }

    public void setIdComentario(int idComentario) {
        this.idComentario = idComentario;
    }

    @Basic
    @Column(name = "cuerpo")
    public String getCuerpo() {
        return cuerpo;
    }

    public void setCuerpo(String cuerpo) {
        this.cuerpo = cuerpo;
    }

    @Basic
    @Column(name = "fechaSubida")
    public Date getFechaSubida() {
        return fechaSubida;
    }

    public void setFechaSubida(Date fechaSubida) {
        this.fechaSubida = fechaSubida;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ComentarioEntity that = (ComentarioEntity) o;
        return idCancion == that.idCancion &&
                idComentario == that.idComentario &&
                Objects.equals(idUser, that.idUser) &&
                Objects.equals(cuerpo, that.cuerpo) &&
                Objects.equals(fechaSubida, that.fechaSubida);
    }

    @Override
    public int hashCode() {

        return Objects.hash(idCancion, idUser, idComentario, cuerpo, fechaSubida);
    }
}

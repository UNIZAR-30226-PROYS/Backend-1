package ORM;

import javax.persistence.*;

@Entity
@Table(name = "comentario", schema = "wolfic", catalog = "")
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
    public java.lang.String getIdUser() {
        return idUser;
    }

    public void setIdUser(java.lang.String idUser) {
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
    public java.lang.String getCuerpo() {
        return cuerpo;
    }

    public void setCuerpo(java.lang.String cuerpo) {
        this.cuerpo = cuerpo;
    }

    @Basic
    @Column(name = "fechaSubida")
    public java.sql.Date getFechaSubida() {
        return fechaSubida;
    }

    public void setFechaSubida(java.sql.Date fechaSubida) {
        this.fechaSubida = fechaSubida;
    }

    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;

        ComentarioEntity that = (ComentarioEntity) object;

        if (idCancion != that.idCancion) return false;
        if (idComentario != that.idComentario) return false;
        if (idUser != null ? !idUser.equals(that.idUser) : that.idUser != null) return false;
        if (cuerpo != null ? !cuerpo.equals(that.cuerpo) : that.cuerpo != null) return false;
        if (fechaSubida != null ? !fechaSubida.equals(that.fechaSubida) : that.fechaSubida != null) return false;

        return true;
    }

    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + idCancion;
        result = 31 * result + (idUser != null ? idUser.hashCode() : 0);
        result = 31 * result + idComentario;
        result = 31 * result + (cuerpo != null ? cuerpo.hashCode() : 0);
        result = 31 * result + (fechaSubida != null ? fechaSubida.hashCode() : 0);
        return result;
    }
}

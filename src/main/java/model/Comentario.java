package main.java.model;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
public class Comentario {
    private int idComentario;
    private String cuerpo;
    private Date fechaSubida;
    private Cancion cancionByIdCancion;
    private Usuario usuarioByIdUser;

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
        Comentario that = (Comentario) o;
        return idComentario == that.idComentario &&
                Objects.equals(cuerpo, that.cuerpo) &&
                Objects.equals(fechaSubida, that.fechaSubida);
    }

    @Override
    public int hashCode() {

        return Objects.hash(idComentario, cuerpo, fechaSubida);
    }

    @ManyToOne
    @JoinColumn(name = "idCancion", referencedColumnName = "idCancion", nullable = false)
    public Cancion getCancionByIdCancion() {
        return cancionByIdCancion;
    }

    public void setCancionByIdCancion(Cancion cancionByIdCancion) {
        this.cancionByIdCancion = cancionByIdCancion;
    }

    @ManyToOne
    @JoinColumn(name = "idUser", referencedColumnName = "idUser", nullable = false)
    public Usuario getUsuarioByIdUser() {
        return usuarioByIdUser;
    }

    public void setUsuarioByIdUser(Usuario usuarioByIdUser) {
        this.usuarioByIdUser = usuarioByIdUser;
    }
}

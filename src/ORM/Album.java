package ORM;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
public class Album {
    private int idAlbum;
    private String nombre;
    private Date fechaCreacion;
    private Usuario usuarioByIdUser;
    private Cancion cancionByNombre;

    @Id
    @Column(name = "idAlbum")
    public int getIdAlbum() {
        return idAlbum;
    }

    public void setIdAlbum(int idAlbum) {
        this.idAlbum = idAlbum;
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
        Album album = (Album) o;
        return idAlbum == album.idAlbum &&
                Objects.equals(usuarioByIdUser, album.usuarioByIdUser) &&
                Objects.equals(cancionByNombre, album.cancionByNombre) &&
                Objects.equals(fechaCreacion, album.fechaCreacion);
    }

    @Override
    public int hashCode() {

        return Objects.hash(idAlbum, usuarioByIdUser, cancionByNombre, fechaCreacion);
    }

    @ManyToOne
    @JoinColumn(name = "idUser", referencedColumnName = "idUser", nullable = false)
    public Usuario getUsuarioByIdUser() {
        return usuarioByIdUser;
    }

    public void setUsuarioByIdUser(Usuario usuarioByIdUser) {
        this.usuarioByIdUser = usuarioByIdUser;
    }

    @ManyToOne
    @JoinColumn(name = "nombre", referencedColumnName = "nombreAlbum", nullable = false)
    public Cancion getCancionByNombre() {
        return cancionByNombre;
    }

    public void setCancionByNombre(Cancion cancionByNombre) {
        this.cancionByNombre = cancionByNombre;
    }
}

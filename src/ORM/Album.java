package ORM;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@IdClass(AlbumPK.class)
public class Album {
    private String nombre;
    private String idUser;
    private Date fechaCreacion;
    private Usuario usuarioByIdUser;
    private Cancion cancionByNombre;

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
        Album album = (Album) o;
        return Objects.equals(nombre, album.nombre) &&
                Objects.equals(idUser, album.idUser) &&
                Objects.equals(fechaCreacion, album.fechaCreacion);
    }

    @Override
    public int hashCode() {

        return Objects.hash(nombre, idUser, fechaCreacion);
    }

    @ManyToOne
    @JoinColumn(name = "idUser", referencedColumnName = "idUser", nullable = false, insertable=false, updatable=false)
    public Usuario getUsuarioByIdUser() {
        return usuarioByIdUser;
    }

    public void setUsuarioByIdUser(Usuario usuarioByIdUser) {
        this.usuarioByIdUser = usuarioByIdUser;
    }

    @ManyToOne
    @JoinColumn(name = "nombre", referencedColumnName = "nombreAlbum", nullable = false, insertable=false, updatable=false)
    public Cancion getCancionByNombre() {
        return cancionByNombre;
    }

    public void setCancionByNombre(Cancion cancionByNombre) {
        this.cancionByNombre = cancionByNombre;
    }
}

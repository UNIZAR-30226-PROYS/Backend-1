package main.java.model;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Album {
    private int idAlbum;
    private String nombre;
    private Date fechaCreacion;
    private Usuario usuarioByIdUser;
    private Collection<Cancion> cancionsByIdAlbum;

    @Id
    @Column(name = "idAlbum")
    public int getIdAlbum() {
        return idAlbum;
    }

    public void setIdAlbum(int idAlbum) {
        this.idAlbum = idAlbum;
    }

    @Basic
    @Column(name = "nombre")
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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
                Objects.equals(nombre, album.nombre) &&
                Objects.equals(fechaCreacion, album.fechaCreacion);
    }

    @Override
    public int hashCode() {

        return Objects.hash(idAlbum, nombre, fechaCreacion);
    }

    @ManyToOne
    @JoinColumn(name = "idUser", referencedColumnName = "idUser", nullable = false)
    public Usuario getUsuarioByIdUser() {
        return usuarioByIdUser;
    }

    public void setUsuarioByIdUser(Usuario usuarioByIdUser) {
        this.usuarioByIdUser = usuarioByIdUser;
    }

    @OneToMany(mappedBy = "albumByIdAlbum")
    public Collection<Cancion> getCancionsByIdAlbum() {
        return cancionsByIdAlbum;
    }

    public void setCancionsByIdAlbum(Collection<Cancion> cancionsByIdAlbum) {
        this.cancionsByIdAlbum = cancionsByIdAlbum;
    }
}

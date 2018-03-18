package ORM;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "cancion", schema = "wolfic")
public class CancionEntity {
    private int idCancion;
    private String nombre;
    private String genero;
    private int duracion;
    private Date fechaSubida;
    private int numRep;
    private String idUser;
    private String nombreAlbum;

    @Id
    @Column(name = "idCancion")
    public int getIdCancion() {
        return idCancion;
    }

    public void setIdCancion(int idCancion) {
        this.idCancion = idCancion;
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
    @Column(name = "genero")
    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    @Basic
    @Column(name = "duracion")
    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    @Basic
    @Column(name = "fechaSubida")
    public Date getFechaSubida() {
        return fechaSubida;
    }

    public void setFechaSubida(Date fechaSubida) {
        this.fechaSubida = fechaSubida;
    }

    @Basic
    @Column(name = "numRep")
    public int getNumRep() {
        return numRep;
    }

    public void setNumRep(int numRep) {
        this.numRep = numRep;
    }

    @Basic
    @Column(name = "idUser")
    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    @Basic
    @Column(name = "nombreAlbum")
    public String getNombreAlbum() {
        return nombreAlbum;
    }

    public void setNombreAlbum(String nombreAlbum) {
        this.nombreAlbum = nombreAlbum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CancionEntity that = (CancionEntity) o;
        return idCancion == that.idCancion &&
                duracion == that.duracion &&
                numRep == that.numRep &&
                Objects.equals(nombre, that.nombre) &&
                Objects.equals(genero, that.genero) &&
                Objects.equals(fechaSubida, that.fechaSubida) &&
                Objects.equals(idUser, that.idUser) &&
                Objects.equals(nombreAlbum, that.nombreAlbum);
    }

    @Override
    public int hashCode() {

        return Objects.hash(idCancion, nombre, genero, duracion, fechaSubida, numRep, idUser, nombreAlbum);
    }
}

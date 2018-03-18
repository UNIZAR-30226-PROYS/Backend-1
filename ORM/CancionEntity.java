package ORM;

import javax.persistence.*;

@Entity
@Table(name = "cancion", schema = "wolfic", catalog = "")
public class CancionEntity {
    private int idCancion;
    private String nombre;
    private String genero;
    private int duracion;
    private Date fechaSubida;
    private int numRep;

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
    public java.lang.String getNombre() {
        return nombre;
    }

    public void setNombre(java.lang.String nombre) {
        this.nombre = nombre;
    }

    @Basic
    @Column(name = "genero")
    public java.lang.String getGenero() {
        return genero;
    }

    public void setGenero(java.lang.String genero) {
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
    public java.sql.Date getFechaSubida() {
        return fechaSubida;
    }

    public void setFechaSubida(java.sql.Date fechaSubida) {
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

    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;

        CancionEntity that = (CancionEntity) object;

        if (idCancion != that.idCancion) return false;
        if (duracion != that.duracion) return false;
        if (numRep != that.numRep) return false;
        if (nombre != null ? !nombre.equals(that.nombre) : that.nombre != null) return false;
        if (genero != null ? !genero.equals(that.genero) : that.genero != null) return false;
        if (fechaSubida != null ? !fechaSubida.equals(that.fechaSubida) : that.fechaSubida != null) return false;

        return true;
    }

    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + idCancion;
        result = 31 * result + (nombre != null ? nombre.hashCode() : 0);
        result = 31 * result + (genero != null ? genero.hashCode() : 0);
        result = 31 * result + duracion;
        result = 31 * result + (fechaSubida != null ? fechaSubida.hashCode() : 0);
        result = 31 * result + numRep;
        return result;
    }
}

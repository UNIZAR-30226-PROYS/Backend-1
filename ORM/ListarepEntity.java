package ORM;

import javax.persistence.*;

@Entity
@Table(name = "listarep", schema = "wolfic", catalog = "")
@IdClass(ListarepEntityPK.class)
public class ListarepEntity {
    private String idUser;
    private String nombre;
    private String idLista;
    private int numElementos;

    @Id
    @Column(name = "idUser")
    public java.lang.String getIdUser() {
        return idUser;
    }

    public void setIdUser(java.lang.String idUser) {
        this.idUser = idUser;
    }

    @Id
    @Column(name = "nombre")
    public java.lang.String getNombre() {
        return nombre;
    }

    public void setNombre(java.lang.String nombre) {
        this.nombre = nombre;
    }

    @Basic
    @Column(name = "idLista")
    public java.lang.String getIdLista() {
        return idLista;
    }

    public void setIdLista(java.lang.String idLista) {
        this.idLista = idLista;
    }

    @Basic
    @Column(name = "numElementos")
    public int getNumElementos() {
        return numElementos;
    }

    public void setNumElementos(int numElementos) {
        this.numElementos = numElementos;
    }

    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;

        ListarepEntity that = (ListarepEntity) object;

        if (numElementos != that.numElementos) return false;
        if (idUser != null ? !idUser.equals(that.idUser) : that.idUser != null) return false;
        if (nombre != null ? !nombre.equals(that.nombre) : that.nombre != null) return false;
        if (idLista != null ? !idLista.equals(that.idLista) : that.idLista != null) return false;

        return true;
    }

    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (idUser != null ? idUser.hashCode() : 0);
        result = 31 * result + (nombre != null ? nombre.hashCode() : 0);
        result = 31 * result + (idLista != null ? idLista.hashCode() : 0);
        result = 31 * result + numElementos;
        return result;
    }
}

package ORM;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "listarep", schema = "wolfic")
@IdClass(ListarepEntityPK.class)
public class ListarepEntity {
    private String idUser;
    private String nombre;
    private String idLista;
    private int numElementos;

    @Id
    @Column(name = "idUser")
    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    @Id
    @Column(name = "nombre")
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Basic
    @Column(name = "idLista")
    public String getIdLista() {
        return idLista;
    }

    public void setIdLista(String idLista) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ListarepEntity that = (ListarepEntity) o;
        return numElementos == that.numElementos &&
                Objects.equals(idUser, that.idUser) &&
                Objects.equals(nombre, that.nombre) &&
                Objects.equals(idLista, that.idLista);
    }

    @Override
    public int hashCode() {

        return Objects.hash(idUser, nombre, idLista, numElementos);
    }
}

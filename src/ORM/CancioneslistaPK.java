package ORM;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class CancioneslistaPK implements Serializable {
    private String listaRep;
    private int idCancion;

    @Column(name = "listaRep")
    @Id
    public String getListaRep() {
        return listaRep;
    }

    public void setListaRep(String listaRep) {
        this.listaRep = listaRep;
    }

    @Column(name = "idCancion")
    @Id
    public int getIdCancion() {
        return idCancion;
    }

    public void setIdCancion(int idCancion) {
        this.idCancion = idCancion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CancioneslistaPK that = (CancioneslistaPK) o;
        return idCancion == that.idCancion &&
                Objects.equals(listaRep, that.listaRep);
    }

    @Override
    public int hashCode() {

        return Objects.hash(listaRep, idCancion);
    }
}

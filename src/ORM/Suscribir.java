package ORM;

import javax.persistence.*;
import java.util.Objects;

@Entity
@IdClass(SuscribirPK.class)
public class Suscribir {
    private String idSuscrito;
    private String idSuscriptor;
    private Usuario usuarioByIdSuscrito;
    private Usuario usuarioByIdSuscriptor;

    @Id
    @Column(name = "idSuscrito")
    public String getIdSuscrito() {
        return idSuscrito;
    }

    public void setIdSuscrito(String idSuscrito) {
        this.idSuscrito = idSuscrito;
    }

    @Id
    @Column(name = "idSuscriptor")
    public String getIdSuscriptor() {
        return idSuscriptor;
    }

    public void setIdSuscriptor(String idSuscriptor) {
        this.idSuscriptor = idSuscriptor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Suscribir suscribir = (Suscribir) o;
        return Objects.equals(idSuscrito, suscribir.idSuscrito) &&
                Objects.equals(idSuscriptor, suscribir.idSuscriptor);
    }

    @Override
    public int hashCode() {

        return Objects.hash(idSuscrito, idSuscriptor);
    }

    @ManyToOne
    @JoinColumn(name = "idSuscrito", referencedColumnName = "idUser", nullable = false, insertable=false, updatable=false)
    public Usuario getUsuarioByIdSuscrito() {
        return usuarioByIdSuscrito;
    }

    public void setUsuarioByIdSuscrito(Usuario usuarioByIdSuscrito) {
        this.usuarioByIdSuscrito = usuarioByIdSuscrito;
    }

    @ManyToOne
    @JoinColumn(name = "idSuscriptor", referencedColumnName = "idUser", nullable = false, insertable=false, updatable=false)
    public Usuario getUsuarioByIdSuscriptor() {
        return usuarioByIdSuscriptor;
    }

    public void setUsuarioByIdSuscriptor(Usuario usuarioByIdSuscriptor) {
        this.usuarioByIdSuscriptor = usuarioByIdSuscriptor;
    }
}

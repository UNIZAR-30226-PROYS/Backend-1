package ORM;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "suscribir", schema = "wolfic")
@IdClass(SuscribirEntityPK.class)
public class SuscribirEntity {
    private String idSuscrito;
    private String idSuscriptor;

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
        SuscribirEntity that = (SuscribirEntity) o;
        return Objects.equals(idSuscrito, that.idSuscrito) &&
                Objects.equals(idSuscriptor, that.idSuscriptor);
    }

    @Override
    public int hashCode() {

        return Objects.hash(idSuscrito, idSuscriptor);
    }
}

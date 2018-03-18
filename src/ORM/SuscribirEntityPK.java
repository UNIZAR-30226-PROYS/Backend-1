package ORM;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class SuscribirEntityPK implements Serializable {
    private String idSuscrito;
    private String idSuscriptor;

    @Column(name = "idSuscrito")
    @Id
    public String getIdSuscrito() {
        return idSuscrito;
    }

    public void setIdSuscrito(String idSuscrito) {
        this.idSuscrito = idSuscrito;
    }

    @Column(name = "idSuscriptor")
    @Id
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
        SuscribirEntityPK that = (SuscribirEntityPK) o;
        return Objects.equals(idSuscrito, that.idSuscrito) &&
                Objects.equals(idSuscriptor, that.idSuscriptor);
    }

    @Override
    public int hashCode() {

        return Objects.hash(idSuscrito, idSuscriptor);
    }
}

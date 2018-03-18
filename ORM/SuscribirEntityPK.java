package ORM;

import javax.persistence.Column;
import javax.persistence.Id;

public class SuscribirEntityPK implements Serializable {
    private String idSuscrito;
    private String idSuscriptor;

    @Column(name = "idSuscrito")
    @Id
    public java.lang.String getIdSuscrito() {
        return idSuscrito;
    }

    public void setIdSuscrito(java.lang.String idSuscrito) {
        this.idSuscrito = idSuscrito;
    }

    @Column(name = "idSuscriptor")
    @Id
    public java.lang.String getIdSuscriptor() {
        return idSuscriptor;
    }

    public void setIdSuscriptor(java.lang.String idSuscriptor) {
        this.idSuscriptor = idSuscriptor;
    }

    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;

        SuscribirEntityPK that = (SuscribirEntityPK) object;

        if (idSuscrito != null ? !idSuscrito.equals(that.idSuscrito) : that.idSuscrito != null) return false;
        if (idSuscriptor != null ? !idSuscriptor.equals(that.idSuscriptor) : that.idSuscriptor != null) return false;

        return true;
    }

    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (idSuscrito != null ? idSuscrito.hashCode() : 0);
        result = 31 * result + (idSuscriptor != null ? idSuscriptor.hashCode() : 0);
        return result;
    }
}

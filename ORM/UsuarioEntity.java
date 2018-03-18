package ORM;

import javax.persistence.*;

@Entity
@Table(name = "usuario", schema = "wolfic", catalog = "")
public class UsuarioEntity {
    private String idUser;
    private String nomAp;
    private String email;
    private String conexion;
    private int ultRep;
    private String contraseña;
    private byte publico;

    @Id
    @Column(name = "idUser")
    public java.lang.String getIdUser() {
        return idUser;
    }

    public void setIdUser(java.lang.String idUser) {
        this.idUser = idUser;
    }

    @Basic
    @Column(name = "NomAp")
    public java.lang.String getNomAp() {
        return nomAp;
    }

    public void setNomAp(java.lang.String nomAp) {
        this.nomAp = nomAp;
    }

    @Basic
    @Column(name = "Email")
    public java.lang.String getEmail() {
        return email;
    }

    public void setEmail(java.lang.String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "Conexion")
    public java.lang.String getConexion() {
        return conexion;
    }

    public void setConexion(java.lang.String conexion) {
        this.conexion = conexion;
    }

    @Basic
    @Column(name = "UltRep")
    public int getUltRep() {
        return ultRep;
    }

    public void setUltRep(int ultRep) {
        this.ultRep = ultRep;
    }

    @Basic
    @Column(name = "Contraseña")
    public java.lang.String getContraseña() {
        return contraseña;
    }

    public void setContraseña(java.lang.String contraseña) {
        this.contraseña = contraseña;
    }

    @Basic
    @Column(name = "Publico")
    public byte getPublico() {
        return publico;
    }

    public void setPublico(byte publico) {
        this.publico = publico;
    }

    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;

        UsuarioEntity that = (UsuarioEntity) object;

        if (ultRep != that.ultRep) return false;
        if (publico != that.publico) return false;
        if (idUser != null ? !idUser.equals(that.idUser) : that.idUser != null) return false;
        if (nomAp != null ? !nomAp.equals(that.nomAp) : that.nomAp != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (conexion != null ? !conexion.equals(that.conexion) : that.conexion != null) return false;
        if (contraseña != null ? !contraseña.equals(that.contraseña) : that.contraseña != null) return false;

        return true;
    }

    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (idUser != null ? idUser.hashCode() : 0);
        result = 31 * result + (nomAp != null ? nomAp.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (conexion != null ? conexion.hashCode() : 0);
        result = 31 * result + ultRep;
        result = 31 * result + (contraseña != null ? contraseña.hashCode() : 0);
        result = 31 * result + (int) publico;
        return result;
    }
}

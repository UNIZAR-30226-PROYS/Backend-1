package ORM;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "usuario", schema = "wolfic")
public class UsuarioEntity {
    private String idUser;
    private String nomAp;
    private String email;
    private String conexion;
    private int ultRep;
    private String contrasenya;
    private byte publico;

    @Id
    @Column(name = "idUser")
    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    @Basic
    @Column(name = "NomAp")
    public String getNomAp() {
        return nomAp;
    }

    public void setNomAp(String nomAp) {
        this.nomAp = nomAp;
    }

    @Basic
    @Column(name = "Email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "Conexion")
    public String getConexion() {
        return conexion;
    }

    public void setConexion(String conexion) {
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
    @Column(name = "Contrasenya")
    public String getContrasenya() {
        return contrasenya;
    }

    public void setContrasenya(String contrasenya) {
        this.contrasenya = contrasenya;
    }

    @Basic
    @Column(name = "Publico")
    public byte getPublico() {
        return publico;
    }

    public void setPublico(byte publico) {
        this.publico = publico;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsuarioEntity that = (UsuarioEntity) o;
        return ultRep == that.ultRep &&
                publico == that.publico &&
                Objects.equals(idUser, that.idUser) &&
                Objects.equals(nomAp, that.nomAp) &&
                Objects.equals(email, that.email) &&
                Objects.equals(conexion, that.conexion) &&
                Objects.equals(contrasenya, that.contrasenya);
    }

    @Override
    public int hashCode() {

        return Objects.hash(idUser, nomAp, email, conexion, ultRep, contrasenya, publico);
    }
}

package ORM;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Usuario {
    private String idUser;
    private String nomAp;
    private String email;
    private String conexion;
    private int ultRep;
    private String contrasenya;
    private byte publico;
    private Collection<Album> albumsByIdUser;
    private Collection<Cancion> cancionsByIdUser;
    private Collection<Comentario> comentariosByIdUser;
    private Collection<Suscribir> suscribirsByIdUser;
    private Collection<Suscribir> suscribirsByIdUser_0;

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
        Usuario usuario = (Usuario) o;
        return ultRep == usuario.ultRep &&
                publico == usuario.publico &&
                Objects.equals(idUser, usuario.idUser) &&
                Objects.equals(nomAp, usuario.nomAp) &&
                Objects.equals(email, usuario.email) &&
                Objects.equals(conexion, usuario.conexion) &&
                Objects.equals(contrasenya, usuario.contrasenya);
    }

    @Override
    public int hashCode() {

        return Objects.hash(idUser, nomAp, email, conexion, ultRep, contrasenya, publico);
    }

    @OneToMany(mappedBy = "usuarioByIdUser")
    public Collection<Album> getAlbumsByIdUser() {
        return albumsByIdUser;
    }

    public void setAlbumsByIdUser(Collection<Album> albumsByIdUser) {
        this.albumsByIdUser = albumsByIdUser;
    }

    @OneToMany(mappedBy = "usuarioByIdUser")
    public Collection<Cancion> getCancionsByIdUser() {
        return cancionsByIdUser;
    }

    public void setCancionsByIdUser(Collection<Cancion> cancionsByIdUser) {
        this.cancionsByIdUser = cancionsByIdUser;
    }

    @OneToMany(mappedBy = "usuarioByIdUser")
    public Collection<Comentario> getComentariosByIdUser() {
        return comentariosByIdUser;
    }

    public void setComentariosByIdUser(Collection<Comentario> comentariosByIdUser) {
        this.comentariosByIdUser = comentariosByIdUser;
    }

    @OneToMany(mappedBy = "usuarioByIdSuscrito")
    public Collection<Suscribir> getSuscribirsByIdUser() {
        return suscribirsByIdUser;
    }

    public void setSuscribirsByIdUser(Collection<Suscribir> suscribirsByIdUser) {
        this.suscribirsByIdUser = suscribirsByIdUser;
    }

    @OneToMany(mappedBy = "usuarioByIdSuscriptor")
    public Collection<Suscribir> getSuscribirsByIdUser_0() {
        return suscribirsByIdUser_0;
    }

    public void setSuscribirsByIdUser_0(Collection<Suscribir> suscribirsByIdUser_0) {
        this.suscribirsByIdUser_0 = suscribirsByIdUser_0;
    }
}

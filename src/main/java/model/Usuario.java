package main.java.model;

import javax.naming.AuthenticationException;
import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

import main.java.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import static main.java.BCrypt.checkpw;
import static main.java.HibernateUtil.getSession;

import java.io.File;
import java.nio.file.StandardCopyOption;
import java.nio.file.Files;

@Entity
public class Usuario {
    private String idUser;
    private String nomAp;
    private String email;
    private String conexion;
    private int ultRep;
    private String contrasenya;
    private boolean publico;
    private final ThreadLocal<Collection<Album>> albumsByIdUser = new ThreadLocal<Collection<Album>>();
    private Collection<Cancion> cancionsByIdUser;
    private Collection<Comentario> comentariosByIdUser;
    private Collection<Listarep> listarepsByIdUser;
    private Collection<Suscribir> suscribirsByIdUser;
    private Collection<Suscribir> suscribirsByIdUser_0;

    public Usuario() {}

    public Usuario(String idUser, String nomAp, String email, String contrasenya) {
        this.idUser = idUser;
        this.nomAp = nomAp;
        this.email = email;
        this.conexion = "desconectado";
        this.ultRep = 0;
        this.contrasenya = contrasenya;
        this.publico = false;
    }

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
    public boolean isPublico() {
        return publico;
    }

    public void setPublico(boolean publico) {
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
        return albumsByIdUser.get();
    }

    public void setAlbumsByIdUser(Collection<Album> albumsByIdUser) {
        this.albumsByIdUser.set(albumsByIdUser);
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

    @OneToMany(mappedBy = "usuarioByIdUser")
    public Collection<Listarep> getListarepsByIdUser() {
        return listarepsByIdUser;
    }

    public void setListarepsByIdUser(Collection<Listarep> listarepsByIdUser) {
        this.listarepsByIdUser = listarepsByIdUser;
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




    /*
     * Anyade un nuevo usuario a la base de datos en caso de que el username no este cogido
     * Devuelve el Usuario creado
     */
    public static Usuario addUser(String username, String password, String email) throws Exception{
        Session session = getSession();
        if (!existsUser(username)){
            Usuario newUser = new Usuario();

            newUser.setIdUser(username);
            String hashPwd = password;
            newUser.setContrasenya(hashPwd);
            newUser.setEmail(email);

            newUser.setNomAp("");
            newUser.setUltRep(0);
            newUser.setPublico(false);
            newUser.setConexion("conectado");

            session.beginTransaction();
            session.save( newUser );
            session.getTransaction().commit();
            try{
                File from = new File("/images/user.svg");
                File to = new File("/images/"+username+"_perfil.svg");
                Files.copy(from.toPath(),to.toPath(),StandardCopyOption.REPLACE_EXISTING);
            }
            catch (Exception e){}
            session.close();


            return newUser;
        }else{
            session.close();
            throw new Exception("Nombre de usuario ya existe");
        }
    }

    /*
     * Devuelve el usuario siempre que exista y la contrasenya sea correcta,
     * si no, lanza excepcion
     */
    public static Usuario login(String username, String password) throws Exception{
        Session session = getSession();
        try {
            Usuario user = correctUser(username, password);
            return user;
        }catch (Exception e){
            throw e;
        }finally {
            session.close();
        }
    }

    /*
     * True -> usuario existe
     * False -> usuario no existe
     */
    public static boolean existsUser(String username){
        Session session = getSession();
        boolean exists = false;
        Query query = session.createQuery("from Usuario where idUser = :user ");
        query.setParameter("user", username);
        if (query.uniqueResult() != null){
           exists = true;
        }
        session.close();
        return exists;
    }

    /*
     * Devuelve el usuario en caso de que exista y la contraseña sea correcta
     */
    public static Usuario correctUser(String username, String password) throws Exception{
        Session session = getSession();
        Query query = session.createQuery("from Usuario where idUser = :user ");
        query.setParameter("user", username);
        Usuario user = (Usuario) query.uniqueResult();
        if (user==null){
            throw new Exception("El usuario no existe");
        }else if (!checkpw(password,user.getContrasenya())){
            throw new AuthenticationException("Contraseña errónea");
        }
        session.close();
        return user;
    }

}

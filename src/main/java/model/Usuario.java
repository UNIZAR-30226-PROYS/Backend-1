package main.java.model;

import javax.naming.AuthenticationException;
import javax.persistence.*;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.ArrayList;
import java.util.Objects;
import java.util.List;

import main.java.HibernateUtil;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

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

    @OneToMany( mappedBy = "usuarioByIdUser")
    public Collection<Album> getAlbumsByIdUser() {
        return albumsByIdUser.get();
    }

    public void setAlbumsByIdUser(Collection<Album> albumsByIdUser) {
        this.albumsByIdUser.set(albumsByIdUser);
    }

    @OneToMany( mappedBy = "usuarioByIdUser")
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

    @OneToMany( mappedBy = "usuarioByIdUser")
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

            //Asignacion de atributos
            newUser.setIdUser(username);
            newUser.setContrasenya(password);
            newUser.setEmail(email);
            newUser.setNomAp("Anonimo");
            newUser.setUltRep(0);
            newUser.setPublico(true);
            newUser.setConexion("conectado");

            //Creacion de listas predefinidas
            List<Listarep> listas = new ArrayList<>();
            Listarep historial = Listarep.initLista(newUser,"historial");
            Listarep mimusica = Listarep.initLista(newUser,"mimusica");
            Listarep favoritos = Listarep.initLista(newUser,"favoritos");
            listas.add(historial);
            listas.add(mimusica);
            listas.add(favoritos);

            //Almacenamiento en BBDD
            session.beginTransaction();
            session.save( newUser );
            session.save( "Listarep", historial );
            session.save( "Listarep", mimusica );
            session.save( "Listarep", favoritos );
            //newUser.setListarepsByIdUser(listas);
            //session.update( newUser );
            session.getTransaction().commit();

            //Inicializacion de Lazy-Fetch de Listas y Canciones
            Hibernate.initialize(newUser.getListarepsByIdUser());
            Hibernate.initialize(newUser.getCancionsByIdUser());

            session.close();
            File from = new File("/contenido/imagenes/user.png");
            File to = new File("/contenido/imagenes/usuarios/"+username+"Perfil.png");
            try {
                Files.copy(from.toPath(),to.toPath(),StandardCopyOption.REPLACE_EXISTING);
            }
            catch (Exception e) {throw new Exception("Cant write lol:"+e.getMessage());}
            if(!Files.exists(from.toPath())) {throw new Exception("Aqui no "+from.toString());}

            return newUser;
        }else{
            session.close();
            throw new Exception("Nombre de usuario ya existe");
        }
    }
    /*
     * Borra permanentemente un usuario del sistema
     *
     */
    public static void borrarUser(String username) throws Exception{
        Session session = getSession();
        Usuario User = getUser(username);
        session.beginTransaction();
        session.delete( User );
        session.getTransaction().commit();
        session.close();
        //TODO: borrar canciones, comentarios etc de usuario si, no ¿lo hace por cascada hibernate?
    }
    /*
     * En caso de que usernameNew no este cojido, modifica a usernameOld con los nuevos atributos.
     */
    public static Usuario modUser(String usernameOld, String usernameNew, String email,String nomAp,Boolean publico) throws Exception{
        Session session = getSession();
        if (!existsUser(usernameNew) || usernameOld.equals(usernameNew)){
            Usuario User = getUser(usernameOld);
            User.setEmail(email);

            User.setNomAp(nomAp);
            User.setPublico(publico);
            User.setConexion("conectado");
            if(!(usernameOld.equals(usernameNew))) {
                User.setIdUser(usernameNew);
            }

            session.beginTransaction();
            session.saveOrUpdate( User );
            session.getTransaction().commit();
            if(!(usernameOld.equals(usernameNew))) {
                User = getUser(usernameOld);
                session.beginTransaction();
                session.delete( User );
                session.getTransaction().commit();
            }
            session.close();
            return User;
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
            //Inicializacion de Lazy-Fetch de Listas y Canciones
            //Hibernate.initialize(user.getListarepsByIdUser());
            //Hibernate.initialize(user.getCancionsByIdUser());
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

    public static void guardarInstante(Usuario user, int rep) throws Exception{
        Session session = getSession();
        if (existsUser(user.getIdUser())){
            user.setUltRep(rep);
            session.beginTransaction();
            session.saveOrUpdate( user );
            session.getTransaction().commit();
        }else{
            throw new Exception("Usuario no existe");
        }
        session.close();
    }


    /*
     * True -> usuario existe
     * False -> usuario no existe
     */
    public static Usuario getUser(String username) throws  Exception{
        Session session = getSession();
        boolean exists = false;
        Query query = session.createQuery("from Usuario where idUser = :user ");
        query.setParameter("user", username);
        Usuario user = (Usuario) query.uniqueResult();
        session.close();
        if (user==null){
            throw new Exception("El usuario no existe");
        }
        return user;
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

    /*
       Devuelve lista de elementos de Usuario que tengan el string user en el idUser
    */
    public static List<Usuario> searchUser(String user){
        Session session = getSession();
        Query query = session.createQuery("from Usuario where idUser like :user ");
        query.setParameter("user", "%"+user+"%");
        List<Usuario> lista = query.list();
        session.close();
        return lista;
    }

}

package main.java.model;

import javax.naming.AuthenticationException;
import javax.persistence.*;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.ArrayList;
import java.util.Objects;
import java.util.List;

import main.java.FileOperations;
import main.java.HibernateUtil;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.annotations.GenericGenerator;
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

    private int idUser;
    private String username;
    private String nomAp;
    private String email;
    private boolean conexion;
    private int ultRep;
    private String contrasenya;
    private boolean publico;
    private final ThreadLocal<Collection<Album>> albumsByIdUser = new ThreadLocal<Collection<Album>>();
    private Collection<Cancion> cancionsByIdUser;
    private Collection<Comentario> comentariosByIdUser;
    private Collection<Listarep> listarepsByIdUser;
    private Collection<Suscribir> suscribirsByIdUser;
    private Collection<Suscribir> suscribirsByIdUser_0;


    @Id
    @GenericGenerator(name="genUsr" , strategy="increment")
    @GeneratedValue(generator="genUsr")
    @Column(name = "idUser")
    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    @Basic
    @Column(name = "username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
    public boolean getConexion() {
        return conexion;
    }

    public void setConexion(boolean conexion) {
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
    public boolean getPublico() {
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
                Objects.equals(username, usuario.username) &&
                Objects.equals(nomAp, usuario.nomAp) &&
                Objects.equals(email, usuario.email) &&
                Objects.equals(conexion, usuario.conexion) &&
                Objects.equals(contrasenya, usuario.contrasenya);
    }

    @Override
    public int hashCode() {

        return Objects.hash(idUser, username, nomAp, email, conexion, ultRep, contrasenya, publico);
    }

    @OneToMany(mappedBy = "usuarioByIdUser")
    @Cascade(CascadeType.ALL)
    public Collection<Album> getAlbumsByIdUser() {
        return albumsByIdUser.get();
    }

    public void setAlbumsByIdUser(Collection<Album> albumsByIdUser) {
        this.albumsByIdUser.set(albumsByIdUser);
    }

    @OneToMany(mappedBy = "usuarioByIdUser")
    @Cascade(CascadeType.ALL)
    public Collection<Cancion> getCancionsByIdUser() {
        return cancionsByIdUser;
    }

    public void setCancionsByIdUser(Collection<Cancion> cancionsByIdUser) {
        this.cancionsByIdUser = cancionsByIdUser;
    }

    @OneToMany(mappedBy = "usuarioByIdUser")
    @Cascade(CascadeType.ALL)
    public Collection<Comentario> getComentariosByIdUser() {
        return comentariosByIdUser;
    }

    public void setComentariosByIdUser(Collection<Comentario> comentariosByIdUser) {
        this.comentariosByIdUser = comentariosByIdUser;
    }

    @OneToMany(mappedBy = "usuarioByIdUser")
    @Cascade(CascadeType.ALL)
    public Collection<Listarep> getListarepsByIdUser() {
        return listarepsByIdUser;
    }

    public void setListarepsByIdUser(Collection<Listarep> listarepsByIdUser) {
        this.listarepsByIdUser = listarepsByIdUser;
    }

    @OneToMany(mappedBy = "usuarioByIdSuscrito")
    @Cascade(CascadeType.ALL)
    public Collection<Suscribir> getSuscribirsByIdUser() {
        return suscribirsByIdUser;
    }

    public void setSuscribirsByIdUser(Collection<Suscribir> suscribirsByIdUser) {
        this.suscribirsByIdUser = suscribirsByIdUser;
    }

    @OneToMany(mappedBy = "usuarioByIdSuscriptor")
    @Cascade(CascadeType.ALL)
    public Collection<Suscribir> getSuscribirsByIdUser_0() {
        return suscribirsByIdUser_0;
    }

    public void setSuscribirsByIdUser_0(Collection<Suscribir> suscribirsByIdUser_0) {
        this.suscribirsByIdUser_0 = suscribirsByIdUser_0;
    }

    /*------------------------------------------------------------------------------------------------------------------
     *------------------------------------------------------------------------------------------------------------------
     *--------------------------------------      FUNCIONES PROPIAS      -----------------------------------------------
     *------------------------------------------------------------------------------------------------------------------
     *----------------------------------------------------------------------------------------------------------------*/

    /*------------------------------------------------------------------------------------------------------------------
     *-----------------------------------   CREACION BORRADO Y MODIFICACION   ------------------------------------------
     *----------------------------------------------------------------------------------------------------------------*/
    /*
     * Anyade un nuevo usuario a la base de datos en caso de que el username no este cogido
     * Devuelve el Usuario creado
     */
    public static Usuario addUser(String username, String password, String email) throws Exception{
        Session session = getSession();
        if (!existsUser(username)){
            Usuario newUser = new Usuario();

            //Asignacion de atributos
            newUser.setIdUser(0);
            newUser.setUsername(username);
            newUser.setContrasenya(password);
            newUser.setEmail(email);
            newUser.setNomAp("Anonimo");
            newUser.setUltRep(0);
            newUser.setPublico(true);
            newUser.setConexion(true);

            //Almacenamiento en BBDD
            session.beginTransaction();
            session.save( newUser );
            session.getTransaction().commit();

            //Creacion de listas predefinidas
            Listarep.addLista(newUser,"historial");
            Listarep.addLista(newUser,"mimusica");
            Listarep.addLista(newUser,"favoritos");

            //Inicializacion de Lazy-Fetch de Listas y Canciones
            newUser.activarCanciones(session);
            newUser.activarListas(session);

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
     */
    public static void borrarUser(String username) throws Exception{
        Session session = getSession();
        Usuario user = getUser(username);
        FileOperations.delete("/contenido/imagenes/usuarios/"+username+"Perfil.png");
        borrarCanciones(user.getIdUser());
        session.beginTransaction();
        session.delete( user );
        session.getTransaction().commit();
        session.close();
    }

    /*
     * Borra permanentemente un usuario del sistema
     */
    public static void borrarUser(int idUser) throws Exception{
        Session session = getSession();
        Usuario user = getUser(idUser);
        FileOperations.delete("/contenido/imagenes/usuarios/"+user.getUsername()+"Perfil.png");
        borrarCanciones(idUser);
        session.beginTransaction();
        session.delete( user );
        session.getTransaction().commit();
        session.close();
    }

    public static void borrarCanciones(int idUser) throws Exception {
        List<Integer> lista = getSongs(idUser);
        for(Integer idCancion : lista){
            FileOperations.delete("/contenido/canciones/"+Integer.toString(idCancion)+".mp3");
            FileOperations.delete("/contenido/imagenes/canciones/"+Integer.toString(idCancion)+".png");
        }
    }

    public Usuario modUser(String nombre, String email,String nomAp,Boolean publico) throws Exception{
        Session session = getSession();
        String oldNombre = this.getUsername();
        this.setUsername(nombre);
        this.setEmail(email);
        this.setNomAp(nomAp);
        this.setPublico(publico);
        if(!oldNombre.equals(nombre)){
            FileOperations.rename("/contenido/imagenes/usuarios/"+oldNombre+"Perfil.png",
                    "/contenido/imagenes/usuarios/"+nombre+"Perfil.png");
        }
        session.beginTransaction();
        session.update( this );
        session.getTransaction().commit();
        session.close();
        return this;
    }

    /*
     * Devuelve el usuario siempre que exista y la contrasenya sea correcta,
     * si no, lanza excepcion
     */
    public static Usuario login(String username, String password) throws Exception{
        Session session = getSession();
        try {
            Usuario user = correctUser(username, password, session);
            //Inicializacion de Lazy-Fetch de Listas y Canciones
            user.setConexion(true);
            user.activarCanciones(session);
            user.activarListas(session);
            // user.activarSuscripciones(session);
            return user;
        }catch (Exception e){
            throw e;
        }finally {
            session.close();
        }
    }

    /*------------------------------------------------------------------------------------------------------------------
     *------------------------------------------    ACTIVACION FETCH    ------------------------------------------------
     *----------------------------------------------------------------------------------------------------------------*/

    //Activa Canciones
    public Usuario activarCanciones(Session session){
        session.refresh(this);
        Hibernate.initialize(this.getCancionsByIdUser());
        return this;
    }

    //Activa Listas
    public Usuario activarListas(Session session){
        session.refresh(this);
        Hibernate.initialize(this.getListarepsByIdUser());
        return this;
    }

    //Activa Listas
    public Usuario activarSuscriptores(Session session){
        session.refresh(this);
        Hibernate.initialize(this.getSuscribirsByIdUser());
        return this;
    }

    //Activa Listas
    public Usuario activarSuscripciones(Session session){
        session.refresh(this);
        Hibernate.initialize(this.getSuscribirsByIdUser_0());
        return this;
    }

    /*------------------------------------------------------------------------------------------------------------------
     *---------------------------------------------      EXIST      ----------------------------------------------------
     *----------------------------------------------------------------------------------------------------------------*/

    /*
     * True -> usuario existe
     * False -> usuario no existe
     */
    public static boolean existsUser(String username){
        Session session = getSession();
        boolean exists = false;
        Query query = session.createQuery("from Usuario where username = :user ");
        query.setParameter("user", username);
        if (query.uniqueResult() != null){
            exists = true;
        }
        session.close();
        return exists;
    }

    /*
     * True -> usuario existe
     * False -> usuario no existe
     */
    public static boolean existsUser(int idUser){
        Session session = getSession();
        boolean exists = false;
        Query query = session.createQuery("from Usuario where idUser = :user ");
        query.setParameter("user", idUser);
        if (query.uniqueResult() != null){
            exists = true;
        }
        session.close();
        return exists;
    }

    /*
     * Devuelve el usuario en caso de que exista y la contraseña sea correcta
     */
    public static Usuario correctUser(String username, String password, Session session) throws Exception{
        Usuario user = getUser(username);
        if (!checkpw(password,user.getContrasenya())) {
            throw new AuthenticationException("Contraseña errónea");
        }
        return user;
    }

    /*
       Devuelve lista de elementos de Usuario que tengan el string user en el idUser
    */
    public static List<Usuario> searchUser(String user){
        Session session = getSession();
        Query query = session.createQuery("from Usuario where username like :user ");
        query.setParameter("user", "%"+user+"%");
        List<Usuario> lista = query.list();
        session.close();
        return lista;
    }

    /*------------------------------------------------------------------------------------------------------------------
     *---------------------------------------------       GET       ----------------------------------------------------
     *----------------------------------------------------------------------------------------------------------------*/

    /*
     * True -> usuario existe
     * False -> usuario no existe
     */
    public static Usuario getUser(String username) throws  Exception{
        Session session = getSession();
        Query query = session.createQuery("from Usuario where username = :user ");
        query.setParameter("user", username);
        Usuario user = (Usuario) query.uniqueResult();
        session.close();
        if (user==null){
            throw new Exception("El usuario no existe");
        }
        return user;
    }

    /*
     * True -> usuario existe
     * False -> usuario no existe
     */
    public static Usuario getUser(int idUser) throws  Exception{
        Session session = getSession();
        Query query = session.createQuery("from Usuario where idUser = :user ");
        query.setParameter("user", idUser);
        Usuario user = (Usuario) query.uniqueResult();
        session.close();
        if (user==null){
            throw new Exception("El usuario no existe");
        }
        return user;
    }

    public static List<Integer> getSongs(int idUser) throws  Exception{
        Session session = getSession();
        Query query = session.createQuery("select idCancion from Cancion where idUser = :user ");
        query.setParameter("user", idUser);
        List<Integer> lista = query.list();
        return lista;
    }

    public static List<Cancion> getSongsObject(int idUser) throws  Exception{
        Session session = getSession();
        Query query = session.createQuery("from Cancion where idUser = :user ");
        query.setParameter("user", idUser);
        List<Cancion> lista = query.list();
        return lista;
    }

    public static Listarep getLista(Usuario user, String nombre) throws Exception{
        Collection<Listarep> aux = user.getListarepsByIdUser();
        boolean exists = false;
        if(aux!=null){
            List<Listarep> listas = new ArrayList<>(aux);
            for(Listarep lista : listas){
                try{
                    if(nombre.equals(lista.getNombre())){
                        return lista;
                    }
                }catch (Exception e){}
            }
        }
        throw new Exception("El usuario no contiene dicha lista");
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

    @Override
    public String toString() {
        return "Usuario{" +
                "idUser=" + idUser +
                ", username='" + username + '\'' +
                ", conexion=" + conexion +
                ", publico=" + publico +
                '}';
    }
}

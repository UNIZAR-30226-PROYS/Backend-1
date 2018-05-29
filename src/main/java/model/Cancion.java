package model;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.query.Query;
import static util.HibernateUtil.getSession;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;
import java.util.List;
import java.util.ArrayList;
import java.util.Objects;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;



@Entity
public class Cancion implements Comparable<Cancion> {
    private int idCancion;
    private String nombre;
    private String genero;
    private int duracion;
    private Date fechaSubida;
    private int numRep;
    private Usuario usuarioByIdUser;
    private Album albumByIdAlbum;
    private Collection<Cancioneslista> cancioneslistasByIdCancion;
    private Collection<Comentario> comentariosByIdCancion;

    @Id
    @Column(name = "idCancion")
    @GenericGenerator(name="genCan" , strategy="increment")
    @GeneratedValue(generator="genCan")
    public int getIdCancion() {
        return idCancion;
    }

    public void setIdCancion(int idCancion) {
        this.idCancion = idCancion;
    }

    @Basic
    @Column(name = "nombre")
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Basic
    @Column(name = "genero")
    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    @Basic
    @Column(name = "duracion")
    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    @Basic
    @Column(name = "fechaSubida")
    public Date getFechaSubida() {
        return fechaSubida;
    }

    public void setFechaSubida(Date fecha) {
        long millis=System.currentTimeMillis();
        java.sql.Date date=new java.sql.Date(millis);
        this.fechaSubida = date;
    }

    @Basic
    @Column(name = "numRep")
    public int getNumRep() {
        return numRep;
    }

    public void setNumRep(int numRep) {
        this.numRep = numRep;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cancion cancion = (Cancion) o;
        return idCancion == cancion.idCancion &&
                duracion == cancion.duracion &&
                numRep == cancion.numRep &&
                Objects.equals(nombre, cancion.nombre) &&
                Objects.equals(genero, cancion.genero) &&
                Objects.equals(fechaSubida, cancion.fechaSubida);
    }

    @Override
    public int hashCode() {

        return Objects.hash(idCancion, nombre, genero, duracion, fechaSubida, numRep);
    }

    @ManyToOne
    @JoinColumn(name = "idUser", referencedColumnName = "idUser", nullable = false)
    public Usuario getUsuarioByIdUser() {
        return usuarioByIdUser;
    }

    public void setUsuarioByIdUser(Usuario usuarioByIdUser) {
        this.usuarioByIdUser = usuarioByIdUser;
    }

    @ManyToOne
    @JoinColumn(name = "idAlbum", referencedColumnName = "idAlbum")
    public Album getAlbumByIdAlbum() {
        return albumByIdAlbum;
    }

    public void setAlbumByIdAlbum(Album albumByIdAlbum) {
        this.albumByIdAlbum = albumByIdAlbum;
    }

    @OneToMany(mappedBy = "cancionByIdCancion")
    @Cascade(CascadeType.DELETE)
    public Collection<Cancioneslista> getCancioneslistasByIdCancion() {
        return cancioneslistasByIdCancion;
    }

    public void setCancioneslistasByIdCancion(Collection<Cancioneslista> cancioneslistasByIdCancion) {
        this.cancioneslistasByIdCancion = cancioneslistasByIdCancion;
    }

    @OneToMany(mappedBy = "cancionByIdCancion")
    @Cascade(CascadeType.DELETE)
    public Collection<Comentario> getComentariosByIdCancion() {
        return comentariosByIdCancion;
    }

    public void setComentariosByIdCancion(Collection<Comentario> comentariosByIdCancion) {
        this.comentariosByIdCancion = comentariosByIdCancion;
    }

    /*------------------------------------------------------------------------------------------------------------------
     *------------------------------------------------------------------------------------------------------------------
     *--------------------------------------------FUNCIONES PROPIAS-----------------------------------------------------
     *------------------------------------------------------------------------------------------------------------------
     *----------------------------------------------------------------------------------------------------------------*/

    /*------------------------------------------------------------------------------------------------------------------
     *-----------------------------------   CREACION BORRADO Y MODIFICACION   ------------------------------------------
     *----------------------------------------------------------------------------------------------------------------*/

    /*
     * TODO: activar canciones sin fetchear el objeto otra vez
     * Anyade un nuevo usuario a la base de datos en caso de que el username no este cogido
     * Devuelve el Usuario creado
     */
    public static Cancion addCancion(String nombre, String genero, Usuario usuario) throws Exception{
        Session session = getSession();
        Usuario user = Usuario.getUser(usuario.getIdUser());
        user.activarCanciones(session);
        user.activarListas(session);

        if (!existsCancion(user,nombre)){
            Cancion newCancion = new Cancion();

            newCancion.setNombre(nombre);
            newCancion.setGenero(genero);
            newCancion.setIdCancion(0);

            //TODO: Poner duracion correcta
            newCancion.setDuracion(0);
            newCancion.setNumRep(0);
            newCancion.setUsuarioByIdUser(user);
            newCancion.setFechaSubida(new Date(0));

            session.beginTransaction();
            session.save( newCancion );
            session.getTransaction().commit();

            Cancioneslista.addCancALista(user, "mimusica", newCancion);

            session.close();

            return newCancion;

        }else{
            session.close();
            throw new Exception("Cancion con el mismo nombre ya existe");
        }

    }

    /*------------------------------------------------------------------------------------------------------------------
     *---------------------------------------------      EXIST      ----------------------------------------------------
     *----------------------------------------------------------------------------------------------------------------*/

    public static boolean existsCancion(Usuario user, String song){
        Collection<Cancion> aux = user.getCancionsByIdUser();
        boolean exists = false;
        if(aux!=null){
            List<Cancion> canciones = new ArrayList<>(aux);
            for(Cancion cancion : canciones){
                if(song.equals(cancion.getNombre())){
                    exists = true;
                    break;
                }
            }
        }
        return exists;
    }

    /*------------------------------------------------------------------------------------------------------------------
     *---------------------------------------------       GET       ----------------------------------------------------
     *----------------------------------------------------------------------------------------------------------------*/

    /*
     * True -> usuario existe
     * False -> usuario no existe
     */
    public static Cancion getCancion(Integer id) throws  Exception{
        Session session = getSession();
        Query query = session.createQuery("from Cancion where idCancion = :id ");
        query.setParameter("id", id);
        Cancion ca = (Cancion) query.uniqueResult();
        session.close();
        if (ca==null){
            throw new Exception("La canción no existe");
        }
        return ca;
    }

    /*------------------------------------------------------------------------------------------------------------------
     *---------------------------------------------     SEARCH      ----------------------------------------------------
     *----------------------------------------------------------------------------------------------------------------*/

    /*
        Devuelve lista de elementos de Cancion que tengan el string song en el nombre
     */
    public static List<Cancion> searchSong(String song){
        Session session = getSession();
        Query query = session.createQuery("from Cancion where nombre like :song ");
        query.setParameter("song", "%"+song+"%");
        List<Cancion> lista = query.list();
        session.close();
        return lista;
    }

    public void incrementarReps(){
        Session session = getSession();
        this.numRep++;
        session.beginTransaction();
        session.update( this );
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public int compareTo(Cancion cancion) {
        return Integer.compare(idCancion, cancion.idCancion);
    }

    @Override
    public String toString() {
        return "Cancion{" +
                "nombre='" + nombre + '\'' +
                '}';
    }
}

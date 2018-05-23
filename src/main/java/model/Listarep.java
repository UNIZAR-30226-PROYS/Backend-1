package main.java.model;

import javax.persistence.*;
import java.util.Collection;
import java.util.ArrayList;
import java.util.Objects;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
import static main.java.HibernateUtil.getSession;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class Listarep {
    private int idLista;
    private String nombre;
    private int numElementos;
    private Collection<Cancioneslista> cancioneslistasByIdLista;
    private Usuario usuarioByIdUser;

    @Id
    @GenericGenerator(name="genLis" , strategy="increment")
    @GeneratedValue(generator="genLis")
    @Column(name = "idLista")
    public int getIdLista() {
        return idLista;
    }

    public void setIdLista(int idLista) {
        this.idLista = idLista;
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
    @Column(name = "numElementos")
    public int getNumElementos() {
        return numElementos;
    }

    public void setNumElementos(int numElementos) {
        this.numElementos = numElementos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Listarep listarep = (Listarep) o;
        return idLista == listarep.idLista &&
                numElementos == listarep.numElementos &&
                Objects.equals(nombre, listarep.nombre);
    }

    @Override
    public int hashCode() {

        return Objects.hash(idLista, nombre, numElementos);
    }

    @OneToMany(mappedBy = "listarepByListaRep")
    public Collection<Cancioneslista> getCancioneslistasByIdLista() {
        return cancioneslistasByIdLista;
    }

    public void setCancioneslistasByIdLista(Collection<Cancioneslista> cancioneslistasByIdLista) {
        this.cancioneslistasByIdLista = cancioneslistasByIdLista;
    }

    @ManyToOne
    @JoinColumn(name = "idUser", referencedColumnName = "idUser", nullable = false)
    public Usuario getUsuarioByIdUser() {
        return usuarioByIdUser;
    }

    public void setUsuarioByIdUser(Usuario usuarioByIdUser) {
        this.usuarioByIdUser = usuarioByIdUser;
    }

    public static Listarep addLista(Usuario user, String nombre) throws Exception{
        Session session = getSession();
        //user.activarListas(session);
        if(!existsListaBool(user,nombre)){
            Listarep newLista = new Listarep();
            newLista.setIdLista(0);
            newLista.setNombre(nombre);
            newLista.setNumElementos(0);
            newLista.setUsuarioByIdUser(user);

            session.beginTransaction();
            session.save( newLista );
            session.getTransaction().commit();
            session.close();

            Collection<Listarep> aux = user.getListarepsByIdUser();
            if(aux!=null){
                List<Listarep> listas = new ArrayList<>(aux);
                listas.add(newLista);
                user.setListarepsByIdUser(listas);
            }else{
                List<Listarep> listas = new ArrayList<>();
                listas.add(newLista);
                user.setListarepsByIdUser(listas);
            }

            return newLista;
        }else{
            session.close();
            throw new Exception("Lista con el mismo nombre ya existe");
        }

    }

    /*
     * Borra la lista nombre de usuario user si esta existe, si no excepcion
     */
    public static void borrarLista(int id) throws Exception{
        Session session = getSession();
        Listarep lista = searchList(id);
        session.beginTransaction();
        session.delete( lista );
        session.getTransaction().commit();
        session.close();
    }

    /*
     * True -> cancion existe para ese user
     * False -> cancion no existe para ese user
     */
    public static boolean existsListaBool(Usuario user, String nombre){
        Collection<Listarep> aux = user.getListarepsByIdUser();
        boolean exists = false;
        if(aux!=null){
            List<Listarep> canciones = new ArrayList<>(aux);
            for(Listarep lista : canciones){
                if(nombre.equals(lista.getNombre())){
                    exists = true;
                    break;
                }
            }
        }
        return exists;
    }

    public static List<Listarep> searchList(String listaS){
        Session session = getSession();
        Query query = session.createQuery("from Listarep where nombre like :lista ");
        query.setParameter("lista", "%"+listaS+"%");
        List<Listarep> lista = query.list();
        session.close();
        return lista;
    }

    public static Listarep searchList(int id) throws Exception{
        Session session = getSession();
        Query query = session.createQuery("from Listarep where idLista = :lista ");
        query.setParameter("lista", id);
        Listarep lista = (Listarep) query.uniqueResult();
        session.close();
        if (lista==null){
            throw new Exception("La lista no existe");
        }
        return lista;
    }
}

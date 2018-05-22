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
    @GenericGenerator(name="gen" , strategy="increment")
    @GeneratedValue(generator="gen")
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

    public static Listarep initLista(Usuario user, String nombre) throws Exception{
        Listarep newLista = new Listarep();
        newLista.setIdLista(0);
        newLista.setNombre(nombre);
        newLista.setNumElementos(0);
        newLista.setUsuarioByIdUser(user);

        return newLista;
    }

    /*
     * True -> cancion existe para ese user
     * False -> cancion no existe para ese user
     */
    public static boolean existsLista(Usuario user, String nombre){
        boolean exists = false;
        for (Listarep lista : user.getListarepsByIdUser()){
            if(lista.getNombre()==nombre){
                exists = true;
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
}

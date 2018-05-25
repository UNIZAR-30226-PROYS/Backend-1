package main.java.model;

import javax.persistence.*;
import java.util.Objects;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.Query;
import static main.java.HibernateUtil.getSession;

@Entity
public class Suscribir {
    private int idSuscripcion;
    private Usuario usuarioByIdSuscrito;
    private Usuario usuarioByIdSuscriptor;

    @Id
    @Column(name = "idSuscripcion")
    public int getIdSuscripcion() {
        return idSuscripcion;
    }

    public void setIdSuscripcion(int idSuscripcion) {
        this.idSuscripcion = idSuscripcion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Suscribir suscribir = (Suscribir) o;
        return idSuscripcion == suscribir.idSuscripcion;
    }

    @Override
    public int hashCode() {

        return Objects.hash(idSuscripcion);
    }

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "idSuscrito", referencedColumnName = "idUser", nullable = false)
    public Usuario getUsuarioByIdSuscrito() {
        return usuarioByIdSuscrito;
    }

    public void setUsuarioByIdSuscrito(Usuario usuarioByIdSuscrito) {
        this.usuarioByIdSuscrito = usuarioByIdSuscrito;
    }

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "idSuscriptor", referencedColumnName = "idUser", nullable = false)
    public Usuario getUsuarioByIdSuscriptor() {
        return usuarioByIdSuscriptor;
    }

    public void setUsuarioByIdSuscriptor(Usuario usuarioByIdSuscriptor) {
        this.usuarioByIdSuscriptor = usuarioByIdSuscriptor;
    }

    /*------------------------------------------------------------------------------------------------------------------
     *------------------------------------------------------------------------------------------------------------------
     *--------------------------------------------FUNCIONES PROPIAS-----------------------------------------------------
     *------------------------------------------------------------------------------------------------------------------
     *----------------------------------------------------------------------------------------------------------------*/

    /*------------------------------------------------------------------------------------------------------------------
     *-----------------------------------   CREACION BORRADO Y MODIFICACION   ------------------------------------------
     *----------------------------------------------------------------------------------------------------------------*/

    public static Suscribir addSuscripcion(int suscriptor, int suscrito) throws Exception{
        Session session = getSession();
        if(!existsSuscribir(suscriptor,suscrito)){
            Suscribir newSus = new Suscribir();
            Usuario uSuscriptor = Usuario.getUser(suscriptor);
            Usuario uSuscrito = Usuario.getUser(suscrito);

            newSus.setUsuarioByIdSuscriptor(uSuscriptor);
            newSus.setUsuarioByIdSuscrito(uSuscrito);

            session.beginTransaction();
            session.save( newSus );
            session.getTransaction().commit();

            session.close();
            return newSus;
        }else{
            session.close();
            throw new Exception("Ya estas suscrito a dicho usuario.");
        }
    }

    public static Suscribir addSuscripcion(int suscriptor, String suscrito) throws Exception{
        return addSuscripcion(suscriptor, Usuario.getUser(suscrito).getIdUser());
    }

    /*------------------------------------------------------------------------------------------------------------------
     *---------------------------------------------      EXIST      ----------------------------------------------------
     *----------------------------------------------------------------------------------------------------------------*/

    /*
        Devuelve si y solo si suscriptor esta suscrito a suscrito.
     */
    public static boolean existsSuscribir(int suscriptor, int suscrito) throws Exception{
        Session session = getSession();
        Boolean exists = false;
        Usuario uSuscrito = Usuario.getUser(suscrito);
        Usuario uSuscriptor = Usuario.getUser(suscriptor);
        Query query = session.createQuery("from Suscribir where usuarioByIdSuscriptor = :suscriptor and usuarioByIdSuscrito = :suscrito");
        query.setParameter("suscriptor", uSuscriptor);
        query.setParameter("suscrito", uSuscrito);
        if (query.uniqueResult() != null){
            exists = true;
        }
        session.close();
        return exists;
    }

    /*------------------------------------------------------------------------------------------------------------------
     *---------------------------------------------     SEARCH      ----------------------------------------------------
     *----------------------------------------------------------------------------------------------------------------*/

    /*
       Devuelve lista de elementos de Suscribir que tengan el string user como suscriptor
    */
    public static List<Suscribir> searchSuscripciones(int user) throws Exception{
        Session session = getSession();
        Usuario usuario = Usuario.getUser(user);
        Query query = session.createQuery("from Suscribir where usuarioByIdSuscriptor = :suscriptor ");
        query.setParameter("suscriptor", usuario);
        List<Suscribir> lista = query.getResultList();
        session.close();
        return lista;
    }
}

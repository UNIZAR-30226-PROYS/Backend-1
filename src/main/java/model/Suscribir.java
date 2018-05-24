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

    @ManyToOne
    @JoinColumn(name = "idSuscrito", referencedColumnName = "idUser", nullable = false)
    public Usuario getUsuarioByIdSuscrito() {
        return usuarioByIdSuscrito;
    }

    public void setUsuarioByIdSuscrito(Usuario usuarioByIdSuscrito) {
        this.usuarioByIdSuscrito = usuarioByIdSuscrito;
    }

    @ManyToOne
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

    public static Suscribir addSuscripcion(String suscriptor, String suscrito) throws Exception{
        Session session = getSession();
        if(existsSuscribir(suscriptor,suscrito)){
            session.close();
            throw new Exception("Ya estas suscrito a dicho usuario.");
        }
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

    }

    /*------------------------------------------------------------------------------------------------------------------
     *---------------------------------------------      EXIST      ----------------------------------------------------
     *----------------------------------------------------------------------------------------------------------------*/

    /*
        Devuelve si y solo si suscriptor esta suscrito a suscrito.
     */
    public static boolean existsSuscribir(String suscriptor, String suscrito) throws Exception{
        Session session = getSession();
        Boolean exists = false;
        Usuario uSuscriptor = Usuario.getUser(suscriptor);
        Usuario uSuscrito = Usuario.getUser(suscrito);
        Query query = session.createQuery("from Suscribir where usuarioByIdSuscriptor = :suscriptor and usuarioByIdSuscrito = :suscrito");
        query.setParameter("suscriptor", uSuscriptor);
        query.setParameter("suscrito", uSuscrito);
        exists = ! query.getResultList().isEmpty();


        session.close();
        return exists;

    }

    /*------------------------------------------------------------------------------------------------------------------
     *---------------------------------------------     SEARCH      ----------------------------------------------------
     *----------------------------------------------------------------------------------------------------------------*/

    /*
       Devuelve lista de elementos de Suscribir que tengan el string user como suscriptor
    */
    public static List<Suscribir> searchSuscripciones(String user) throws Exception{
        Session session = getSession();
        Usuario uSuscriptor = Usuario.getUser(user);
        Query query = session.createQuery("from Suscribir where usuarioByIdSuscriptor = :suscriptor ");
        query.setParameter("suscriptor", uSuscriptor);
        List<Suscribir> lista = query.getResultList();
        session.close();
        return lista;
    }
}

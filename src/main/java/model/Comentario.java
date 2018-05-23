package main.java.model;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;
import java.util.Objects;
import org.hibernate.Session;
import static main.java.HibernateUtil.getSession;

@Entity
public class Comentario {
    private int idComentario;
    private String cuerpo;
    private Date fechaSubida;
    private Cancion cancionByIdCancion;
    private Usuario usuarioByIdUser;

    @Id
    @Column(name = "idComentario")
    public int getIdComentario() {
        return idComentario;
    }

    public void setIdComentario(int idComentario) {
        this.idComentario = idComentario;
    }

    @Basic
    @Column(name = "cuerpo")
    public String getCuerpo() {
        return cuerpo;
    }

    public void setCuerpo(String cuerpo) {
        this.cuerpo = cuerpo;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comentario that = (Comentario) o;
        return idComentario == that.idComentario &&
                Objects.equals(cuerpo, that.cuerpo) &&
                Objects.equals(fechaSubida, that.fechaSubida);
    }

    @Override
    public int hashCode() {

        return Objects.hash(idComentario, cuerpo, fechaSubida);
    }

    @ManyToOne
    @JoinColumn(name = "idCancion", referencedColumnName = "idCancion", nullable = false)
    public Cancion getCancionByIdCancion() {
        return cancionByIdCancion;
    }

    public void setCancionByIdCancion(Cancion cancionByIdCancion) {
        this.cancionByIdCancion = cancionByIdCancion;
    }

    @ManyToOne
    @JoinColumn(name = "idUser", referencedColumnName = "idUser", nullable = false)
    public Usuario getUsuarioByIdUser() {
        return usuarioByIdUser;
    }

    public void setUsuarioByIdUser(Usuario usuarioByIdUser) {
        this.usuarioByIdUser = usuarioByIdUser;
    }


    public static Comentario addComentario(Usuario user, Cancion cancion,String texto) throws Exception{
        Session session = getSession();

        Comentario newCom = new Comentario();

        newCom.setCuerpo(texto);
        newCom.setUsuarioByIdUser(user);
        newCom.setCancionByIdCancion(cancion);
        newCom.setFechaSubida(new Date(0));

        session.beginTransaction();
        session.save( newCom );
        session.getTransaction().commit();

        session.close();
        return newCom;

    }

    public static List<Comentario> searchComentarios(Cancion cancion) throws Exception{
        Session session = getSession();
        Query query = session.createQuery("from Comentario where cancionByIdCancion = :cancion ");
        query.setParameter("cancion", cancion);
        List<Comentario> lista = query.getResultList();
        session.close();
        return lista;
    }
}

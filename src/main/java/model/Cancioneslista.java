package main.java.model;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import org.hibernate.Session;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.query.Query;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.sql.Date;

import static main.java.HibernateUtil.getSession;

@Entity
public class Cancioneslista {
    private int idCancLista;
    private Date fechaIntroduccion;
    private Listarep listarepByListaRep;
    private Cancion cancionByIdCancion;

    @Id
    @GenericGenerator(name="genCanLis" , strategy="increment")
    @GeneratedValue(generator="genCanLis")
    @Column(name = "idCancLista")
    public int getIdCancLista() {
        return idCancLista;
    }

    public void setIdCancLista(int idCancLista) {
        this.idCancLista = idCancLista;
    }

    @Basic
    @Column(name = "fechaIntroduccion")
    public Date getFechaIntroduccion() {
        return fechaIntroduccion;
    }

    public void setFechaIntroduccion(Date fechaIntroduccion) {
        this.fechaIntroduccion = fechaIntroduccion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cancioneslista that = (Cancioneslista) o;
        return idCancLista == that.idCancLista &&
                Objects.equals(fechaIntroduccion, that.fechaIntroduccion);
    }

    @Override
    public int hashCode() {

        return Objects.hash(idCancLista, fechaIntroduccion);
    }

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "listaRep", referencedColumnName = "idLista", nullable = false)
    public Listarep getListarepByListaRep() {
        return listarepByListaRep;
    }

    public void setListarepByListaRep(Listarep listarepByListaRep) {
        this.listarepByListaRep = listarepByListaRep;
    }

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "idCancion", referencedColumnName = "idCancion", nullable = false)
    public Cancion getCancionByIdCancion() {
        return cancionByIdCancion;
    }

    public void setCancionByIdCancion(Cancion cancionByIdCancion) {
        this.cancionByIdCancion = cancionByIdCancion;
    }

    /*------------------------------------------------------------------------------------------------------------------
     *------------------------------------------------------------------------------------------------------------------
     *--------------------------------------------FUNCIONES PROPIAS-----------------------------------------------------
     *------------------------------------------------------------------------------------------------------------------
     *----------------------------------------------------------------------------------------------------------------*/

    /*------------------------------------------------------------------------------------------------------------------
     *-----------------------------------   CREACION BORRADO Y MODIFICACION   ------------------------------------------
     *----------------------------------------------------------------------------------------------------------------*/

    public static Listarep addCancALista(Usuario user, String lista, Cancion cancion) throws Exception{
        Listarep listarep = Usuario.getLista(user,lista);
        addCancALista(listarep, cancion);
        return listarep;
    }

    public static Listarep borrarCancDeLista(Usuario user, String lista, Cancion cancion) throws Exception{
        Listarep listarep = Usuario.getLista(user,lista);
        borrarCancDeLista(listarep, cancion);
        return listarep;
    }

    //TODO: si es historial ver que se añada al final, y si ya existe en historial actualizar fecha
    public static Listarep addCancALista(Listarep lista, Cancion cancion) throws Exception{
        Session session = getSession();
        if(!existsCancList(lista,cancion)){
            Cancioneslista objeto = new Cancioneslista();
            objeto.setIdCancLista(0);
            objeto.setFechaIntroduccion(new Date(0));
            objeto.setCancionByIdCancion(cancion);
            objeto.setListarepByListaRep(lista);

            session.beginTransaction();
            session.save( objeto );
            session.getTransaction().commit();
            session.close();

            //session.refresh(lista);
            return lista;
        }else{
            session.close();
            throw new Exception("Cancion ya existe en dicha lista");
        }
    }

    public static Listarep borrarCancDeLista(Listarep lista, Cancion cancion) throws Exception{
        Session session = getSession();
        Collection<Cancioneslista> listacanciones = lista.getCancioneslistasByIdLista();
        if(listacanciones!=null) {
            Cancioneslista borrar = null;
            listacanciones.contains(cancion);
            for (Cancioneslista x : listacanciones) {
                if (x.getCancionByIdCancion().getIdCancion() == cancion.getIdCancion() &&
                        x.getListarepByListaRep().getIdLista() == lista.getIdLista()){
                    borrar = x;
                    break;
                }
            }
            if(borrar != null){
                session.beginTransaction();
                session.delete(borrar);
                session.getTransaction().commit();
                session.refresh(lista);
                session.close();
                return lista;
            }else{
                session.close();
                throw new Exception("La lista no contiene dicha cancion");
            }
        }else{
            session.close();
            throw new Exception("La lista es vacia");
        }
    }

    /*------------------------------------------------------------------------------------------------------------------
     *---------------------------------------------      EXIST      ----------------------------------------------------
     *----------------------------------------------------------------------------------------------------------------*/

    public static boolean existsCancList(Listarep lista, Cancion song){
        Collection<Cancioneslista> aux = lista.getCancioneslistasByIdLista();
        boolean exists = false;
        if(aux!=null){
            List<Cancioneslista> canciones = new ArrayList<>(aux);
            for(Cancioneslista cancion : canciones){
                try{
                    Cancion temp = Cancion.getCancion(cancion.getIdCancLista());
                    if(song.getNombre().equals(temp.getNombre())){
                        exists = true;
                        break;
                    }
                }catch (Exception e){}
            }
        }
        return exists;
    }
}

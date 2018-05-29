package model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import util.FileOperations;
import org.hibernate.Session;
import org.hibernate.annotations.GenericGenerator;

import static util.HibernateUtil.getSession;

@Entity
public class Cancioneslista implements Comparable<Cancioneslista>{
    private int idCancLista;
    private Timestamp fechaIntroduccion;
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
    public Timestamp getFechaIntroduccion() {
        return fechaIntroduccion;
    }

    public void setFechaIntroduccion(Timestamp fecha) {
        long millis=System.currentTimeMillis();
        java.sql.Timestamp date=new java.sql.Timestamp(millis);
        this.fechaIntroduccion = date;
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
        Cancioneslista aux = existsCancList(lista,cancion);
        if(aux==null){
            System.out.println("Entro en el if");
            Cancioneslista objeto = new Cancioneslista();
            objeto.setIdCancLista(0);
            objeto.setFechaIntroduccion(new Timestamp(0));
            objeto.setCancionByIdCancion(cancion);
            objeto.setListarepByListaRep(lista);

            session.beginTransaction();
            session.save( objeto );
            session.getTransaction().commit();
            session.close();

            //session.refresh(lista);
            return lista;
        }else{
            System.out.println("Entro en el else");
            aux.setFechaIntroduccion(new Timestamp(0));
            session.beginTransaction();
            session.update( aux );
            session.getTransaction().commit();
            session.refresh(lista);
            session.close();
            return lista;
        }
    }

    public static Listarep borrarCancDeLista(Listarep lista, Cancion cancion) throws Exception{
        Session session = getSession();
        Collection<Cancioneslista> listacanciones = lista.getCancioneslistasByIdLista();
        if(listacanciones!=null) {
            Cancioneslista borrar = null;
            for (Cancioneslista x : listacanciones) {
                if (x.getCancionByIdCancion().getIdCancion() == cancion.getIdCancion() &&
                        x.getListarepByListaRep().getIdLista() == lista.getIdLista()){
                    borrar = x;
                    break;
                }
            }
            if(borrar != null){
                session.beginTransaction();
                if(lista.getNombre().equals("mimusica")){
                    FileOperations.delete("/contenido/canciones/"+Integer.toString(cancion.getIdCancion())+".mp3");
                    FileOperations.delete("/contenido/imagenes/canciones/"+Integer.toString(cancion.getIdCancion())+".png");
                    session.delete(cancion);
                }else{
                    session.delete(borrar);
                }
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

    public static Cancioneslista existsCancList(Listarep lista, Cancion song){
        Collection<Cancioneslista> aux = lista.getCancioneslistasByIdLista();
        if(aux!=null){
            List<Cancioneslista> canciones = new ArrayList<>(aux);
            for(Cancioneslista cancionlista : canciones){
                try{
                    Cancion cancion = cancionlista.getCancionByIdCancion();
                    if(song.getIdCancion()==cancion.getIdCancion()){
                        return cancionlista;
                    }
                }catch (Exception e){}
            }
        }
        return null;
    }

    @Override
    public int compareTo(Cancioneslista cancion) {
        return this.fechaIntroduccion.compareTo(cancion.fechaIntroduccion);
    }

    @Override
    public String toString() {
        return "Cancioneslista{" +
                "cancionByIdCancion=" + cancionByIdCancion +
                '}';
    }
}

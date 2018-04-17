package ControllerORM;

import ORM.*;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.naming.AuthenticationException;

public class LoginORM {

    public static Session getSession() throws HibernateException {
        return HibernateUtil.getSessionFactory().getCurrentSession();
        //return HibernateUtil.getSessionFactory().openSession(); //para conversaciones "largas"
    }

    /*
     * Anyade un nuevo usuario a la base de datos en caso de que el username no este cogido
     * Devuelve el Usuario creado
     */
    public Usuario addUser(String username, String password, String email) throws Exception{
        Session session = getSession();
        if (!existsUser(username)){
            Usuario newUser = new Usuario();

            newUser.setIdUser(username);
            String hashPwd = password;
            newUser.setContrasenya(hashPwd);
            newUser.setEmail(email);

            newUser.setNomAp("");
            newUser.setUltRep(0);
            newUser.setPublico(false);
            newUser.setConexion("conectado");

            session.beginTransaction();
            session.save( newUser );
            session.getTransaction().commit();

            session.close();
            return newUser;
        }else{
            session.close();
            throw new Exception("Nombre de usuario ya existe");
        }
    }

    /*
     * Devuelve el usuario siempre que exista y la contrasenya sea correcta,
     * si no, lanza excepcion
     */
    public Usuario login(String username, String password) throws Exception{
        Session session = getSession();
        try {
            Usuario user = correctUser(username, password);
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
    public boolean existsUser(String username){
        Session session = getSession();
        Query query = session.createQuery("from Usuario where idUser = :user ");
        query.setParameter("user", username);
        boolean exists = (Long) query.uniqueResult() > 0;
        session.close();
        return exists;
    }

    /*
     * Devuelve el usuario en caso de que exista y la contraseña sea correcta
     */
    public Usuario correctUser(String username, String password) throws Exception{
        Session session = getSession();
        Query query = session.createQuery("from Usuario where idUser = :user ");
        query.setParameter("user", username);
        Usuario user = (Usuario) query.uniqueResult();
        session.close();
        if (user==null){
            throw new Exception("Usuario no existe");
        }else if (user.getContrasenya()!=password){
            throw new AuthenticationException("Contrasenya erronea");
        }
        return user;
    }

}

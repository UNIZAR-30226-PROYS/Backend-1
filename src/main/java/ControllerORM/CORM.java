package ControllerORM;

import ORM.*;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class CORM {

    public void addUser(Session session, String username, String password, String email){
        if (!existsUser(session, username)){
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
        }
    }

    public boolean existsUser(Session session, String username){
        Query query = session.createQuery("from Usuario where idUser = :user ");
        query.setParameter("user", username);
        boolean exists = (Long) query.uniqueResult() > 0;
        return exists;
    }

}

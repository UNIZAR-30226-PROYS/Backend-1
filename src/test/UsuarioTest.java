
import org.junit.Test;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Assert;

import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class UsuarioTest {

    static main.java.model.Usuario u;


    @BeforeClass
    static public void beforeTest(){
        u = new main.java.model.Usuario("Nico", "Nicolas Lera", "hello@mail.com", "1234");
        main.java.model.Usuario.addUser("UsuarioTest2", "123456789", "uTest@gmai.com");
    }


    @Test
    public void test1(){
        System.out.println("Test 1");
        assertTrue("No deberia haber reproducciones",u.getUltRep()==0);
        assertTrue("Deberia estar desconectado",!u.getConexion());
        assertTrue("Perfil deberia ser privado",!u.isPublico());
    }

    @Test
    public void test2(){
        main.java.model.Usuario user;
        try {
            user = main.java.model.Usuario.getUser("UsuarioTest2");
        }
        catch(Exception e) {
            System.out.println("El usuario no existe");
        }
        assertTrue("Nombre de usuario deberia coincidir",user.getIdUser().equals("UsuarioTest2"));
        assertTrue("El email deberia ser uTest@gmail.com",user.getEmail().equals("uTest@gmai.com"));
        assertTrue("El usuario tendria que ser anonimo",user.getNomAp().equals("Anonimo"));
        Collection<main.java.model.Listarep> c = user.getListarepsByIdUser();
        assertTrue("Deberia tener listas asignadas al comenzar",c.isEmpty());
    }


}

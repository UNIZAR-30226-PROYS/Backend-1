import org.junit.Test;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Assert;

import static org.junit.Assert.assertTrue;

public class SuscribirTest {

    private Usuario u;
    //@Test

    @BeforeClass
    public void antes(){
        try {
            Usuario.addUser("UsuarioTest1", "987654321", "usuario@usuario.com");
        }
        catch (Exception e){
            System.out.println("No se puede insertar");
        }
    }

    @Test
    public void Test(){
        try {
            Suscribir.addSuscripcion("UsuarioTest2", "UsuarioTest1");
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            assertTrue("Deberia existir la subscripcion",
                    Suscribir.existsSuscribir("UsuarioTest2", "UsuarioTest1"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

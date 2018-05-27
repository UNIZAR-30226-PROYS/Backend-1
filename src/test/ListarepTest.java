import org.junit.Test;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Assert;

import static org.junit.Assert.assertTrue;

public class ListarepTest {

    private Usuario user;

    @BeforeClass
    public void antesTest(){
        try {
            this.user = Usuario.getUser("UsuarioTest2");
        }
        catch (Exception e){
            System.out.println("No se encuentra el usuario");
        }
    }

    @Test
    public void test(){
        Listarep.addLista(this.user,"NuevisimaLista");
        assertTrue("La lista tendria que existir",
                Listarep.existsListaBool(this.user,"NuevisimaLista"));
    }
}

import org.junit.Test;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Assert;

import static org.junit.Assert.assertTrue;

public class ListarepTest {

    private main.java.model.Usuario user;

    @BeforeClass
    public void antesTest(){
        try {
            this.user = main.java.model.Usuario.getUser("UsuarioTest2");
        }
        catch (Exception e){
            System.out.println("No se encuentra el usuario");
        }
    }

    @Test
    public void test(){
        main.java.model.Listarep.addLista(this.user,"NuevisimaLista");
        assertTrue("La lista tendria que existir",
                main.java.model.Listarep.existsListaBool(this.user,"NuevisimaLista"));
    }
}

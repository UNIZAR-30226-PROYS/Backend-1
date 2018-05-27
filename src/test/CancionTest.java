
import main.java.model.Usuario;
import org.junit.Test;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class CancionTest {
    private Usuario u;

    @BeforeClass
    public void beforeTest(){
        try {
            this.u = Usuario.getUser("UsuarioTest2");
            main.java.model.Cancion.addCancion("1,2,3","infantil",u);
        }
        catch(Exception e){
            System.out.println("Ese usuario no existe");
        }
    }

    @Test
    public void Test(){

        assertTrue("Cancion deberia existir", main.java.model.Cancion.existsCancion(this.u,"1,2,3"));

        List<main.java.model.Cancion> l = main.java.model.Cancion.searchSong("1,2,3");

        boolean existe = false;
        for(main.java.model.Cancion aux: l){
            if(aux.getNombre()=="1,2,3"){
                existe = true;
            }
        }

        assertTrue("Cancion deberia encontrarse",existe);
    }

}

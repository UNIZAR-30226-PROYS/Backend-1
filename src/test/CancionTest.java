
import main.java.model.Usuario;
import org.junit.Test;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class CancionTest {
    private main.java.model.Cancion c;

    @BeforeClass
    public void beforeTest(){
        c = new main.java.model.Cancion();
    }

    @Test
    public void Test(){
        main.java.model.Usuario u = new main.java.model.Usuario("Nico", "Nicolas Lera",
                "hello@mail.com", "1234");
        c = main.java.model.Cancion.addCancion("hola","rock",u);
        assertTrue("Cancion deberia existir", main.java.model.Cancion.existsCancion(u,"hola"));

        List<main.java.model.Cancion> l = main.java.model.Cancion.searchSong("hola");

        boolean existe = false;
        for(main.java.model.Cancion aux: l){
            if(aux.getNombre()=="hola"){
                existe = true;
            }
        }

        assertTrue("Cancion deberia encontrarse",existe);
    }

}

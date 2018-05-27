
import Usuario;
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
            Cancion.addCancion("1,2,3","infantil",u);
        }
        catch(Exception e){
            System.out.println("Ese usuario no existe");
        }
    }

    @Test
    public void Test(){

        assertTrue("Cancion deberia existir", Cancion.existsCancion(this.u,"1,2,3"));

        List<Cancion> l = Cancion.searchSong("1,2,3");

        boolean existe = false;
        for(Cancion aux: l){
            if(aux.getNombre()=="1,2,3"){
                existe = true;
            }
        }

        assertTrue("Cancion deberia encontrarse",existe);
    }

}

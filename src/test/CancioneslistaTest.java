import main.java.model.*;
import org.junit.Test;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Assert;

import java.util.Collection;
import java.util.Iterator;

import static org.junit.Assert.assertTrue;

public class CancioneslistaTest {
    private Listarep lr;
    private Cancion song;

    @BeforeClass
    public void antesTest() throws Exception {
        Usuario u = Usuario.getUser("UsuarioTest2");
        Collection<Listarep> c1 = u.getListarepsByIdUser();
        Iterator<Listarep> it = c1.iterator();
        boolean encontrado = false;
        Listarep aux;
        while(it.hasNext()&&!encontrado){
            aux = it.next();
            if(aux.getNombre().equals("NuevisimaLista")){
                encontrado=true;
                this.lr=aux;
            }
        }

        Collection<Cancion> c2 = u.getCancionsByIdUser();
        Iterator<Cancion> it1 = c2.iterator();
        boolean encontrado1 = false;
        Cancion aux1;
        while(it.hasNext()&&!encontrado){
            aux1 = it1.next();
            if(aux1.getNombre().equals("1,2,3")){
                encontrado=true;
                this.song=aux1;
            }
        }
    }
    
    @Test
    public void test() throws Exception {
        Cancioneslista.addCancALista(this.lr,this.song);
        assertTrue("Deberia existir la cancion en la lista", Cancioneslista.existsCancList(this.lr,this.song));
    }

}

import org.junit.Test;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Assert;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class ComentarioTest {
    private Usuario u;
    private Cancion song;

    @BeforeClass
    public void antesDeTest(){
        try{
             u = Usuario.getUser("UsuarioTest2");
        }
        catch (Exception e){
            System.out.println("No se puede elegir el usuario");
        }

        Collection<Cancion> c = u.getCancionsByIdUser();

        Iterator<Cancion> it = c.iterator();
        if(it.hasNext()) {
            this.song = it.next();
        }
        else{
            System.out.println("No hay canciones");
        }

        Comentario.addComentario(this.u, this.song,"Me gusta");
    }

    @Test
    public void test(){
        List<Comentario> li = Comentario.searchComentarios(this.song);
        Iterator<Comentario> it = li.iterator();
        assertTrue("Debería existir un comentario",it.hasNext());
        Comentario com = it.next();
        assertTrue("El texto no coincide",com.getCuerpo().equals("Me gusta"));
        Usuario user = com.getUsuarioByIdUser();
        assertTrue("El usuario no es el mismo",user.equals(this.u));
        Cancion song = com.getCancionByIdCancion();
        assertTrue("La cancion no es la misma",song.equals(this.song));
    }

}

import org.junit.Test;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Assert;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class ComentarioTest {
    private main.java.model.Usuario u;
    private main.java.model.Cancion song;

    @BeforeClass
    public void antesDeTest(){
        try{
             u = main.java.model.Usuario.getUser("UsuarioTest2");
        }
        catch (Exception e){
            System.out.println("No se puede elegir el usuario");
        }

        Collection<main.java.model.Cancion> c = u.getCancionsByIdUser();

        Iterator<main.java.model.Cancion> it = c.iterator();
        if(it.hasNext()) {
            this.song = it.next();
        }
        else{
            System.out.println("No hay canciones");
        }

        main.java.model.Comentario.addComentario(this.u, this.song,"Me gusta");
    }

    @Test
    public void test(){
        List<main.java.model.Comentario> li = main.java.model.Comentario.searchComentarios(this.song);
        Iterator<main.java.model.Comentario> it = li.iterator();
        assertTrue("Debería existir un comentario",it.hasNext());
        main.java.model.Comentario com = it.next();
        assertTrue("El texto no coincide",com.getCuerpo().equals("Me gusta"));
        main.java.model.Usuario user = com.getUsuarioByIdUser();
        assertTrue("El usuario no es el mismo",user.equals(this.u));
        main.java.model.Cancion song = com.getCancionByIdCancion();
        assertTrue("La cancion no es la misma",song.equals(this.song));
    }

}

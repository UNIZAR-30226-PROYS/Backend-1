import org.junit.runner.JUnitCore;

public class Main {
    public static void Main(){
        JUnitCore.runClasses(UsuarioTest.class);
        JUnitCore.runClasses(CancionTest.class);
        JUnitCore.runClasses(ComentarioTest.class);
        JUnitCore.runClasses(SuscribirTest.class);
        JUnitCore.runClasses(ListarepTest.class);
        JUnitCore.runClasses(CancioneslistaTest.class);
    }
}

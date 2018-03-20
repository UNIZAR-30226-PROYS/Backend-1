import ORM.*;

import java.sql.Date;

public class crear {
    Usuario user(String username, String nomAp, String email){
        Usuario newUser = new Usuario();
        newUser.setIdUser(username);
        newUser.setNomAp(nomAp);
        newUser.setEmail(email);
        newUser.setConexion("desconectado");
        newUser.setUltRep(0);
        newUser.setContrasenya(" ");
        newUser.setPublico(false);
        return newUser;
    }

    /*Cancion sin album*/
    Cancion cancion(String nombre, String genero, int duracion, Date fecha, Usuario user) {
        Cancion newSong = new Cancion();
        newSong.setNombre(nombre);
        newSong.setGenero(genero);
        newSong.setDuracion(duracion);
        newSong.setFechaSubida(fecha);
        newSong.setNumRep(0);
        newSong.setUsuarioByIdUser(user);
        return newSong;
    }

    /*Cancion con album*/
    Cancion cancion(String nombre, String genero, int duracion, Usuario user, Date fecha, Album album) {
        Cancion newSong = new Cancion();
        newSong.setNombre(nombre);
        newSong.setGenero(genero);
        newSong.setDuracion(duracion);
        newSong.setFechaSubida(fecha);
        newSong.setNumRep(0);
        newSong.setUsuarioByIdUser(user);
        newSong.setAlbumByIdAlbum(album);
        return newSong;
    }

}

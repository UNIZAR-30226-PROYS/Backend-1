package controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.*;
import java.nio.file.StandardCopyOption;


public class changeFileName {


    //Old name ruta antigua del fichero, newName ruta nueva.
    public static void rename(String oldName, String newName)throws Exception {
        File from = new File(oldName);
        File to = new File(newName);
        try {
            Files.move(from.toPath(),to.toPath(), StandardCopyOption.REPLACE_EXISTING);
        }
        catch (Exception e) {
            throw new Exception("no se ha podido mover el fichero:"+e.getMessage());
        }
    }
}
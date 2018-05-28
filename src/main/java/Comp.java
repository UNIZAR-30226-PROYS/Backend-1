package main.java;

import main.java.model.Cancioneslista;
import java.util.Comparator;

public class Comp implements Comparator<Cancioneslista> {
    @Override
    public int compare(Cancioneslista a, Cancioneslista b) {
        return a.getFechaIntroduccion().compareTo(b.getFechaIntroduccion());// compare here all values
    }
}
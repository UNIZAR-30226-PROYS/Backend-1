package main.java.controller;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Iterator;

import static org.apache.commons.text.WordUtils.capitalizeFully;

public class SongDescriptionController {
    public static JSONObject readJsonFromUrl(String url) throws IOException {
        InputStream is = new URL(url).openStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = rd.readLine();    //JSON solo tendrá una línea
            JSONObject json = new JSONObject(jsonText);
            return json;
        } finally {
            is.close();
        }
    }

    public static String getText(String title) throws IOException {
        String busq = "https://en.wikipedia.org/w/api.php?format=json&action=query&prop=extracts&exintro=1&explaintext=1&titles=";
        String normTitle = capitalizeFully(title);  //Deja las primeras letras de cada palabra mayúsculas y el resto, minúsculas.
        normTitle = normTitle.replaceAll(" ", "_");
        busq = busq.concat(normTitle);
        JSONObject json = readJsonFromUrl(busq);
        JSONObject query = json.getJSONObject("query");
        JSONObject pages = query.getJSONObject("pages");
        Iterator keys = pages.keys();                                                     //Para obtener la clave variable
        JSONObject val = pages.getJSONObject((String) keys.next());
        String text = val.getString("extract");
        String desc = "La Wikipedia no tiene descripción para esta canción. ¡Anímate a poner una!";
        if(text.contains("song")) desc = text;                                       //Si es la descripción de una canción, la aceptamos.
        return desc;
    }

}

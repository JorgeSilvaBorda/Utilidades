package web;

import com.Parametros;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Clase con métodos para acceso y trabajo WEB.
 * @author Jorge Silva Borda
 */
public class MetodosWeb {
    
    /**
     * Clase para conectar con una determinada URL
     * @param ruta {@code String} - La URL a conectar.
     * @return {@code String} - La respuesta web.
     */
    private static String getWeb(String ruta){
	try {
	    URL url = new URL(ruta);
	    HttpURLConnection urlc = (HttpURLConnection) url.openConnection();
	    BufferedReader br = new BufferedReader(new InputStreamReader(urlc.getInputStream()));
	    StringBuilder builder = new StringBuilder();
	    String linea;
	    while((linea = br.readLine()) != null){
		builder.append(linea).append(Parametros.SEP);
	    }
	    return builder.toString();
	} catch (IOException ex) {
	    System.out.println("No se pudo conectar con la ruta." + Parametros.SEP + ex);
	    return "";
	}
    }
}

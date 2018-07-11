package archivos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import com.Parametros;

/**
 * @author Jorge Silva Borda
 */
public class Archivos {
    
    /**
     * Obtener el texto de un archivo {@code File}.
     * @param archivo {@code File} - Archivo que contiene el texto
     * @return {@code String} - Con el contenido del texto extraído.
     */
    public static String getTextoArchivo(File archivo){
	if(archivo.exists() && archivo.isFile()){
	    try{
		FileReader reader = new FileReader(archivo);
		BufferedReader br = new BufferedReader(reader);
		String linea;
		StringBuilder builder = new StringBuilder();
		while((linea = br.readLine()) != null){
		    builder.append(linea);
		    builder.append(Parametros.SEP);
		}
		return builder.toString();
	    }catch (IOException ex) {
		System.out.println("No se puede obtener el texto del archivo.");
		System.out.println(ex);
	    }
	}
	return "";
    }
    
    /**
     * Obtener el texto de un archivo desde su ruta como {@code String}.
     * @param rutaArchivo {@code String} - String que contiene el texto
     * @return {@code String} - Con el contenido del texto extraído.
     */
    public static String getTextoArchivo(String rutaArchivo){
	return getTextoArchivo(new File(rutaArchivo));
    }
    
    /**
     * Contar las líneas de un archivo {@code File}.
     * @param archivo {@code File} - Archivo con las líneas a contar.
     * @return {@code Integer} - Cantidad de líneas en el archivo leído
     */
    public static int lineasArchivo(File archivo){
	if(archivo.exists() && archivo.isFile()){
	    try{
		FileReader reader = new FileReader(archivo);
		BufferedReader br = new BufferedReader(reader);
		int cont = 0;
		while(br.readLine() != null){
		    cont ++;
		}
		return cont;
	    }catch (IOException ex) {
		System.out.println("No se puede obtener el texto del archivo.");
		System.out.println(ex);
	    }
	}
	return -1;
    }
    
    /**
     * Contar las líneas de un archivo desde la ruta en {@code String} de uno.
     * @param rutaArchivo {@code String} - Archivo con las líneas a contar.
     * @return {@code Integer} - Cantidad de líneas en el archivo leído
     */
    public static int lineasArchivo(String rutaArchivo){
	return lineasArchivo(new File(rutaArchivo));
    }
}
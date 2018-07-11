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
    
    public static String getTextoArchivo(String rutaArchivo){
	return getTextoArchivo(new File(rutaArchivo));
    }
    
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
    
    public static int lineasArchivo(String rutaArchivo){
	return lineasArchivo(new File(rutaArchivo));
    }
}

package indicadores;

import com.Parametros;
import com.excepciones.ExcepcionIndicador;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import json.JSONArray;
import json.JSONException;
import json.JSONObject;

/**
 * Clase para obtener indicadores económicos y de uso general en Chile
 *
 * @author Jorge Silva Borda
 */
public class Indicadores {

    /**
     * Función que retorna arreglo JSON con todos los indicadores actuales. Los indicadores disponibles son: [uf, ivp, dolar, dolar_intercambio, euro,
     * ipc, utm, imacec, tpm, libra_cobre, tasa_desempleo]. Los rangos de fecha de los valores son los siguientes: Unidad de fomento (UF):	Valores
     * desde 1977 hasta hoy. Libra de Cobre:	Valores desde 2012 hasta hoy. Tasa de desempleo:	Valores desde 2009 hasta hoy. Euro:	Valores desde 1999
     * hasta hoy. Imacec:	Valores desde 2004 hasta hoy. Dólar observado:	Valores desde 1984 hasta hoy. Tasa Política Monetaria (TPM):	Valores desde
     * 2001 hasta hoy. Indice de valor promedio (IVP):	Valores desde 1990 hasta hoy. Indice de Precios al Consumidor (IPC):	Valores desde 1928 hasta
     * hoy. Dólar acuerdo:	Valores desde 1988 hasta hoy. Unidad Tributaria Mensual (UTM):	Valores desde 1990 hasta hoy.
     *
     * @return JSONArray.
     * @throws com.excepciones.ExcepcionIndicador en caso de que la URL se encuentre mal compuesta.
     * @see json.JSONArray
     */
    public static JSONArray traerIndicadorJson() throws ExcepcionIndicador {
	JSONArray arr = new JSONArray();
	try {
	    BufferedReader br = getDatos(Parametros.RUTA_WEB_INDICADORES);
	    String line;
	    StringBuilder builder = new StringBuilder();
	    while ((line = br.readLine()) != null) {
		builder.append(line);
		arr = new JSONArray(line);
	    }
	} catch (JSONException ex) {
	    System.out.println("Error al obtener los datos del WebService." + Parametros.SEP + ex);
	} catch (IOException ex) {
	    System.out.println("Error IO");
	    System.out.println(ex);
	}
	return arr;
    }

    /**
     * Función que retorna un String con el indicador requerido y la fecha indicada.
     *
     * @param tipo {@code String}. El tipo de indicador a buscar. Ej: "uf", "utm", "dolar".
     * @param fecha {@code String}. La fecha a buscar el valor en formato dd-mm-aaaa.
     * @return {@code String}. Con el valor del indicador para la fecha enviada.
     * @throws com.excepciones.ExcepcionIndicador en caso de que el tipo de indicador no exista o el formato de la fecha no sea el correcto.
     */
    public static String traerIndicadorTipoFecha(String tipo, String fecha) throws ExcepcionIndicador {
	if (!validarIndicador(tipo)) {
	    throw new ExcepcionIndicador("Excepción al validar el tipo de indicador." + Parametros.SEP + "El indicador '" + tipo + "', no se encuentra.");
	}
	if(!validarFechaIndicador(fecha)){
	    throw new ExcepcionIndicador("Excepción al validar el tipo de indicador." + Parametros.SEP + "El parámetro [fecha] ('" + fecha + "'), posee un formato incorrecto. => 'dd-mm-yyyy'");
	}
	String ruta = Parametros.RUTA_WEB_INDICADORES + tipo + "/" + fecha;
	try {
	    BufferedReader br = getDatos(ruta);
	    String line;
	    StringBuilder builder = new StringBuilder();
	    while ((line = br.readLine()) != null) {
		builder.append(line);
	    }
	    JSONArray arreglo = new JSONObject(builder.toString()).getJSONArray("serie");
	    return arreglo.getJSONObject(0).get("valor").toString();
	} catch (IOException | JSONException ex) {
	    System.out.println("No se pudo obtener el indicador: " + fecha + "." + Parametros.SEP + ex);
	    return "";
	}
    }

    /**
     * Función que retorna Arreglo JSON con todos los valores para las posibles fechas del tipo de indicador seleccionado.
     *
     * @param tipo {@code String}. Con el tipo de indicador a buscar.
     * @return {@code JSONArray}. Con todas las fechas posibles del indicador seleccionado.
     *
     * Unidad de fomento (UF):	Valores desde 1977 hasta hoy. Libra de Cobre:	Valores desde 2012 hasta hoy. Tasa de desempleo:	Valores desde 2009 hasta
     * hoy. Euro:	Valores desde 1999 hasta hoy. Imacec:	Valores desde 2004 hasta hoy. Dólar observado:	Valores desde 1984 hasta hoy. Tasa Política
     * Monetaria (TPM):	Valores desde 2001 hasta hoy. Indice de valor promedio (IVP):	Valores desde 1990 hasta hoy. Indice de Precios al Consumidor
     * (IPC):	Valores desde 1928 hasta hoy. Dólar acuerdo:	Valores desde 1988 hasta hoy. Unidad Tributaria Mensual (UTM):	Valores desde 1990 hasta
     * hoy.
     * @throws com.excepciones.ExcepcionIndicador en caso de que la validación del indicador no sea exitosa (El indicador no existe.).
     * @see json.JSONArray
     */
    public static JSONArray traerIndicadorTipo(String tipo) throws ExcepcionIndicador {
	if (!validarIndicador(tipo)) {
	    throw new ExcepcionIndicador("Excepción al validar el tipo de indicador." + Parametros.SEP + "El indicador '" + tipo + "', no se encuentra.");
	}
	String ruta = Parametros.RUTA_WEB_INDICADORES + tipo;
	try {
	    BufferedReader br = getDatos(ruta);
	    String line;
	    StringBuilder builder = new StringBuilder();
	    while ((line = br.readLine()) != null) {
		builder.append(line);
	    }
	    JSONObject json = new JSONObject(builder.toString());
	    JSONArray arreglo = json.getJSONArray("serie");
	    return arreglo;
	} catch (IOException | JSONException ex) {
	    System.out.println("Error al obtener los datos del WebService." + Parametros.SEP + ex);
	}
	return new JSONArray();
    }

    /**
     * Método privado para obtener el dato desde la URL
     * @param ruta {@code String} - La URL a consultar.
     * @return {@code BufferedReader} - Con la secuencia de respuesta.
     * @throws ExcepcionIndicador En caso de que la respuesta no sea HTTP 200/OK.
     */
    private static BufferedReader getDatos(String ruta) throws ExcepcionIndicador {
	try {
	    URL url = new URL(ruta);
	    HttpURLConnection urlc = (HttpURLConnection) url.openConnection();
	    if(urlc.getResponseCode() != 200){
		throw new ExcepcionIndicador("La consulta web arrojó un error " + urlc.getResponseCode() + "." + Parametros.SEP + "Verifique la composición de la url (" + ruta + ")");
	    }
	    BufferedReader br = new BufferedReader(new InputStreamReader(urlc.getInputStream()));
	    return br;
	} catch (IOException ex) {
	    System.out.println("No se pudo conectar con la ruta." + Parametros.SEP + ex);
	    return null;
	}
    }

    /**
     * Permite validar los indicadores consultados de acuerdo al listado de indicadores publicados en la web. La web del servicio se encuentra
     * parametrizada en {@link com.Parametros}.
     *
     * La validación del parámetro de indicador se realiza en duro sobre un arreglo que se encuentra publicada en esa web. Por lo que debe ser
     * revisada cada cierto tiempo en caso de que aparezcan nuevos indicadores o sean modificados algunos.
     *
     * @param indicador {@code String} - El tipo de indicador a consultar.
     * @return {@code boolean} - {@code true} en caso de que el indicador exista. {@code false} en caso de que no exista.
     */
    private static boolean validarIndicador(String indicador) {
	for (String ind : Parametros.INDICADORES) {
	    if (indicador.trim().equals(ind)) {
		return true;
	    }
	}
	return false;
    }
    
    /**
     * Validador simple de fecha. Largo del campo principalmente.
     * @param fecha {@code String} - La fecha en formato dd-mm-yyyy
     * @return {@code boolean} - {@code true} en caso de ser válida. {@code false} en caso de no serlo.
     */
    private static boolean validarFechaIndicador(String fecha){
	if(fecha.length() < 10 || fecha.length() > 10){
	    return false;
	}
	if(fecha.split("-").length < 3 || fecha.split("-").length > 3){
	    return false;
	}
	return true;
    }

}

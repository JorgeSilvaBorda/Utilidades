package com;

import com.excepciones.ExcepcionComun;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Random;

/**
 * Algoritmos de uso común
 *
 * @author Jorge Silva Borda
 */
public class Comunes {

    /**
     * Genera un numero {@code int} aleatorio entre un mínimo y máximo. Ambos inclusive.
     *
     * @param minimo {@code int} - El tope mínimo
     * @param maximo {@code int} - El tope máximo
     * @return {@code int} - El número generado.
     * @throws com.excepciones.ExcepcionComun
     */
    public static int aleatorioEntre(int minimo, int maximo) throws ExcepcionComun {
	if (minimo > maximo) {
	    throw new ExcepcionComun("Excepción al generar numero aleatorio." + Parametros.SEP + "El mínimo no puede ser mayor que el máximo.");
	}
	Random rand = new Random();
	return rand.nextInt((maximo + minimo) + 1) + minimo;
    }

    /**
     * Genera textos random de un largo definido
     *
     * @param largo {@code int} - El largo esperado de la cadena random generada.
     * @return {@code String} - El texto generado.
     * @throws com.excepciones.ExcepcionComun
     */
    public static String palabraRandom(int largo) throws ExcepcionComun {
	if (largo < 1) {
	    throw new ExcepcionComun("Excepción al generar la palabra random." + Parametros.SEP + "El largo no puede ser menor a '1'");
	}
	char[] separados = "aAbBcCdDeEfFgGhHiIjJkKlLmMnNoOpPqQrRsStTuUvVwWxXyYzZ".toCharArray();
	StringBuilder builder = new StringBuilder();
	try {
	    for (int i = 0; i < largo; i++) {
		builder.append(separados[aleatorioEntre(0, separados.length)]);
	    }
	} catch (ExcepcionComun ex) {
	    System.out.println("Error al generar la palabra random.");
	    System.out.println(ex);
	}

	return builder.toString();
    }

    /**
     * Genera una secuencia aleatoria de números de un largo definido y la devuelve como {@code String}.
     *
     * @param largo {@code int} - El largo deseado de la secuencia de números.
     * @return {@code String} - La secuencia generada como texto.
     * @throws com.excepciones.ExcepcionComun
     */
    public static String secuenciaNumerosString(int largo) throws ExcepcionComun {
	if(largo < 1){
	    throw new ExcepcionComun("Excepción al generar la secuencia de números String." + Parametros.SEP + "El largo no puede ser menor a '1'");
	}
	char[] numeros = "0123456789".toCharArray();
	StringBuilder builder = new StringBuilder();
	try {
	    for (int i = 0; i < largo; i++) { // Secuencia no debe comenzar con "0" en caso de que se desee generar un numero int
		if (i == 0) {
		    builder.append(numeros[aleatorioEntre(1, 9)]);
		} else {
		    builder.append(numeros[aleatorioEntre(0, 9)]);
		}
	    }
	} catch (ExcepcionComun ex) {
	    System.out.println("Error al generar la secuencia de números String.");
	    System.out.println(ex);
	}

	return builder.toString();
    }

    /**
     * Genera una secuencia de números aleatoria de un largo determinado
     *
     * @param largo {@code String} - Largo deseado.
     * @return {@code int} - La secuencia de números generada.
     * @throws com.excepciones.ExcepcionComun
     */
    public static int secuenciaNumeros(int largo) throws ExcepcionComun{
	return Integer.parseInt(secuenciaNumerosString(largo));
    }

    /**
     * Serializa objetos y los escribe en disco. El objeto a serializar debe implementar la clase Serializable.
     *
     * @param o {@code Object} - El objeto a serializar.
     * @param nombreArchivo {@code String} - el nombre del archivo que será escrito con el objeto.
     * @return {@code boolean} - {@code True} si lo escribe, {@code False} Si no lo puede escribir.
     * @see Serializable
     */
    public static boolean serializar(Object o, String nombreArchivo) {
	try {
	    FileOutputStream fos = new FileOutputStream(nombreArchivo);
	    ObjectOutputStream oos = new ObjectOutputStream(fos);
	    oos.writeObject(oos);
	    oos.close();
	    return true;
	} catch (IOException ex) {
	    System.out.println("No se puede serializar el objeto");
	    System.out.println(ex);
	    return false;
	}
    }

    /**
     * Desserializa un objeto en base a su ruta como archivo en disco. Normalmente se debe hacer un cast del objeto que se lee, puesto que todos los
     * devueltos de esta función, son del tipo {@code Object}
     *
     * @param nombreArchivo {@code String} - La ruta del archivo a leer.
     * @return {@code Object} - El objeto leído.
     */
    public static Object desSerializar(String nombreArchivo) {
	try {
	    FileInputStream fis = new FileInputStream(nombreArchivo);
	    ObjectInputStream ois = new ObjectInputStream(fis);
	    Object o = ois.readObject();
	    ois.close();
	    return o;
	} catch (IOException | ClassNotFoundException ex) {
	    System.out.println("No se puede des serializar el objeto");
	    System.out.println(ex);
	    return null;
	}
    }
}

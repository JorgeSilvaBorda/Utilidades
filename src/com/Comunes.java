package com;

import java.util.Random;

/**
 * Algoritmos de uso común
 * @author Jorge Silva Borda
 */
public class Comunes {

    /**
     * Genera un numero {@code int} aleatorio entre un mínimo y máximo. Ambos inclusive.
     * @param minimo {@code int} - El tope mínimo
     * @param maximo {@code int} - El tope máximo
     * @return {@code int} - El número generado.
     */
    public static int aleatorioEntre(int minimo, int maximo) {
	Random rand = new Random();
	return rand.nextInt((maximo + minimo) + 1) + minimo;
    }

    /**
     * Genera textos random de un largo definido
     * @param largo {@code int} - El largo esperado de la cadena random generada.
     * @return {@code String} - El texto generado.
     */
    public static String palabraRandom(int largo) {
	char[] separados = "aAbBcCdDeEfFgGhHiIjJkKlLmMnNoOpPqQrRsStTuUvVwWxXyYzZ".toCharArray();
	StringBuilder builder = new StringBuilder();
	for (int i = 0; i < largo; i++) {
	    builder.append(separados[aleatorioEntre(0, separados.length)]);
	}

	return builder.toString();
    }

    /**
     * Genera una secuencia aleatoria de números de un largo definido y la devuelve como {@code String}.
     * @param largo {@code int} - El largo deseado de la secuencia de números.
     * @return  {@code String} - La secuencia generada como texto.
     */
    public static String secuenciaNumerosString(int largo) {
	char[] numeros = "0123456789".toCharArray();
	StringBuilder builder = new StringBuilder();
	for (int i = 0; i < largo; i++) { // Secuencia no debe comenzar con "0" en caso de que se desee generar un numero int
	    if (i == 0) {
		builder.append(numeros[aleatorioEntre(1, 9)]);
	    } else {
		builder.append(numeros[aleatorioEntre(0, 9)]);
	    }
	}
	return builder.toString();
    }

    /**
     * Genera una secuencia de números aleatoria de un largo determinado
     * @param largo {@code String} - Largo deseado.
     * @return {@code int} - La secuencia de números generada.
     */
    public static int secuenciaNumeros(int largo) {
	return Integer.parseInt(secuenciaNumerosString(largo));
    }
}
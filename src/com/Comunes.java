package com;

import java.util.Random;

/**
 *
 * @author Jorge Silva Borda
 */
public class Comunes {

    public static int aleatorioEntre(int minimo, int maximo) {
	Random rand = new Random();
	return rand.nextInt((maximo + minimo) + 1) + minimo;
    }

    public static String palabraRandom(int largo) {
	char[] separados = "aAbBcCdDeEfFgGhHiIjJkKlLmMnNoOpPqQrRsStTuUvVwWxXyYzZ".toCharArray();
	StringBuilder builder = new StringBuilder();
	for (int i = 0; i < largo; i++) {
	    builder.append(separados[aleatorioEntre(0, separados.length)]);
	}

	return builder.toString();
    }

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

    public static int secuenciaNumeros(int largo) {
	return Integer.parseInt(secuenciaNumerosString(largo));
    }
}
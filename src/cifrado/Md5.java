package cifrado;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * Clase de cifrado MD5
 * @author Jorge Silva Borda
 */
public class Md5 {
    
    /**
     * Cifra un texto con el algoritmo MD5
     * @param mensaje {@code String} - El texto a cifrar.
     * @return {@code String} - El texto cifrado
     * @throws NoSuchAlgorithmException 
     */
    public static String cifrar(String mensaje) throws NoSuchAlgorithmException {
	MessageDigest md = MessageDigest.getInstance("MD5");
	md.update(mensaje.getBytes());
	byte[] digest = md.digest();
	StringBuilder str = new StringBuilder();
	for (byte b : digest) {
	    str.append(Integer.toHexString(0xFF & b));
	}
	return str.toString();
    }
    
    /**
     * Cirfa un texo con el algoritmo MD5 y lo devuelve en formato Base 64
     * @param mensaje {@code String} - El texto a cifrar
     * @return {@code String} - El texto cifrado
     * @throws NoSuchAlgorithmException 
     */
    public static String cifrarBase64(String mensaje) throws NoSuchAlgorithmException {
	MessageDigest md = MessageDigest.getInstance("MD5");
	md.update(mensaje.getBytes());
	byte[] digest = md.digest();
	byte[] encoded = Base64.getEncoder().encode(digest);
	return new String(encoded);
    }
}
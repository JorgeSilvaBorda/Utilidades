package cifrado;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * @author Jorge Silva Borda
 */
public class Sha {
    public static String cifrarSha1(String mensaje) throws NoSuchAlgorithmException {
	MessageDigest md = MessageDigest.getInstance("SHA-1");
	md.update(mensaje.getBytes());
	byte[] digest = md.digest();
	StringBuilder str = new StringBuilder();
	for (byte b : digest) {
	    str.append(Integer.toHexString(0xFF & b));
	}
	return str.toString();
    }
  
    public static String cifrarSha1Base64(String mensaje) throws NoSuchAlgorithmException {
	MessageDigest md = MessageDigest.getInstance("SHA-1");
	md.update(mensaje.getBytes());
	byte[] digest = md.digest();
	byte[] encoded = Base64.getEncoder().encode(digest);
	return new String(encoded);
    }
  
    public static String cifrarSha256Base64(String mensaje) throws NoSuchAlgorithmException {
	MessageDigest md = MessageDigest.getInstance("SHA-256");
	md.update(mensaje.getBytes());
	byte[] digest = md.digest();
	byte[] encoded = Base64.getEncoder().encode(digest);
	return new String(encoded);
    }
    
    public static String cifrarSha256(String mensaje) throws NoSuchAlgorithmException {
	MessageDigest md = MessageDigest.getInstance("SHA-256");
	md.update(mensaje.getBytes());
	byte[] digest = md.digest();
	StringBuilder sr = new StringBuilder();
	for (byte b : digest) {
	    sr.append(Integer.toHexString(0xFF & b));
	}
	return sr.toString();
    }
    
    public static String cifrarSha512Base64(String mensaje) throws NoSuchAlgorithmException {
	MessageDigest md = MessageDigest.getInstance("SHA-512");
	md.update(mensaje.getBytes());
	byte[] digest = md.digest();
	byte[] encoded = Base64.getEncoder().encode(digest);
	return new String(encoded);
    }
    
    public static String cifrarSha512(String mensaje) throws NoSuchAlgorithmException {
	MessageDigest md = MessageDigest.getInstance("SHA-512");
	md.update(mensaje.getBytes());
	byte[] digest = md.digest();
	StringBuilder sr = new StringBuilder();
	for (byte b : digest) {
	    sr.append(Integer.toHexString(0xFF & b));
	}
	return sr.toString();
    }
}
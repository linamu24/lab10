import javax.crypto.Cipher;
import javax.crypto.SecretKey;

public class Simetrico {
private final static String PADDING = "AES/ECB/PKCS5Padding";
 

public static byte[] cifrar(SecretKey llave, String texto){
    byte [] textoCifrado;
    try {
        // Crear un objeto Cipher para cifrar el texto
        Cipher cifrador = Cipher.getInstance(PADDING);
        byte[] textoClaro = texto.getBytes();
        cifrador.init(Cipher.ENCRYPT_MODE, llave);
        textoCifrado = cifrador.doFinal(textoClaro);
        return textoCifrado;
    } catch (Exception e) {
        e.printStackTrace();
        return null;
    }
}

public static byte[] descifrar(SecretKey llave, byte[] textoCifrado){
    byte [] textoClaro;
    try {
        // Crear un objeto Cipher para descifrar el texto
        Cipher cifrador = Cipher.getInstance(PADDING);
        cifrador.init(Cipher.DECRYPT_MODE, llave);
        textoClaro = cifrador.doFinal(textoCifrado);
    } catch (Exception e) {
        e.printStackTrace();
        return null;
    }
    return textoClaro;
    }
}

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class MainAsimetrico4 {
    private final static String ALGORITMO = "RSA";

    public static void imprimir(byte[] contenido) {
        int i = 0;
        for (; i < contenido.length - 1; i++) {
            System.out.print(contenido[i] + " ");
        }
        System.out.println(contenido[i] + " ");
    }

    public static byte[] leerArchivo(String nombreArchivo) throws IOException {
        return Files.readAllBytes(Paths.get(nombreArchivo));
    }

    public static void main(String[] args) throws Exception {

        // Leer las llaves desde archivos
        byte[] llavePrivadaBytes = leerArchivo("llavePrivada.key");
        byte[] llavePublicaBytes = leerArchivo("llavePublica.key");

        // Reconstruir las llaves
        KeyFactory keyFactory = KeyFactory.getInstance(ALGORITMO);
        PKCS8EncodedKeySpec specPrivada = new PKCS8EncodedKeySpec(llavePrivadaBytes);
        X509EncodedKeySpec specPublica = new X509EncodedKeySpec(llavePublicaBytes);

        PrivateKey llavePrivada = keyFactory.generatePrivate(specPrivada);
        PublicKey llavePublica = keyFactory.generatePublic(specPublica);

        // Leer el mensaje cifrado desde archivo
        byte[] mensajeCifrado = leerArchivo("mensajeCifrado.txt");

        System.out.println("Mensaje cifrado leido del archivo en byte[]:");
        imprimir(mensajeCifrado);

        // Descifrar usando la llave privada
        byte[] mensajeDescifrado = Asimetrico.descifrar(llavePrivada, ALGORITMO, mensajeCifrado);

        System.out.println("Mensaje descifrado en byte[]:");
        imprimir(mensajeDescifrado);

        System.out.println("Mensaje descifrado como texto:");
        System.out.println(new String(mensajeDescifrado));
    }
}

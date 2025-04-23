import java.io.FileOutputStream;
import java.io.IOException;
import java.security.*;
import java.util.Scanner;

public class MainAsimetrico3 {
    private final static String ALGORITMO = "RSA";

    public static void imprimir(byte[] contenido) {
        int i = 0;
        for (; i < contenido.length - 1; i++) {
            System.out.print(contenido[i] + " ");
        }
        System.out.println(contenido[i] + " ");
    }

    public static void guardarEnArchivo(String nombreArchivo, byte[] datos) throws IOException {
        FileOutputStream fos = new FileOutputStream(nombreArchivo);
        fos.write(datos);
        fos.close();
    }

    public static void main(String[] args) throws Exception {

        // Leer texto de entrada
        Scanner scanner = new Scanner(System.in);
        System.out.println("Escriba un mensaje de texto: ");
        String texto = scanner.nextLine();
        scanner.close();

        System.out.println("Texto original: " + texto);
        System.out.print("Texto en byte[]: ");
        imprimir(texto.getBytes());

        // Generar llaves
        KeyPairGenerator generator = KeyPairGenerator.getInstance(ALGORITMO);
        generator.initialize(1024);
        KeyPair keyPair = generator.generateKeyPair();
        PublicKey llavePublica = keyPair.getPublic();
        PrivateKey llavePrivada = keyPair.getPrivate();

        // Guardar llaves en archivos
        guardarEnArchivo("llavePublica.key", llavePublica.getEncoded());
        guardarEnArchivo("llavePrivada.key", llavePrivada.getEncoded());

        // Cifrar con la llave pública
        byte[] textoCifrado = Asimetrico.cifrar(llavePublica, ALGORITMO, texto);
        System.out.println("Texto cifrado en byte[]: ");
        imprimir(textoCifrado);

        // Guardar el texto cifrado en archivo
        guardarEnArchivo("mensajeCifrado.txt", textoCifrado);

        System.out.println("Llaves y mensaje cifrado guardados exitosamente.");
    }
}

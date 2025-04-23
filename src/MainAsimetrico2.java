import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Scanner;

public class MainAsimetrico2 {
    private final static String ALGORITMO = "RSA";

    public static void imprimir (byte [] contenido){
        int i=0;
        for (; i<contenido.length -1; i++){
            System.out.print(contenido[i]+ " ");
        }
        System.out.println(contenido[i]+ " ");
    }

    public static void main(String[] args) throws NoSuchAlgorithmException {

        //Pedir el texto a cifrar por consola e imprimirlo
        Scanner scanner = new Scanner(System.in);
        System.out.println("Escriba un mensaje de texto: "); 
        String texto = scanner.nextLine();
        System.out.println("Input en texto plano: "+ texto);

        //Imprimir el texto claro
        System.out.print("Input en bytes[]: ");
        imprimir(texto.getBytes());

        //Generar un par de llaves asimetricas
        KeyPairGenerator generator = KeyPairGenerator.getInstance(ALGORITMO);
        generator.initialize(1024);
        KeyPair keyPair = generator.generateKeyPair();
        PublicKey llavePublica = keyPair.getPublic();
        PrivateKey llavePrivada = keyPair.getPrivate();

        long tiempoInicial = System.nanoTime();
        byte[] textoCifrado = Asimetrico.cifrar(llavePrivada, ALGORITMO,texto);
        System.out.println("input cifrado en RSA con llaves de 1024 bits en byte[]: ");
        imprimir(textoCifrado);

        byte[] textoDescifrado = Asimetrico.descifrar(llavePublica, ALGORITMO, textoCifrado);
        long tiempoFinal = System.nanoTime();
        System.out.println("Input descifrado en byte[]: ");
        imprimir(textoDescifrado);

        System.out.println("Input descifrado en byte[]: " + new String(textoDescifrado));

        System.out.println("Tiempo de cifrado y descifrado: " + (tiempoFinal - tiempoInicial) + " nanosegundos");
        System.out.println("Tiempo de cifrado y descifrado: " + (tiempoFinal - tiempoInicial)/1000000 + " milisegundos");
        
        scanner.close();
    }
}

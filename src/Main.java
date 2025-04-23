import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class Main {
    private final static String ALGORITMO = "AES";

    public static void imprimir (byte [] contenido){
        int i=0;
        for (; i<contenido.length -1; i++){
            System.out.print(contenido[i]+ " ");
        }
        System.out.println(contenido[i]+ " ");
    }
    public static void main2(String[] args) throws Exception {

        //Pedir el texto a cifrar por consola e imprimirlo
        Scanner scanner = new Scanner(System.in);
        System.out.println("Escriba el texto que desea cifrar: "); 
        String texto = scanner.nextLine();
        System.out.println("Mensaje de entrada en texto String: "+ texto);
        
        //Imprimir el texto claro
        System.out.print("Texto claro: ");
        imprimir(texto.getBytes());

        //Generar la llave
        KeyGenerator keygen= KeyGenerator.getInstance(ALGORITMO);
        SecretKey secretKey= keygen.generateKey();

        //Cifrar e imprir
        long tiempoInicial = System.nanoTime();
        byte [] textoCifrado =Simetrico.cifrar(secretKey, texto);
        System.out.print("Texto cifrado: ");
        imprimir(textoCifrado);

        //Descifrar e imprimir
        byte [] textoDescifrado= Simetrico.descifrar(secretKey,textoCifrado);
        long tiempoFinal = System.nanoTime();
        System.out.print("Texto descifrado: ");
        imprimir(textoDescifrado);
        System.out.print("Texto descifrado en String: " + new String(textoDescifrado));

        System.out.println("Tiempo de cifrado y descifrado: " + (tiempoFinal - tiempoInicial) + " nanosegundos");
        System.out.println("Tiempo de cifrado y descifrado: " + (tiempoFinal - tiempoInicial)/1000000 + " milisegundos");
        
        scanner.close();
    }

    public static void main3(String[] args) throws Exception {

        //Pedir el texto a cifrar por consola e imprimirlo
        Scanner scanner = new Scanner(System.in);
        System.out.println("Escriba el texto que desea cifrar: "); 
        String texto = scanner.nextLine();
        System.out.println("Mensaje de entrada en texto String: "+ texto);
        
        //Imprimir el texto claro
        System.out.print("Texto claro: ");
        imprimir(texto.getBytes());

        //Generar la llave
        KeyGenerator keygen= KeyGenerator.getInstance(ALGORITMO);
        SecretKey secretKey= keygen.generateKey();

        //Genarar archivo y guardar la llave
        FileOutputStream archivo = new FileOutputStream("llave.key");
        ObjectOutputStream oos = new ObjectOutputStream(archivo);
        oos.writeObject(secretKey);
        oos.close();

        //Cifrar e imprir
        byte [] textoCifrado =Simetrico.cifrar(secretKey, texto);
        System.out.print("Texto cifrado: ");
        imprimir(textoCifrado);

        //Guardar el texto cifrado en un archivo
        archivo = new FileOutputStream("mensajeCifrado.txt");
        oos = new ObjectOutputStream(archivo);
        oos.writeObject(textoCifrado);
        oos.close();

        System.out.println("Texto cifrado guardado en el archivo mensajeCifrado.txt");
    }

    public static void main(String[] args) throws Exception {

        //Recuperarar texto cifrado de un archivo
        FileInputStream archivo = new FileInputStream("mensajeCifrado.txt");
        ObjectInputStream ois = new ObjectInputStream(archivo);
        byte[] textoCifrado = (byte[]) ois.readObject();
        ois.close();

        //Recuperar la llave de un archivo
        archivo = new FileInputStream("llave.key");
        ObjectInputStream ois2 = new ObjectInputStream(archivo);
        SecretKey secretKey = (SecretKey) ois2.readObject();
        ois2.close();

        //Descifrar e imprimir
        byte [] textoDescifrado= Simetrico.descifrar(secretKey,textoCifrado);
        System.out.print("Texto descifrado: ");
        imprimir(textoDescifrado);
        System.out.print("Texto descifrado en String: " + new String(textoDescifrado));

    }
}

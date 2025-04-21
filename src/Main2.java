import java.util.Scanner;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class Main2 {
    private final static String ALGORITMO = "AES";

    public static void imprimir (byte [] contenido){
        int i=0;
        for (; i<contenido.length -1; i++){
            System.out.print(contenido[i]+ " ");
        }
        System.out.println(contenido[i]+ " ");
    }
    public static void main(String[] args) throws Exception {

        //Pedir el t1 y t2 a cifrar
        Scanner scanner = new Scanner(System.in);
        System.out.println("Escriba el t1 que desea cifrar: "); 
        String t1 = scanner.nextLine();
        System.out.println("Mensaje t1 de entrada en texto String: "+ t1);

        System.out.println("Escriba el t2 que desea cifrar: "); 
        String t2 = scanner.nextLine();
        System.out.println("Mensaje t2 de entrada en texto String: "+ t2);
        
        //Imprimir el texto claro
        System.out.print("Texto claro t1: ");
        imprimir(t1.getBytes());
        System.out.print("Texto claro t2: ");
        imprimir(t2.getBytes());

        //Generar las llaves k1 y k2
        KeyGenerator keygen= KeyGenerator.getInstance(ALGORITMO);
        SecretKey k1= keygen.generateKey();
        SecretKey k2= keygen.generateKey();

        //Cifrar e imprir t1 y t2
        
        byte [] tc1 =Simetrico.cifrar(k1, t1);
        byte [] tc2 =Simetrico.cifrar(k2, t2);
        System.out.print("Texto cifrado t1: ");
        imprimir(tc1);
        System.out.print("Texto cifrado t2: ");
        imprimir(tc2);

        //Descifrar e imprimir tc1 con k1 y k2
        byte [] tdK1= Simetrico.descifrar(k1,tc1);
        byte [] tdK2= Simetrico.descifrar(k2,tc1);
        
        System.out.print("Texto descifrado con k1: ");
        imprimir(tdK1);
        System.out.print("Texto descifrado con k1 en String: " + new String(tdK1));

        System.out.print("Texto descifrado con k2: ");
        imprimir(tdK2);
        System.out.print("Texto descifrado con k2 en String: " + new String(tdK2));
        scanner.close();
    }
}

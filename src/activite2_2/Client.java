package activite2_2;

import java.io.*;
import java.net.*;
import java.util.Scanner;
public class Client {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 1234);
             Scanner clavier = new Scanner(System.in)) {
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            out.flush();
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            System.out.println("Connecte au serveur.");
            while (true) {
                System.out.print("Premier operande (ou FIN) : ");
                String s1 = clavier.nextLine().trim();
                if (s1.equalsIgnoreCase("FIN")) {
                    out.writeObject("FIN");
                    out.flush();
                    break;
                }
                System.out.print("Operateur (+ - * /) : ");
                String sOp = clavier.nextLine().trim();
                System.out.print("Deuxieme operande : ");
                String s2 = clavier.nextLine().trim();
                // Validation
                double a, b;
                try {
                    a = Double.parseDouble(s1);
                    b = Double.parseDouble(s2);
                } catch (NumberFormatException e) {
                    System.out.println("Operande invalide.");
                    continue;
                }
                if (!sOp.matches("[+\\-*/]")) {
                    System.out.println("Operateur invalide.");
                    continue;
                }
                Operation op = new Operation(a, sOp.charAt(0), b);
                out.writeObject(op);
                out.flush();
                Object resultat = in.readObject();
                System.out.println("Resultat : " + resultat);
            }
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Erreur client : " + e.getMessage());
        }
    }
}
package activite2_2;

import java.io.*;
import java.net.*;
public class Serveur {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(1234)) {
            System.out.println("Serveur en attente sur le port 1234.");
            try (Socket socket = serverSocket.accept()) {
                System.out.println("Client connecte");
                // OUT en premier + flush  puis IN pour eviter le bloque 
                ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                out.flush();
                ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
                while (true) {
                    Object recu = in.readObject();
                    if (recu instanceof String && recu.equals("FIN")) {
                        System.out.println("Fin de la communication.");
                        break;
                    }
                    Operation op = (Operation) recu;
                    System.out.println("Reçu : " + op);
                    Object resultat = calculer(op);
                    out.writeObject(resultat);
                    out.flush();
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Erreur serveur : " + e.getMessage());
        }
    }
    private static Object calculer(Operation op) {
        double a = op.getOperande1();
        double b = op.getOperande2();
        switch (op.getOperateur()) {
            case '+': return a + b;
            case '-': return a - b;
            case '*': return a * b;
            case '/':
                if (b == 0) return "Erreur : division par zero";
                return a / b;
            default:  return "Erreur : opérateur inconnu";
        }
    }
}
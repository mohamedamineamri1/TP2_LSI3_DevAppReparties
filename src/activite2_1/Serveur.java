package activite2_1;
import java.io.*;
import java.net.*;
public class Serveur {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(1234)) {
            System.out.println("Serveur en attente sur le port 1234");
            try (Socket socket = serverSocket.accept()) {
                System.out.println("Client connecte : " + socket.getInetAddress());
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(socket.getOutputStream(),true);
                while (true) {
                    String op1 = in.readLine();
                    if (op1 == null || op1.equalsIgnoreCase("FIN")) {
                        System.out.println("Fin de la communication.");
                        break;
                    }
                    String operateur = in.readLine();
                    String op2 = in.readLine();
                    System.out.println("Reçu : " + op1 + " " + operateur + " " + op2);
                    String resultat = calculer(op1,operateur,op2);
                    out.println(resultat);
                }
            }
        } catch (IOException e) {
            System.err.println("Erreur serveur:" +e.getMessage());
        }
    }
    private static String calculer(String s1,String operateur,String s2) {
        try {
            double a = Double.parseDouble(s1);
            double b = Double.parseDouble(s2);
            switch (operateur) {
                case "+": return String.valueOf(a + b);
                case "-": return String.valueOf(a - b);
                case "*": return String.valueOf(a * b);
                case "/":
                    if (b == 0) return "Erreur : division par zéro";
                    return String.valueOf(a / b);
                default:  return "Erreur : opérateur inconnu";
            }
        } catch (NumberFormatException e) {
            return "Erreur : opérande invalide";
        }
    }
}

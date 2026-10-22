package activite2_1;
import java.io.*;
import java.net.*;
import java.util.Scanner;
import java.util.regex.*;
public class Client {
    // nombre  operateur  nombre   (exemple  : 55 * 25, -3.5 / 2)
    private static final Pattern PATTERN = Pattern.compile(
            "^\\s*(-?\\d+(?:\\.\\d+)?)\\s*([+\\-*/])\\s*(-?\\d+(?:\\.\\d+)?)\\s*$"); 
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost",1234);
             Scanner clavier = new Scanner(System.in)) {
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            System.out.println("Connecte au serveur.");
            while (true) {
                System.out.print("Operation (exemple : 55 * 25) ou FIN : ");
                String saisie = clavier.nextLine();
                if (saisie.trim().equalsIgnoreCase("FIN")) {
                    out.println("FIN");
                    break;
                }
                // Validation de la syntaxe avant l'envoi
                Matcher m = PATTERN.matcher(saisie);
                if (!m.matches()) {
                    System.out.println("Syntaxe incorrecte. Format attendu : nombre operateur nombre");
                    continue;
                }
                // Envoi des 3 elements
                out.println(m.group(1));   // operande 1
                out.println(m.group(2));   // operateur
                out.println(m.group(3));   // operande 2
                //  Lecture et affichage du resultat
                String resultat = in.readLine();
                System.out.println("Resultat : " + resultat);
            }
        } catch (IOException e) {
            System.err.println("Erreur client : " + e.getMessage());
        }
    }
}

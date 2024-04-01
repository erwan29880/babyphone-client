import back.SocketReceiver;
import connections.ConnectionServeur;
import front.Fenetre;

/** main class */
public class App {
    public static void main(String[] args) throws Exception {
        String message = "";

        try {
            // démarrage du serveur si besoin
            ConnectionServeur.demarrerServeur();
            message = "Le serveur est en cours de démarrage.";
        }
        catch (Exception e) {
            message = "Le serveur est en route.";
        } finally {
            // lancement du front-end
            new Fenetre(message);

        }

        // lancement de la réception
        new SocketReceiver().init();
    }
}

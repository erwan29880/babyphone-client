package configuration;

/** Configuration utilisateur à renseigner */
final public class Constantes {
    /** renseigner l'adresse ipv4 du serveur */
    public final static String HOST = "192.168.1.??";

    /** rensigner le port d'écoute du serveur */
    public final static int PORT = 12345;

    /** renseigner le mot de passe utilisé pour se connecter en ssh */
    public final static String PWD = "your/password";
        
    /** renseigner le port ssh - 22 par défaut */
    public final static int SSH_PORT = 22;

    /** renseigner l'indentifiant pour se connecter en ssh */
    public final static String SSH_USERNAME = "myUser";

    /** renseigner le chemin d'accès au jar du serveur */
    public final static String COMMANDE = "cd path/to/your/folder && java -jar name/of/serveur/jar/.jar && exit";
    
    /** seuil audio pour la sortie audio */
    public final static int THRESHOLD = 30;

    /** message à envoyer au serveur pour l'éteindre */
    public final static String STOP_MESSAGE = "stop\n";

    /** taux d'échantillonage audio */
    public final static int SAMPLE_RATE = 22050;

    /** nombre de bits */
    public final static int SAMPLE_SIZE_IN_BITS = 16;

    /** nombre de canaux audio */
    public final static int CHANNELS = 1;

    /** taille du buffer pour la réception des données */
    public final static int NOMBRE_DE_BITS_PAR_BUFFER = 1024;
}

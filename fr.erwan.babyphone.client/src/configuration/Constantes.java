package configuration;

/**
 * HOST : {@link connections.ConnectionServeur}, {@link back.SocketReceiver}
 * PORT : {@link back.SocketReceiver}
 * PRIVATE_KEY : {@link connections.ConnectionServeur}
 * SSH_PORT : {@link connections.ConnectionServeur}
 * SSH_USERNAME : {@link connections.ConnectionServeur}
 * COMMANDE : {@link connections.ConnectionServeur}
 * THRESHOLD : {@link front.Fenetre}, {@link back.SocketReceiver}
 * STOP_MESSAGE : {@link back.SocketReceiver}
 * SAMPLE_RATE, SAMPLE_SIZE_IN_BITS, CHANNELS : {@link back.sound.Utilities}, vérifier la configuration du serveur également
 * NOMBRE_DE_BITS_PAR_BUFFER : {@link back.SocketReceiver}, vérifier la configuration du serveur également
 * 
 * rappel ssh : 
 *  génération de la clé :
 *      ssh-keygen -f id_rsa
 * 
 *  mise au bon format pour java com.jcraft.jsch : 
 *      voir https://mkyong.com/java/jsch-invalid-privatekey-exception/
 *      ssh-keygen -p -f ~/.ssh/id_rsa -m pem
 *      format : 
 *           -----BEGIN RSA PRIVATE KEY-----
 *           MIIG4wIBAAK...
 *           ...
 *           ...E428GBDI4
-----END RSA PRIVATE KEY-----
 *      
 *      
 *  copie de la clé sur le serveur distant puis :
 *      cat id_rsa >> ~/.ssh/authorized_keys
 * 
 *  chmod 600 pour les fichiers de clés 
 * 
 */
final public class Constantes {
    public final static String HOST = "192.168.1.??";
    public final static int PORT = 12345;
    
    public final static String PRIVATE_KEY = new StringBuilder()
        .append(System.getProperty("user.home"))
        .append(System.getProperty("file.separator"))
        .append(".ssh")
        .append(System.getProperty("file.separator"))
        .append("id_rsa")
        .toString();
    
    public final static int SSH_PORT = 22;
    public final static String SSH_USERNAME = "myUser";
    public final static String COMMANDE = "cd path/to/your/folder && java -jar name/of/serveur/jar/.jar && exit";
    
    public final static int THRESHOLD = 30;
    public final static String STOP_MESSAGE = "stop\n";
    public final static int SAMPLE_RATE = 22050;
    public final static int SAMPLE_SIZE_IN_BITS = 16;
    public final static int CHANNELS = 1;
    public final static int NOMBRE_DE_BITS_PAR_BUFFER = 1024;
}

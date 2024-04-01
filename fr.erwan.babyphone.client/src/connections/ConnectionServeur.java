package connections;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;


/** classe pour démarrer le serveur via ssh et une commande bash */
final public class ConnectionServeur {

    /** constructeur */
    public ConnectionServeur() {}

    /**
     * démarrage du serveur
     * @throws Exception pour toute exception de connexion ou de commande
     */
    public static void demarrerServeur() throws Exception{

            JSch jsch = new JSch();
            jsch.addIdentity(configuration.Constantes.PRIVATE_KEY);

            // Créer la session SSH
            Session session = jsch.getSession(
                configuration.Constantes.SSH_USERNAME, 
                configuration.Constantes.HOST, 
                configuration.Constantes.SSH_PORT);
            
            // Désactiver l'affichage des messages de confirmation
            java.util.Properties config = new java.util.Properties();
            config.put("StrictHostKeyChecking", "no");
            session.setConfig(config);
            session.connect();

            // exécuter une commande bash sur le serveur
            Channel channel = session.openChannel("exec");
            String command = configuration.Constantes.COMMANDE;
            ((ChannelExec) channel).setCommand(command);
            channel.setInputStream(null);
            ((ChannelExec) channel).setErrStream(System.err);
            channel.connect();

            // Lire la sortie de la commande
            InputStream in = channel.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            System.out.println(reader.readLine());
            reader.close();
            channel.disconnect();
            session.disconnect();
    }
}

package connections;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.UIKeyboardInteractive;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.UserInfo;


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

            // Créer la session SSH
            Session session = jsch.getSession(
                configuration.Constantes.SSH_USERNAME, 
                configuration.Constantes.HOST, 
                configuration.Constantes.SSH_PORT);

            // renseigner le mot de passe par console interactive
            UserInfo ui = new MyUserInfo();
            session.setUserInfo(ui);
            session.setPassword(configuration.Constantes.PWD.getBytes());
            
            // supprime les erreurs
            java.util.Properties config = new java.util.Properties();
            config.put("StrictHostKeyChecking", "no");
            session.setConfig(config);
            
            // connexion
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

    /** implémentation de l'interface UserInfo de Jsch,
     * et de UIKeyboardIntercative pour que Jsch renseigne le mot de passe en console interactive
    */
    public static class MyUserInfo implements UserInfo, UIKeyboardInteractive {

        @Override
        public String getPassphrase() {
            return null;
        }
        @Override
        public String getPassword() {
            return null;
        }
        @Override
        public boolean promptPassphrase(String arg0) {
            return false;
        }
        @Override
        public boolean promptPassword(String arg0) {
            return false;
        }
        @Override
        public boolean promptYesNo(String arg0) {
            return false;
        }
        @Override
        public void showMessage(String arg0) {
        }
        @Override
        public String[] promptKeyboardInteractive(String arg0, String arg1,
                String arg2, String[] arg3, boolean[] arg4) {
            return null;
        }
    }
}

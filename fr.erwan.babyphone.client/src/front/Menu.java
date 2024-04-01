package front;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import events.StreamListener;
import events.UpdateStatusListener;

/**
 * La barre de menu
 * utilisée pour éteindre le serveur
 */
final public class Menu extends JMenuBar{
    
    /**
     * l'item du menu pour éteindre le serveur
     */
    private JMenuItem menuItemQuit;

    /**
     * observateur pour l'extinction du serveur
     * @see fr.erwan.babyphone.client.sound.SocketReceiver
     */
    private static StreamListener streamListener;

    /**
     * observateur pour la mise à jour de la barre de statut
     * @see fr.erwan.babyphone.client.front.Fenetre
     */
    private static UpdateStatusListener updateStatusListener;


    /**
     * constructeur
     */
    public Menu() {
        super();
        init();
        addListenerOnQuit();
    }

    /**
     * placement des éléments dans la barre de menu
     */
    private void init() {
        JMenu actions = new JMenu("Menu");
        menuItemQuit = new JMenuItem("éteindre le serveur");
        actions.add(menuItemQuit);
        add(actions);
    }

    /**
     * gestion d'évènement sur l'élément "stopper le serveur" du menu
     */
    public void addListenerOnQuit() {
        menuItemQuit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tellServerToShutDown(true);
                setStatus("Le serveur va s'arrêter dès que vous aurez fermé cette fenêtre");
            }
        });
    }

    /**
     * propagation de l'évènement pour stopper le serveur
     * @param stop vrai pour stopper le serveur
     */
    private void tellServerToShutDown(boolean stop) {
        if (streamListener != null) {
            streamListener.setStop(stop);
        }
    }

    /**
     * propagation de l'évènement pour mettre à jour la barre de statut dans le front
     * @param message
     */
    private void setStatus(String message) {
        if (updateStatusListener != null) {
            updateStatusListener.setStatus(message);
        }
    }

    /**
     * ajout d'un observateur pour détecter les changements de statuts
     * @param u l'interface lien observeur observé
     */
    public static void addUpdateStatusListener(UpdateStatusListener u) {
        updateStatusListener = u;
    }

    /**
     * ajout d'un observateur pour détecter quand stopper le serveur
     * @param s l'interface lien observeur observé
     */
    public static void addStreamListener(StreamListener s) {
        streamListener = s;
    }
}

package front.boutons;

import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import events.DisposeListener;
import events.IsRunningListener;

/** le bouton quitter */
final public class QuitButton extends JButton{

    /**
     * observateur pour se déconnecter du serveur
     * @see fr.erwan.babyphone.client.sound.SocketReceiver
     */
    private static IsRunningListener isRunningListener;

    /**
     * observateur pour quitter le front-end
     * @see fr.erwan.babyphone.client.front.Fenetre
     */
    private static DisposeListener disposeListener;

    /** constructeur */
    public QuitButton() {
        super("quitter");
        initAction();
    }

    /** gestion d'évènement au click */
    private void initAction() {
        addActionListener(new ActionListener() {
            @Override 
            public void actionPerformed(ActionEvent e) {
                // se déconnecter du serveur : fermeture du programme 1/2
                if (isRunningListener != null) isRunningListener.setRunning(false);
                
                // quitter le front-end : fermeture du programme 2/2
                if (disposeListener != null) disposeListener.frameDispose();
            }
        });
    }

    /**
     * ajout d'un observateur pour stopper la connection au serveur
     * @param i l'interface lien observeur observé
     */
    public static void addIsRunningListener(IsRunningListener i) {
        isRunningListener = i;
    }

    /**
     * ajout d'un observateur pour stopper le front-end
     * @param d l'interface lien observeur observé
     */
    public static void addDisposListener(DisposeListener d) {
        disposeListener = d;
    }
}

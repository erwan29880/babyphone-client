package front.boutons;

import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.List;
import java.util.ArrayList;

import back.SocketReceiver;
import events.ThresholdListener;


/** incrémentation du seuil audio */
final public class IncreaseButton extends JButton{

    /**
     * la liste des observateurs du seuil audio
     * @see fr.erwan.babyphone.client.sound.SocketReceiver
     * @see fr.erwan.babyphone.client.front.Fenetre
     */
    private static List<ThresholdListener> thresholdListeners = new ArrayList<>();

    /** constructeur */
    public IncreaseButton() {
        super("augmenter le seuil");
        initAction();
    }

    /** gestion d'évènement au click */
    private void initAction() {
        this.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!thresholdListeners.isEmpty()) {
                    for (ThresholdListener t: thresholdListeners) {
                        int newThreashold = SocketReceiver.getThreshold() + 1;
                        t.setThreshold(newThreashold);
                    }
                }
            }
        });
    }

    /**
     * ajout d'un observateur pour le seuil audio
     * @param t l'interface lien observeur observé
     */
    public static void addThresholdListener(ThresholdListener t) {
        thresholdListeners.add(t);
    }
}

package front;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import java.awt.BorderLayout;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import back.SocketReceiver;
import events.DisposeListener;
import events.IsRunningListener;
import events.ThresholdListener;
import events.UpdateStatusListener;
import front.boutons.BoutonsPanel;
import front.boutons.DecreaseButton;
import front.boutons.IncreaseButton;
import front.boutons.QuitButton;


/**
 * classe principale de l'affichage
 *  regroupe les classes Menu, BoutonsPanel
 */
final public class Fenetre extends JFrame implements UpdateStatusListener, 
                                                     ThresholdListener, 
                                                     DisposeListener, 
                                                     KeyListener{

    /** seuil pour envoyer le son vers la sortie audio */
    private int threshold = configuration.Constantes.THRESHOLD;

    /** instance de la barre de menu */
    private Menu menu = new Menu();

    /** le containeur pour afficher seuil */
    private JLabel thresholdLabel;

    /** le containeur pour afficher le statut */
    private JLabel statusLabel;

    /**
     * observateur de isRunning pour l'évènement clavier q
     * utilisé par SocketReceiver
     */
    private static IsRunningListener isRunningListener;

    /**
     * constructeur
     * @param message un message d'initialisation pour le statuten fonction de la connection au serveur ou non 
     */
    public Fenetre(String message) {
        super("Babyphone");
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setSize(600, 150);
        setLayout(new BorderLayout());
        initListeners();
        init();
        initKeyListener();
        initCloseOperation();
        setStatus(message);
        setVisible(true);
    } 

    /** inscription en tant qu'observateur pour les boutons, le menu, le back */
    private void initListeners() {
        DecreaseButton.addThresholdListener(this);
        IncreaseButton.addThresholdListener(this);
        Menu.addUpdateStatusListener(this);
        SocketReceiver.addUpdateStatusListener(this);
        QuitButton.addDisposListener(this);
    }

    /** définition des zones de texte, ajout du menu */
    private void init() {
        thresholdLabel = new JLabel("Seuil : " + this.threshold + "  ", SwingConstants.RIGHT);
        statusLabel = new JLabel("");
        
        setJMenuBar(menu);
        add(thresholdLabel, BorderLayout.NORTH);
        add(new BoutonsPanel(), BorderLayout.CENTER);
        add(statusLabel, BorderLayout.SOUTH);
    }

    /** initialisation de l'écoute du clavier */
    private void initKeyListener() {
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
    }

    /** méthode personnalisée au click de fermeture de fenêtre */
    private void initCloseOperation() {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent event) {
                if (isRunningListener != null) isRunningListener.setRunning(false);
                dispose(); 
            }
        });
    }


    /**
     * implémentation de l'interface UpdateStatusListener
     * mets à jour l'affichage du statut
     * @param status le message à afficher
     */
    @Override
    public void setStatus(String status) {
        SwingUtilities.invokeLater(() -> {
            statusLabel.setText(status);
        });
    }

    /**
     * implémentation de l'interface ThresholdListener
     * mise à jour du seuil audio 
     * @param threshold le nouveau seuil
     */
    @Override
    public void setThreshold(int threshold) {
        this.threshold = threshold;
        SwingUtilities.invokeLater(() -> {
            thresholdLabel.setText(
                new StringBuilder()
                    .append("Seuil : ")
                    .append(this.threshold)
                    .append("  ")
                    .toString()

            );
        });
    }

    /**
     * implémentation de l'interface DisposeListener
     * ferme la fenêtre
     * n'éteint pas le programme, le socket est toujours actif et est géré par un autre gestionnaire d'évènements
     */
    @Override
    public void frameDispose() {
        this.dispose();
    }


    /** implémentation de l'interface keyListener */
    @Override
    public void keyTyped(KeyEvent e) {}

    /** implémentation de l'interface keyListener */
    @Override
    public void keyReleased(KeyEvent e) {}

    /** implémentation de l'interface keyListener */
    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println(e.getKeyCode());

        if(e.getKeyCode()== KeyEvent.VK_DOWN) {
            setThresholdFromFrame(-1);
        }
        else if(e.getKeyCode()== KeyEvent.VK_UP) {
            setThresholdFromFrame(1);
        }
        else if (e.getKeyCode() == 81) { // q
            if (isRunningListener != null) isRunningListener.setRunning(false);
            this.dispose();
        }
    }

    /** mise à jour du threshold sans interface avec un keyEvent up ou down */
    private void setThresholdFromFrame(int increment) {
        // récupérer le seuil du back
        int newThreshold = SocketReceiver.getThreshold();
        
        // incrémenter/décrémenter en fonction de la touche pressée
        newThreshold += increment;

        // mise à jour du seuil dans le back et dans cette classe
        SocketReceiver.setThresholdFromFrame(newThreshold);
        this.threshold = newThreshold;
        
        // mise à jour de l'affichage
        thresholdLabel.setText(
            new StringBuilder()
                .append("Seuil : ")
                .append(newThreshold)
                .append("  ")
                .toString()

        );
    }

    /** ajout d'un observateur pour isRunning, 
     * permet de couper la connexion du back au serveur
    */
    public static void addIsRunningListener(IsRunningListener r) {
        isRunningListener = r;
    }
}

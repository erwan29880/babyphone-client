package front.boutons;

import javax.swing.JPanel;
import java.awt.GridLayout;

/** la barre de menu */
final public class BoutonsPanel extends JPanel {

    /** constructeur */
    public BoutonsPanel() {
        super(new GridLayout(1, 3));
        initButtons();
    }
    
    /** ajout des boutons, voir les classes du même package */
    private void initButtons() {
        add(new DecreaseButton());
        add(new IncreaseButton());
        add(new QuitButton());
    }
}

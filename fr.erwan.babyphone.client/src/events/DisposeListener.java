package events;

/**
 * interface pour stopper le front-end
 * @see fr.erwan.babyphone.client.front.Fenetre
 * @see fr.erwan.babyphone.client.front.boutons.QuitBouton
 */
public interface DisposeListener {
    /** fermer la fenêtre */
    void frameDispose();
}

package events;

/**
 * Mise à jour du statut pour l'affichage front
 * @see fr.erwan.babyphone.client.front.Menu
 * @see fr.erwan.babyphone.client.back.SocketReceiver
 * @see fr.erwan.babyphone.client.front.Fenetre
 */
public interface UpdateStatusListener {
    /** mise à jour du statut front-end */
    void setStatus(String status);
}

package events;

/**
 * interface utilisée pour mettre à jour le seuil de sortie audio
 * @see fr.erwan.babyphone.client.back.SocketReceiver
 * @see fr.erwan.babyphone.client.front.Fenetre
 * @see fr.erwan.babyphone.client.front.boutons.IncreaseButton
 * @see fr.erwan.babyphone.client.front.boutons.DecreaseButton
 */
public interface ThresholdListener {
    /** mise à jour du seuil audio en back et front */
    void setThreshold(int threshold);
}

package events;

/**
 * interface pour stopper la connexion au serveur
 * @see fr.erwan.babyphone.client.back.SocketReceiver
 * @see fr.erwan.babyphone.client.front.boutons.QuitBouton 
 */
public interface IsRunningListener {
    /** stopper la connexion client depuis le back */
    void setRunning(boolean isRunning);
}

package events;

/**
 * interface pour stopper le serveur 
 * @see fr.erwan.babyphone.client.back.SocketReceiver
 * @see fr.erwan.babyphone.client.front.Menu
 */
public interface StreamListener {
    /** stopper le serveur depuis le back */
    void setStop(boolean stop);
}

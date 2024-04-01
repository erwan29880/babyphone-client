package back;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

import back.sound.Utilities;
import events.IsRunningListener;
import events.StreamListener;
import events.ThresholdListener;
import events.UpdateStatusListener;
import front.Fenetre;
import front.Menu;
import front.boutons.DecreaseButton;
import front.boutons.IncreaseButton;
import front.boutons.QuitButton;

/** gestion de la connexion au serveur et du stream audio */
final public class SocketReceiver implements StreamListener, IsRunningListener, ThresholdListener{
    
    /** Observateurs front-end pour l'affichage du statut */
    private static List<UpdateStatusListener> updateStatusListeners = new ArrayList<>();

    /** adresse ipv4 du serveur distant */
    private String serverAddress = configuration.Constantes.HOST;

    /** port serveur du serveur distant */
    private int serverPort = configuration.Constantes.PORT;
    
    /** variable pour déconnecter le client */
    private boolean isRunning = true;

    /** variable pour stopper le serveur */
    private boolean isStopped = false;

    /** seuil audio */
    private static int threshold = configuration.Constantes.THRESHOLD;
    
    /** fonctions utilitaires */
    private Utilities utils = new Utilities();

    /** constructeur */
    public SocketReceiver() {
        initListeners();
    }

    /** subscription aux gestionnaires d'évènements */
    private void initListeners() {
        DecreaseButton.addThresholdListener(this);
        IncreaseButton.addThresholdListener(this);
        QuitButton.addIsRunningListener(this);
        Fenetre.addIsRunningListener(this);
        Menu.addStreamListener(this);
    }

    /** initialisation de la connection et lancement du streaming audio en fonction du seuil */
    public void init() {

        try (Socket socket = new Socket(serverAddress, serverPort);
            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream()) {

            // notification au front-end
            updateStatus("Status: Connecté au server");

            // configuration de l'audio
            AudioFormat audioFormat = utils.getAudioFormat();
            SourceDataLine line = AudioSystem.getSourceDataLine(audioFormat);
            
            // ouverture du canal de sortie
            line.open(audioFormat);
            line.start();


            byte[] buffer = new byte[configuration.Constantes.NOMBRE_DE_BITS_PAR_BUFFER];
            int bytesRead;

            // boucle pour la sortie audio
            while (isRunning && (bytesRead = inputStream.read(buffer)) != -1) {
                
                // calcul du root mean square pour le buffer
                double rms = utils.calculateRMS(buffer);

                // convertir le rms en décibels
                double decibels = 20.0 * Math.log10(rms);


                // sortie audio si le seuil est inférieur aux décibels
                if (decibels > SocketReceiver.threshold) {
                    line.write(buffer, 0, bytesRead);
                }

                // stopper le serveur
                if (isStopped) {
                    outputStream.write(configuration.Constantes.STOP_MESSAGE.getBytes());
                    break;
                }
            }

            // libération de la sortie audio
            line.drain();
            line.close();
        } catch (IOException | LineUnavailableException e) {
            System.out.println(e.getMessage());
        }
    }


    /**
     * notifier le front-end du changement de statut
     * @param message le message à afficher
     */
    private void updateStatus(String message) {
        if (!updateStatusListeners.isEmpty()) {
            for (UpdateStatusListener u: updateStatusListeners) {
                u.setStatus(message);
            }
        }
    }

    /**
     * ajout d'un observateur pour la mise à jour du statut
     * @see fr.erwan.babyphone.client.front.Fenetre
     * @param u le lien observateur observé
     */
    public static void addUpdateStatusListener(UpdateStatusListener u) {
        updateStatusListeners.add(u);
    }

    /**
     * setter de la variable isStopped pour stopper le serveur
     * @param stop true pour stopper le serveur
     */
    public void setStop(boolean stop) {
        this.isStopped = stop;
    }

    /**
     * setter de la variable isRunning pour stopper la connexion au serveur
     * @param isRunning false pour stopper la connexion 
     */
    public void setRunning(boolean isRunning) {
        this.isRunning = isRunning;
    }

    /**
     * setter pour le seuil audio
     * @param threshold le nouveau seuil audio
     */
    public void setThreshold(int threshold) {
        SocketReceiver.threshold = threshold;
    }
    
    /**
     * getter pour le seuil audio, utilisé en front-end par les boutons increase et decrease
     * @return le seuil audio
     */
    public static int getThreshold() {
        return threshold;
    }

    /**
     * mise à jour du seuil audio à partir du front end sans listener
     * @param threshold le nouveau seuil audio
     */
    public static void setThresholdFromFrame(int threshold) {
        SocketReceiver.threshold = threshold;
    }

   
}

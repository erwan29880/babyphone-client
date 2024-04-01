package back.sound;

import javax.sound.sampled.AudioFormat;

/** fonctions utilitaires pour l'audio */
final public class Utilities {
    
    /** le taux d'échantillonage */
    private float sampleRate = configuration.Constantes.SAMPLE_RATE;

    /** le nombre de bits */
    int sampleSizeInBits = configuration.Constantes.SAMPLE_SIZE_IN_BITS;

    /** le nombre de canaux */
    int channels = configuration.Constantes.CHANNELS;

    /** constructeur */
    public Utilities() {
    }

    /**
     * getter pour le format audio d'entrée et de sortie
     * @return le format audio
     */
    public AudioFormat getAudioFormat() {
        boolean signed = true;
        boolean bigEndian = false;
        return new AudioFormat(sampleRate, sampleSizeInBits, channels, signed, bigEndian);
    }

    /**
     * calcul du root mean squarred (rms) du buffer
     * @param buffer les bytes de l'audio
     * @return le rms
     */
    public double calculateRMS(byte[] buffer) {
        // Calculate RMS (Root Mean Square) value for the buffer
        double sum = 0.0;
        for (byte b : buffer) {
            sum += b * b;
        }
        double mean = sum / buffer.length;
        return Math.sqrt(mean);
    }
}

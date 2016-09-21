/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package radiorelogio;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;
import javazoom.jl.player.advanced.PlaybackEvent;
import javazoom.jl.player.advanced.PlaybackListener;

/**
 *
 * @author hugo
 */
public class Tocar implements Runnable {

    private String musica;
    private boolean tocando;
    private int pausedOnFrame;

    @Override
    public void run() {

        if (musica != null) {
            try {
                FileInputStream fis = new FileInputStream(musica);
                AdvancedPlayer playMP3 = new AdvancedPlayer(fis);
                tocando = true;
                playMP3.setPlayBackListener(new PlaybackListener() {
                    @Override
                    public void playbackFinished(PlaybackEvent event) {
                        pausedOnFrame = event.getFrame();
                    }
                });
                playMP3.play();

            } catch (FileNotFoundException | JavaLayerException exc) {
                exc.printStackTrace();
                System.out.println("Failed to play the file.");
            }

        }

        tocando = false;
    }

    public String getMusica() {
        return musica;
    }

    public void setMusica(String musica) {
        this.musica = musica;
    }

    public boolean isTocando() {
        return tocando;
    }

    public void setTocando(boolean tocando) {
        this.tocando = tocando;
    }

}

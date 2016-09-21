/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package radiorelogio;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javazoom.jl.decoder.JavaLayerException;

/**
 *
 * @author hugo
 */
public class Radio implements Runnable {

    @Override
    public void run() {
        try {
            FileInputStream fis = new FileInputStream("Gui/02 - Joker.mp3");
            Player playMP3 = new Player(fis);
            playMP3.play();

        } catch (FileNotFoundException | JavaLayerException exc) {
            exc.printStackTrace();
            System.out.println("Failed to play the file.");
        }
    }
}

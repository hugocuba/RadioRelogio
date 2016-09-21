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
 * @author Ivo
 */
public class Falar implements Runnable {

    private String hora;
    private boolean falando;

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getHora() {
        return hora;
    }
    
    public boolean getFalando(){
        return falando;
    }
    
    public void setFalando(boolean falando){
        this.falando = falando;
    }

    public void run() {
        String[] horaPartida;
        horaPartida = this.hora.split(":");
            try {
                FileInputStream hora = new FileInputStream("Voices_Time/HRS" + horaPartida[0].toString() + ".mp3");
                Player playHora = new Player(hora);
                FileInputStream min = new FileInputStream("Voices_Time/MIN" + horaPartida[1].toString() + ".mp3");
                Player playMin = new Player(min);
                    playHora.play();
                    playMin.play();
            } catch (FileNotFoundException | JavaLayerException exc) {
                exc.printStackTrace();
        }
        falando = false;
    }

}

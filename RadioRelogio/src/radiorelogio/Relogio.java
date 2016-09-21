/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package radiorelogio;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JLabel;

/**
 *
 * @author a1502794
 */
public class Relogio implements Runnable {

    private JLabel hr;
    private boolean mostrarData;
    String[] horaPartida;
    Falar falar = new Falar();
    
    public Relogio(JLabel hora) {
        this.hr = hora;
    }

    public void mostrarData(boolean mostrar) {
        this.mostrarData = mostrar;
    }

    @Override
    public void run() {
        try {
            while (true) {
                Date d = new Date();
                StringBuilder data = new StringBuilder();
                if (mostrarData) {
                    SimpleDateFormat sdfData = new SimpleDateFormat("EEE, d 'de' MMMMM 'de' yyyy");
                    data.append(sdfData.format(d));
                    this.hr.setText(data.toString());
                    Thread.sleep(1000);
                    this.hr.revalidate();
                } else {
                    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
                    this.hr.setText(sdf.format(d));
                    Thread.sleep(1000);
                    this.hr.revalidate();
                    horaPartida = this.hr.getText().split(":");
                    if (horaPartida[1].equals("00") && horaPartida[2].equals("00")) {
                        if (!falar.getFalando()) {
                            falar.setFalando(true);
                            Thread falarThread = new Thread(falar);
                            falar.setHora(sdf.format(d));
                            falarThread.start();
                        }
                    }
                }
            }
        } catch (Exception ex) {
            System.out.println("Problema na atualização da data/hora");
            ex.printStackTrace();
        }
    }
}
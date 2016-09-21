/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package radiorelogio;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

/**
 *
 * @author a1502794
 */
public class RadioRelogio extends JFrame {

    private final Relogio relogio;
    Falar falar = new Falar();
    DefaultListModel<String> dlm = new DefaultListModel<>();
    ArrayList<String> diretorios = new ArrayList<>();
    Tocar tocar = new Tocar();
    Thread tocarThread = null;
    ExecutorService executor;
    String[] horaPartida;

    /**
     * Creates new form RadioRelogio
     */
    public void escolherArquivos() {
        File[] arquivos;
        final JFileChooser fc = new JFileChooser();
        fc.setMultiSelectionEnabled(true);
        int returnVal = fc.showOpenDialog(null);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            arquivos = fc.getSelectedFiles();

            for (File arquivo : arquivos) {
                diretorios.add(arquivo.getAbsolutePath());
                dlm.addElement(arquivo.getName());
            }
            jListMusicas.setModel(dlm);
        }
    }

    public void falarHora() {
        if (!falar.getFalando()) {
            falar.setFalando(true);
            Thread falarThread = new Thread(falar);
            falar.setHora(this.labelRelogio.getText());
            falarThread.start();
        }
    }

    public void tocar() {
        tocarThread = new Thread(tocar);
        tocarThread.start();
    }

    public void parar() {
        tocarThread.stop();
        tocar.setTocando(false);
        tocar();
    }

    public void tocarMusica(String musica) {
        try {
            tocar.setMusica(musica);

            if (!tocar.isTocando()) {
                tocar();
            } else {
                parar();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public RadioRelogio() {

        try {
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("alarmclock.ttf")));
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }

        initComponents();

        relogio = new Relogio(labelRelogio);
        relogio.mostrarData(false);
        Thread relogioThread = new Thread(relogio);
        relogioThread.start();

        Relogio data = new Relogio(jLabelData);
        data.mostrarData(true);
        Thread dataThread = new Thread(data);
        dataThread.start();

        Runnable r = new Runnable() {

            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
            Date d = new Date();

            @Override
            public void run() {
                try {
                    while (true) {
                        horaPartida = labelRelogio.getText().split(":");
                        if (horaPartida[1].equals("13") && horaPartida[2].equals("00")) {
                            if (tocar.isTocando()) {
                                while (tocar.isTocando()) {
                                    try {
                                        System.out.println("tocando");
                                        Thread.sleep(1000);
                                    } catch (InterruptedException ex) {
                                        Logger.getLogger(RadioRelogio.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                }
                                falar.setFalando(true);
                                Thread falarThread = new Thread(falar);
                                falar.setHora(labelRelogio.getText());
                                falarThread.start();
                            } else {
                                falar.setFalando(true);
                                Thread falarThread = new Thread(falar);
                                falar.setHora(labelRelogio.getText());
                                falarThread.start();
                            }
                        }
                        labelRelogio.revalidate();
                        Thread.sleep(1000);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

        new Thread(r).start();

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        labelRelogio = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jListMusicas = new javax.swing.JList<>();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButtonFalar = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabelData = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        labelRelogio.setFont(new Font("alarm clock", Font.PLAIN, 80));
        labelRelogio.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelRelogio.setText("00:00:00");

        jListMusicas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jListMusicasMouseClicked(evt);
            }
        });
        jListMusicas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jListMusicasKeyPressed(evt);
            }
        });
        jScrollPane2.setViewportView(jListMusicas);

        jButton1.setText("<<");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText(">>");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButtonFalar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/radiorelogio/icon-speaker.gif"))); // NOI18N
        jButtonFalar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonFalarActionPerformed(evt);
            }
        });

        jButton3.setText("...");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabelData.setFont(new java.awt.Font("Noto Sans", 0, 18)); // NOI18N
        jLabelData.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelData.setText("Data");

        jButton4.setText(">");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("||");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton3))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(labelRelogio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(jLabelData, javax.swing.GroupLayout.DEFAULT_SIZE, 358, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonFalar, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonFalar)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(labelRelogio, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelData)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3)
                    .addComponent(jButton4)
                    .addComponent(jButton5))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        tocar.setTocando(false);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButtonFalarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonFalarActionPerformed
        falarHora();
    }//GEN-LAST:event_jButtonFalarActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        escolherArquivos();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jListMusicasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jListMusicasKeyPressed
        if (evt.getKeyCode() == 127) {
            int[] indices = jListMusicas.getSelectedIndices();
            for (int i = indices.length - 1; i >= 0; i--) {
                dlm.removeElementAt(indices[i]);
                diretorios.remove(indices[i]);
            }

        }
    }//GEN-LAST:event_jListMusicasKeyPressed

    private void jListMusicasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jListMusicasMouseClicked
        if (evt.getClickCount() == 2) {
            if (jListMusicas.getSelectedIndex() > -1) {
                tocarMusica(diretorios.get(jListMusicas.getSelectedIndex()));
            }
        }
    }//GEN-LAST:event_jListMusicasMouseClicked

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed

    }//GEN-LAST:event_jButton5ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(RadioRelogio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RadioRelogio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RadioRelogio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RadioRelogio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new RadioRelogio().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButtonFalar;
    private javax.swing.JLabel jLabelData;
    private javax.swing.JList<String> jListMusicas;
    private javax.swing.JScrollPane jScrollPane2;
    public javax.swing.JLabel labelRelogio;
    // End of variables declaration//GEN-END:variables
}

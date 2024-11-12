
package com.mycompany.labfinal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Tragamonedas extends JFrame {
    private JLabel lblRodillo1, lblRodillo2, lblRodillo3;
    private JButton btnGirar;
    private boolean enJuego = false;

    public Tragamonedas() {
        setTitle("Juego de Tragamonedas");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new FlowLayout());
        setLocationRelativeTo(null);

        // Inicializar los rodillos como etiquetas
        lblRodillo1 = new JLabel("0");
        lblRodillo2 = new JLabel("0");
        lblRodillo3 = new JLabel("0");

        btnGirar = new JButton("Girar");

        // Acción para el botón de girar
        btnGirar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!enJuego) {
                    enJuego = true;
                    iniciarGiros();
                }
            }
        });

        // Añadir componentes a la ventana
        add(lblRodillo1);
        add(lblRodillo2);
        add(lblRodillo3);
        add(btnGirar);

        setVisible(true);
    }

    private void iniciarGiros() {
        Thread hilo1 = new Thread(new Rodillo(lblRodillo1));
        Thread hilo2 = new Thread(new Rodillo(lblRodillo2));
        Thread hilo3 = new Thread(new Rodillo(lblRodillo3));

        // Iniciar los hilos
        hilo1.start();
        hilo2.start();
        hilo3.start();

        // Detener el juego después de 2 segundos
        Timer timer = new Timer(2000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                enJuego = false;
                hilo1.interrupt();
                hilo2.interrupt();
                hilo3.interrupt();
            }
        });
        timer.setRepeats(false);
        timer.start();
    }

    // Clase interna para cada rodillo
    private class Rodillo implements Runnable {
        private JLabel label;
        
        public Rodillo(JLabel label) {
            this.label = label;
        }
        
        public void run() {
            while (!Thread.currentThread().isInterrupted()) {
                int numero = (int) (Math.random() * 10); // Número entre 0 y 9
                label.setText(String.valueOf(numero));
                
                try {
                    Thread.sleep(100); // Velocidad de giro
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt(); // Finalizar si es interrumpido
                }
            }
        }
    }
}

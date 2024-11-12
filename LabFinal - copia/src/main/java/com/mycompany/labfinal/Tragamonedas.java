package com.mycompany.labfinal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Tragamonedas extends JFrame {
    private JLabel lblRodillo1, lblRodillo2, lblRodillo3;
    private JButton btnGirar;
    private boolean enJuego = false;
    private ImageIcon[] iconos;

    public Tragamonedas() {
        setTitle("Juego de Tragamonedas");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new FlowLayout());
        setLocationRelativeTo(null);

        // Inicializar rodillos como etiquetas de imagen
        lblRodillo1 = new JLabel();
        lblRodillo2 = new JLabel();
        lblRodillo3 = new JLabel();
        btnGirar = new JButton("Girar");

        // Cargar las imágenes de los iconos en un arreglo
        cargarIconos();

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

    private void cargarIconos() {
        // Ruta donde están las imágenes
        iconos = new ImageIcon[5];
        iconos[0] = new ImageIcon("src/main/java/com/mycompany/labfinal/resources/cereza.png");
        iconos[1] = new ImageIcon("src/main/java/com/mycompany/labfinal/resources/manzana.png");
        iconos[2] = new ImageIcon("src/main/java/com/mycompany/labfinal/resources/limon.png");
        iconos[3] = new ImageIcon("src/main/java/com/mycompany/labfinal/resources/sandia.png");
        iconos[4] = new ImageIcon("src/main/java/com/mycompany/labfinal/resources/mora.png");
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
                int indice = (int) (Math.random() * iconos.length); // Selecciona un icono aleatorio
                label.setIcon(iconos[indice]);
                
                try {
                    Thread.sleep(100); // Velocidad de giro
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt(); // Finalizar si es interrumpido
                }
            }
        }
    }
}

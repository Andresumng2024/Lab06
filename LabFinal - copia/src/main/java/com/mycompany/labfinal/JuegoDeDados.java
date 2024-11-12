package com.mycompany.labfinal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class JuegoDeDados extends JFrame {
    private JButton btnTirar;
    private JLabel lblDado1, lblDado2;
    private ImageIcon[] diceImages;

    public JuegoDeDados() {
        setTitle("Juego de Dados");
        setSize(250, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
  setLocationRelativeTo(null);
        // Inicializar los botones y etiquetas
        btnTirar = new JButton("Tirar los Dados");
        lblDado1 = new JLabel();
        lblDado2 = new JLabel();

        diceImages = new ImageIcon[6];

        // Cargar las imágenes de los dados desde la carpeta "resources"
        for (int i = 0; i < 6; i++) {
            String path = "src/main/java/com/mycompany/labfinal/resources/dice" + (i + 1) + ".png";
            diceImages[i] = new ImageIcon(path);
        }

        // Acción para tirar los dados
        btnTirar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int dice1 = (int) (Math.random() * 6);
                int dice2 = (int) (Math.random() * 6);

                // Mostrar las imágenes de los dados
                lblDado1.setIcon(diceImages[dice1]);
                lblDado2.setIcon(diceImages[dice2]);
            }
        });

        // Añadir los componentes a la ventana
        add(btnTirar);
        add(lblDado1);
        add(lblDado2);

        setVisible(true);
    }

    public static void main(String[] args) {
        new JuegoDeDados();
    }
}

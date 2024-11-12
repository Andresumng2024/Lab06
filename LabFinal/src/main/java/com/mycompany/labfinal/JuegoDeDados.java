/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.labfinal;

/**
 *
 * @author USUARIA
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class JuegoDeDados extends JFrame {
    private JLabel lblResultado;
    private JButton btnLanzar;
    private ImageIcon[] dados;
    private Random random = new Random();

    public JuegoDeDados() {
        // Configuración de la ventana de juego de dados
        setTitle("Juego de Dados");
        setSize(300, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new FlowLayout());

        // Cargar imágenes para los dados
        dados = new ImageIcon[6];
        dados[0] = new ImageIcon(getClass().getResource("/resources/dice1.png"));
dados[1] = new ImageIcon(getClass().getResource("/resources/dice2.png"));
dados[2] = new ImageIcon(getClass().getResource("/resources/dice3.png"));
dados[3] = new ImageIcon(getClass().getResource("/resources/dice4.png"));
dados[4] = new ImageIcon(getClass().getResource("/resources/dice5.png"));
dados[5] = new ImageIcon(getClass().getResource("/resources/dice6.png"));

        lblResultado = new JLabel("Haz clic para lanzar los dados", SwingConstants.CENTER);
        lblResultado.setFont(new Font("Arial", Font.PLAIN, 20));
        add(lblResultado);

        btnLanzar = new JButton("Lanzar Dados");
        add(btnLanzar);
        btnLanzar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Crear un hilo para simular el lanzamiento de los dados
                new Thread(new Runnable() {
                    public void run() {
                        lanzarDados();
                    }
                }).start();
            }
        });

        setVisible(true);
    }

    private void lanzarDados() {
        int dado1 = random.nextInt(6); // Lanza dado 1
        int dado2 = random.nextInt(6); // Lanza dado 2

        // Simula una pequeña espera para mostrar el lanzamiento
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Muestra las imágenes de los dados
        lblResultado.setIcon(dados[dado1]);
        JLabel lblDado2 = new JLabel(dados[dado2]);
        lblDado2.setFont(new Font("Arial", Font.PLAIN, 30));
        add(lblDado2);
        validate();
        repaint();
    }

    public static void main(String[] args) {
        new JuegoDeDados();
    }
}
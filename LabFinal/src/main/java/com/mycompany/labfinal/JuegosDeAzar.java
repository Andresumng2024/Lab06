package com.mycompany.labfinal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class JuegosDeAzar extends JFrame {
    private JButton btnTragamonedas;
    private JButton btnJuegoDeDados;

    public JuegosDeAzar() {
        setTitle("Seleccionar Juego");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        // Botones para jugar Tragamonedas y Dados
        btnTragamonedas = new JButton("Jugar Tragamonedas");
        btnJuegoDeDados = new JButton("Jugar Dados");

        // Agregar los botones al JFrame
        add(btnTragamonedas);
        add(btnJuegoDeDados);

        // Acción para jugar al tragamonedas
        btnTragamonedas.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new Tragamonedas(); // Abre el juego de tragamonedas
            }
        });

        // Acción para jugar al juego de dados
        btnJuegoDeDados.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new JuegoDeDados(); // Abre el juego de dados
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        // Inicia la aplicación
        new JuegosDeAzar();
    }
}

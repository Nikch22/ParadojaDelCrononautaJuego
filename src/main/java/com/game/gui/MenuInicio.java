package main.java.com.game.gui;

import javax.swing.*;
import java.awt.event.*;

import main.java.com.game.common.Menu;

public class MenuInicio extends Menu {
    public MenuInicio() {
        super("/recursos/assets/imagenes/backgrounds/portada_es.png");

        JButton botonNuevoJuego = crearBotonTransparente("Nuevo Juego");
        botonNuevoJuego.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Lógica para comenzar un nuevo juego
            }
        });

        JButton botonConfiguracion = crearBotonTransparente("Configuración");
        botonConfiguracion.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Lógica para mostrar el menú de configuración
            }
        });

        JButton botonSalir = crearBotonTransparente("Salir");
        botonSalir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Lógica para salir del juego
            }
        });

        añadirElementoABackground(botonNuevoJuego);
        añadirElementoABackground(botonConfiguracion);
        añadirElementoABackground(botonSalir);
    }
}
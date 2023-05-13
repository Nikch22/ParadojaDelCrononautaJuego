package main.java.com.game.gui;

import main.java.com.game.core.*;

import javax.swing.*;
import java.awt.*;

public class GameWindow extends JFrame {
    //private Juego juego;
    private MenuInicio menuInicio;
    //private MenuConfiguracion menuConfiguracion;
    private CardLayout cardLayout;
    private JPanel mainPanel;

    public GameWindow() {
        // Configuración inicial de la ventana
        setTitle("Mi Juego 2D");
        setSize(1920, 1080);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Crear las diferentes "pantallas" del juego
        //juego = new Juego();
        menuInicio = new MenuInicio();
        //menuConfiguracion = new MenuConfiguracion();

        // Configurar un CardLayout para cambiar entre las "pantallas"
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        mainPanel.add(menuInicio, "MenuInicio");
        //mainPanel.add(menuConfiguracion, "MenuConfiguracion");
        //mainPanel.add(juego, "Juego");

        // Mostrar el menu de inicio por defecto
        cardLayout.show(mainPanel, "MenuInicio");

        // Añadir el panel principal a la ventana
        add(mainPanel);

        // Hacer visible la ventana
        setVisible(true);
    }

    public void cambiarAPantalla(String nombrePantalla) {
        cardLayout.show(mainPanel, nombrePantalla);
    }

    // getters y setters...
}

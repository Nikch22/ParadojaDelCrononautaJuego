package main.java.com.game.gui;

import main.java.com.game.core.*;

import javax.swing.*;
import java.awt.*;

public class GameWindow extends JFrame {

    private Juego juego;
    private MenuInicio menuInicio;
    private MenuConfiguracion menuConfiguracion;
    private CardLayout cardLayout;
    private JPanel mainPanel;

    public GameWindow() {
        // Configuración inicial de la ventana
        setTitle("La Paradoja Del Crononauta");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //setPreferredSize(new Dimension(1920, 1080)); // Establece el 1080p preferido de la ventana.
        setUndecorated(true);  // Esta línea oculta la barra de título y los bordes de la ventana
        // Evita que la ventana se redimensione
        setResizable(false);

        // Obtener el tamaño de la pantalla, excluyendo la barra de tareas
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
//        Rectangle bounds = ge.getMaximumWindowBounds();
//        setSize(bounds.getSize());
        GraphicsDevice device = ge.getDefaultScreenDevice();

        // Establecer la ventana en la posición y el tamaño deseados
        setExtendedState(JFrame.MAXIMIZED_BOTH);
//        setPreferredSize(bounds.getSize());
        // Establece esta ventana como la ventana de pantalla completa
//        device.setFullScreenWindow(this);

        setMinimumSize(new Dimension(912, 513)); // Establecer un tamaño mínimo si lo deseas
//        setLocation(bounds.getLocation());

        // Carga la imagen del ícono desde la carpeta de recursos
        ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource("recursos/assets/imagenes/iconos/game_icon.png"));
        // Establece la imagen como ícono de la ventana
        setIconImage(icon.getImage());

        // Configurar un CardLayout para cambiar entre las "pantallas"
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        mainPanel.setOpaque(false); // Esto hace que el mainPanel sea transparente

        // Crear las diferentes "pantallas" del juego
        juego = new Juego(this);
        menuInicio = new MenuInicio(this);
        menuConfiguracion = new MenuConfiguracion(this);

        mainPanel.add(menuInicio.getBackgroundPanel(), "MenuInicio");
        mainPanel.add(menuConfiguracion.getBackgroundPanel(), "MenuConfiguracion");
        mainPanel.add(juego.getEscenarioActual(), "Juego");

        // Mostrar el menu de inicio por defecto
        cardLayout.show(mainPanel, "MenuInicio");

        // Establecer mainPanel como el panel de contenido de la ventana
        setContentPane(mainPanel);

        // Ajusta el tamaño de la ventana según sus componentes y la centra en la pantalla.
//        pack();
//        setLocationRelativeTo(null);
        // Hacer visible la ventana
        setVisible(true);
    }

    public void cambiarAPantalla(String nombrePantalla) {
        cardLayout.show(mainPanel, nombrePantalla);
    }

    public void iniciarNuevoJuego() {
        // Iniciar la historia del juego
        juego.iniciarHistoria();

        // Cambiar la vista a la pantalla del juego
        cambiarAPantalla("Juego");
    }

    public MenuInicio getMenuInicio() {
        return menuInicio;
    }
    
    public JPanel getMainPanel() {
    return mainPanel;
    }

}

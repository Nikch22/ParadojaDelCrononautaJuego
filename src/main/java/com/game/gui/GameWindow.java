package main.java.com.game.gui;

import javax.swing.*;
import java.awt.*;

/**
 *
 * @author huals
 */
public class GameWindow extends JFrame {

    private JPanel mainPanel; // Panel principal que contiene otros paneles (MenuInicio, EscenarioPanel).
   /* private MenuInicio menuInicio; // Panel del menú de inicio.
    private Escenario escenario; // Objeto Escenario que contiene el panel del escenario del juego.
    DESCOMENTAR*/

    // Constructor de la clase GameWindow.
    public GameWindow() {
        setTitle("La Paradoja del Crononauta"); // Establece el título de la ventana.
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Cierra la aplicación cuando se cierra la ventana.
        setPreferredSize(new Dimension(1920, 1280)); // Establece el tamaño preferido de la ventana.
        
        // Carga la imagen del ícono desde la carpeta de recursos
        ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource("recursos/assets/imagenes/icons/game_icon.png"));
        // Establece la imagen como ícono de la ventana
        setIconImage(icon.getImage());
        
        // Crea el panel principal con un CardLayout para gestionar los paneles.
        mainPanel = new JPanel(new CardLayout());
        getContentPane().add(mainPanel, BorderLayout.CENTER); // Añade el panel principal al centro del JFrame.
        /*DESCOMENTAR
        // Crea e instancia el panel del menú de inicio y lo añade al panel principal.
        menuInicio = new MenuInicio(this);
        mainPanel.add(menuInicio, "MenuInicio");*/
        
        // Ajusta el tamaño de la ventana según sus componentes y la centra en la pantalla.
        pack();
        setLocationRelativeTo(null);
        setVisible(true); // Muestra la ventana del juego.
    }

    // Método para iniciar el juego. Se llama cuando el jugador selecciona "Nuevo Juego" en el menú de inicio.
    public void iniciarJuego() {
        /*escenario = new Escenario(); // Crea una nueva instancia del objeto Escenario.
        
        // Añade el panel del escenario al panel principal.
        mainPanel.add(escenario.getEscenarioPanel(), "EscenarioPanel");
        DESCOMENTAR*/
        // Cambia la vista del panel principal al panel del escenario.
        CardLayout layout = (CardLayout) mainPanel.getLayout();
        layout.show(mainPanel, "EscenarioPanel");
    }
}

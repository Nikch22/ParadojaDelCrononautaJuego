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
        setTitle("La Paradoja Del Crononauta");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //setPreferredSize(new Dimension(1920, 1080)); // Establece el 1080p preferido de la ventana.
        
        // Evita que la ventana se redimensione
        setResizable(false);
        
        // Obtener el tamaño de la pantalla, excluyendo la barra de tareas
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        Rectangle bounds = ge.getMaximumWindowBounds();
        setSize(bounds.getSize());
        
        // Establecer la ventana en la posición y el tamaño deseados
        setPreferredSize(bounds.getSize());
        setMinimumSize(new Dimension(912, 513)); // Establecer un tamaño mínimo si lo deseas
        setLocation(bounds.getLocation());
        
        // Carga la imagen del ícono desde la carpeta de recursos
        ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource("recursos/assets/imagenes/iconos/game_icon.png"));
        // Establece la imagen como ícono de la ventana
        setIconImage(icon.getImage());
                

        // Crear las diferentes "pantallas" del juego
        //juego = new Juego();
        menuInicio = new MenuInicio();
        //menuConfiguracion = new MenuConfiguracion();

        // Configurar un CardLayout para cambiar entre las "pantallas"
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        mainPanel.setOpaque(false); // Esto hace que el mainPanel sea transp
        
        // Aplicar el fondo a la ventana principal
        setContentPane(menuInicio.getBackgroundPanel());
        
        mainPanel.add(menuInicio, "MenuInicio");
        //mainPanel.add(menuConfiguracion, "MenuConfiguracion");
        //mainPanel.add(juego, "Juego");

        // Mostrar el menu de inicio por defecto
        cardLayout.show(mainPanel, "MenuInicio");

        // Añadir el panel principal a la ventana
        getContentPane().add(mainPanel); // Añade mainPanel al panel de contenido en lugar de a la ventana

        // Ajusta el tamaño de la ventana según sus componentes y la centra en la pantalla.
        pack();
        setLocationRelativeTo(null);
        // Hacer visible la ventana
        setVisible(true);
    }

    public void cambiarAPantalla(String nombrePantalla) {
        cardLayout.show(mainPanel, nombrePantalla);
    }

    
    // Método para iniciar el juego. Se llama cuando el jugador selecciona "Nuevo Juego" en el menú de inicio.
   // public void iniciarJuego() {
        /*escenario = new Escenario(); // Crea una nueva instancia del objeto Escenario.

        // Añade el panel del escenario al panel principal.
        mainPanel.add(escenario.getEscenarioPanel(), "EscenarioPanel");
        DESCOMENTAR*/
        // Cambia la vista del panel principal al panel del escenario.
     /*   CardLayout layout = (CardLayout) mainPanel.getLayout();
        layout.show(mainPanel, "EscenarioPanel");
    }*/
    
    
    // getters y setters...
}

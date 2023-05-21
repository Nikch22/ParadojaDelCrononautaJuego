package main.java.com.game.gui;

import main.java.com.game.utils.*;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import javax.sound.sampled.*;

/**
 *
 * @author huals
 */
public class Escenario extends JPanel {

    private ImagePanel imagePanel;

    public Escenario(String imagePath) {
        // Configuración inicial del panel
        setLayout(new BorderLayout());

        // Configurar el ImagePanel con la ruta de la imagen
        setImageBackground(imagePath);
    }

    public void setImageBackground(String imagePath) {
        // Crear un nuevo ImagePanel con la nueva imagen
        imagePanel = new ImagePanel(imagePath);

        // Obtener el tamaño de la pantalla, excluyendo la barra de tareas
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        Rectangle bounds = ge.getMaximumWindowBounds();
        //System.out.println(bounds.getSize());
        imagePanel.setPreferredSize(bounds.getSize());

        // Limpiar cualquier cosa que se esté mostrando en el panel y agregar el nuevo ImagePanel
        removeAll();
        imagePanel.setLayout(new BorderLayout());
        add(imagePanel, BorderLayout.CENTER);

        // Asegurarse de que los cambios se apliquen
        revalidate();
        repaint();
    }

    public void addPanel(JPanel panel, String position) {
        // Si la posición es "NORTH" o "SOUTH", asignamos el panel a esa posición
        imagePanel.add(panel, position);

        // Asegurarse de que los cambios se apliquen
        revalidate();
        repaint();
    }

    public void removePanel(JPanel panel) {
        remove(panel);
        revalidate();
        repaint();
    }
}

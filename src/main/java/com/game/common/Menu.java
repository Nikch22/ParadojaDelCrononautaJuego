package main.java.com.game.common;

import javax.swing.*;
import java.awt.*;

/**
 *
 * @author huals
 */
public abstract class Menu extends JPanel {
    private JLabel background;

    public Menu(String backgroundImagePath) {
        // Configuración inicial del panel
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Crear y configurar el background
        background = new JLabel();
        ImageIcon backgroundIcon = new ImageIcon(backgroundImagePath);
        background.setIcon(backgroundIcon);
        background.setLayout(new BoxLayout(background, BoxLayout.Y_AXIS));

        // Añadir el background al panel
        add(background);
    }

    protected JButton crearBotonTransparente(String texto) {
        JButton boton = new JButton(texto);
        boton.setOpaque(false);
        boton.setContentAreaFilled(false);
        boton.setBorderPainted(false);
        boton.setForeground(Color.WHITE);
        boton.setAlignmentX(Component.CENTER_ALIGNMENT);
        return boton;
    }

    protected void añadirElementoABackground(JComponent elemento) {
        background.add(Box.createVerticalGlue());
        background.add(elemento);
        background.add(Box.createVerticalGlue());
    }
}
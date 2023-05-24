package main.java.com.game.utils;

import javax.swing.*;
import java.awt.*;

/**
 *
 * @author huals
 */
public class CustomDialog extends JPanel {
    public CustomDialog(String idioma) {
        setLayout(new BorderLayout());
        setBackground(new Color(0, 0, 0, 123)); // Color de fondo negro transparente
        JLabel message = new JLabel(idioma.equals("en") ? "Are you sure you want to finish this adventure?" : "¿Estás seguro que quieres terminar esta aventura?", SwingConstants.CENTER);
        message.setForeground(Color.WHITE); // Color de texto blanco
        add(message, BorderLayout.CENTER);
    }
}

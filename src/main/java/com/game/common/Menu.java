package main.java.com.game.common;

import main.java.com.game.utils.*;

import javax.swing.*;
import java.awt.*;
import javax.imageio.ImageIO;
import java.io.IOException;

/**
 *
 * @author huals
 */
public abstract class Menu extends JPanel {

    private ImagePanel background;
    private JPanel buttonPanel; // Nuevo panel para los botones
    private Color btnBgColor;
    private Color btnHvBgColor;

    public Menu() {
        // Configuración inicial del panel
        setLayout(new BorderLayout());
        setBackground(new Color(0, 0, 0, 0));

        buttonPanel = new JPanel();
        buttonPanel.setMinimumSize(new Dimension(400, 400));
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setOpaque(false); // Hacer transparente el panel de botones
    }

    protected JButton createButton(String text) {
        btnBgColor = new Color(145, 133, 122, 100);
        btnHvBgColor = new Color(255, 211, 193, 70);

        JButton button = new TranslucentButton(text, btnBgColor, btnHvBgColor, new Font("Oswald", Font.BOLD, 26), new Dimension(200, 60), new Dimension(600, 60)); // Color de fondo translúcido;       
        button.setRolloverEnabled(true); // Habilita el efecto visual de hover

        //button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setForeground(new Color(255, 255, 252, 100));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        return button;
    }

    protected void addSpace(int height) {
        buttonPanel.add(Box.createVerticalStrut(height));
    }

    public void setBackground(String imagePath) {
        background = new ImagePanel(imagePath);
        background.setLayout(new BorderLayout());
        add(background, BorderLayout.CENTER);

        // Establecer el layout del background como GridBagLayout
        background.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.CENTER;
        background.add(buttonPanel, gbc);

    }

    public JPanel getBackgroundPanel() {
        return background;
    }

    public JPanel getButtonPanel() {
        return buttonPanel;
    }
}

package main.java.com.game.utils;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author huals
 */
public class ImagePanel extends JPanel {
    private Image image;

    public ImagePanel(String imagePath) {
        try {
            image = ImageIO.read(getClass().getResource(imagePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Dibuja la imagen escalada para llenar el panel
        g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), this);
    }
}

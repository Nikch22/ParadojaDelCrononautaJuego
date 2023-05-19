/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.java.com.game.utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 *
 * @author huals
 */
public class TranslucentButton extends JButton {

    private Color background;
    private Color hoverBackground;

    public TranslucentButton(String text, Color background, Color hoverBackground, Font font, Dimension dimension, Dimension maxDimension) {
        super(text);
        this.background = background;
        this.hoverBackground = hoverBackground;
        setOpaque(false);
        setFont(font);
        setPreferredSize(dimension);
        setMaximumSize(maxDimension);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setBackground(hoverBackground);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setBackground(background);
            }
        });
    }

    public TranslucentButton(String text, Color background, Color hoverBackground, Font font, Dimension dimension) {
        super(text);
        this.background = background;
        this.hoverBackground = hoverBackground;
        setOpaque(false);
        setFont(font);
        setPreferredSize(dimension);
        setMaximumSize(dimension);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setBackground(hoverBackground);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setBackground(background);
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        setBackground(getModel().isRollover() ? hoverBackground : background);

        Graphics2D g2 = (Graphics2D) g.create();
        g2.setComposite(AlphaComposite.SrcOver.derive(1f)); // Establecer opacidad para el fondo 0.95, ETC
        g2.setColor(getBackground());
        g2.fillRect(0, 0, getWidth(), getHeight());
        g2.dispose();

        // Establecer el color de fuente antes de pintar el texto
        g2 = (Graphics2D) g.create();
        g2.setColor(getForeground());

        // Centrar el texto verticalmente
        FontMetrics fm = g2.getFontMetrics();
        int x = (getWidth() - fm.stringWidth(getText())) / 2;
        int y = (getHeight() - fm.getHeight()) / 2 + fm.getAscent();
        g2.drawString(getText(), x, y);

        g2.dispose();
    }

    @Override
    protected void paintBorder(Graphics g) {
        // Sobrescribir este método para evitar que se pinte el borde
    }
}

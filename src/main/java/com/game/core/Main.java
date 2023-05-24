package main.java.com.game.core;

import main.java.com.game.gui.GameWindow;
import javax.swing.SwingUtilities;


public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GameWindow ventana = new GameWindow();
        });
    }
}

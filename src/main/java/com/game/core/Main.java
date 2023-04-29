/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.java.com.game.core;


import main.java.com.game.gui.GameWindow;
import javax.swing.SwingUtilities;

/**
 *
 * @author huals
 */
public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GameWindow ventana = new GameWindow();
        });
    }
}
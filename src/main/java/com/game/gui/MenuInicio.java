package main.java.com.game.gui;

import java.awt.Color;
import javax.swing.*;
import java.awt.event.*;

import main.java.com.game.core.*;
import main.java.com.game.utils.CustomDialog;
import main.java.com.game.common.Menu;

public class MenuInicio extends Menu {

    private GameWindow ventanaPrincipal; // Agregar un campo para la ventana principal
    private String idioma = GameSettings.getLanguage();

    public MenuInicio(GameWindow ventanaPrincipal) {
        this.ventanaPrincipal = ventanaPrincipal; // Inicializar la ventana principal
        String[] opciones = {"Nuevo Juego", "Ajustes", "Salir"};
        if (GameSettings.getLanguage().equals("en")) {
            String[] op = {"New Game", "Settings", "Exit"};
            opciones = op;
        }
        setBackground("/recursos/assets/imagenes/backgrounds/portada_" + idioma +".png");

        for (String opcion : opciones) {
            JButton button = createButton(opcion);
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    switch (opcion) {
                        case "Nuevo Juego":
                        case "New Game":
                            nuevoJuego();
                            break;
                        case "Ajustes":
                        case "Settings":
                            ajustes();
                            break;
                        case "Salir":
                        case "Exit":
                            salir();
                            break;
                    }
                }
            });

            getButtonPanel().add(button); // Agrega el botón al panel de botones
            addSpace(35);
        }
        getButtonPanel().add(Box.createVerticalGlue()); // Agrega un espacio vertical flexible en la parte inferior

    }

    private void nuevoJuego() {
        System.out.println("Nuevo Juego seleccionado.");
        // Usar la referencia a ventanaPrincipal que se pasó al constructor y llamar a su método iniciarNuevoJuego
        ventanaPrincipal.iniciarNuevoJuego();
    }

    private void ajustes() {
        System.out.println("Ajustes seleccionado.");
        // Aquí puedes agregar la lógica para Ajustes
        ventanaPrincipal.cambiarAPantalla("MenuConfiguracion");
    }

    private void salir() {
        System.out.println("Salir seleccionado.");
        // Guarda los valores actuales para poder restaurarlos luego
        Color oldBackgroundColor = UIManager.getColor("OptionPane.background");
        Color oldButtonColor = UIManager.getColor("Button.background");

        // Define los nuevos colores
        UIManager.put("OptionPane.background", new Color(50, 50, 50));  // Oscuro
        UIManager.put("Panel.background", new Color(50, 50, 50));  // Oscuro
        UIManager.put("Button.background", new Color(200, 200, 200));  // Gris claro

        CustomDialog customDialog = new CustomDialog(GameSettings.getLanguage());
        Object[] options = {"Sí, salir", "No, quedarme"};  // Personalizar textos de los botones
        Object[] optionsEn = {"Yes, quit", "No, stay"};

        int confirm = JOptionPane.showOptionDialog(
                null,
                customDialog,
                GameSettings.getLanguage().equals("en") ? "Confirmation Request" : "Solicitud de Confirmación",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null, GameSettings.getLanguage().equals("en") ? optionsEn : options, options[1]);
        if (confirm == JOptionPane.YES_OPTION) {
            System.exit(0);
        }

        // Restaura los colores antiguos
        UIManager.put("OptionPane.background", oldBackgroundColor);
        UIManager.put("Panel.background", oldBackgroundColor);
        UIManager.put("Button.background", oldButtonColor);
    }

}

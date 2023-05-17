package main.java.com.game.gui;

import javax.swing.*;
import java.awt.event.*;

import main.java.com.game.common.Menu;

public class MenuInicio extends Menu {
    public MenuInicio() {
       setBackground("/recursos/assets/imagenes/backgrounds/portada_es.png");
       String[] opciones = {"Nuevo Juego", "Ajustes", "Salir"};

        for (String opcion : opciones) {
            JButton button = createButton(opcion);
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    switch (opcion) {
                        case "Nuevo Juego":
                            nuevoJuego();
                            break;
                        case "Ajustes":
                            ajustes();
                            break;
                        case "Salir":
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
        System.out.println("Inicio seleccionado.");
        // Aquí puedes agregar la lógica para Inicio
        }

        private void ajustes() {
            System.out.println("Ajustes seleccionado.");
            // Aquí puedes agregar la lógica para Ajustes
        }

        private void salir() {
            System.out.println("Salida seleccionado.");
            // Aquí puedes agregar la lógica para Salida
        }

}
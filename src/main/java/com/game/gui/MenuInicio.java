package main.java.com.game.gui;

import javax.swing.*;
import java.awt.event.*;

import main.java.com.game.core.*;
import main.java.com.game.common.Menu;

public class MenuInicio extends Menu {
    private GameWindow ventanaPrincipal; // Agregar un campo para la ventana principal
    
    public MenuInicio(GameWindow ventanaPrincipal) {
       this.ventanaPrincipal = ventanaPrincipal; // Inicializar la ventana principal
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
        System.out.println("Nuevo Juego seleccionado.");
        // Usar la referencia a ventanaPrincipal que se pasó al constructor y llamar a su método iniciarNuevoJuego
        ventanaPrincipal.iniciarNuevoJuego();
//        // Crear una nueva instancia del juego
//        Juego nuevoJuego = new Juego();
//
//        // Iniciar la historia del juego
//        nuevoJuego.iniciarHistoria();
//
//        // Obtener el escenario actual del juego
//        Escenario escenarioActual = nuevoJuego.getEscenarioActual();
//
//        // Reemplazar el contenido de la ventana principal con el nuevo escenario
//        JFrame ventanaPrincipal = (JFrame) SwingUtilities.getWindowAncestor(this);
//        ventanaPrincipal.getContentPane().removeAll();
//        ventanaPrincipal.getContentPane().add(escenarioActual);
//        ventanaPrincipal.revalidate();
//        ventanaPrincipal.repaint();
        }

        private void ajustes() {
            System.out.println("Ajustes seleccionado.");
            // Aquí puedes agregar la lógica para Ajustes
        }

        private void salir() {
            System.out.println("Salir seleccionado.");
            // Aquí puedes agregar la lógica para Salida
        }

}
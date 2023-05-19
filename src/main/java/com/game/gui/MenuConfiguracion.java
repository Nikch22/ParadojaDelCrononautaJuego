package main.java.com.game.gui;

import javax.swing.*;
import java.awt.event.*;

import main.java.com.game.core.*;
import main.java.com.game.common.Menu;

/**
 *
 * @author huals
 */
public class MenuConfiguracion extends Menu {
    private GameWindow ventanaPrincipal; // Agregar un campo para la ventana principal
    
    public MenuConfiguracion(GameWindow ventanaPrincipal) {
       this.ventanaPrincipal = ventanaPrincipal; // Inicializar la ventana principal
       setBackground("/recursos/assets/imagenes/backgrounds/configuracion.jpg");
       String[] opciones = {"Ajustar Volumen", "Activar Narración por Voz", "Cambiar Idioma"};

        for (String opcion : opciones) {
            JButton button = createButton(opcion);
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    switch (opcion) {
                        case "Ajustar Volumen":
                            ajustarVolumen();
                            break;
                        case "Activar Narración por Voz":
                            activarNarracionPorVoz();
                            break;
                        case "Cambiar Idioma":
                            cambiarIdioma();
                            break;
                    }
                }
            });
            
            getButtonPanel().add(button); // Agrega el botón al panel de botones
            addSpace(35);
        }
        getButtonPanel().add(Box.createVerticalGlue()); // Agrega un espacio vertical flexible en la parte inferior

    }

    private void ajustarVolumen() {
        System.out.println("Ajustar Volumen seleccionado.");
        // Aquí puedes agregar la lógica para Ajustar Volumen
    }

    private void activarNarracionPorVoz() {
        System.out.println("Activar Narración por Voz seleccionado.");
        // Aquí puedes agregar la lógica para Activar Narración por Voz
    }

    private void cambiarIdioma() {
        System.out.println("Cambiar Idioma seleccionado.");
        // Aquí puedes agregar la lógica para Cambiar Idioma
    }
}

package main.java.com.game.core;

import main.java.com.game.gui.DialogPanel;
import main.java.com.game.gui.*;
import main.java.com.game.minijuegos.*;
import javax.swing.*;
import java.awt.*;

public class Juego {
    private Minijuego minijuegoActual;
    private DialogPanel dialogPanel;
    private Escenario escenarioActual;


    public Juego() {
        // Aquí se inicializa el primer diálogo de la historia
        escenarioActual = new Escenario("/recursos/assets/imagenes/iconos/bg2_test.jpg");        
        // Inicializar la historia y los minijuegos
        //iniciarHistoria();
        
    }

    public void iniciarHistoria() {        
        String[] dialogos = {
            "Hola, ¿cómo estás?",
            "Me llamo Juan",
            "¿Qué haces por aquí?",
            "¡Qué interesante!",
            "Adiós"
        };
        // Crear un nuevo DialogPanel y agregarlo al escenario
        dialogPanel = new DialogPanel(dialogos,this);
        escenarioActual.addPanel(dialogPanel, BorderLayout.SOUTH);
    }
     
     
    public void mostrarDialogo() {
        // Este método se encarga de mostrar el diálogo en la pantalla.
        // Como el diálogo se actualiza automáticamente al hacer clic en el panel,
        // no es necesario hacer nada más aquí.
    }

    public void avanzarDialogo() {
        // Aquí se maneja el avance en la historia.
        // Este método se llama cada vez que el jugador presiona el botón para avanzar en el diálogo.
        // Si el diálogo actual indica que el próximo evento es un minijuego, entonces se inicia el minijuego.
        // Si el diálogo actual indica que el próximo evento es otro diálogo, entonces se pasa al siguiente diálogo.
        
        /*if (dialogoActual.esMinijuego()) {
            iniciarMinijuego();
        } else {
            dialogoActual = dialogoActual.getSiguienteDialogo();
            mostrarDialogo();
        }*/
    }

    public void iniciarMinijuego() {
        // Aquí se inicializa el minijuego correspondiente al estado actual de la historia
        minijuegoActual = new Minijuego(/* Parámetros necesarios para el minijuego actual */);
        
        // Mostrar el minijuego en la pantalla
        mostrarMinijuego();
    }

    public void mostrarMinijuego() {
        // Este método se encarga de mostrar el minijuego en la pantalla.
    }

    public void finalizarMinijuego() {
        // Este método se llama cuando el jugador ha completado el minijuego.
        mostrarDialogo();
    }
    
    public Escenario getEscenarioActual() {
    return escenarioActual;
    }
}

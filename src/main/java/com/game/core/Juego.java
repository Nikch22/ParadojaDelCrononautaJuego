package main.java.com.game.core;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import main.java.com.game.gui.DialogPanel;
import main.java.com.game.gui.*;
import main.java.com.game.minijuegos.*;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class Juego {

    private Minijuego minijuegoActual;
    public DialogPanel dialogPanel;
    private Escenario escenarioActual;
    private String idioma;
    private String archivoDialogos;
    public GameWindow ventanaPrincipal;

    public Juego(GameWindow ventanaPrincipal) {
        this.ventanaPrincipal = ventanaPrincipal;
        // Aquí se inicializa el primer diálogo de la historia
        escenarioActual = new Escenario("/recursos/assets/imagenes/backgrounds/p1_bg_1.jpg");
        // Inicializar la historia y los minijuegos
        //iniciarHistoria();
    }

    public void iniciarHistoria() {

        idioma = GameSettings.getLanguage();
        archivoDialogos = "/recursos/assets/idiomas/dialogos_" + idioma + ".json";
        System.out.println(GameSettings.getLanguage());
        // Llamas al método cargarDialogos y guardas el resultado en una variable
        Map<String, ArrayList<JSONObject>> dialogosPorPantalla = cargarDialogos();
        if (dialogPanel == null) {
            // Crear un nuevo DialogPanel y agregarlo al escenario
            dialogPanel = new DialogPanel(dialogosPorPantalla, this);
            if (GameSettings.isVoiceNarrationEnabled()) {
                String narracion = (String) dialogosPorPantalla.get("pantalla1").get(0).get("narracion");
                if (narracion != "") {
                    // Detener el audio en curso antes de iniciar uno nuevo
                    String pathAudio = "/recursos/assets/audio/narraciones/" + idioma + "/" + narracion + ".mp3";
                    dialogPanel.playAudio(pathAudio);

                }
            }
            escenarioActual.addPanel(dialogPanel, BorderLayout.SOUTH);
        } else {
            if (GameSettings.isVoiceNarrationEnabled()) {
                String narracion = (String) dialogosPorPantalla.get("pantalla1").get(0).get("narracion");
                if (narracion != "") {
                    // Detener el audio en curso antes de iniciar uno nuevo
                    String pathAudio = "/recursos/assets/audio/narraciones/" + idioma + "/" + narracion + ".mp3";
                    dialogPanel.playAudio(pathAudio);

                }
            }
            // dialogPanel ya existe, resetear su estado
            dialogPanel.resetPanel();
        }
    }

    public Map<String, ArrayList<JSONObject>> cargarDialogos() {
        Map<String, ArrayList<JSONObject>> dialogosPorPantalla = new HashMap<>();

        try {
            // Carga el archivo JSON            
            if (!idioma.equals("es") && !idioma.equals("en")) {
                // Si el idioma no está especificado correctamente, puedes lanzar una excepción o establecer un valor predeterminado.
                throw new IllegalArgumentException("Idioma no válido: " + idioma);
            }

            InputStream inputStream = getClass().getResourceAsStream(archivoDialogos);
            InputStreamReader reader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(reader);
            /*InputStream inputStream = getClass().getResourceAsStream("/recursos/assets/idiomas/dialogos_es.json");
            InputStreamReader reader = new InputStreamReader(inputStream);
            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(reader);*/

            // Obtiene el objeto "dialogos" del JSON
            JSONObject dialogosObject = (JSONObject) jsonObject.get("dialogos");

            // Recorre cada pantalla y obtiene los diálogos
            for (Object pantallaObj : dialogosObject.keySet()) {
                String pantalla = (String) pantallaObj;
                JSONArray dialogosPantalla = (JSONArray) dialogosObject.get(pantalla);

                // Crea un ArrayList para almacenar los diálogos de la pantalla actual
                ArrayList<JSONObject> dialogos = new ArrayList<>();

                // Recorre los diálogos de la pantalla
                for (Object dialogoObj : dialogosPantalla) {
                    JSONObject dialogo = (JSONObject) dialogoObj;
                    dialogos.add(dialogo);
                }

                // Agrega el ArrayList de diálogos al HashMap, usando el nombre de la pantalla como clave
                dialogosPorPantalla.put(pantalla, dialogos);
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
            // Manejo de errores
        }
        return dialogosPorPantalla;
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

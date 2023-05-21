package main.java.com.game.gui;

import main.java.com.game.core.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.net.URL;
import java.io.InputStream;
import java.io.BufferedInputStream;
import javax.swing.border.EmptyBorder;
import javax.swing.event.MouseInputAdapter;
import javax.swing.text.Caret;
import javax.swing.text.Highlighter;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import org.json.simple.JSONObject;
//Libreria javazoom para manejar los audios
import javazoom.jl.player.Player;
import javazoom.jl.decoder.JavaLayerException;
import main.java.com.game.utils.CustomDialog;

public class DialogPanel extends JPanel {

    private JLabel characterImageLabel;
    private JTextPane dialogTextPane;
    private JLabel clickIconLabel;
    private JLabel homeIconLabel;
    private JLabel exitIconLabel;
    private Map<String, ArrayList<JSONObject>> dialogos;
    String personaje;
    String frase;
    private JPanel iconPanel;
    private int dialogoActual = 0;
    private int pantallaActualIndex = 1;
    private String pantallaActual = "pantalla" + pantallaActualIndex;
    private Image backgroundImage;
    private Juego referenciaJuego;
    private String backgroundEscenarioActual;
    private String nuevoEscenarioPath;
    private String idiomaConfigurado = (String) GameSettings.getLanguage();
    private boolean vocesActivadas = GameSettings.isVoiceNarrationEnabled();
    // Agregar la variable de instancia audioPlayer
    private Player audioPlayer;

    public DialogPanel(Map<String, ArrayList<JSONObject>> dialogosPorPantalla, Juego referenciaJuego) {
        this.referenciaJuego = referenciaJuego;
        this.dialogos = dialogosPorPantalla;

        // Añade esta línea
        this.setBorder(new EmptyBorder(0, 0, 0, 0));
        // Asignno Background del panel de dialogos
//        String backgroundImagePath = "/recursos/assets/imagenes/dialogos_1.png";
//        backgroundImage = new ImageIcon(getClass().getResource(backgroundImagePath)).getImage();

        String initialcharacter = (String) dialogos.get(pantallaActual).get(0).get("personaje");
        String characterImagePath = "/recursos/assets/imagenes/personajes/" + initialcharacter + ".png";
        String initialDialogText = (String) initialcharacter + ": " + dialogos.get("pantalla1").get(0).get("frase");
        String clickIconPath = "/recursos/assets/imagenes/iconos/click_claro.png";

        // Cambiando el layout del panel
        setLayout(new BorderLayout());
        setBackground(new Color(185, 185, 185, 100));

        setPreferredSize(new Dimension(Integer.MAX_VALUE, 150));

        // Creacion y configuracion de los íconos de inicio y salida
        String homeIconPath = "/recursos/assets/imagenes/iconos/casa_claro.png";
        String exitIconPath = "/recursos/assets/imagenes/iconos/salida_claro.png";

        ImageIcon homeIconImage = new ImageIcon(getClass().getResource(homeIconPath));
        Image homeImage = homeIconImage.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
        homeIconImage = new ImageIcon(homeImage);
        homeIconLabel = new JLabel(homeIconImage);

        ImageIcon exitIconImage = new ImageIcon(getClass().getResource(exitIconPath));
        Image exitImage = exitIconImage.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
        exitIconImage = new ImageIcon(exitImage);
        exitIconLabel = new JLabel(exitIconImage);

        // Crear y configurar la imagen del personaje
        ImageIcon characterImageIcon = new ImageIcon(getClass().getResource(characterImagePath));
        // Redimensionar la imagen
        Image characterImage = characterImageIcon.getImage().getScaledInstance(90, 90, Image.SCALE_DEFAULT);
        characterImageIcon = new ImageIcon(characterImage);
        characterImageLabel = new JLabel(characterImageIcon);

        // Create a panel for the characterImageLabel and place it to the WEST
        JPanel characterImagePanel = new JPanel();
        characterImagePanel.setLayout(new BorderLayout());
        characterImagePanel.add(characterImageLabel, BorderLayout.NORTH);
        characterImagePanel.setOpaque(false);

        // Crear y configurar el área de texto de diálogo
        dialogTextPane = new JTextPane() {
            public void setHighlighter(Highlighter h) {
                /* Sobrescribir para ignorar */ }

        };
        dialogTextPane.setText(initialDialogText);

        // Configuración de la caja de texto
        dialogTextPane.setEditable(false);
        dialogTextPane.setFont(new Font("Oswald", Font.BOLD, 28));
        dialogTextPane.setForeground(new Color(252, 252, 252));
        dialogTextPane.setOpaque(false);
        dialogTextPane.setMinimumSize(new Dimension(500, 60));
        dialogTextPane.setMaximumSize(new Dimension(Integer.MAX_VALUE, 60)); // Añadido para permitir la expansión horizontal

        // Hacer que el texto no sea seleccionable
        Caret caret = dialogTextPane.getCaret();
        caret.setBlinkRate(0);
        caret.setVisible(false);

        // Para el centrado vertical
        StyledDocument doc = dialogTextPane.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(0, doc.getLength(), center, false);

        // Centrar vertical y horizontalmente el texto en el dialogTextPane
        JPanel dialogTextPanel = new JPanel();
        dialogTextPanel.setLayout(new GridBagLayout()); // Cambiado a GridBagLayout
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0; // El texto se expandirá horizontalmente
        gbc.weighty = 1.0; // El texto se expandirá verticalmente
        dialogTextPanel.add(dialogTextPane, gbc);
        dialogTextPanel.setOpaque(false);

        // Crear y configurar el ícono de clic
        ImageIcon clickIconImage = new ImageIcon(getClass().getResource(clickIconPath));
        // Redimensionar la imagen
        Image clickImage = clickIconImage.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
        clickIconImage = new ImageIcon(clickImage);
        clickIconLabel = new JLabel(clickIconImage);

        // Crear un panel para characterImageLabel y dialogTextPane y agregarlo al norte
        JPanel northPanel = new JPanel(new BorderLayout());
        northPanel.add(characterImagePanel, BorderLayout.WEST);
        northPanel.add(dialogTextPanel, BorderLayout.CENTER);
        northPanel.setBackground(new Color(0, 0, 0, 0));
        northPanel.setOpaque(false);
        add(northPanel, BorderLayout.NORTH);

        // Crear un panel para clickIconLabel y agregarlo al sur
        JPanel southPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        southPanel.add(clickIconLabel);
        southPanel.setBackground(new Color(0, 0, 0, 0));
        southPanel.setOpaque(false);
        add(southPanel, BorderLayout.SOUTH);

        // Agregar filtro de eventos de mouse para ignorar eventos de arrastre
        Toolkit.getDefaultToolkit().addAWTEventListener(new AWTEventListener() {
            public void eventDispatched(AWTEvent event) {
                if (event instanceof MouseEvent) {
                    MouseEvent mouseEvent = (MouseEvent) event;
                    if (mouseEvent.getSource() == dialogTextPane) {
                        switch (mouseEvent.getID()) {
                            case MouseEvent.MOUSE_DRAGGED:
                            case MouseEvent.MOUSE_PRESSED:
                            case MouseEvent.MOUSE_RELEASED:
                                mouseEvent.consume();
                                break;
                        }
                    }
                }
            }
        }, AWTEvent.MOUSE_EVENT_MASK | AWTEvent.MOUSE_MOTION_EVENT_MASK);

        // Agregar un MouseListener a los íconos de inicio y salida
        homeIconLabel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                dialogoActual = 0;
                pantallaActualIndex = 1;
                pantallaActual = "pantalla1";
                frase = "";
                personaje = "";
                setNuevoEscenario("/recursos/assets/imagenes/backgrounds/p1_bg_1.jpg");
                // Aquí agregar la lógica para ir al inicio
                referenciaJuego.ventanaPrincipal.cambiarAPantalla("MenuInicio");
            }
        });

        exitIconLabel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                // Aquí agregar la lógica para salir del juego
                salir();
            }
        });

        // Agregar un MouseListener al panel para detectar clics
        addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                eventMouseClicked();
            }
        });

        // Agregar un MouseListener específico para la caja de texto
        dialogTextPane.addMouseListener(new MouseInputAdapter() {
            public void mouseClicked(MouseEvent e) {
                eventMouseClicked();
            }
        });

        // Agrego el panel de iconos al escenario
        JPanel iconPanel = createIconPanel();
        this.iconPanel = iconPanel;
        referenciaJuego.getEscenarioActual().addPanel(iconPanel, BorderLayout.EAST);
    }

    public void eventMouseClicked() {
        // Aquí agregar la lógica para cambiar el diálogo cuando el panel es clickeado
        if (dialogoActual != -1) {
            dialogoActual++;
        } else {
            dialogoActual = 0;
        }
        if (pantallaActualIndex == 1 || pantallaActualIndex == 2 || pantallaActualIndex == 3 || pantallaActualIndex == 4 || pantallaActualIndex == 5 || pantallaActualIndex == 6) {
            if (dialogoActual < dialogos.get(pantallaActual).size()) {
                JSONObject dialogo = dialogos.get(pantallaActual).get(dialogoActual);
                if (!dialogo.get("personaje").equals("Minijuego")) {
                    System.out.println(pantallaActual);

                    personaje = (String) dialogo.get("personaje");
                    String backgroundName = (String) dialogo.get("background");
                    nuevoEscenarioPath = "/recursos/assets/imagenes/backgrounds/" + backgroundName + ".jpg";
                    if (!personaje.equals("Guion") && !personaje.equals("Narrador") && !personaje.equals("Script") && !personaje.equals("Narrator")) {
                        frase = (String) personaje + ": " + dialogo.get("frase");
                    } else {
                        frase = (String) dialogo.get("frase");
                    }

                    if (backgroundName != backgroundEscenarioActual) {
                        setNuevoEscenario(nuevoEscenarioPath);
                    }
                    setDialogCharacterImage(personaje);
                    setDialogText(frase);
                    //Si está activa la opción de narracion por voz
                    if (vocesActivadas) {
                        String narracion = (String) dialogo.get("narracion");
                        if (narracion != "") {
                            // Detener el audio en curso antes de iniciar uno nuevo
                            String pathAudio = "/recursos/assets/audio/narraciones/" + idiomaConfigurado + "/" + narracion + ".mp3";
                            playAudio(pathAudio);

                        }
                    }
                } else {
                    //Lógica del Minijuego
                    System.out.println("Se llama a Minijuego");
                }

            } else {
                if (pantallaActualIndex != 6) {
                    dialogoActual = -1;
                    pantallaActualIndex++;
                    pantallaActual = "pantalla" + pantallaActualIndex;
                } else {
                    System.out.println("Historia Terminada!");
                    dialogoActual = 0;
                    pantallaActualIndex = 1;
                    pantallaActual = "pantalla1";
                    frase = "";
                    personaje = "";
                    setNuevoEscenario("/recursos/assets/imagenes/backgrounds/p1_bg_1.jpg");
                    referenciaJuego.ventanaPrincipal.cambiarAPantalla("MenuInicio");
                }
            }
        }
    }

    public JPanel createIconPanel() {
        // Crear un panel para contener los íconos de inicio y salida
        JPanel iconPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        iconPanel.add(homeIconLabel);
        iconPanel.add(exitIconLabel);
        iconPanel.setOpaque(false);
        return iconPanel;
    }

    public void setDialogCharacterImage(String personaje) {
        String characterImagePath = "/recursos/assets/imagenes/personajes/" + personaje + ".png";
        // Crear y configurar la imagen del personaje
        ImageIcon characterImageIcon = new ImageIcon(getClass().getResource(characterImagePath));
        // Redimensionar la imagen
        Image characterImage = characterImageIcon.getImage().getScaledInstance(90, 90, Image.SCALE_DEFAULT);
        characterImageIcon = new ImageIcon(characterImage);
        characterImageLabel.setIcon(characterImageIcon);
    }

    public void setDialogText(String dialogText) {
        dialogTextPane.setText(dialogText);
        StyledDocument doc = dialogTextPane.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(0, doc.getLength(), center, false);
    }

    public void setNuevoEscenario(String pathNuevoEscenario) {
        Escenario escenarioActual = referenciaJuego.getEscenarioActual();
        escenarioActual.removePanel(DialogPanel.this);
        escenarioActual.setImageBackground(pathNuevoEscenario);
        // Vuelve a agregar el panel de iconos al escenario
        referenciaJuego.getEscenarioActual().addPanel(iconPanel, BorderLayout.EAST);
        escenarioActual.addPanel(DialogPanel.this, BorderLayout.SOUTH);
    }

    public void playAudio(final String audioPath) {
        // Detener el audio en curso antes de iniciar uno nuevo
        stopAudio();

        // El resto del método playAudio() existente ...
        SwingWorker<Void, Void> audioWorker = new SwingWorker<>() {
            @Override
            protected Void doInBackground() throws Exception {
                try {
                    URL url = getClass().getResource(audioPath);
                    InputStream is = url.openStream();
                    BufferedInputStream bis = new BufferedInputStream(is);
                    // Almacenar el Player en la variable de instancia audioPlayer
                    audioPlayer = new Player(bis);
                    audioPlayer.play();
                } catch (JavaLayerException | IOException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void done() {
                // Libera la referencia al Player después de que termine de reproducir el audio
                audioPlayer = null;
            }
        };

        audioWorker.execute();
    }

    public void stopAudio() {
        // Si hay un audio en curso, detenerlo y liberar recursos
        if (audioPlayer != null) {
            audioPlayer.close();
            audioPlayer = null;
        }
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

        CustomDialog customDialog = new CustomDialog();
        Object[] options = {"Sí, salir", "No, quedarme"};  // Personalizar textos de los botones

        int confirm = JOptionPane.showOptionDialog(
                null,
                customDialog,
                "Solicitud de Confirmación",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null, options, options[1]);
        if (confirm == JOptionPane.YES_OPTION) {
            System.exit(0);
        }

        // Restaura los colores antiguos
        UIManager.put("OptionPane.background", oldBackgroundColor);
        UIManager.put("Panel.background", oldBackgroundColor);
        UIManager.put("Button.background", oldButtonColor);
    }

}

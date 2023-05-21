package main.java.com.game.gui;

import main.java.com.game.core.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.border.EmptyBorder;
import javax.swing.event.MouseInputAdapter;
import javax.swing.text.Caret;
import javax.swing.text.Highlighter;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import org.json.simple.JSONObject;

public class DialogPanel extends JPanel {

    private JLabel characterImageLabel;
    private JTextPane dialogTextPane;
    private JLabel clickIconLabel;
    private Map<String, ArrayList<JSONObject>> dialogos;
    private int dialogoActual = 0;
    private int pantallaActualIndex = 1;
    private String pantallaActual = "pantalla" + pantallaActualIndex;
    private Image backgroundImage;
    private Juego referenciaJuego;
    private String backgroundEscenarioActual;
    private String nuevoEscenarioPath;
    private String idiomaConfigurado = (String) GameSettings.getLanguage();

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
    }

//    // Sobrescribimos paintComponent para dibujar la imagen de fondo
//    @Override
//    protected void paintComponent(Graphics g) {
//        super.paintComponent(g);
//        if (backgroundImage != null) {
//            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
//        }
//    }
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
                    //Si está activa la opción de narracion por voz
                    if(GameSettings.isVoiceNarrationEnabled()) {
                        String narracion = (String) dialogo.get("narracion");
                        if(narracion != ""){
                        String pathAudio = "/recursos/assets/audio/narraciones/" + idiomaConfigurado + "/" + narracion + ".mp3";
                            System.out.println(pathAudio);
                        playAudio(pathAudio);
                        }
                    }

                    String personaje = (String) dialogo.get("personaje");
                    String backgroundName = (String) dialogo.get("background");
                    nuevoEscenarioPath = "/recursos/assets/imagenes/backgrounds/" + backgroundName + ".jpg";
                    String frase;
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
                }
            }
        }
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
        escenarioActual.addPanel(DialogPanel.this, BorderLayout.SOUTH);
    }
    
    
    public void playAudio(String audioPath) {
        Clip audioClip;
        try {
            // Abrir el archivo de audio
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(getClass().getResource(audioPath));

            // Obtener un clip de audio y cargarlo con el audio del stream
            audioClip = AudioSystem.getClip();
            audioClip.open(audioStream);

            // Reproducir el clip
            audioClip.start();
            // Para que el audio se repita continuamente
            //audioClip.loop(Clip.LOOP_CONTINUOUSLY);

        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }
}

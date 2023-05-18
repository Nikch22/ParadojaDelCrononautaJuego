package main.java.com.game.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

public class DialogPanel extends JPanel {
    private JLabel characterImageLabel;
    private JTextPane dialogTextPane;
    private JLabel clickIconLabel;
    private String[] dialogos;
    private int dialogoActual = 0;
    private Image backgroundImage;

   public DialogPanel(String[] dialogos) {
       this.dialogos = dialogos;
        // Asegúrate de cambiar la ruta a tu imagen de fondo real
        String backgroundImagePath = "/recursos/assets/imagenes/dialogos_1.png";
        backgroundImage = new ImageIcon(getClass().getResource(backgroundImagePath)).getImage();

        String characterImagePath = "/recursos/assets/imagenes/personajes/anciano.png";
        String initialDialogText = dialogos[0];
        String clickIconPath = "/recursos/assets/imagenes/iconos/click_claro.png";       

        // Cambiando el layout del panel
        setLayout(new BorderLayout());
        setBackground(Color.BLACK);
        
        setPreferredSize(new Dimension(Integer.MAX_VALUE,170));

        // Crear y configurar la imagen del personaje
        ImageIcon characterImageIcon = new ImageIcon(getClass().getResource(characterImagePath));
        // Redimensionar la imagen
        Image characterImage = characterImageIcon.getImage().getScaledInstance(90, 90 ,Image.SCALE_DEFAULT);
        characterImageIcon = new ImageIcon(characterImage);
        characterImageLabel = new JLabel(characterImageIcon);
        
        // Create a panel for the characterImageLabel and place it to the WEST
        JPanel characterImagePanel = new JPanel();
        characterImagePanel.setLayout(new BorderLayout());
        characterImagePanel.add(characterImageLabel, BorderLayout.NORTH);
        characterImagePanel.setOpaque(false);

        // Crear y configurar el área de texto de diálogo
        dialogTextPane = new JTextPane();
        dialogTextPane.setText(initialDialogText);

        // Configuración de la caja de texto
        dialogTextPane.setEditable(false);
        dialogTextPane.setFont(new Font("Oswald", Font.BOLD, 32)); 
        dialogTextPane.setForeground(Color.LIGHT_GRAY); 
        dialogTextPane.setOpaque(false);
        dialogTextPane.setMinimumSize(new Dimension(500, 60));
        dialogTextPane.setMaximumSize(new Dimension(Integer.MAX_VALUE, 60)); // Añadido para permitir la expansión horizontal
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
        northPanel.setOpaque(false);// set the background color
        add(northPanel, BorderLayout.NORTH);

        // Crear un panel para clickIconLabel y agregarlo al sur
        JPanel southPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        southPanel.add(clickIconLabel);
        southPanel.setOpaque(false);
        add(southPanel, BorderLayout.SOUTH);

        // Agregar un MouseListener al panel para detectar clics
        addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                // Aquí puedes agregar la lógica para cambiar el diálogo cuando el panel es clickeado
                dialogoActual++;
                if (dialogoActual < dialogos.length) {
                    setDialogText(dialogos[dialogoActual]);
                }
            }
        });
    }
   
    // Sobrescribimos paintComponent para dibujar la imagen de fondo
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }

    public void setDialogText(String dialogText) {
        dialogTextPane.setText(dialogText);
        StyledDocument doc = dialogTextPane.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(0, doc.getLength(), center, false);
    }
}

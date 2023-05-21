package main.java.com.game.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import main.java.com.game.core.*;
import main.java.com.game.common.Menu;

/**
 *
 * @author huals
 */
public class MenuConfiguracion extends Menu {

    private GameWindow ventanaPrincipal;
    private String idiomaSeleccionado;
    private boolean voces;

    public MenuConfiguracion(GameWindow ventanaPrincipal) {
        this.ventanaPrincipal = ventanaPrincipal;
        setBackground("/recursos/assets/imagenes/backgrounds/configuracion.jpg");
        
        getButtonPanel().setLayout(new BoxLayout(getButtonPanel(), BoxLayout.Y_AXIS));

        JLabel titulo = new JLabel("CONFIGURACIONES", SwingConstants.CENTER);
        titulo.setForeground(new Color(250, 250, 240, 200));
        titulo.setFont(new Font("Oswald", Font.BOLD, 34));

        ImageIcon originalIcon = new ImageIcon(getClass().getClassLoader().getResource("recursos/assets/imagenes/iconos/casa_blanco.png"));
        ImageIcon homeIcon = new ImageIcon(originalIcon.getImage().getScaledInstance(63, 63, Image.SCALE_SMOOTH));
        JButton homeButton = new JButton(homeIcon);
        homeButton.setBorderPainted(false);
        homeButton.setContentAreaFilled(false);
        homeButton.setFocusPainted(false);
        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ventanaPrincipal.cambiarAPantalla("MenuInicio");
            }
        });

        JPanel upperPanel = new JPanel(new GridBagLayout());
        upperPanel.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        upperPanel.add(titulo, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 0;
        gbc.anchor = GridBagConstraints.NORTHEAST;
        upperPanel.add(homeButton, gbc);

        getButtonPanel().add(upperPanel);

        addSpace(70);

        JLabel labelNarracion = new JLabel("Narración por Voz");
        labelNarracion.setForeground(new Color(250, 250, 240));
        labelNarracion.setFont(new Font("Oswald", Font.BOLD, 26));

        JCheckBox checkBoxNarracion = new JCheckBox("Activar voces");
        checkBoxNarracion.setFont(new Font("Oswald", Font.BOLD, 22));
        checkBoxNarracion.setOpaque(false);
        checkBoxNarracion.setForeground(new Color(220, 194, 184));
        checkBoxNarracion.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange() == ItemEvent.SELECTED) { 
                    voces = !voces;
                    activarNarracionPorVoz();
                }
            }
        });

        JPanel panelTituloNarracion = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelTituloNarracion.setOpaque(false);
        panelTituloNarracion.add(labelNarracion);

        JPanel panelCheckBoxNarracion = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelCheckBoxNarracion.setOpaque(false);
        panelCheckBoxNarracion.add(checkBoxNarracion);

        getButtonPanel().add(panelTituloNarracion);
        getButtonPanel().add(panelCheckBoxNarracion);

        addSpace(35);

        JLabel labelIdioma = new JLabel("Idioma");
        labelIdioma.setForeground(new Color(250, 250, 240));
        labelIdioma.setFont(new Font("Oswald", Font.BOLD, 26));

        ImageIcon iconEsp = new ImageIcon(new ImageIcon(getClass().getClassLoader().getResource("recursos/assets/imagenes/iconos/bandera_es_1.png")).getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
        JButton buttonEsp = new JButton("Español", iconEsp);
        buttonEsp.setHorizontalTextPosition(JButton.CENTER);
        buttonEsp.setVerticalTextPosition(JButton.BOTTOM);
        buttonEsp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                idiomaSeleccionado = "es";
                cambiarIdioma(idiomaSeleccionado);
            }
        });

        ImageIcon iconIng = new ImageIcon(new ImageIcon(getClass().getClassLoader().getResource("recursos/assets/imagenes/iconos/bandera_en_1.png")).getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
        JButton buttonIng = new JButton("Inglés", iconIng);
        buttonIng.setHorizontalTextPosition(JButton.CENTER);
        buttonIng.setVerticalTextPosition(JButton.BOTTOM);
        buttonIng.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                idiomaSeleccionado = "en";
                cambiarIdioma(idiomaSeleccionado);
            }
        });

        JPanel panelTituloIdioma = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelTituloIdioma.setOpaque(false);
        panelTituloIdioma.add(labelIdioma);

        JPanel panelOpcionesIdioma = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelOpcionesIdioma.setOpaque(false);
        panelOpcionesIdioma.add(buttonEsp);
        panelOpcionesIdioma.add(buttonIng);

        getButtonPanel().add(panelTituloIdioma);
        getButtonPanel().add(panelOpcionesIdioma);
        
        getButtonPanel().add(Box.createVerticalGlue()); 
    }

    private void cambiarIdioma(String idioma) {
        System.out.println("Idioma seleccionado: " + idioma);
        // Cambiar el idioma
        GameSettings.setLanguage(idioma);
        MenuInicio menuInicio = ventanaPrincipal.getMenuInicio();
        menuInicio.setBackground("/recursos/assets/imagenes/backgrounds/portada_"+ idioma +".png");
        menuInicio = new MenuInicio(ventanaPrincipal);
        ventanaPrincipal.getMainPanel().remove(1);
        ventanaPrincipal.getMainPanel().add(menuInicio.getBackgroundPanel(), "MenuInicio");
        menuInicio.revalidate();
        menuInicio.repaint();
        
        ventanaPrincipal.cambiarAPantalla("MenuInicio");
    }

    private void ajustarVolumen() {
        System.out.println("Ajustar Volumen seleccionado.");
        // Aquí puedes agregar la lógica para Ajustar Volumen
    }

    private void activarNarracionPorVoz() {
        System.out.println("Activar Narración por Voz seleccionado.");
        // Activar Narración por Voz
        GameSettings.setVoiceNarrationEnabled(voces);
    }

}

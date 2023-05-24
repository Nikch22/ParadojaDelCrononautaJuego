/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.java.com.game.minijuegos;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import main.java.com.game.gui.GameWindow;

import main.java.com.game.utils.Mj2Listener;

/**
 *
 * @author Mar
 */
public class Minijuego2 extends JPanel{
    int n;
    public GameWindow ventanaPrincipal;
    
    public Minijuego2(GameWindow ventanaPrincipal){
        this.n = 0;
        this.ventanaPrincipal = ventanaPrincipal; // Inicializar la ventana principal
    }
    
    public void inicio(){
        
        JFrame ventana = new JFrame("Vasijas");
        ventana.setExtendedState(JFrame.MAXIMIZED_BOTH); // Establece el estado de la ventana como maximizado
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Cierra la aplicación al cerrar la ventana
        ventana.setLayout(new BorderLayout()); // Utiliza un BorderLayout para organizar los componentes
        ventana.setExtendedState(JFrame.MAXIMIZED_BOTH);    
        ventana.setUndecorated(true);  // Esta línea oculta la barra de título y los bordes de la ventana
        // Evita que la ventana se redimensione
        ventana.setResizable(false);
        
        
            int numero = (int)(Math.random()*3 + 1);
            int v1 = (int)(Math.random()*3 + 1);
            int v2 = (int)(Math.random()*2);
            int v3=0;
            if((v2 == 0) && (v1 == 1)){ v2 = 2; v3 = 3; }
            else if((v2 == 0) && (v1 == 2)){ v2 = 1; v3 = 3; }
            else if((v2 == 0) && (v1 == 3)){ v2 = 1; v3 = 2; }
            else if((v2 == 1) && (v1 == 3)){ v2 = 2; v3 = 1; }
            else if((v2 == 1) && (v1 == 1)){ v2 = 3; v3 = 2; }
            else if((v2 == 1) && (v1 == 2)){ v2 = 3; v3 = 1; }
            
            int b=numero+3*n;
            int b4=v1+3*n; 
            int b5=v2+3*n;
            int b6=v3+3*n;
            
            JLabel labelFondo = new JLabel();
            labelFondo.setLayout(new BorderLayout());
            
            JPanel panelSuperior = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            // Crear los botones
            JButton menu = new JButton(new ImageIcon(System.getProperty("user.dir")+"\\src\\recursos\\assets\\imagenes\\iconos\\casa_blanco_c.png"));
            JButton salir = new JButton(new ImageIcon(System.getProperty("user.dir")+"\\src\\recursos\\assets\\imagenes\\iconos\\salida_blanco_.png"));
            menu.setOpaque(false);
            menu.setContentAreaFilled(false);
            menu.setBorderPainted(false);
            salir.setOpaque(false);
            salir.setContentAreaFilled(false);
            salir.setBorderPainted(false);
            Dimension di = new Dimension(30,30);
            menu.setPreferredSize(di);
            salir.setPreferredSize(di);
            // Agregar los botones al panel
            panelSuperior.add(menu);
            panelSuperior.add(salir);
            panelSuperior.setOpaque(false);
            
            JButton botonSuperior = new JButton(new ImageIcon(System.getProperty("user.dir")+"\\src\\recursos\\assets\\imagenes\\iconos\\play.png"));
            botonSuperior.setAlignmentX(Component.CENTER_ALIGNMENT); // Centra el botón horizontalmente
            botonSuperior.setOpaque(false);
            botonSuperior.setContentAreaFilled(false);
            botonSuperior.setBorderPainted(false);
            JLabel texto = new JLabel("<html>Ayuda a Alex a encontrar la pieza, escucha el sonido que hace y<br>selecciona el jarrón donde se encuentre",SwingConstants.CENTER);
            texto.setBackground(new Color(255,255,255,150));
            texto.setOpaque(true);
            texto.setFont(new Font("Arial", Font.BOLD, 20)); // Cambia el tamaño y el estilo de la fuente
            
            JPanel panelCentral = new JPanel(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(10, 10, 10, 10);
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.anchor = GridBagConstraints.CENTER; // Alinea el botón al centro del panel
            panelCentral.add(texto, gbc);
            gbc.gridy = 1;
            panelCentral.add(botonSuperior, gbc);
            panelCentral.setOpaque(false);
        
            //Panel botones
            JPanel panelBotones = new JPanel(new GridBagLayout()); // Panel para los botones con 2 filas y 3 columnas
            Dimension dimension = new Dimension(300,265);
            Dimension dimen = new Dimension(300,50);
            gbc.insets = new Insets(10, 10, 10, 10);

            //Creacion botones
            JButton boton = new JButton(new ImageIcon(System.getProperty("user.dir")+"\\src\\recursos\\assets\\imagenes\\sprites\\vasija_1.png"));
            JButton boton2 = new JButton(new ImageIcon(System.getProperty("user.dir")+"\\src\\recursos\\assets\\imagenes\\sprites\\vasija_2.png"));
            JButton boton3 = new JButton(new ImageIcon(System.getProperty("user.dir")+"\\src\\recursos\\assets\\imagenes\\sprites\\vasija_3.png"));
            JButton boton4 = new JButton(new ImageIcon(System.getProperty("user.dir")+"\\src\\recursos\\assets\\imagenes\\iconos\\sonido_copia.png"));
            JButton boton5 = new JButton(new ImageIcon(System.getProperty("user.dir")+"\\src\\recursos\\assets\\imagenes\\iconos\\sonido_copia.png"));
            JButton boton6 = new JButton(new ImageIcon(System.getProperty("user.dir")+"\\src\\recursos\\assets\\imagenes\\iconos\\sonido_copia.png"));
                    
            //Tamaño botones        
            boton.setPreferredSize(dimension);
            boton2.setPreferredSize(dimension);
            boton3.setPreferredSize(dimension);
            boton4.setPreferredSize(dimen);
            boton5.setPreferredSize(dimen);
            boton6.setPreferredSize(dimen);
        
            //Botones transparentes
            boton.setOpaque(false);
            boton.setContentAreaFilled(false);
            boton.setBorderPainted(false);
            boton2.setOpaque(false);
            boton2.setContentAreaFilled(false);
            boton2.setBorderPainted(false);
            boton3.setOpaque(false);
            boton3.setContentAreaFilled(false);
            boton3.setBorderPainted(false);
            boton4.setOpaque(false);
            boton4.setContentAreaFilled(false);
            boton4.setBorderPainted(false);
            boton5.setOpaque(false);
            boton5.setContentAreaFilled(false);
            boton5.setBorderPainted(false);
            boton6.setOpaque(false);
            boton6.setContentAreaFilled(false);
            boton6.setBorderPainted(false);
        
            //Accion de los botones
            
            //Sonidos
            botonSuperior.addActionListener(new Mj2Listener(System.getProperty("user.dir")+"\\src\\recursos\\assets\\audio\\sonidos\\minijuegos\\vasijas_"+b+".wav"));
            boton4.addActionListener(new Mj2Listener(System.getProperty("user.dir")+"\\src\\recursos\\assets\\audio\\sonidos\\minijuegos\\vasijas_"+b4+".wav"));
            boton5.addActionListener(new Mj2Listener(System.getProperty("user.dir")+"\\src\\recursos\\assets\\audio\\sonidos\\minijuegos\\vasijas_"+b5+".wav"));
            boton6.addActionListener(new Mj2Listener(System.getProperty("user.dir")+"\\src\\recursos\\assets\\audio\\sonidos\\minijuegos\\vasijas_"+b6+".wav"));
            
            //Seleccionamos
            final int finalV2 = v2; // Create a final variable for v3
            final int finalV3 = v3; // Create a final variable for v3
            final int finaln = n;
            boton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(numero==v1){
                        n++;
                    }
                    else if(n!=0){
                        n=0;
                    }
                    if(finaln!=2){
                        inicio();
                    }
                    else{
                        fin();
                    }
                    ventana.dispose();
                }
            });
            boton2.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (numero == finalV2) {
                        n++;
                    } else if (n != 0) {
                        n=0;
                    }
                    if(finaln!=2){
                        inicio();
                    }
                    else{
                        fin();
                    }
                    ventana.dispose();
                }
            });
            boton3.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (numero == finalV3) {
                        n++;
                    } else if (n != 0) {
                        n=0;
                    }
                    if(finaln!=2){
                        inicio();
                    }
                    else{
                        fin();
                    }
                    ventana.dispose();
                }
            });
        
            //Añadimos los botones al panel
            gbc.gridx = 0;
            gbc.gridy = 0;
            panelBotones.add(boton, gbc);
            gbc.gridx = 1;
            gbc.gridy = 0;
            panelBotones.add(boton2, gbc);
            gbc.gridx = 2;
            gbc.gridy = 0;
            panelBotones.add(boton3, gbc);
            gbc.gridx = 0;
            gbc.gridy = 1;
            panelBotones.add(boton4, gbc);
            gbc.gridx = 1;
            gbc.gridy = 1;
            panelBotones.add(boton5, gbc);
            gbc.gridx = 2;
            gbc.gridy = 1;
            panelBotones.add(boton6, gbc);
            panelBotones.setOpaque(false);

            JPanel panelRelleno = new JPanel(); // Panel vacío para añadir espacio debajo de los botones
            panelRelleno.setPreferredSize(new Dimension(1, 50));
            panelRelleno.setOpaque(false);

            JPanel panelBotonesRelleno = new JPanel();
            panelBotonesRelleno.setLayout(new BoxLayout(panelBotonesRelleno, BoxLayout.Y_AXIS));
            panelBotonesRelleno.add(panelBotones);
            panelBotonesRelleno.add(panelRelleno);
            panelBotonesRelleno.setOpaque(false);

            labelFondo.add(panelSuperior, BorderLayout.NORTH);
            labelFondo.add(panelCentral, BorderLayout.CENTER);
            labelFondo.add(panelBotonesRelleno, BorderLayout.SOUTH);
            ventana.setContentPane(labelFondo);
        
            ventana.addComponentListener(new ComponentAdapter() {
                @Override
                public void componentResized(ComponentEvent e) {
                    int width = ventana.getWidth();
                    int height = ventana.getHeight();
                    ImageIcon imagen = new ImageIcon(System.getProperty("user.dir")+"\\src\\recursos\\assets\\imagenes\\backgrounds\\minijuegos\\mj2_fondo.jpg");
                    imagen = new ImageIcon(imagen.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));
                    labelFondo.setIcon(imagen);
                }
            });

            ventana.setVisible(true); // Muestra la ventana
    }
    
    public void fin(){
        this.ventanaPrincipal.setEnabled(true);
    }
}

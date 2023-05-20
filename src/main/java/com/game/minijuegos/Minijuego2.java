package main.java.com.game.minijuegos;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import main.java.com.game.utils.Mj2Listener;
import main.java.com.game.gui.*;

/**
 *
 * @author Mar
 */
public class Minijuego2 extends JPanel{
    int n;
    //GameWindow ventanaPrincipal; 
    JPanel labelFondo = new JPanel(new BorderLayout());
    JPanel panelCentral = new JPanel(new GridBagLayout());
    JPanel panelSuperior = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    JPanel panelBotonesRelleno = new JPanel();
    
    public Minijuego2(){
        this.n = 0;
        //this.ventanaPrincipal = ventanaPrincipal; // Inicializar la ventana principal
    }
    
    public int getn(){
        return n;
    }
    
    public JPanel getBackgroundPanel(){
        return labelFondo;
    }
    
    public void inicio(){
        
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
            
            // Crear los botones
            JButton menu = new JButton(new ImageIcon(System.getProperty("user.dir")+"\\src\\recursos\\assets\\imagenes\\iconos\\casa_blanco.png"));
            JButton salir = new JButton(new ImageIcon(System.getProperty("user.dir")+"\\src\\recursos\\assets\\imagenes\\iconos\\salida_blanco.png"));
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
            
            JButton botonSuperior = new JButton(new ImageIcon(System.getProperty("user.dir")+"\\src\\recursos\\assets\\imagenes\\minijuegos\\play.png"));
            botonSuperior.setAlignmentX(Component.CENTER_ALIGNMENT); // Centra el botón horizontalmente
            botonSuperior.setOpaque(false);
            botonSuperior.setContentAreaFilled(false);
            botonSuperior.setBorderPainted(false);
            JLabel texto = new JLabel("<html>Ayuda a Alex a encontrar la pieza, escucha el sonido que hace y<br>selecciona el jarrón donde se encuentre",SwingConstants.CENTER);
            texto.setBackground(new Color(255,255,255,150));
            texto.setOpaque(true);
            texto.setFont(new Font("Arial", Font.BOLD, 20)); // Cambia el tamaño y el estilo de la fuente
            
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
            JButton boton = new JButton(new ImageIcon(System.getProperty("user.dir")+"\\src\\recursos\\assets\\imagenes\\minijuegos\\vasija_1.png"));
            JButton boton2 = new JButton(new ImageIcon(System.getProperty("user.dir")+"\\src\\recursos\\assets\\imagenes\\minijuegos\\vasija_2.png"));
            JButton boton3 = new JButton(new ImageIcon(System.getProperty("user.dir")+"\\src\\recursos\\assets\\imagenes\\minijuegos\\vasija_3.png"));
            JButton boton4 = new JButton(new ImageIcon(System.getProperty("user.dir")+"\\src\\recursos\\assets\\imagenes\\minijuegos\\sonido.png"));
            JButton boton5 = new JButton(new ImageIcon(System.getProperty("user.dir")+"\\src\\recursos\\assets\\imagenes\\minijuegos\\sonido.png"));
            JButton boton6 = new JButton(new ImageIcon(System.getProperty("user.dir")+"\\src\\recursos\\assets\\imagenes\\minijuegos\\sonido.png"));
                    
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
            botonSuperior.addActionListener(new Mj2Listener(System.getProperty("user.dir")+"\\src\\recursos\\assets\\sonidos\\vasijas_"+b+".wav"));
            boton4.addActionListener(new Mj2Listener(System.getProperty("user.dir")+"\\src\\recursos\\assets\\sonidos\\vasijas_"+b4+".wav"));
            boton5.addActionListener(new Mj2Listener(System.getProperty("user.dir")+"\\src\\recursos\\assets\\sonidos\\vasijas_"+b5+".wav"));
            boton6.addActionListener(new Mj2Listener(System.getProperty("user.dir")+"\\src\\recursos\\assets\\sonidos\\vasijas_"+b6+".wav"));
            
            //Seleccionamos
            final int finalV2 = v2; // Create a final variable for v3
            final int finalV3 = v3; // Create a final variable for v3
            boton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(numero==v1){
                        n++;
                    }
                    else if(n!=0){
                        n--;
                    }
                    inicio();
                }
            });
            boton2.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                if (numero == finalV2) {
                    n++;
                } else if (n != 0) {
                    n--;
                }
                inicio();
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
                    inicio();
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

            panelBotonesRelleno.setLayout(new BoxLayout(panelBotonesRelleno, BoxLayout.Y_AXIS));
            panelBotonesRelleno.add(panelBotones);
            panelBotonesRelleno.add(panelRelleno);
            panelBotonesRelleno.setOpaque(false);
            
            ////////
            labelFondo.add(panelSuperior, BorderLayout.NORTH);
            labelFondo.add(panelCentral, BorderLayout.CENTER);
            labelFondo.add(panelBotonesRelleno, BorderLayout.SOUTH);
            //////////
    }
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.java.com.game.utils;

import java.awt.event.*;
import java.io.*;
import javax.sound.sampled.*;

/**
 *
 * @author HP ELITEBOOK
 */
public class Mj2Listener implements ActionListener {
    
    String name;
    
    public Mj2Listener(String n) {
        this.name = n;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        File soundFile = new File(name);
        try {
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.start();
            System.out.println(name);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException a) {
            a.printStackTrace();
        }
    }
}

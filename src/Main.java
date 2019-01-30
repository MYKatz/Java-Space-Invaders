import java.io.File;
import java.awt.event.ActionListener; 
import java.awt.event.ActionEvent;
import javax.swing.Timer;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JFrame;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;

import javax.swing.JPanel;

public class Main extends JFrame {

    public Main()
    {	
    	Timer timer;
    	int Xbound = 1000;
    	int Ybound = 1000;
    	Board b = new Board();
    	add(b);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(Xbound,Ybound);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
        
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("img\\videoplayback.wav").getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch(Exception ex) {
            System.out.println("Error with playing sound.");
            ex.printStackTrace();
        }
        
        ActionListener updatescore = new ActionListener(){
        	public void actionPerformed(ActionEvent evt){
        		setTitle("Score: " + b.getScore());
        	}
        };
        
        timer = new Timer(100,updatescore);
        timer.start();
        
        
    }
     
    public static void main(String[] args) {
    	new Main();
    }
}
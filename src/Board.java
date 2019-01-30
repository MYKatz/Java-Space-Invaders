import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import java.awt.Color;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.Timer;
import java.awt.event.*;
import java.util.*;
import java.awt.Font;
import java.math.*;


public class Board extends JPanel implements KeyListener {
	long bulletTime = 0;
	boolean gamestarted = false;
	int level = -1;
	int levellimit = 4;
	int score = 0;
	bossShip boss;
	Background bgrnd = new Background();
	BufferedImage[] bgs = new BufferedImage[levellimit+2];
	alien[][] aliens = new alien[3][10];
	alien b = new alien(50,70,10,1);
	Ship s;
	Timer t;
	ArrayList<Character> keyspressed = new ArrayList<Character>();
	ArrayList<Bullet> bullets = new ArrayList<Bullet>();
	ArrayList<alienBullet> alienbullets = new ArrayList<alienBullet>();
	ArrayList<alienbullet2> bossbullets = new ArrayList<alienbullet2>();
	Random r = new Random();
	double bosschance = 1;
	public Board() {
		
		
		//Setting up Backgrounds//
		try {
			bgs[0] = ImageIO.read(new File("img\\backgrounds\\windowsxp.jpg"));
			bgs[1] = ImageIO.read(new File("img\\backgrounds\\awildappeared.png"));
			bgs[2] = ImageIO.read(new File("img\\backgrounds\\euparl.jpg"));
			bgs[3] = ImageIO.read(new File("img\\backgrounds\\smallworld.jpg"));
			bgs[4] = ImageIO.read(new File("img\\backgrounds\\finalstarscool.jpg"));
			bgs[5] = ImageIO.read(new File("img\\backgrounds\\black.png"));
		} catch (IOException e) {
			
		}
		
		
		
		addKeyListener(this);
			setFocusable(true);
		
		ActionListener sl = new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				
				if(gamestarted){
					bgrnd.setImage(bgs[level]);
				}
				
				if(level >= 4){
					boss.move();
					if(r.nextInt(500) <= Math.floor(bosschance)){
						for(int i = 0;i<4;i++){
							bossbullets.add(new alienbullet2(boss.getX()-150+175*i,boss.getY()+250,r.nextInt(9)+1,1)); 
							bosschance =1.0;
						}
					
						
					}
					else{
						if(bosschance <= 10){
							bosschance = bosschance + 0.1;
						}
						
					}
					
					for(int j=0;j<bossbullets.size();j++){
						bossbullets.get(j).move();
						if(bossbullets.get(j).intersects(s)){
							JOptionPane.showMessageDialog(null, "Game Over!");
							System.exit(0);
						}
					}
					
					for(int i=0;i<bullets.size();i++){
						if(bullets.get(i).intersects(boss)){
							bullets.remove(i);
							boss.takeDmg();
						}
					}
					
				}
				
				
				for(int l=0;l<aliens.length;l++) {
					for(int p=0;p<aliens[l].length;p++) {
						
						
					
						if(aliens[l][p] != null){
							aliens[l][p].move();
							if(r.nextInt(200) == 1 && alienbullets.size() < 4){
								alienbullets.add(new alienBullet(aliens[l][p].getX()+50,aliens[l][p].getY()+90,10,1));
							}
							
							for(int i=0;i<bullets.size();i++) {
								
								if(aliens[l][p] != null){
									
								
								if(bullets.get(i).intersects(aliens[l][p])){
									bullets.remove(i);
									aliens[l][p] = null;
									score = score + 25;
								}}
								
								else if(bullets.get(i).getY() < -100){
									bullets.remove(i);
								}
							}
						}
						
						
					}
				}
				
				for(int u=0; u<alienbullets.size();u++){
					alienbullets.get(u).move();
					
					if(alienbullets.get(u).getY() > 1000){
						alienbullets.remove(u);
					}
					
					else if(alienbullets.get(u).intersects(s)){
						JOptionPane.showMessageDialog(null, "Game Over!");
						System.exit(0);
					}
				}
				
				if(isAliens() == false && level <= levellimit && level != -1){
					level = level + 1;
					spawnaliens(level + 1);
				
				}
				
				
				repaint();
				if(keyspressed.contains("a".charAt(0)) && keyspressed.contains("d".charAt(0))){
					if(keyspressed.get(1) == "a".charAt(0)) {
						s.setDirection(-1);
						s.move();
						
					}
					if(keyspressed.get(1) == "d".charAt(0)) {
						s.setDirection(1);
						s.move();
						
					}
				}
				else if(keyspressed.contains("a".charAt(0))) {
					s.setDirection(-1);
					s.move();
				}
				else if(keyspressed.contains("d".charAt(0))){
					s.setDirection(1);
					s.move();
				}
				for(int i=0;i<bullets.size();i++) {
					bullets.get(i).move();
				}
				
				
			}
		};
		t = new Timer(10,sl);
		t.start();
	}
	
	public void spawnaliens(int rows){
		aliens = new alien[rows][10];
		if(rows <= levellimit){
			
		
		for(int l=0;l<aliens.length;l++) {
			for(int p=0;p<aliens[l].length;p++) {
				if(l % 2 == 1) {
				aliens[l][p] = new alien(1+p*100,1+l*100,2,-1);
				}
				else{
				aliens[l][p] = new alien(1+p*100,1+l*100,2,1);
				}
			}
			}
		}
		
		else{
			boss = new bossShip();
		}
	}
	
	public boolean isAliens(){
		for(alien[] al:aliens){
			for(alien a: al){
				if(a != null && a.getY()<1000) {return true;}
			}
		}
		return false;
	}
	
	public void paint(Graphics g) {
		g.setColor(Color.white);
		g.fillRect(0, 0, getWidth(), getHeight());
		g.drawImage(bgrnd.getImage(), bgrnd.getX(), bgrnd.getY(), this);
	if(gamestarted == false){
		g.setFont(new Font("TimesRoman", Font.PLAIN, 56));
		g.drawString("Welcome to \"Face Invaders\"",100 , 100);
		g.drawString("Press 'T', 'O', or 'M' to choose a class", 100, 250);
		g.drawString("T: Well rounded", 200, 350);
		g.drawString("O: Fast Reload, Slow movement ", 200, 450);
		g.drawString("M: Slow Reload, Fast movement", 200, 550);
	}
	else{
		
		if(level >= levellimit){
			g.setFont(new Font("TimesRoman", Font.PLAIN, 32));
			g.drawImage(boss.getImage(), boss.getX() , boss.getY(), this);
				for(int i=0;i<bossbullets.size();i++){
					g.drawImage(bossbullets.get(i).getImage(), bossbullets.get(i).getX(), bossbullets.get(i).getY(), this);
				}
			g.drawString("Health: "+boss.getHealth(),400,20);
		}
		
		for(int l=0;l<aliens.length;l++) {
			for(int p=0;p<aliens[l].length;p++) {
				if(aliens[l][p] != null){
					
				
				g.drawImage(aliens[l][p].getImage(), aliens[l][p].getX(), aliens[l][p].getY(), this);
				}
			}
		}
		
		g.drawImage(s.getImage(), s.getX(), s.getY(), this);
		
		for(int i=0; i<bullets.size();i++) {
			g.drawImage(bullets.get(i).getImage(), bullets.get(i).getX(), bullets.get(i).getY(), this);
		}
		
		for(int u=0; u<alienbullets.size();u++){
			g.drawImage(alienbullets.get(u).getImage(), alienbullets.get(u).getX(), alienbullets.get(u).getY(), this);
		}
		
	}	
	}
	public void keyTyped(KeyEvent e) {}

	public void keyPressed(KeyEvent e) {
		if(e.getKeyChar() == "a".charAt(0)){
			if(!keyspressed.contains("a".charAt(0))) {
				keyspressed.add("a".charAt(0));
			}
		}
		else if(e.getKeyChar() == "d".charAt(0)) {
			if(!keyspressed.contains("d".charAt(0))) {
				keyspressed.add("d".charAt(0));
			}
		}
		
		if(e.getKeyChar() == "f".charAt(0)) {
			if(System.currentTimeMillis() - bulletTime > s.bulletDelay()) {
				bullets.add(new Bullet(s.getX()+21,s.getY()-40,10,-1));
				bulletTime = System.currentTimeMillis();
			}
		}
		if(e.getKeyChar() == "m".charAt(0)){
			if(gamestarted ==  false){
				s = new Macron();
				try {
					s.setImage(ImageIO.read(new File("img\\macron.png")));
				} catch(IOException ex) {}
				spawnaliens(1);
				gamestarted = true;
			}
			level++;
		}
		if(e.getKeyChar() == "o".charAt(0)){
			s = new Obama();
			if(gamestarted ==  false){
				try {
					s.setImage(ImageIO.read(new File("img\\obama.png")));
				} catch(IOException ex) {}
				spawnaliens(1);
				gamestarted = true;
			}
			level++;
		}
		if(e.getKeyChar() == "t".charAt(0)){
			s = new Trudeau();
			if(gamestarted ==  false){
				try {
					s.setImage(ImageIO.read(new File("img\\trudeau.png")));
				} catch(IOException ex) {}
				spawnaliens(1);
				gamestarted = true;
			}
			level++;
		}
		
		if(e.getKeyChar() == "i".charAt(0)){
			level = 3;
		}
		
		repaint();
		System.out.println(keyspressed);
		
	}
	public void keyReleased(KeyEvent e) {
		
		if(keyspressed.contains(e.getKeyChar())){
			keyspressed.remove(keyspressed.indexOf(e.getKeyChar()));
		}
	}
	
	public int getScore(){
		return score;
	}
}

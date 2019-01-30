
	import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

import java.awt.image.BufferedImage; 
	import java.io.File; 
	import java.io.IOException;
	import java.util.Random;

	public class bossShip extends Sprite {
		private int health = 100;
		Random r = new Random();
		
		public bossShip() {
			super(400,100,15,1);
			try {
					 setImage(ImageIO.read(new File("img\\Trump1.png")));
				
				
			} catch(IOException ex) {}
			
		}
		
		public void takeDmg(){
			health = health -4;
			if(health <= 0){
				JOptionPane.showMessageDialog(null, "YOU WIN!");
				System.exit(0);
			}
		}
		
		public int getHealth(){
			return health;
		}

		
		public void move() {
			if(xpos <= 0 || xpos > 750) {
				
				ypos = ypos + r.nextInt(7) - 3;
				direction = direction * -1;
			}
			
			xpos = xpos + speed*direction;
			

		}
	}



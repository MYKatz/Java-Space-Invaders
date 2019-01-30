import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Bullet extends Sprite {

		
		public Bullet(int x, int y, int s, int d) {
			super(x,y,s,d);
			try {
				super.setImage(ImageIO.read(new File("img\\bullet.png")));
			} catch(IOException ex) {}
		}

		public void move() {
			ypos = ypos - speed;

		}
		public boolean intersects(alien a) {
			if(xpos >= a.getX() && xpos <= a.getX() + 100){
				if(ypos >= a.getY() && ypos <= a.getY()+100){
					return true;
				}
				
			}
			return false;
		}
		
		
		public boolean intersects(bossShip b){
			if(xpos >= b.getX() && xpos <= b.getX() + 250){
				if(ypos >= b.getY() && ypos <= b.getY()+250){
					return true;
				}
				
			}
			return false;
		}
}

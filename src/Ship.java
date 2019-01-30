import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO; 


public abstract class Ship extends Sprite {
	
		
		public Ship(int x, int y, int s, int d) {
			super(x,y,s,d);
			try {
				super.setImage(ImageIO.read(new File("img\\macron.png")));
			} catch(IOException ex) {}
		}
		
		public abstract int bulletDelay();
		
		public void setDirection(int xm){
			direction = xm;
		}
		public abstract void move();
}

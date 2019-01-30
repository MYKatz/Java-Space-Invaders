import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class alienBullet extends Sprite {

		
		public alienBullet(int x, int y, int s, int d) {
			super(x,y,s,d);
			try {
				super.setImage(ImageIO.read(new File("img\\UKIP.png")));
			} catch(IOException ex) {}
		}

		public void move() {
			ypos = ypos + speed;

		}
		public boolean intersects(Ship s) {
			if(xpos >= s.getX()+10 && xpos <= s.getX() + 90){
				if(ypos >= s.getY()+10 && ypos <= s.getY()+90){
					return true;
				}
				
			}
			return false;
		}
}

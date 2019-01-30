import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO; 


public class Background {
	//private Bomb bomb;
		private BufferedImage img;
		private int xpos;
		private int ypos;
		
		public Background() {
			xpos = 0; ypos = 0;
			try {
				img = ImageIO.read(new File("img\\backgrounds\\black.png"));
			} catch(IOException ex) {}
		}
		public int getX(){
			return xpos;
		}
		
		public void setImage(BufferedImage i){
			img = i;
		}
		
		
		public int getY(){
			return ypos;
		}
		public BufferedImage getImage(){
			return img;
		}
}

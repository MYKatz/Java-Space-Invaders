import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Macron extends Ship {

			
			public Macron() {
				super(500,800,20,1);
				try {
					super.setImage(ImageIO.read(new File("img\\macron.png")));
				} catch(IOException ex) {}
			}
			
			public int bulletDelay(){
				return 500;
			}
			
			public void setDirection(int xm){
				direction = xm;
			}
			public void move() {
			if(xpos + speed*direction > -90 && xpos+speed*direction < 1000){	
			xpos = xpos + speed*direction;
			}
			
			if(direction == 1){
				try {
					super.setImage(ImageIO.read(new File("img\\macron.png")));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else{
				try {
					super.setImage(ImageIO.read(new File("img\\macron2.png")));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			}
}

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Trudeau extends Ship {

			
			public Trudeau() {
				super(500,800,13,1);
				try {
					super.setImage(ImageIO.read(new File("img\\macron.png")));
				} catch(IOException ex) {}
			}
			
			public int bulletDelay(){
				return 350;
			}
			
			public void setDirection(int xm){
				direction = xm;
			}
			public void move() {
			if(xpos + speed*direction > 0 && xpos+speed*direction < 900){	
			xpos = xpos + speed*direction;
			}
			if(direction == 1){
				try {
					super.setImage(ImageIO.read(new File("img\\trudeau.png")));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else{
				try {
					super.setImage(ImageIO.read(new File("img\\trudeau2.png")));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			}
}
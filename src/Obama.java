import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Obama extends Ship {

			
			public Obama() {
				super(500,800,3,1);
				try {
					super.setImage(ImageIO.read(new File("img\\macron.png")));
				} catch(IOException ex) {}
			}
			
			public int bulletDelay(){
				return 200;
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
					super.setImage(ImageIO.read(new File("img\\obama.png")));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else{
				try {
					super.setImage(ImageIO.read(new File("img\\obama2.png")));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			}
}

import javax.imageio.ImageIO; 
import java.awt.image.BufferedImage; 
import java.io.File; 
import java.io.IOException;
import java.util.Random;

public class alien extends Sprite {
	Random rand = new Random();
	
	public alien(int x, int y, int s, int d) {
		super(x,y,s,d);
		int spritenum = rand.nextInt(3);
		try {
			if(spritenum == 0){
				 setImage(ImageIO.read(new File("img\\boris.png")));
			}
			else if(spritenum == 1){
				setImage(ImageIO.read(new File("img\\nigel.png")));
			}
			else{
				setImage(ImageIO.read(new File("img\\paul.png")));
			}
			
		} catch(IOException ex) {}
		
	}


	
	public void move() {
		if(xpos <= 0 || xpos > 900) {
			
			ypos = ypos + 100;
			direction = direction * -1;
		}
		
		xpos = xpos + speed*direction;

	}
}

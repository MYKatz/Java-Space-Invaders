import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

public abstract class Sprite {
	//private Bomb bomb;
	private BufferedImage img;
	public int xpos;
	public int ypos;
	public int direction;
	public int speed;
	
	public Sprite(int x, int y, int s, int d) {
		xpos = x; ypos = y; speed = s; direction = d;
		
	}
	public void setImage(BufferedImage i){
		img = i;
	}
	
	public int getX(){
		return xpos;
	}
	public int getY(){
		return ypos;
	}
	public BufferedImage getImage(){
		return img;
	}
	public abstract void move();
}

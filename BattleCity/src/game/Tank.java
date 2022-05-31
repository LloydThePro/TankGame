package game;



import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Tank{
	
	private static final int width = 45, height = 45;
	public float xPos = 50.0f, yPos = 50.0f;
	private BufferedImage texture[];
	private float fSpeed = 6f;
	public static enum DIR{
		UP, DOWN, LEFT, RIGTH
	};
	private DIR currentDir;
	Tank(){
		currentDir = DIR.UP;
		texture = new BufferedImage[4];
	}
	
	
	public void moveTank(DIR dir) {
		
		currentDir = dir;
		switch(dir) {
		case UP:
			yPos -= fSpeed;
			break;
		case DOWN:
			yPos += fSpeed;
			break;
		case LEFT:
			xPos -= fSpeed;
			break;
		case RIGTH:
			xPos += fSpeed;
			break;
		}
	}
	
	public void drawTank(Graphics g) {
		
		switch(currentDir) {
		case UP:
			g.drawImage(texture[0],(int)xPos, (int)yPos, 50, 50, null);
			break;
		case DOWN:
			g.drawImage(texture[1],(int)xPos, (int)yPos, 50, 50, null);
			break;
		case LEFT:
			g.drawImage(texture[2],(int)xPos, (int)yPos, 50, 50, null);
			break;
		case RIGTH:
			g.drawImage(texture[3],(int)xPos, (int)yPos, 50, 50, null);
			break;
		}
		
		//g.drawImage(texture,(int)xPos, (int)yPos, 50, 50, null);
	}
	
	public void loadImage() {
		try {
			texture[0] = ImageIO.read(getClass().getResource("/assets/tank.png"));
			texture[1] = ImageIO.read(getClass().getResource("/assets/tank_down.png"));
			texture[2] = ImageIO.read(getClass().getResource("/assets/tank_left.png"));
			texture[3] = ImageIO.read(getClass().getResource("/assets/tank_right.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void shootTank() {
		
	}

	
	
	
}

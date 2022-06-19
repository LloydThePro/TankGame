package game;



import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import game.Entity;
public class Tank extends Entity{
	
	public static enum DIR{
		UP, DOWN, LEFT, RIGTH
	};
	
	public int prevX, prevY;
	private static int textureWidth, textureHeight;
	
	Tank(){
		
		
		
		yPos = 100;
		xPos = 100;
		prevX = xPos;
		prevY = yPos;
		
		entTypeID = 1;
		textureWidth = GameDisplay.width / World.chunkRow;
		textureHeight = GameDisplay.height / World.chunkCol;
		
		width = (int) ((GameDisplay.width / World.chunkRow) * 0.75);
		height =(int) ((GameDisplay.height / World.chunkCol) * 0.75);
		
		
		
		
		currentDir = Entity.DIR.UP;
		texture = new BufferedImage[4];
	}
	
	
	public void moveTank(Entity.DIR dir) {
		prevX = xPos;
		prevY = yPos;
		currentDir = dir;
		switch(dir) {
		case UP:
			yPos -= speed;
			break;
		case DOWN:
			yPos += speed;
			break;
		case LEFT:
			xPos -= speed;
			break;
		case RIGTH:
			xPos += speed;
			break;
		}
		
		
	}
	
	public void draw(Graphics g, int width, int height) {
		
		int offsetX = (int)(textureWidth * 0.25) / 2;
		int offsetY = (int)(textureHeight * 0.25) / 2;
		
		switch(currentDir) {
		case UP:
			g.drawImage(texture[0],(int)xPos - offsetX, (int)yPos - offsetY, textureWidth, textureHeight, null);
			break;
		case DOWN:
			g.drawImage(texture[1],(int)xPos - offsetX, (int)yPos - offsetY, textureWidth, textureHeight, null);
			break;
		case LEFT:
			g.drawImage(texture[2],(int)xPos - offsetX, (int)yPos - offsetY, textureWidth, textureHeight, null);
			break;
		case RIGTH:
			g.drawImage(texture[3],(int)xPos - offsetX, (int)yPos - offsetY, textureWidth, textureHeight, null);
			break;
		}
	}
	
	public static void loadImage() {
		try {
			Tank tank = new Tank();
			
			texture[0] = ImageIO.read(tank.getClass().getResource("/assets/tank.png"));
			texture[1] = ImageIO.read(tank.getClass().getResource("/assets/tank_down.png"));
			texture[2] = ImageIO.read(tank.getClass().getResource("/assets/tank_left.png"));
			texture[3] = ImageIO.read(tank.getClass().getResource("/assets/tank_right.png"));
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	
	
	

	
	
	
}

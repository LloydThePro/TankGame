package game;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import game.Tank.DIR;

public class Entity {
	protected int xPos;
	protected int yPos;
	protected static int width, height;
	protected static BufferedImage texture[];
	protected static int entTypeID;
	protected int speed = 6;
	protected static int maxBulletCount = 2;
	
	public static enum DIR{
		UP, DOWN, LEFT, RIGTH
	};
	
	
	protected DIR currentDir;
	
	
	
	Entity(){
		
		
		entTypeID = -1;
		texture = new BufferedImage[4];
	}
	
	
	public void draw(Graphics g, int width, int height) {
		switch(currentDir) {
		case UP:
			g.drawImage(texture[0],(int)xPos, (int)yPos, this.width, this.height, null);
			break;
		case DOWN:
			g.drawImage(texture[1],(int)xPos, (int)yPos, this.width, this.height, null);
			break;
		case LEFT:
			g.drawImage(texture[2],(int)xPos, (int)yPos, this.width, this.height, null);
			break;
		case RIGTH:
			g.drawImage(texture[3],(int)xPos, (int)yPos, this.width, this.height, null);
			break;
		}
	}
	
	
	public void fireWeapon(ArrayList<Bullet> bullets) {
		int size = bullets.size();
		int counter = 0;
		for(int i = 0; i < size; i++) {
			
			Bullet bullet = bullets.get(i);
			if(bullet.getOwner() == this) {
				counter++;
			}
			
		}
		
		
		if(counter >= maxBulletCount)return;	// return when max bullets was fired
		
		Bullet bullet = new Bullet((xPos + (width/2)) - (Bullet.width / 2), (yPos + (height / 2)) - (Bullet.height / 2), currentDir, this);
		bullets.add(bullet);
		
		
	}
	
	
}

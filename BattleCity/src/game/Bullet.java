package game;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import game.Entity;


public class Bullet {
	private int x, y;
	private static BufferedImage texture[];
	private static boolean isTextureLoaded = false;
	private static final int speed = 2;
	public static int width, height;
	private boolean isActive = true;
	private Entity owner;
	Entity.DIR bulletDirection;
	
	
	Bullet(){}
	Bullet(int x, int y,Entity.DIR dir, Entity owner){
		
		int offsetX = (GameDisplay.width / World.chunkRow) / ChunkTile.tileRow;
		int offsetY = (GameDisplay.height / World.chunkCol) / ChunkTile.tileCol;
		
		float scaler = 0.55f;
		
		width = (int)(offsetX * scaler);
		height = (int)(offsetY * scaler);
		loadTexture();
		
		this.x = x;
		this.y = y;
		bulletDirection = dir;
		this.owner = owner;
		
	}
	
	
	public int getX() {return x;}
	public int getY() {return y;}
	public boolean getBulletState() {return isActive;}
	public Entity getOwner() {return owner;}
	public void setBulletState(boolean state) {
		isActive = state;
	}
	
	public void setPosAndDirection(int x, int y, Entity.DIR dir) {
		this.x = x;
		this.y = y;
		bulletDirection = dir;
	}
	
	private static void loadTexture() {
		if(isTextureLoaded)return;
		
		
		texture = new BufferedImage[4];
		try {
			Bullet bullet = new Bullet();
			texture[0] = ImageIO.read(bullet.getClass().getResource("/assets/bullet_up.png"));
			texture[1] = ImageIO.read(bullet.getClass().getResource("/assets/bullet_down.png"));
			texture[2] = ImageIO.read(bullet.getClass().getResource("/assets/bullet_left.png"));
			texture[3] = ImageIO.read(bullet.getClass().getResource("/assets/bullet_right.png"));
			
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		isTextureLoaded = true;
		
	}
	
	public void draw(Graphics g) {
		if(!isActive)return;
		if(!isTextureLoaded)return;
		
		
		switch(bulletDirection) {
			
		case UP:
			g.drawImage(texture[0], x, y, width, height, null);
			break;
		case DOWN:
			g.drawImage(texture[1], x, y, width, height, null);
			break;
			
		case LEFT:
			g.drawImage(texture[2], x, y, width, height, null);
			break;
		case RIGTH:
			g.drawImage(texture[3], x, y, width, height, null);
			break;
		}
		
		
	}
	
	public void move(int limX, int limY) {
		
		if(!isActive)return;
		
		switch(bulletDirection) {
			
		case UP:
			if(y > 0) {
				y -= speed;
			}else
				isActive = false;
			break;
		case DOWN:
			if(y < limY) {
				y += speed;
			}else
				isActive = false;
			break;
			
		case LEFT:
			if(x > 0) {
				x -= speed;
			}else
				isActive = false;
			break;
		case RIGTH:
			if(x < limX) {
				x += speed;
			}else
				isActive = false;
			break;
		}
		
	}
	
	
}

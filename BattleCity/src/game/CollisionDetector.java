package game;

import game.Entity;

class Rect{
	int x, y;
	int maxX, maxY;
	
	
	Rect(){}
	Rect(Entity a){
		x = a.xPos;
		y = a.yPos;
		maxX = (a.width + x);
		maxY = (a.height + y);
	}
	
	Rect(Bullet a){
		x = a.getX();
		y = a.getY();
		maxX = (Bullet.width + x);
		maxY = (Bullet.height + y);
	}
	
	Rect(int tilePosX, int tilePosY, int maxX, int maxY){
		
		x = tilePosX;
		y = tilePosY;
		this.maxX = tilePosX + maxX;
		this.maxY = tilePosY + maxY;
		
	}
	
	
}

public class CollisionDetector {
	
	
	public static boolean checkCollision(Entity a, Entity b) {
		
		Rect entA = new Rect(a), entB = new Rect(b);
		
		
		if(entA.maxX >= entB.x && entA.x <= entB.maxX && entA.maxY >= entB.y && entA.y <= entB.maxY)
			return true;
		
		
		return false;
	}
	
	
	
	public static boolean checkCollision(Bullet bullet, int tileX, int tileY) {
		int offsetX = (GameDisplay.width / World.chunkRow) / 2;
		int offsetY = (GameDisplay.height / World.chunkCol) / 2;
		
		Rect entA = new Rect(tileX * offsetX, tileY * offsetY, offsetX, offsetY);
		Rect entB = new Rect(bullet);
		
		
		if(entA.maxX >= entB.x && entA.x <= entB.maxX && entA.maxY >= entB.y && entA.y <= entB.maxY)
			return true;
		
		return false;
	}
	
	
	
	public static boolean checkCollision(Entity a,int tileX, int tileY) {
		
		int offsetX = (GameDisplay.width / World.chunkRow) / 2;
		int offsetY = (GameDisplay.height / World.chunkCol) / 2;
		
		
		
		Rect entA = new Rect(a);
		Rect entB = new Rect(tileX * offsetX, tileY * offsetY, offsetX, offsetY);
		
		
		if(entA.maxX >= entB.x && entA.x <= entB.maxX && entA.maxY >= entB.y && entA.y <= entB.maxY)
			return true;
		
		
		return false;
	}
	
}

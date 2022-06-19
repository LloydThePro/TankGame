package game;


import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Tile {
	
	private static BufferedImage tileTexture[] = new BufferedImage[4];
	public static enum TYPE{
		EMPTY, BRICK, WATER, FOREST, IRON
	};
	
	private TYPE tileType;
	
	Tile(){
		
		tileType = Tile.TYPE.EMPTY;
	}
	Tile(TYPE type){
		
		tileType = type;
	}
	
	public BufferedImage getTileTexture() {
		
		switch(tileType) {
		case EMPTY:
			return null;
			
		case BRICK: 
			return  tileTexture[0];
		case WATER: 
			return  tileTexture[1];
		case FOREST:
			return tileTexture[2];
		case IRON: 
			return  tileTexture[3];
		}
		return null;
		
	}
	
	public void setType(TYPE type) {
		tileType = type;
		
	}
	
	public TYPE getType() {
		return tileType;
	}
	
	public static void loadTexture() {
		new Tile().loadImage();
	}
	
	private void loadImage() {
		
		
		try {
			
			tileTexture[0] = ImageIO.read(getClass().getResource("/assets/brick.png"));
			tileTexture[1] = ImageIO.read(getClass().getResource("/assets/water.png"));
			tileTexture[2] = ImageIO.read(getClass().getResource("/assets/forrest.png"));
			tileTexture[3] = ImageIO.read(getClass().getResource("/assets/iron.png"));
			
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}

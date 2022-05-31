package game;
import game.Tile;

import java.awt.Color;
import java.awt.Graphics;

import java.io.*;


public class World {
	public static final int tileRow = 16, tileCol = 12;
	Tile worldTiles[];

	World(){
		
		loadLevel();
		
	}
	
	void loadLevel() {
		
		InputStream stream = getClass().getResourceAsStream("/level/base_level.txt");
		BufferedReader br = new BufferedReader(new InputStreamReader(stream));
		
		worldTiles = new Tile[tileRow * tileCol];
		for(int y = 0; y < tileCol; y++) {
			String line = null;
			try {
				line = br.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if(line == null)return;
			
			for(int x = 0; x < tileRow; x++) {
				switch(line.charAt(x)) {
				case '.':
					worldTiles[x + y * tileRow] = new Tile(Tile.TYPE.EMPTY);
					break;
				case '#':
					worldTiles[x + y * tileRow] = new Tile(Tile.TYPE.BRICK);
					break;
				case '*':
					worldTiles[x + y * tileRow] = new Tile(Tile.TYPE.IRON);
					break;
				case '&':
					worldTiles[x + y * tileRow] = new Tile(Tile.TYPE.WATER);
					break;
				case '%':
					worldTiles[x + y * tileRow] = new Tile(Tile.TYPE.FOREST);
					break;
				default:
					worldTiles[x + y * tileRow] = new Tile(Tile.TYPE.EMPTY);
					break;
				}
				
			}
		}
		worldTiles[0].loadImage();
	}
	
	void drawLevel(Graphics g, int width, int height) {
		
		
		int offsetX = width / tileRow;
		int offsetY = height / tileCol;
		
		for(int y = 0; y < tileCol; y++) {
			for(int x = 0; x < tileRow; x++) {
				Tile tile = worldTiles[x + y * tileRow];
				Tile.TYPE type = tile.getType();
				
				switch(tile.getType()) {
				case EMPTY:
					g.setColor(new Color(0,0,0));
					g.fillRect(x * offsetX, y * offsetY, offsetX, offsetY);
					break;
				default:
					g.drawImage(tile.getTileTexture(), x * offsetX, y * offsetY, offsetX, offsetY, null);
					break;
				
				
				}
				
				
				
			}
		}
		
		
	}
	
}

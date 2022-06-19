package game;
import game.Tile;

import java.awt.Color;
import java.awt.Graphics;

import java.io.*;
import game.ChunkTile;

public class World {
	public static final int chunkRow = 16, chunkCol = 12;
	ChunkTile chunkTiles[];
	World(){
		
		loadLevel();
		
	}
	
	void loadLevel() {
		
		
		chunkTiles = new ChunkTile[chunkRow * chunkCol];
		
		
		InputStream stream = getClass().getResourceAsStream("/level/base_level.txt");
		BufferedReader br = new BufferedReader(new InputStreamReader(stream));
		
		
		for(int y = 0; y < chunkCol; y++) {
			
			
			String line = null;
			try {
				line = br.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if(line == null)return;
			
			for(int x = 0; x < chunkRow; x++) {
				chunkTiles[x + y * chunkRow] = new ChunkTile();
				
				Tile.TYPE type = Tile.TYPE.EMPTY;
				switch(line.charAt(x)) {
				case '.':
					type = Tile.TYPE.EMPTY;
					
					break;
				case '#':
					type = Tile.TYPE.BRICK;
					
					break;
				case '*':
					type = Tile.TYPE.IRON;
					
					break;
				case '&':
					type = Tile.TYPE.WATER;
					
					break;
				case '%':
					type = Tile.TYPE.FOREST;
					break;
				default:
					type = Tile.TYPE.EMPTY;
					break;
				}
				chunkTiles[x + y * chunkRow] = new ChunkTile(type);
			}
			
			
		}
		Tile.loadTexture();
	}
	
	
	public Tile getTile(int x, int y) {
		
	
		int chunkX = x / ChunkTile.tileRow, chunkY = y / ChunkTile.tileCol;
		int tileX = x %  ChunkTile.tileRow, tileY = y %  ChunkTile.tileCol;
		
		
		return chunkTiles[chunkX + chunkY * chunkRow].getTile(tileX, tileY);
		
	}
	
	public ChunkTile getChunk(int x, int y) {
		
		int chunkX = x / ChunkTile.tileRow, chunkY = y / ChunkTile.tileCol;
		
		return chunkTiles[chunkX + chunkY * chunkRow];
	}
	
	void drawLevel(Graphics g, int width, int height) {
		
		
		int offsetX = width / chunkRow;
		int offsetY = height / chunkCol;
		
		for(int y = 0; y < chunkCol; y++) {
			for(int x = 0; x < chunkRow; x++) {
				chunkTiles[x + y * chunkRow].drawChunks(g, width, height, offsetX * x, offsetY * y);
			}
		}
		
		
	}
	
}

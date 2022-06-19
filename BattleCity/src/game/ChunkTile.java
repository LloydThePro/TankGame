package game;
import java.awt.Color;
import java.awt.Graphics;

import game.Tile;
public class ChunkTile {
	public final static int tileRow = 2, tileCol = 2;
	private	Tile[] chunk;
	
	ChunkTile(){
		chunk = new Tile[tileRow * tileCol];
		for	(int i = 0; i < chunk.length; i++) {
			chunk[i] = new Tile(Tile.TYPE.EMPTY);
		}
		
	}
	
	ChunkTile(Tile.TYPE type){
		chunk =  new Tile[tileRow * tileCol];;
		for	(int i = 0; i < chunk.length; i++) {
			chunk[i] = new Tile(type);
		}
	}
	
	public Tile getTile(int x, int y) {
		return chunk[x + y * tileRow];
	}
	
	
	public void drawChunks(Graphics g, int width, int height, int x, int y) {
		int offsetX = width / World.chunkRow;
		int offsetY = height / World.chunkCol;
		
		int offsetXh = (width / World.chunkRow) / tileRow;
		int offsetYh = (height / World.chunkCol) / tileCol;
		
		
		for(int yPos = 0; yPos < tileCol; yPos++ ) {
			for(int xPos = 0; xPos < tileRow; xPos++ ) {
				
				
				Tile tile = chunk[xPos + yPos * tileRow];
				
				switch(tile.getType()) {
					
				case EMPTY:
					g.setColor(new Color(0,0,0));
					g.fillRect((xPos * offsetXh) + x, (yPos * offsetYh) + y, offsetXh, offsetYh);
					break;
				default:
					g.drawImage(tile.getTileTexture(), (xPos * offsetXh) + x, (yPos * offsetYh) + y, offsetXh, offsetYh, null);
					g.setColor(Color.CYAN);
					g.drawRect((xPos * offsetXh) + x, (yPos * offsetYh) + y, offsetXh, offsetYh);
					break;
				
				}
				
				
			}
		}
		
	}
	
}

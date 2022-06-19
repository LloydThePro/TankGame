package game;

import java.awt.Dimension;

public class QuadTreeNode {
	private int width, height;
	private int x, y;
	private Entity[] containedEnt;
	private QuadTreeNode[] childNodes;
	private Tile[] containedTile;
	int currentDept = 0;
	QuadTreeNode(int x, int y, int height, int width, int depth){
		
		currentDept = depth + 1;
		if(currentDept >= QuadTree.maxDepth)return;
		
		
		
	
		childNodes = new QuadTreeNode[4];
		
		this.x = x;
		this.y = y;
		this.height = height;
		this.width = width;
		
		int offsetX = width / 4;
		int offsetY = width / 4;
		
		for(int Y = 0; Y < 2; Y++) {
			for(int X = 0; X < 2; X++) {
				
				try {
					childNodes[X + Y * 2] = new QuadTreeNode(X * offsetX, Y * offsetY, offsetX * (X + 1), offsetY * (Y + 1), currentDept);
				}catch(ArrayIndexOutOfBoundsException e) {
					e.printStackTrace();
					System.out.println("x: " + X + " y: " + " length: " + childNodes.length);
				}
				
				
				
			}
		}
		
	}
	
	
	public void insert(Entity ent) {
		
		
		int quadX = 0;
		int quadY = 0;
		
	}
	
	public void insert(Tile tile) {
		
	}

	public int getEntityCount() {
		if(containedEnt == null)return 0;
		
		return containedEnt.length;
		
	}
	
	public int getTileCount() {
		if(containedTile == null)return 0;
		
		return containedTile.length;
		
	}
	
	
	
}

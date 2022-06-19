package game;



public class QuadTree {
	
	public static final int maxDepth = 4;
	private int nodeDepth = 0;
	QuadTreeNode topNode;
	
	
	QuadTree(int width, int height){
		topNode = new QuadTreeNode(0,0, width, height, 1);
	}
	
	
}

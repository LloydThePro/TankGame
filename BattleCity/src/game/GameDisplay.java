package game;
import game.Tank;
import game.Tank.DIR;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.*;
import game.CollisionDetector;
import java.math.*;




class MouseInput implements MouseListener{
	
	public int x = 0, y = 0;
	boolean isAvailable = false;
	
	
	@Override
	public void mouseClicked(MouseEvent m) {
		// TODO Auto-generated method stub
		
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	
	public boolean isValid() {
		boolean temp = isAvailable;
		isAvailable = false;
		return temp;
	}
	
}

public class GameDisplay extends JPanel implements KeyListener{
	
	public static int width, height;
	private Image frontBuffer, backBuffer;
	private Graphics render;
	private boolean isUpdate = true;
	private Tank tank;
	private World gameWorld;
	public static MouseInput ms = new MouseInput();
	private long elapseTime = 0;
	private float fTime = 0.0f;
	private ArrayList<Bullet> activeBullets;
	
	Bullet debugB = new Bullet(150, 150, Entity.DIR.DOWN, tank);
	
	//debug
	private int colX = 0, colY = 0;
	
	private boolean isColliding = false;
	
	GameDisplay(int width, int height){
		gameWorld = new World();
		
		
		activeBullets = new ArrayList<Bullet>();
		
		
		this.width = width;
		this.height = height;
		
		frontBuffer = createImage(width, height);
		
		backBuffer = createImage(width, height);
		this.setLayout(null);
		this.setVisible(true);
		this.setBounds(0,0, width, height);
		this.setPreferredSize(new Dimension(width, height));
		setDoubleBuffered(true);
		tank = new Tank();
		Tank.loadImage();
	}
	
	public void paint(Graphics g) {
		g.drawImage(frontBuffer, 0, 0, this);
		g.dispose();
	}
	
	public void updateLoop() {
		
		while(isUpdate) {
			long t1 = System.currentTimeMillis();
			backBuffer = createImage(width, height);
			render = backBuffer.getGraphics();
			
			collision();
			
			
			gameWorld.drawLevel(render, width, height);
			tank.draw(render, width, height);
			updateBullets();
			
			render.setColor(Color.GREEN);
			render.drawRect(tank.xPos, tank.yPos, tank.width, tank.height);
			
			int offsetX = (GameDisplay.width / World.chunkRow) / 2;
			int offsetY = (GameDisplay.height / World.chunkCol) / 2;
			if(isColliding) {
				render.setColor(Color.RED);
				render.fillRect(colX * offsetY, colY * offsetX, offsetX, offsetY);
			}
			debugB.draw(render);
			
			// debug
			for(int i = 0; i < activeBullets.size(); i++) {
				Bullet b = activeBullets.get(i);
				render.setColor(Color.RED);;
				render.drawRect(b.getX(), b.getY(), b.width, b.height);
			}
			
			
			
			Image temp = backBuffer;
			backBuffer = frontBuffer;
			frontBuffer = temp;
			long t2 = System.currentTimeMillis();
			elapseTime = t2 - t1;
			
			
			
			
			float fElapseTime = (float)elapseTime / 1000;
			float waitTime = 1/120;
			fTime += fElapseTime;
			
			if(fTime > waitTime) {
				
				repaint();
				fTime = 0;
			}
			render.dispose();
		}
	}
	
	
	void updateBullets() {
		
		
		for(int i = 0; i < /*activeBullets.size()*/1; i++) {
			
			Bullet bullet = /*activeBullets.get(i)*/ debugB;
			
			
			int offsetX = (GameDisplay.width / World.chunkRow) / 2;
			int offsetY = (GameDisplay.height / World.chunkCol) / 2;
			
			
			int bulletX = bullet.getX() / offsetX, bulletY = bullet.getY() / offsetY;
			float rX = bulletX % offsetX, rY = bulletY % offsetY;
			
			int xPos = bulletX - 1, yPos = bulletY - 1;
			
			
			
			isColliding = true;
			colX = xPos + 1;
			colY = yPos;
			
			boolean isSolid = false;
			Tile.TYPE tileType = Tile.TYPE.BRICK;
			
			try {
				tileType = gameWorld.getTile(xPos, yPos).getType();
			}catch(ArrayIndexOutOfBoundsException e) {
				System.out.println("Xpos: " + xPos + " yPos: " + yPos);
			}
			
			int depth = 4;
			
			if(tileType == Tile.TYPE.BRICK || tileType == Tile.TYPE.IRON || tileType == Tile.TYPE.FOREST) {
				isSolid = true;
			}
			
			/*if(CollisionDetector.checkCollision(bullet, xPos, yPos) && isSolid) {
				bullet.setBulletState(false);
				
				isColliding = true;
				colX = xPos;
				colY = yPos;
				
			}*/
			if(CollisionDetector.checkCollision(bullet, xPos, yPos) && gameWorld.getTile(xPos, yPos).getType() == Tile.TYPE.IRON) {
				bullet.setBulletState(false);
				
				isColliding = true;
				colX = xPos;
				colY = yPos;
				
			}
			
			
			
			
			
			
			
			
			
			
			
			
			/*for(int y = yPos - depth; y <= yPos + depth; y++) {
				for(int x = xPos - depth; x <= xPos + depth;x++ ) {
					
					
					if(tileType == Tile.TYPE.BRICK || tileType == Tile.TYPE.IRON || tileType == Tile.TYPE.FOREST) {
						isSolid = true;
					}
					
					
					if(CollisionDetector.checkCollision(bullet, x, y) && isSolid) {
						bullet.setBulletState(false);
						
						isColliding = true;
						colX = x;
						colY = y;
						
					}
					
				}
			}*/
			
			
			
			
			
			
			
			
			
			
			
			
			/*for(int y = 0; y < World.chunkCol * 2; y++) {
				for(int x = 0; x < World.chunkRow * 2; x++) {
				
					boolean isSolid = false;
					Tile.TYPE tileType = gameWorld.getTile(x, y).getType();
					
					if(tileType == Tile.TYPE.BRICK || tileType == Tile.TYPE.IRON || tileType == Tile.TYPE.FOREST) {
						isSolid = true;
					}
					
					
					if(CollisionDetector.checkCollision(bullet, x, y) && isSolid) {
						bullet.setBulletState(false);
						isColliding = true;
						colX = x;
						colY = y;
						
						
					}
					
				}
			}*/
			
			
			
			
			
			if(!bullet.getBulletState()) {
				
				activeBullets.remove(i);
				break;
				
			}
			
			
			
			
			
			
			bullet.draw(render);
			
			//bullet.move(width, height);
		}
		
		
	}
	
	
	
	
	
	
	void collision() {
		
		
		for(int x = 0; x < World.chunkRow*2; x++) {
			
			for(int y = 0; y < World.chunkCol*2; y++) {
				
				Tile tile = gameWorld.getTile(x, y);
				
				boolean isSolid = true;
				Tile.TYPE type = tile.getType();
				
				if(type == Tile.TYPE.EMPTY)
					isSolid = false;
				
				
				if(CollisionDetector.checkCollision(tank, x, y) && isSolid) {
					//colX = x;
					//colY = y;
					//isColliding = true;
					tank.xPos = tank.prevX;
					tank.yPos = tank.prevY;
				}
				
				
			}
			
		}
		
		
		
		
	}
	
	

	@Override
	public void keyPressed(KeyEvent key) {
		// TODO Auto-generated method stub
		int code = key.getKeyCode();
		
		
		
		switch(code) {
			
		case KeyEvent.VK_W:
			tank.moveTank(Entity.DIR.UP);
			break;
		case KeyEvent.VK_S:
			tank.moveTank(Entity.DIR.DOWN);
			break;
		case KeyEvent.VK_D:
			tank.moveTank(Entity.DIR.RIGTH);
			break;
		case KeyEvent.VK_A:
			tank.moveTank(Entity.DIR.LEFT);
			break;
			
		case KeyEvent.VK_UP:
			debugB.bulletDirection = Entity.DIR.UP;
			debugB.move(width, height);
			break;
		case KeyEvent.VK_DOWN:
			debugB.bulletDirection = Entity.DIR.DOWN;
			debugB.move(width, height);
			break;
		case KeyEvent.VK_LEFT:
			debugB.bulletDirection = Entity.DIR.LEFT;
			debugB.move(width, height);
			break;
		case KeyEvent.VK_RIGHT:
			
			debugB.bulletDirection = Entity.DIR.RIGTH;
			debugB.move(width, height);
			break;
		case KeyEvent.VK_NUMPAD0:
			
			debugB.setBulletState(true);
			debugB.setPosAndDirection(tank.xPos, tank.yPos, tank.currentDir);
			break;
			
		}
		
		
		
		
	}

	@Override
	public void keyReleased(KeyEvent key) {
		// TODO Auto-generated method stub
		
		
		
		if(key.getKeyCode() == KeyEvent.VK_SPACE) {
			tank.fireWeapon(activeBullets);
		}
		
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
}

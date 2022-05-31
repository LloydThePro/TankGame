package game;
import game.Tank;
import game.Tank.DIR;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;


public class GameDisplay extends JPanel implements KeyListener{
	
	private int width, height;
	private Image frontBuffer, backBuffer;
	private Graphics render;
	private boolean isUpdate = true;
	private Tank tank;
	private World gameWorld;
	
	private long elapseTime = 0;
	private float fTime = 0.0f;
	
	
	
	
	GameDisplay(int width, int height){
		gameWorld = new World();
		
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
		tank.loadImage();
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
			
			
			gameWorld.drawLevel(render, width, height);
			//render.setColor(new Color(100,100,255));
			//render.fillRect((int)tank.xPos,(int)tank.yPos,50, 50);
			
			tank.drawTank(render);
			
			Image temp = backBuffer;
			backBuffer = frontBuffer;
			frontBuffer = temp;
			long t2 = System.currentTimeMillis();
			elapseTime = t2 - t1;
			
			
			
			
			float fElapseTime = (float)elapseTime / 1000;
			float waitTime = 1/120;
			fTime += fElapseTime;
			//System.out.println(fTime);
			if(fTime > waitTime) {
				
				repaint();
				fTime = 0;
			}
			render.dispose();
		}
	}
	
	void updateTank() {
		
	}

	@Override
	public void keyPressed(KeyEvent key) {
		// TODO Auto-generated method stub
		int code = key.getKeyCode();
		
		
		switch(code) {
			
		case KeyEvent.VK_W:
			
			tank.moveTank(Tank.DIR.UP);
			break;
		case KeyEvent.VK_S:
			
			tank.moveTank(Tank.DIR.DOWN);
			break;
		case KeyEvent.VK_D:
			tank.moveTank(Tank.DIR.RIGTH);
			break;
		case KeyEvent.VK_A:
			tank.moveTank(Tank.DIR.LEFT);
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
}

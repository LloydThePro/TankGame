package game;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

import game.GameDisplay;


public class BattleCity {

	public static void main(String[] args) {
		
		GameDisplay display = new GameDisplay(800,600);
		JFrame frame = new JFrame();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.add(display);
		frame.pack();
		frame.addMouseListener(display.ms);
		frame.addKeyListener(display);
		display.updateLoop();
	}

}

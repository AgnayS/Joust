package mainApp;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JComponent;
import javax.swing.JFrame;

import gameObjects.*;

public class GameComponent extends JComponent {
	private final static int FRAME_WIDTH = 2500, FRAME_HEIGHT = 1500;
	private final static double TICK_LENGTH = 0.05;
	
	private ArrayList<Hero> heroes;
	private ArrayList<PlatformPiece> platformPieces;
	private ArrayList<DynamicGameObject> dynamicGameObjects;
	private ArrayList<GameObject> gameObjects;
	
	public GameComponent() {
		JFrame frame = new JFrame();
		Dimension frameSize = new Dimension(FRAME_WIDTH, FRAME_HEIGHT);
		frame.setSize(frameSize);
		frame.setTitle("JOUST!");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;

		for (GameObject gameObject: gameObjects) {
			gameObject.drawOn(g2d);
		} // end for
	}
	
	public void updateGame() {
		for(DynamicGameObject dynamicGameObject : dynamicGameObjects) {
			dynamicGameObject.update(heroes, platformPieces);
			if(dynamicGameObject.shouldBeRemoved()) {
				dynamicGameObjects.remove(dynamicGameObject);
				if(heroes.remove(dynamicGameObject)){
					//TODO: what happens when a player dies?
				}
			}
		}
	}
	
	private void displayGame() {
		for(GameObject gameObject : gameObjects) {
			gameObject.drawOn(null);
		}
	}
}

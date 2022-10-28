package mainApp;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JTextField;

import gameObjects.*;

public class GameComponent extends JComponent {
	private final static double TICK_LENGTH = 0.05;
	
	private ArrayList<Hero> heroes = new ArrayList<>();
	private ArrayList<PlatformPiece> platformPieces = new ArrayList<>();
	private ArrayList<DynamicGameObject> dynamicGameObjects = new ArrayList<>();
	private ArrayList<GameObject> gameObjects = new ArrayList<>();
	public JTextField jtf;
	
	public GameComponent(ArrayList<PlatformPiece> platformPieces, Hero hero) {

		this.heroes.add(hero);
		this.platformPieces = platformPieces;
		gameObjects.addAll(platformPieces);
		gameObjects.add(hero);
		dynamicGameObjects.add(hero);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;

		for (GameObject gameObject: gameObjects) {
			gameObject.drawOn(g2d);
		}
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

	public void drawScreen() {
		this.repaint();
	}
}

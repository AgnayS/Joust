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
	
	public GameComponent(ArrayList<PlatformPiece> platformPieces) {

		this.heroes.add(new Hero(20,150,KeyEvent.VK_UP,KeyEvent.VK_LEFT,KeyEvent.VK_RIGHT));
		this.heroes.add(new Hero(20,20,KeyEvent.VK_S,KeyEvent.VK_Z,KeyEvent.VK_C));
		this.platformPieces = platformPieces;

		dynamicGameObjects.addAll(heroes);
		Grunt g = new Grunt(100,100);
		dynamicGameObjects.add(g);
		g.moveRight();
		Hopper h = new Hopper(200,100);
		dynamicGameObjects.add(h);
		h.moveUp();
		h.moveRight();
		gameObjects.addAll(platformPieces);
		gameObjects.addAll(dynamicGameObjects);
	}
	
	public void addHeroListener(JFrame frame) {
		for(Hero hero : heroes) {
			hero.addKeyAdapter(frame);
		}
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
				gameObjects.remove(dynamicGameObject);
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

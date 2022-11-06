package mainApp;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import gameObjects.*;

public class GameComponent extends JComponent {
	private final static double TICK_LENGTH = 0.05;
	
	private ArrayList<Hero> heroes = new ArrayList<>();
	private ArrayList<PlatformPiece> platformPieces = new ArrayList<>();
	private ArrayList<DynamicGameObject> dynamicGameObjects = new ArrayList<>();
	private ArrayList<GameObject> gameObjects = new ArrayList<>();
	
	JLabel scoreLabel = new JLabel();
	JLabel heroLivesLabel = new JLabel();
	
	public GameComponent(ArrayList<PlatformPiece> platformPieces, JLabel score,JLabel lives) {
		
		this.scoreLabel = score;
		this.heroLivesLabel = lives;

		this.heroes.add(new Hero(20,150,KeyEvent.VK_UP,KeyEvent.VK_LEFT,KeyEvent.VK_RIGHT));
		//temporarily removing 2nd hero to test enemy interactions for one hero
		//this.heroes.add(new Hero(20,20,KeyEvent.VK_S,KeyEvent.VK_Z,KeyEvent.VK_C));
		this.platformPieces = platformPieces;

		dynamicGameObjects.addAll(heroes);
		Grunt g = new Grunt(100,100);
		dynamicGameObjects.add(g);
		//testing adding enemies to frame
		g.track(heroes.get(0));
		Hopper h = new Hopper(200,100);
		dynamicGameObjects.add(h);
		h.moveUp();
		h.moveRight();
		//end testing adding enemies to frame
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
		ArrayList<DynamicGameObject> keepList = new ArrayList<>();
		ArrayList<Hero> heroKeepList = new ArrayList<>();
		ArrayList<Enemy> enemyList = new ArrayList<>();
		
		for(DynamicGameObject dynamicGameObject : dynamicGameObjects) {
			dynamicGameObject.update(heroes, platformPieces);
			
			if(!dynamicGameObject.shouldBeRemoved()) {
				keepList.add(dynamicGameObject);
				dynamicGameObject.track(heroes.get(0));
			} else if(dynamicGameObject instanceof Egg == false){ //had to implement instanceof to remove egg since when killing the egg would run this loop again and re-create an endless egg loop
				keepList.add(new Egg(dynamicGameObject.getxPos(), dynamicGameObject.getyPos(),System.currentTimeMillis())); 
			}
		}
		for(Hero hero : heroes) {
			hero.update(heroes, platformPieces);
			if(!hero.shouldBeRemoved()) {
				heroKeepList.add(hero);
			}
		}
		dynamicGameObjects = keepList; //updates list of dynamic game objects to keep in the game
		heroes = heroKeepList; //keeps heroes in the game
		gameObjects = new ArrayList<GameObject>(); //refreshes all objects in the game
		gameObjects.addAll(dynamicGameObjects); //re adds all non-removed objects
		gameObjects.addAll(platformPieces); //re adds all platforms
		gameObjects.addAll(heroes); //re adds non-removed heroes
		
		heroLivesLabel.setText("The number of lives remaining are " + heroes.get(0).getLives());
		scoreLabel.setText("Score " + heroes.get(0).getScore());
		

		//TODO the game crashes when 0 lives are reach, we should create a game over screen and run it over our level screen

	}

	public void drawScreen() {
		this.repaint();
	}
}

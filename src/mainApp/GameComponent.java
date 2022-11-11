package mainApp;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import gameObjects.*;
/**
 * GameComponent is the driver for all things being pasted onto the frame. It keeps a list of all game objects an platforms to update on the screen.
 * Updategame() handles a major portion of the graphics handling, if an object is marked for removal it will not be re-added to the list of objects to be created for the game.
 * Updategame() handles updating the score and lives labels as well as game end or game continue such as moving to the next level when all enemies are defeated.
 * 
 * @author jonescm
 *
 */
public class GameComponent extends JComponent {
	
	private final static double TICK_LENGTH = 0.05;
	private final static double TICKS_PER_LAVA_FRAME = 4;
	private final static int LAVA_TILE_WIDTH = 96, LAVA_TILE_HEIGHT = 48;
	private static Image LAVA_SPRITESHEET = null;
	static {
		try {
			LAVA_SPRITESHEET = ImageIO.read(new File("lava.png"));
		} catch (IOException e) {
			System.err.println("Platform spritesheet not found! Exiting...");
			System.exit(0);
		}
	}
	
	
	private ArrayList<Hero> heroes = new ArrayList<>();
	private ArrayList<PlatformPiece> platformPieces = new ArrayList<>();
	private ArrayList<DynamicGameObject> dynamicGameObjects = new ArrayList<>();
	private ArrayList<GameObject> gameObjects = new ArrayList<>();
	private double currentLavaFrame = 0;
	
	private ArrayList<ArrayList<PlatformPiece>> levels;
	private int currentLevel = -1;
	
	JLabel scoreLabel = new JLabel();
	JLabel heroLivesLabel = new JLabel();
	JButton restartButon = new JButton();
	
	public GameComponent(ArrayList<ArrayList<PlatformPiece>> levels, JLabel score, JLabel lives) {
		this.levels = levels;
		this.scoreLabel = score;
		this.heroLivesLabel = lives;
		this.heroes.add(new Hero(20,150,KeyEvent.VK_UP,KeyEvent.VK_LEFT,KeyEvent.VK_RIGHT));
		loadNextLevel();
	}
	
	public void loadNextLevel() {
		currentLevel++;
		dynamicGameObjects.clear();
		gameObjects.clear();
		if(currentLevel == levels.size()) {
			return;
		}
		platformPieces = levels.get(currentLevel);
		for(PlatformPiece p : platformPieces) {
			switch(p.getPlatformType()) {
				case 1 -> {
					heroes.get(0).setxPos((int) p.getxPos());
					heroes.get(0).setyPos((int) p.getyPos() - PlatformPiece.DEFAULT_HEIGHT);
				}
				case 2 -> dynamicGameObjects.add(new Grunt((int) p.getxPos(),(int)p.getyPos() - PlatformPiece.DEFAULT_HEIGHT));
				case 3 -> dynamicGameObjects.add(new Hopper((int) p.getxPos(),(int)p.getyPos() - PlatformPiece.DEFAULT_HEIGHT));
				case 4 -> dynamicGameObjects.add(new Drone((int) p.getxPos(),(int)p.getyPos() - PlatformPiece.DEFAULT_HEIGHT));
			}
		}
		dynamicGameObjects.addAll(heroes);
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

		
		
		g2d.setColor(Color.BLACK);
		g2d.fillRect(0, 0, 3840, 2160);
		

		
		for (GameObject gameObject: gameObjects) {
			gameObject.drawOn(g2d);
		}
		
		
		
		if(heroes.size() == 0) {
			g.setColor(Color.BLACK);
			g.drawRect(0, 0, 3840, 2160);
			g.fillRect(0, 0, 3840, 2160);
			g.setFont(new Font("Arial", 0, 120));
			g.setColor(Color.RED);
			g.drawString("Game Over!", 300, 200);
		} else if(currentLevel == levels.size()) {
			g.setColor(Color.BLACK);
			g.drawRect(0, 0, 3840, 2160);
			g.fillRect(0, 0, 3840, 2160);
			g.setFont(new Font("Arial", 0, 120));
			g.setColor(Color.GREEN);
			g.drawString("You Won!", 370, 200);
		}
		
		drawLava(g2d);
	}
	
	public void drawLava(Graphics2D g2d) {
		for(int x = 0; x < MainApp.LEVEL_WIDTH*PlatformPiece.DEFAULT_WIDTH; x+=LAVA_TILE_WIDTH) {
			g2d.drawImage(LAVA_SPRITESHEET, x, MainApp.LEVEL_HEIGHT*PlatformPiece.DEFAULT_HEIGHT - LAVA_TILE_HEIGHT, (int) x + LAVA_TILE_WIDTH, MainApp.LEVEL_HEIGHT*PlatformPiece.DEFAULT_HEIGHT, (int)Math.floor(currentLavaFrame)*LAVA_TILE_WIDTH, 0, (int)(1+Math.floor(currentLavaFrame))*LAVA_TILE_WIDTH, LAVA_TILE_HEIGHT, null);
			currentLavaFrame+=1/TICKS_PER_LAVA_FRAME;
			currentLavaFrame%=3;
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
				if(heroes.size() != 0) { //if a hero is in play, enemies will track them
					if(dynamicGameObject.getBounceCooldown() > 0) {
						dynamicGameObject.decrementBounceCooldown();
						dynamicGameObject.setxVel(dynamicGameObject.getxVel()/2);
						if(dynamicGameObject.getBounceCooldown() == 0) {
							dynamicGameObject.setxVel(0);
						}
					} else dynamicGameObject.track(heroes.get(0));
				} else {
					heroLivesLabel.setText("Game Over! All Lives Lost!");
				}
			} else {
				keepList.addAll(dynamicGameObject.getRemnants());
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
		
		if(heroes.size() > 0) { //while a hero is in play, update lives and score
			heroLivesLabel.setText("The number of lives remaining are " + (heroes.get(0).getLives() + 1));
			scoreLabel.setText("Score " + heroes.get(0).getScore());

		}
		if(heroes.size() == 1 && dynamicGameObjects.size() == 1) { //if hero is only dynamic game object, it means you beat the level and new platforms and enemies will be created
			heroLivesLabel.setText("You beat the level!");
			loadNextLevel();
		}
	}

	public void drawScreen() {
		this.repaint();
	}
}

package gameObjects;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;

public class Hero extends DynamicGameObject {
	private final static int HERO_WIDTH = 20;
	private final static int HERO_HEIGHT = 20;
	private final static int DAMAGE_COOLDOWN = 1;
	private final static Image MOVING_LEFT_HERO_SPRITE = null, MOVING_RIGHT_HERO_SPRITE = null, JUMPING_LEFT_HERO_SPRITE = null, JUMPING_RIGHT_HERO_SPRITE = null, FALLING_LEFT_HERO_SPRITE = null, FALLING_RIGHT_HERO_SPRITE = null;
	public final static double HERO_SPEED = 8;
	
	private final int leftKey, rightKey, upKey;
	private int lives;
	private int score;
	private int damageCooldown;
	
	

	public Hero(int xPos, int yPos, int upKey, int leftKey, int rightKey) {
		super(HERO_WIDTH, HERO_HEIGHT, MOVING_RIGHT_HERO_SPRITE, xPos, yPos);
		this.lives = 3;
		this.leftKey = leftKey;
		this.rightKey = rightKey;
		this.upKey = upKey;
	}
	
	@Override
	public void drawOn(Graphics2D g2) {
		g2.fillOval((int)xPos,(int)yPos, HERO_WIDTH, HERO_HEIGHT);
	}
	
	public void addKeyAdapter(JFrame frame) {
		KeyAdapter keyAdapter = new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == leftKey) {
					moveLeft();
				} else if (e.getKeyCode() == rightKey) {
					moveRight();
				} else if (e.getKeyCode() == upKey) {
					moveUp();
				}
			}
			@Override
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode() == leftKey || e.getKeyCode() == rightKey) {
					setxVel(0);
				} else if (e.getKeyCode() == upKey) {
					setyVel(0);
				}
			}
		};
		frame.addKeyListener(keyAdapter);
	}
	
	public void moveRight() {
		xVel = HERO_SPEED;
	}
	
	public void moveLeft() {
		xVel = -HERO_SPEED;
	}
	
	public void moveUp() {
		yVel = -HERO_SPEED;
	}
	//M3 addition, if hero has more than 1 life then he respawns, else is game over
//	public void die() {
//		if(this.lives != 0) {
//			this.lives --;
//			
//		} else {
//			
//		}
//	}
}

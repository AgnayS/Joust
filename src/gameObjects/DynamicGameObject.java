package gameObjects;

import java.awt.Image;
import java.util.ArrayList;

import mainApp.MainApp;
/**
 * Dynamic gameobject is an extension of gameObject
 * it has collision direction which will move the object based on what it collides with and where
 * movement of all objects is stored in this class, gravity is implemented in this class
 * Hero interactions are handled in this class.
 * Bouncing based on collision is handled in this class
 * @author jonescm
 *
 */
public abstract class DynamicGameObject extends GameObject {

	private final static double GRAVITATIONAL_ACCELERATION = 1;
	private final static double TERMINAL_VELOCITY = 18;
	private final static int MAX_X = (int) (MainApp.LEVEL_WIDTH*(PlatformPiece.DEFAULT_WIDTH-2));
	
	protected int bounceCooldown = 0;
	protected double xVel, yVel;

	public DynamicGameObject(int width, int height, Image sprite, double xPos, double yPos) {
		super(width, height, sprite, xPos, yPos);
	}

	public int[] getCollisionDirection(GameObject other) {
		double distanceIntoLeft = this.xPos + this.width - other.getxPos();
		double distanceIntoRight = other.getxPos() + other.getWidth() - this.xPos;
		double distanceIntoTop = this.yPos + this.height - other.getyPos();
		double distanceIntoBottom = other.getyPos() + other.getHeight() - this.yPos;
		double distanceIntoClosestSide = Math.min(Math.min(distanceIntoTop, distanceIntoBottom), Math.min(distanceIntoLeft, distanceIntoRight));	
		if(distanceIntoClosestSide <= 0 || (distanceIntoTop < 5 && (distanceIntoLeft < 5 || distanceIntoRight < 5))) {
			return null;
		} else if(distanceIntoTop > 6 && distanceIntoClosestSide == distanceIntoLeft) {
			return new int[] {-1,0};
		} else if(distanceIntoTop > 6 && distanceIntoClosestSide == distanceIntoRight) {
			return new int[] {1,0};
		} else if(distanceIntoClosestSide == distanceIntoTop) {
			return new int[] {0,-1};
		} else {
			return new int[] {0,1};
		}
	}
	
	public boolean isCollidingWith(GameObject other) {
		return getCollisionDirection(other) != null;
	}

	public void update(ArrayList<Hero> heroes, ArrayList<PlatformPiece> platformPieces) {
		
//		xPos = ((xPos+this.width) % (MAX_X)) - this.width;
//		System.out.println(xPos);
		if(xPos > MAX_X+this.width*2) {
			xPos = -this.width*.8;
		}
		if(xPos < -this.width*.8) {
			xPos = MAX_X+this.width*2;
		}
		xPos += xVel;
		yPos += yVel;
		yVel += GRAVITATIONAL_ACCELERATION;
		if(yVel > TERMINAL_VELOCITY) {
			yVel = TERMINAL_VELOCITY;
		}
		for (Hero hero : heroes) {
			if (this.isCollidingWith(hero)) {
				this.handleHeroInteraction(hero);
			}
		}
		for (PlatformPiece platformPiece : platformPieces) {
			if (this.isCollidingWith(platformPiece)) {
				platformPiece.handleCollision(this);
			}
		}
		if(this.yPos + this.height > PlatformPiece.DEFAULT_HEIGHT*MainApp.LEVEL_HEIGHT) {
			this.markToRemove();
		}
	}

	public void handleHeroInteraction(Hero hero){}

	public double getxVel() {
		return xVel;
	}

	public double getyVel() {
		return yVel;
	}

	public void setxVel(double xVel) {
		if(bounceCooldown == 0) {
			this.xVel = xVel;
		}
	}

	public void setyVel(double yVel) {
		this.yVel = yVel;
	}

	public void setyPos(int yPos) {
		this.yPos = yPos;
	}

	protected void adjustPosition(int[] positionIncrement) {
		xPos += positionIncrement[0];
		yPos += positionIncrement[1];
	}

	public void track(Hero hero) {
		
	}

	public int getBounceCooldown() {
		return bounceCooldown;
	}
	
	public void decrementBounceCooldown() {
		bounceCooldown--;
	}
	
	public void setBounceCooldown(int bounceCooldown) {
		this.bounceCooldown = bounceCooldown;
	}
}

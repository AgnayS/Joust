package gameObjects;

import java.awt.Image;
import java.util.ArrayList;

public abstract class DynamicGameObject extends GameObject {

	private final static double GRAVITATIONAL_ACCELERATION = -3;
	
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
		if(distanceIntoClosestSide < 0) {
			return null;
		} else if(distanceIntoClosestSide == distanceIntoLeft) {
			return new int[] {-1,0};
		} else if(distanceIntoClosestSide == distanceIntoRight) {
			return new int[] {1,0};
		} else if(distanceIntoClosestSide == distanceIntoTop) {
			return new int[] {0,-1};
		} else {
			return new int[] {0,1};
		}
	}

	public void update(ArrayList<Hero> heroes, ArrayList<PlatformPiece> platformPieces) {
		xPos += xVel;
		yPos += yVel;
		//heroes += GRAVITATIONAL_ACCELERATION;
//		for (Hero hero : heroes) {
//			int[] collisionDirection = this.getCollisionDirection(hero);
//			if (!(collisionDirection[0] == 0 && collisionDirection[1] == 0)) {
//				this.handleHeroInteraction(hero);
//			}
//		}
//		for (PlatformPiece platformPiece : platformPieces) {
//			int[] collisionDirection = this.getCollisionDirection(platformPiece);
//			if (!(collisionDirection[0] == 0 && collisionDirection[1] == 0)) {
//				platformPiece.handleCollision(this, collisionDirection);
//			}
//		}
	}

	public void handleHeroInteraction(Hero hero) {
	}

	public double getxVel() {
		return xVel;
	}

	public double getyVel() {
		return yVel;
	}

	public void setxVel(double xVel) {
		this.xVel = xVel;
	}

	public void setyVel(double yVel) {
		this.yVel = yVel;
	}

}

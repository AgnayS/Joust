package gameObjects;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Arrays;

public class PlatformPiece extends GameObject {
	private final static ArrayList<Image> PIECE_SPRITES = new ArrayList<>(16);
	public final static int DEFAULT_WIDTH = 48, DEFAULT_HEIGHT = 48;
	
	public PlatformPiece(int pieceType, double xPos, double yPos) {
		super(DEFAULT_WIDTH, DEFAULT_HEIGHT, null, xPos*DEFAULT_WIDTH, yPos*DEFAULT_HEIGHT);
	}
	
	public void handleCollision(DynamicGameObject object) {
		int[] collisionDirection = object.getCollisionDirection(this);
		while(collisionDirection != null) {
			object.adjustPosition(collisionDirection);
			if(collisionDirection[1] != 0) {
				object.setyVel(0);
			}
			collisionDirection = object.getCollisionDirection(this);
		}
	}
	
	@Override
	public void drawOn(Graphics2D g2) {
		g2.setColor(Color.BLACK);
		g2.fillRect((int)xPos, (int)yPos, width, height);
	}
	
	public String toString() {
		return "("+xPos+", "+yPos+")";
	}
}

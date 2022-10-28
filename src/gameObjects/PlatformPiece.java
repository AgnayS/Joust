package gameObjects;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;

public class PlatformPiece extends GameObject {
	private final static ArrayList<Image> PIECE_SPRITES = new ArrayList<>(16);
	public final static int DEFAULT_WIDTH = 48, DEFAULT_HEIGHT = 48;
	private final static double BOUNCE_STRENGTH = 2;
	
	public PlatformPiece(int pieceType, double xPos, double yPos) {
		super(DEFAULT_WIDTH, DEFAULT_HEIGHT, null, xPos*DEFAULT_WIDTH, yPos*DEFAULT_HEIGHT);
	}
	
	public void handleCollision(DynamicGameObject object, int[] collisionDirection) {
		if(collisionDirection[0] != 0) {
			object.setxVel(collisionDirection[0]*BOUNCE_STRENGTH);
		} else {
			object.setyVel(collisionDirection[1]*BOUNCE_STRENGTH);
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

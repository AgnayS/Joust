package gameObjects;

import java.awt.Image;
import java.util.ArrayList;

public class PlatformPiece extends GameObject {
	private final static ArrayList<Image> PIECE_SPRITES = new ArrayList<>(16);
	private final static int DEFAULT_WIDTH = 10, DEFAULT_HEIGHT = 10, SINGLE_HEIGHT = 5;
	private final static double BOUNCE_STRENGTH = 2;
	
	public PlatformPiece(int pieceType, double xPos, double yPos) {
		super(DEFAULT_WIDTH, pieceType < 4 ? SINGLE_HEIGHT : DEFAULT_HEIGHT, PIECE_SPRITES.get(pieceType), xPos, yPos);
	}
	
	public void handleCollision(DynamicGameObject object, int[] collisionDirection) {
		if(collisionDirection[0] != 0) {
			object.setxVel(collisionDirection[0]*BOUNCE_STRENGTH);
		} else {
			object.setyVel(collisionDirection[1]*BOUNCE_STRENGTH);
		}
	}
}

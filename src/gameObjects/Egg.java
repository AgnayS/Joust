package gameObjects;

import java.awt.Image;

public class Egg extends GameObject {
	private final static int DEFAULT_POINT_VALUE = 10;
	private final static int EGG_WIDTH = 1;
	private final static int EGG_HEIGHT = 1;
	private final static Image EGG_SPRITE = null;
	
	private int pointValue;

	public Egg(int xPos, int yPos) {
		super(EGG_WIDTH, EGG_HEIGHT, EGG_SPRITE, xPos, yPos);
		this.pointValue = DEFAULT_POINT_VALUE;
	}
}

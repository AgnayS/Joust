package gameObjects;

import java.awt.Image;

public class Egg extends GameObject {
	private final static int EGG_WIDTH = 1;
	private final static int EGG_HEIGHT = 1;
	private final static Image EGG_SPRITE = null;

	public Egg(int xPos, int yPos) {
		super(EGG_WIDTH, EGG_HEIGHT, EGG_SPRITE, xPos, yPos);
	}
}

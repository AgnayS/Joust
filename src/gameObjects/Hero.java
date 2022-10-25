package gameObjects;

import java.awt.Image;

public class Hero extends GameObject {
	private final static int HERO_WIDTH = 1;
	private final static int HERO_HEIGHT = 1;
	private final static Image HERO_SPRITE = null;

	public Hero(int xPos, int yPos) {
		super(HERO_WIDTH, HERO_HEIGHT, HERO_SPRITE, xPos, yPos);
	}
}

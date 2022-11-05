package gameObjects;

import java.awt.Image;

public class Egg extends DynamicGameObject {
	private final static int TICKS_TO_HATCH = 200;
	private final static int EGG_WIDTH = 1;
	private final static int EGG_HEIGHT = 1;
	private final static Image DEFAULT_EGG_SPRITE = null, HATCHING_EGG_SPRITE = null;
	
	private int pointValue;
	private int ticksUntilHatch;

	public Egg(double xPos, double yPos, int pointValue) {
		super(EGG_WIDTH, EGG_HEIGHT, DEFAULT_EGG_SPRITE, xPos, yPos);
		System.out.println("No Egg?");
		this.pointValue = pointValue;
		this.ticksUntilHatch = TICKS_TO_HATCH;
	}

	
	
	
}

package gameObjects;

import java.awt.Image;

public class Egg extends DynamicGameObject {
	private final static int TICKS_TO_HATCH = 200;
	private final static int EGG_WIDTH = 1;
	private final static int EGG_HEIGHT = 1;
	private final static Image DEFAULT_EGG_SPRITE = null, HATCHING_EGG_SPRITE = null;
	
	private int pointValue;
	private int ticksUntilHatch;

	public Egg(int xPos, int yPos, int pointValue) {
		super(EGG_WIDTH, EGG_HEIGHT, DEFAULT_EGG_SPRITE, xPos, yPos);
		this.pointValue = pointValue;
		this.ticksUntilHatch = TICKS_TO_HATCH;
	}
	
	@Override
	public void handleHeroInteraction(Hero hero) {
		
	}
}

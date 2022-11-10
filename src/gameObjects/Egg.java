package gameObjects;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;

public class Egg extends DynamicGameObject {
	private final int TICKS_TO_HATCH = 40;
	private final static int EGG_WIDTH = 10;
	private final static int EGG_HEIGHT = 10;
	private final static Image DEFAULT_EGG_SPRITE = null, HATCHING_EGG_SPRITE = null;
	
	private int pointValue = 250;
	private int ticksUntilHatch;
	public long creationTime;

	public Egg(double xPos, double yPos) {
		super(EGG_WIDTH, EGG_HEIGHT, DEFAULT_EGG_SPRITE, xPos, yPos);
		this.ticksUntilHatch = TICKS_TO_HATCH;
	}
	public void handleHeroInteraction(Hero hero) {			
		hero.updateScore(this.pointValue); //test point value of 100
		this.markToRemove();
	}
	@Override
	public void drawOn(Graphics2D g2) {
		g2.setColor(Color.GRAY);
		g2.fillOval((int)xPos,(int)yPos, width, height);
		g2.setColor(Color.BLACK);
	}
	
	public void update(ArrayList<Hero> heroes, ArrayList<PlatformPiece> platformPieces, ArrayList<DynamicGameObject> keepList) {
		super.update(heroes, platformPieces);
		ticksUntilHatch--;
		if(ticksUntilHatch < 0 && !markedForRemoval) {
			markToRemove();
			Grunt newGrunt = new Grunt((int)this.xPos, (int)this.yPos);
			keepList.add(newGrunt);
		}
	}
	
	
	
	
}

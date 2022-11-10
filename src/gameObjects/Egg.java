package gameObjects;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;
/**
 * Egg is an extension of dynamic game object, unlike enemy the egg will not be tracking the hero and is how the hero will increase its score.
 * When an egg is not destroyed by a hero collision in time based on its Ticks_To_Hatch, a new grunt is created where the egg was located
 * 
 * @author jonescm
 *
 */
public class Egg extends DynamicGameObject {
	private final static int TICKS_TO_HATCH = 40;
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
		hero.updateScore(this.pointValue); //updates the hero it collides with score
		this.markToRemove();
	}
	@Override
	public void drawOn(Graphics2D g2) {
		g2.setColor(Color.yellow);
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

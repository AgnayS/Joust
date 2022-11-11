package gameObjects;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Heart extends DynamicGameObject {
	private final static int HEART_WIDTH = 17, HEART_HEIGHT = 17;
	private final static String HEART_SPRITE_PATH = "heart.png";
	
	public Heart(double xPos, double yPos) {
		super(HEART_WIDTH, HEART_HEIGHT, null, xPos, yPos);
		this.spriteNumber = 0;
		try {
			spriteSheet = ImageIO.read(new File(HEART_SPRITE_PATH));
		} catch (IOException e) {
			System.err.println("Heart sprite not found! Exiting...");
			System.exit(0);
		}
	}
	
	@Override
	public void handleHeroInteraction(Hero hero) {
		hero.addLife();
		markToRemove();
	}
}

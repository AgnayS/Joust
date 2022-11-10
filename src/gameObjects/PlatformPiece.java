package gameObjects;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.imageio.ImageIO;

public class PlatformPiece extends GameObject {
	public final static String SPRITESHEET_PATH = "platformSpritesheet";
	
	
	public final static int DEFAULT_WIDTH = 64, DEFAULT_HEIGHT = 64;
	
	private final int platformSpawnType;
	
	public PlatformPiece(int spriteNumber, int xPos, int yPos, int platformSpawnType) {
		super(DEFAULT_WIDTH, DEFAULT_HEIGHT, null, xPos*DEFAULT_WIDTH, yPos*DEFAULT_HEIGHT);
		this.spriteNumber = spriteNumber;
		this.platformSpawnType = platformSpawnType;
		try {
			spriteSheet = ImageIO.read(new File("platformSpritesheet.png"));
		} catch (IOException e) {
			System.err.println("Platform spritesheet not found! Exiting...");
			System.exit(0);
		}
	}
	
	public void handleCollision(DynamicGameObject object) {
		int[] collisionDirection = object.getCollisionDirection(this);
		while(collisionDirection != null) {
			object.adjustPosition(collisionDirection);
			if(collisionDirection[1] != 0) {
				object.setyVel(0);
			}
			collisionDirection = object.getCollisionDirection(this);
		}
	}
	
	public String toString() {
		return "("+xPos+", "+yPos+")";
	}

	public int getPlatformType() {
		return platformSpawnType;
	}
}

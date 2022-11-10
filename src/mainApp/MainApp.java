
package mainApp;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.Border;

import gameObjects.GameObject;
import gameObjects.Hero;
import gameObjects.PlatformPiece;

/**
 * Class: MainApp
 * 
 * @author Put your team name here <br>
 *         Purpose: Top level class for CSSE220 Project containing main method
 *         <br>
 *         Restrictions: None
 */
public class MainApp {
	private final static String[] LEVEL_PATHS = new String[] { "level0.txt", "level1.txt", "badLevel.txt", "level2.txt" ,"level3.txt"};
	private static final int DELAY = 50;
	public static final int LEVEL_HEIGHT = 7;
	public static final int LEVEL_WIDTH = 20;

	public static void main(String[] args) {
		//JOptionPane.showInputDialog("Welcome to JOUST! Please Enter your name to get started");

		MainApp mainApp = new MainApp();
		mainApp.runApp(0);
	} // main

	private void runApp(int levelIndex) {	
		String currentLine;
		JFrame frame = new JFrame("Joust!");

		JPanel scorePanel = new JPanel(); // Create The Two Jpanels and Jlabels.
		JLabel scoreLabel = new JLabel("The score is: "); // Add The Jlabels to the panel
		scorePanel.add(scoreLabel);
		scorePanel.setBackground(Color.orange);
		frame.add(scorePanel, BorderLayout.NORTH);// Add the panels to the frame
		JPanel heroLivesPanel = new JPanel();
		JLabel heroLivesLabel = new JLabel("Lives left: ");
		heroLivesPanel.add(heroLivesLabel);
		heroLivesPanel.setBackground(Color.gray);
		frame.add(heroLivesPanel, BorderLayout.SOUTH);

		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setFocusable(true);
		
		GameComponent gameComponent = new GameComponent(loadLevels(), scoreLabel, heroLivesLabel);
		GameListener gameListener = new GameListener(gameComponent);
		Timer timer = new Timer(DELAY, gameListener);
		timer.start();

		Dimension gameSize = new Dimension(LEVEL_WIDTH * PlatformPiece.DEFAULT_WIDTH, LEVEL_HEIGHT * PlatformPiece.DEFAULT_HEIGHT);
		

		
		frame.add(gameComponent);
		gameComponent.setPreferredSize(gameSize);
		frame.pack();

		gameComponent.addHeroListener(frame);
		KeyAdapter keyAdapter = new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_U) {
					frame.dispose();
					runApp((levelIndex + 1) % LEVEL_PATHS.length);
				} else if (e.getKeyCode() == KeyEvent.VK_D) {
					frame.dispose();
					runApp((levelIndex - 1) % LEVEL_PATHS.length);
				}
			}
		};
		frame.addKeyListener(keyAdapter);
		frame.validate();
	}
	
	public static ArrayList<ArrayList<PlatformPiece>> loadLevels(){
		ArrayList<ArrayList<PlatformPiece>> levels = new ArrayList<>();
		for(String path : LEVEL_PATHS) {
			try {
				levels.add(loadLevel(path));
			} catch (IOException e) {
				System.err.println("I/O Exception occured, check again");
			} catch (InvalidLevelFormatException e) {
				System.err.println(e.getMessage());
			}
		}
		return levels;
	}

	private static ArrayList<PlatformPiece> loadLevel(String path) throws IOException, InvalidLevelFormatException, FileNotFoundException {
		BufferedReader br;
		try{
			br = new BufferedReader(new FileReader(path));
		} catch(FileNotFoundException e) {
			throw e;
		}
		String currentLine;
		char[][] fileAsArray = new char[LEVEL_HEIGHT][LEVEL_WIDTH];
		int i = 0;
		while ((currentLine = br.readLine()) != null) {
			if(currentLine.length() != LEVEL_WIDTH || i == LEVEL_HEIGHT) {
				throw new InvalidLevelFormatException(path, i, 0, 2);
			}
			fileAsArray[i] = currentLine.toCharArray();
			i++;
		}
		if(fileAsArray.length < LEVEL_HEIGHT) {
			throw new InvalidLevelFormatException(path, i, 0, 2);
		}
		
		boolean hasHeroSpawn = false;
		boolean hasEnemySpawn = false;
		
		ArrayList<PlatformPiece> level = new ArrayList<>();
		
		for(int y = 0; y < LEVEL_HEIGHT; y++) {
			for(int x = 0; x < LEVEL_WIDTH; x++) {
				if(fileAsArray[y][x] >= 48 && fileAsArray[y][x] <= 52) {
					if(fileAsArray[y][x] >= 49 && (y == 0 || fileAsArray[y - 1][x] != '.')) {
						throw new InvalidLevelFormatException(path, y, x, 1);
					}
					
					if (fileAsArray[y][x] >= 50) {
						hasEnemySpawn = true;
					} else if(fileAsArray[y][x] == 49) {
						if(hasHeroSpawn == true) {
							throw new InvalidLevelFormatException(path, y, x, 5);
						} else {
							hasHeroSpawn = true;
						}
					}
					
					boolean[] neighbors = {
						y > 0 && fileAsArray[y - 1][x] != '.',
						x > 0 && fileAsArray[y][x - 1] != '.',
						x > 0 && y < LEVEL_HEIGHT - 1 && fileAsArray[y + 1][x - 1] != '.',
						y < LEVEL_HEIGHT - 1&& fileAsArray[y + 1][x] != '.',
						x < LEVEL_WIDTH - 1 && y < LEVEL_HEIGHT - 1 && fileAsArray[y + 1][x + 1] != '.',
						x < LEVEL_WIDTH - 1 && fileAsArray[y][x + 1] != '.'
					};
					int pieceType = 0;
					
					if(!neighbors[1]) {
						pieceType = 4;
					}
					if(!neighbors[3]) {
						pieceType += 2;
					}
					if(!neighbors[5]) {
						pieceType += 1;
					}
					if(!neighbors[2] && pieceType < 2) {
						pieceType += 9;
					}
					if(!neighbors[4] && (pieceType == 0 || pieceType == 4)) {
						pieceType += 8;
					}
					if(!neighbors[4] && pieceType == 9) {
						pieceType = 11;
					}
					if(!neighbors[0]) {
						pieceType += 13;
					}
					
					level.add(new PlatformPiece(pieceType, x, y, fileAsArray[y][x] - 48));
					
					
				} else if (fileAsArray[y][x] != '.') {
					throw new InvalidLevelFormatException(path, y, x, 0);
				}
			}
			
		}
		if(!hasHeroSpawn) {
			throw new InvalidLevelFormatException(path, 0, 0, 4);
		}
		if(!hasEnemySpawn) {
			throw new InvalidLevelFormatException(path, 0, 0, 3);
		}
		return level;
	}
}

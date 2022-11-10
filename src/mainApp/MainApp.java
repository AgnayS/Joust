
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
	private final static String[] LEVEL_PATHS = new String[] { "level2.txt", "level1.txt","level3.txt","level4.txt","badLevel.txt"  };
	private static final int DELAY = 50;

	public static void main(String[] args) {
		JOptionPane.showInputDialog("Welcome to JOUST! Please Enter your name to get started");

		MainApp mainApp = new MainApp();
		mainApp.runApp(0);
	} // main

	private void runApp(int levelIndex) {	
		ArrayList<PlatformPiece> platformPieces = new ArrayList<>();
		File level = new File(LEVEL_PATHS[levelIndex]);
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(level));
		} catch (FileNotFoundException e1) {
			System.err.println("File not Found :(");
			return;
		}

		String currentLine;
		int width = 0;
		int j = 0;
		JFrame frame = new JFrame("Joust!");
		
		

		JPanel scorePanel = new JPanel(); // Create The Two Jpanels and Jlabels.
		JLabel scoreLabel = new JLabel("The score is: "); // Add The Jlabels to the panel
		scorePanel.add(scoreLabel);
		scorePanel.setBackground(Color.cyan);
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

		try {
			while ((currentLine = br.readLine()) != null) {
				if (width == 0) {
					width = currentLine.length();
				} else if (currentLine.length() != width) {
					throw new InvalidLevelFormatException(LEVEL_PATHS[levelIndex]);
				}
				for (int i = 0; i < currentLine.length(); i++) {
					if (currentLine.charAt(i) == 'L') {
						platformPieces.add(new PlatformPiece(5, i, j));
					} else if (currentLine.charAt(i) != '.') {
						throw new InvalidLevelFormatException(LEVEL_PATHS[levelIndex]);
					}
				}
				j++;

			}

			GameComponent gameComponent = new GameComponent(platformPieces, scoreLabel, heroLivesLabel);

			GameListener gameListener = new GameListener(gameComponent);
			Timer timer = new Timer(DELAY, gameListener);
			timer.start();

			Dimension frameSize = new Dimension(width * PlatformPiece.DEFAULT_WIDTH, j * PlatformPiece.DEFAULT_HEIGHT);
			frame.getContentPane().setPreferredSize(frameSize);
			frame.pack();
			frame.add(gameComponent);
			gameComponent.addHeroListener(frame);

		} catch (IOException e) {
			System.err.println("I/O Exception occured, check again");
		} catch (InvalidLevelFormatException e) {
			System.err.println("Level formatted incorrectly");
		}
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
}

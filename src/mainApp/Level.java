package mainApp;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.Buffer;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import javax.swing.JComponent;
import javax.swing.JFrame;

import org.w3c.dom.ls.LSException;

import gameObjects.PlatformPiece;

public class Level extends JComponent {

	String pathToFile;
	ArrayList<PlatformPiece> platformPieces = new ArrayList<>();

	public Level(String path) {
		pathToFile = path;
	}

	public void displayLevel() {

		HashMap<Integer, Integer> coordinates = new HashMap<>();
		JFrame frame = new JFrame();

		Dimension frameSize = new Dimension(2500, 1500);

		frame.setTitle("JOUST!");
		frame.setSize(frameSize);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

		File level = new File(pathToFile);
		BufferedReader br = null;
		int width = 0, height = 0;

		try {
			br = new BufferedReader(new FileReader(level));
		} catch (FileNotFoundException e1) {

			System.out.println("File not Found :(");
		}

		String currentLine;
		int j = 0;
		try {
			while ((currentLine = br.readLine()) != null) {
				if(height == 0) {
					height = currentLine.length();
				} else if (currentLine.length() != height){
					throw new InvalidLevelFormatException(pathToFile);
				}
				for(int i = 0; i < currentLine.length(); i++) {
					if(currentLine.charAt(i) == 'L') {
						platformPieces.add(new PlatformPiece(5,i,j));
					} else if (currentLine.charAt(i) != '.') {
						throw new InvalidLevelFormatException(currentLine);
					}
				}
			}

		} catch (IOException e) {
			System.out.println("Io Exception occured, check again");
		} catch (InvalidLevelFormatException e) {
			
		}

		
		
		frame.add(new Platform(coordinates));

	}

	public void frameFromFile(String pathToFile) {

	}

	protected void paintComponent(Graphics graphics) {
		super.paintComponent(graphics);
	
		Graphics2D graphics2 = (Graphics2D) graphics;
		for(PlatformPiece pp : platformPieces) {
			pp.drawOn(graphics2);
		}
	}
	
	
}

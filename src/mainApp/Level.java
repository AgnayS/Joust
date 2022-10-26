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
import java.util.HashMap;
import java.util.Scanner;

import javax.swing.JComponent;
import javax.swing.JFrame;

import org.w3c.dom.ls.LSException;

public class Level extends JComponent {

	String pathToFile;

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
		try {
			while ((currentLine = br.readLine()) != null) {
				if (currentLine.charAt(currentLine.length() - 1) == '|' && currentLine.charAt(0) == '|') {
					height++;
					width = 0;

					for (int i = 1; i < currentLine.length() - 1; i++) {
						if (currentLine.charAt(i) == '.') {
							width++;
						} else if (currentLine.charAt(i) == 'L') {
							coordinates.put(width, height);
							width++;
						} else
							System.out.println(new InvalidLevelFormatException(currentLine));
						// TODO handle correct character but asymmetric matrix
						// TODO crash program if wrong text file is loaded, or if asking user for input,
						// put inside a while loop and ask for path again
					}
				} else {
					System.out.println(new InvalidLevelFormatException(currentLine));
				}

			}

		} catch (IOException e) {
			System.out.println("Io Exception occured, check again");
		}

		frame.add(new Platform(coordinates));

	}

	public void frameFromFile(String pathToFile) {

	}
}

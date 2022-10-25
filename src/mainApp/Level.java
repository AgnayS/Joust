package mainApp;

import java.awt.Dimension;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.Buffer;
import java.util.Scanner;

import javax.swing.JFrame;

public class Level {

	String pathToFile;

	public Level(String path) {
		pathToFile = path;
	}

	public void displayLevel() {
		File level = new File(pathToFile);
		BufferedReader br = null;
		String st;
		JFrame jf = new JFrame();

		try {
			br = new BufferedReader(new FileReader(level));
		} catch (FileNotFoundException e1) {

			System.out.println("File not Found :(");
		}
		try {
			st = br.readLine();
			{
				System.out.println(st);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}

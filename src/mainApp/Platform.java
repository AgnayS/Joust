package mainApp;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import javax.swing.JComponent;

public class Platform extends JComponent {
	HashMap<Integer, Integer> coordinates = new HashMap<>();

	public Platform(HashMap<Integer, Integer> a) {
		coordinates = a;

	}

	protected void paintComponent(Graphics graphics) {
		super.paintComponent(graphics);

		Graphics2D graphics2 = (Graphics2D) graphics;
		for (Integer i : coordinates.keySet()) {
			graphics2.drawRect(i*100, coordinates.get(i)*300, 100, 100);
			graphics2.fillRect(i*100, coordinates.get(i)*300, 100, 100);
		}
	}
}

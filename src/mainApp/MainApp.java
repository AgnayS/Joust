package mainApp;

import java.awt.Dimension;

import javax.swing.JFrame;

/**
 * Class: MainApp
 * @author Put your team name here
 * <br>Purpose: Top level class for CSSE220 Project containing main method 
 * <br>Restrictions: None
 */
public class MainApp {
	
	private void runApp() 
	{
		Level obj = new Level("level2.txt");
		obj.displayLevel();
	}
	
	public static void main(String[] args) {
		MainApp mainApp = new MainApp();
		mainApp.runApp();		
	} // main

}





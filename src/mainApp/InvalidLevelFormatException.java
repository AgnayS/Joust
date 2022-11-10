package mainApp;

import java.io.FileNotFoundException;
import java.io.FileReader;
/**
 * Invalid level format exception, handles all known cases where an exception within the game (such as a bad level format) may occur and handles it
 * @author jonescm
 *
 */
public class InvalidLevelFormatException extends Exception
{
	
	String path;
	int row, col;
	int reason;
		
	public InvalidLevelFormatException(String path, int row, int col, int reason)
	{
		this.path = path;
		this.row = row;
		this.col = col;
		this.reason = reason;
	}
	
	@Override
	public String getMessage()
	{
		return switch(reason) {
			case 0 -> "Could not read level "+path+": Invalid character at row "+row+", column "+col;
			case 1 -> "Could not read level "+path+": Obstructed spawn platform at row "+row+", column "+col;
			case 2 -> "Could not read level "+path+": Invalid level dimensions.";
			case 3 -> "Could not read level "+path+": No enemy spawn platforms found.";
			case 4 -> "Could not read level "+path+": No hero spawn platforms found.";
			case 5 -> "Could not read level "+path+": More than one hero spawn found at row "+row+", column "+col;
			default -> "Could not read level "+path+": Invalid level format.";
		};
	}
}

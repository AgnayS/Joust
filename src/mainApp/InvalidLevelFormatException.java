package mainApp;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class InvalidLevelFormatException extends Exception
{
	String pathToFile;
	public InvalidLevelFormatException(String path)
	{
		pathToFile = path;
	}
	
	@Override
	public String getMessage()
	{
		return "Invalid Level File Format";		
	}
}

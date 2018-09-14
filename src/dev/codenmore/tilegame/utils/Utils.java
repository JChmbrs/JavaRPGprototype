package dev.codenmore.tilegame.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Utils {
	//helper functions that can help us with our game
	
	public static String loadFileAsString(String path) {
		StringBuilder builder = new StringBuilder(); //allows strings to be appended etc.
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(path));
			String line;
			while((line = br.readLine()) != null)  //as long as we haven't hit the end of the file
				builder.append(line + "\n");
			
			br.close(); //closes the file
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		return builder.toString(); //converts everything we've appended to the builder object
								   //to a string, which will be the loaded file
	}
	
	public static int parseInt(String number) {
		//going to take a string "5" and convert that to its int value
		try {
		     return Integer.parseInt(number);  //Integer is just a way to work on ints
		}catch(NumberFormatException e) { //if you try and pass it in as a string that 
										  //is not a number, it will throw an error
			e.printStackTrace(); //prints error to screen
			
			return 0; //so our program doesn't crash
		}
	}
	
	
}

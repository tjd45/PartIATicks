package uk.ac.cam.tjd45.oopjava.tick5;

import java.io.Reader;
import java.net.URL;
import java.net.URLConnection;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

public class PatternLoader {
	
	
	public static List<Pattern> loadFromURL(String url) throws IOException {
		URL destination = new URL(url);
		URLConnection conn = destination.openConnection();
		return load(new InputStreamReader(conn.getInputStream()));
		
	}
		
	
	public static List<Pattern> loadFromDisk(String filename) throws IOException {
		return load(new FileReader(filename));
	}
   
	
	public static List<Pattern> load(Reader r) throws IOException {
      //TODO: Complete the implementation of this method.
   		BufferedReader buff = new BufferedReader(r);
   		List<Pattern> resultList = new LinkedList<Pattern>();
   		
   		
   		String message = buff.readLine();
   		while (message != null) {
   			try{

   				Pattern p = new Pattern(message);
   				resultList.add(p);
   				
   				}
   			
   			catch (PatternFormatException e) {
   				//e.printStackTrace();
   			}
   			message = buff.readLine();
   			
   		}
   		
   		return resultList;
   		
	   
   } 
 }
package uk.ac.cam.tjd45.oopjava.tick4;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;

import uk.ac.cam.acr31.life.World;

public class TextLife {
	   
	public static void main(String[] args) throws PatternFormatException, CommandLineException, IOException {
	      
		CommandLineOptions c = new CommandLineOptions(args);
	    List<Pattern> list;
	    
	    if (c.getSource().startsWith("http://")) {
	    	list = PatternLoader.loadFromURL(c.getSource());
	    } else {
	    	list = PatternLoader.loadFromDisk(c.getSource());
	    }
	      
	    if (c.getIndex() == null) {
	    	int i = 0;
	        for (Pattern p : list)
	        	System.out.println((i++)+"\t"+p.getName()+"\t"+p.getAuthor());
	      	} else {
	      		Pattern p = list.get(c.getIndex());
	      		World w = null;
	      		if (c.getWorldType().equals(CommandLineOptions.WORLD_TYPE_AGING)) {
	      			w = new AgingWorld(p.getWidth(), p.getHeight());
	      		} else if (c.getWorldType().equals(CommandLineOptions.WORLD_TYPE_ARRAY)) {
	      			w = new ArrayWorld(p.getWidth(), p.getHeight());
	      		} else {
	      			w = new PackedWorld();
	      		}
	      	
	      		p.initialise(w);
	      		int userResponse = 0;
	
	      		while (userResponse != 'q') {
	      			w.print(new OutputStreamWriter(System.out));
	      			try {
	      				userResponse = System.in.read();
	      			} catch (IOException e) {
	      				return; //just exit the program
	      			}
	      			w = w.nextGeneration(0);
	      		}
	      
	      	}
	}
}
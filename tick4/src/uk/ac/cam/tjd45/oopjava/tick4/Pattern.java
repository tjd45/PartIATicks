package uk.ac.cam.tjd45.oopjava.tick4;

import uk.ac.cam.acr31.life.World;

public class Pattern {
   private String name;
   private String author;
   private int width;
   private int height;
   private int startCol;
   private int startRow;
   private String cells;
   //TODO: write public 'get' methods for EACH of the fields above;
   //      for instance 'getName' should be written as:
   
   	public String getName() {
      	return name;
	}

	public String getAuthor() {
		return author;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public int getstartCol() {
		return startCol;
	}
	
	public int getstartRow() {
		return startRow;
	}
	
	public String getCells() {
		return cells;
	}
	
   public Pattern(String format) throws PatternFormatException{
      //TODO: initialise all fields of this class using contents of
      //      'format' to determine the correct values.
	
      	String[] arrofString = format.split(":");
      	
     	if (arrofString.length != 7)
     		throw new PatternFormatException("Invalid pattern format: Incorrect number of fields in pattern (found " + arrofString.length + ").");
		name = arrofString[0];
		author = arrofString[1];
     	try {
   			width = Integer.parseInt(arrofString[2]); //error: not an integer value
			} 
			catch (NumberFormatException error)
			{
   			throw new PatternFormatException("Invalid pattern format: Could not interpret the width field as a number ('"+arrofString[2]+"' given).");
   			}
   		try {
   			height = Integer.parseInt(arrofString[3]); //error: not an integer value
			} 
			catch (NumberFormatException error)
			{
   			throw new PatternFormatException("Invalid pattern format: Could not interpret the height field as a number ('"+arrofString[3]+"' given).");
   			}
   		try {
   			startCol = Integer.parseInt(arrofString[4]); //error: not an integer value
			} 
			catch (NumberFormatException error)
			{
   			throw new PatternFormatException("Invalid pattern format: Could not interpret the startX field as a number ('"+arrofString[4]+"' given).");
   			}
   		try {
   			startRow = Integer.parseInt(arrofString[5]); //error: not an integer value
			} 
			catch (NumberFormatException error)
			{
   			throw new PatternFormatException("Invalid pattern format: Could not interpret the startX field as a number ('"+arrofString[5]+"' given).");
   			}
     	cells = arrofString[6];
     	
     	//TODO: Using loops, update the appropriate cells of 'world'
     	
}
   public void initialise(World newworld) throws PatternFormatException{
      //TODO: update the values in the 2D array representing the state of
      //      'world' as expressed by the contents of the field 'cells'.
      String[] ChosenCells = cells.split(" ");
      for (int i = 0; i < ChosenCells.length; i++) {
     			char[] tempchararray = ChosenCells[i].toCharArray();
     			for (int j = 0; j < tempchararray.length; j++) {
     				if 	(tempchararray[j] == '1') {
     					newworld.setCell(j+startCol,i+startRow,true);
     				} else {
     							if (tempchararray[j] != '0') {
     								throw new PatternFormatException("Invalid pattern format: Malformed pattern '"+cells+"'."); 
     							}
     				}
     			}
     		}
} }
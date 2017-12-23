package uk.ac.cam.tjd45.oopjava.tick2;

import uk.ac.cam.acr31.life.World;
import java.io.Writer;
import java.awt.Graphics;
import java.io.PrintWriter;
import java.io.OutputStreamWriter;

public class TestArrayWorld implements World {
   private int generation;
   private int width;
   private int height;
   private boolean[][] cells;
   
   public TestArrayWorld(int w, int h) {
      width = w;
      height = h;
      generation = 0;// TODO: set generation equal to zero
      cells = new boolean[height][width];// TODO: set cells to reference a new rectangular two-dimensional
      //       boolean array of size height by width
}
   
   protected TestArrayWorld(TestArrayWorld prev) {
      width = prev.width;
      height = prev.height;
      generation = (prev.generation + 1);// TODO: set generation equal to prev.generation+1
      cells = new boolean [height][width];// TODO: set cells to reference a new rectangular two-dimensional
      //       boolean array of size height by width
}
   
     	

   public boolean getCell(int col, int row) { 	if (row < 0 || row > cells.length - 1) return false;
   												if (col < 0 || col > cells[row].length - 1) return false;
   												
   												return cells[row][col];
											 }
   public void setCell(int col, int row, boolean alive) { if ((row > -1 & row < cells.length ) & (col > -1 || col < cells[row].length)){
																cells[row][col] = alive;
															} }
   public int getWidth()  { return width; }
   public int getHeight()  { return height; }
   public int getGeneration()  { return generation; }
   public int getPopulation()  { return 0; }
   public void print(Writer w) {
   									PrintWriter pw = new PrintWriter(w);
   									
   									pw.println("-"); 
   									for (int row = 0; row < cells.length; row++) { 
      									for (int col = 0; col < cells[row].length; col++) {
         									pw.print(getCell(col, row) ? "#" : "_"); 
      										}
   										pw.println(); 
   										} 
   									// See the Java documentation for PrintWriter
   								
  			 						// use pw.print("something") to write to the writer
   									// use pw.println("something") to write a newline
  
   									// you must always call pw.flush() at the end of this method
   									// to force the PrintWriter to write to the terminal (if you
   									// do not, then data may be buffered inside PrintWriter).
   									pw.flush();
								}		
   public void draw(Graphics g, int width, int height)  { /*Leave empty*/ }
   
   private TestArrayWorld nextGeneration() {
      //Construct a new TestArrayWorld object to hold the next generation:
      TestArrayWorld world = new TestArrayWorld(this);
      //TODO: Use for loops with "setCell" and "computeCell" to populate "world"
      for (int row = 0; row < cells.length; row++) { 
      		for (int col = 0; col < cells[row].length; col++) {
      			
         		world.setCell(col,row,computeCell(cells,col,row));
         		
      			} 
   		} 
      return world;
}

private int countNeighbours(boolean[][] world, int col, int row) {
  		int pos = col + (row*8);
  		int posmod = pos % 8;
  		int i = 0;
  		if (getCell(col+1,row+1)) {i+=1;};
  		if (getCell(col+1,row)) {i+=1;};
  		if (getCell(col+1,row-1)) {i+=1;};
  		if (getCell(col,row-1)) {i+=1;};
  		if (getCell(col,row+1)) {i+=1;};
  		if (getCell(col-1,row-1)) {i+=1;};
  		if (getCell(col-1,row)) {i+=1;};
  		if (getCell(col-1,row+1)) {i+=1;};
  		
  		return i;
  		
  	}
  	
  	private boolean computeCell(boolean[][] world,int col,int row) {

   // liveCell is true if the cell at position (col,row) in world is live
   		boolean liveCell = getCell(col, row);
	
   // neighbours is the number of live neighbours to cell (col,row)
   		int neighbours = countNeighbours(world, col, row);

   // we will return this value at the end of the method to indicate whether 
   // cell (col,row) should be live in the next generation
   		boolean nextCell = false;
	
   //A live cell with less than two neighbours dies (underpopulation)
   		if (neighbours < 2) {
      		nextCell = false;
   		}
 
   //A live cell with two or three neighbours lives (a balanced population)
   		if (liveCell & ((neighbours == 2) || (neighbours == 3))) {
   			nextCell = true;
   		}

   //A live cell with with more than three neighbours dies (overcrowding)
   		if (liveCell & (neighbours > 3)) {
   			nextCell = false;
		}
   //A dead cell with exactly three live neighbours comes alive
   		if ((!liveCell) & (neighbours == 3)) {
   			nextCell = true;
   		}
	
   return nextCell;
}
   
   public World nextGeneration(int log2StepSize)  {
      TestArrayWorld world = this;
      for (int i = 0; i < (1<<log2StepSize); i++)//TODO: repeat the statement in curly brackets 2^log2StepSize times
      {
         world = world.nextGeneration();
      }
      return world;
   }
   //TODO: Add any other private methods which you find helpful
}
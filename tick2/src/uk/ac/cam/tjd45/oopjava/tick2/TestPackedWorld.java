package uk.ac.cam.tjd45.oopjava.tick2;

import uk.ac.cam.acr31.life.World;
import java.io.Writer;
import java.awt.Graphics;
import java.io.PrintWriter;
import java.io.OutputStreamWriter;

public class TestPackedWorld implements World {
   private int generation;
   private int width;
   private int height;
   private long cells;
   
   public TestPackedWorld() {
      width = 8;
      height = 8;
      generation = 0;// TODO: set generation equal to zero
      cells = 0L;// TODO: set cells to reference a new rectangular two-dimensional
      //       boolean array of size height by width
}
   
   protected TestPackedWorld(TestPackedWorld prev) {
      width = prev.width;
      height = prev.height;
      generation = (prev.generation + 1);// TODO: set generation equal to prev.generation+1
      cells = 0L;// TODO: set cells to reference a new rectangular two-dimensional
      //       boolean array of size height by width
}
   
     	
   
   public boolean getCell(int col, int row) {
		if ((col > 7) | (row > 7) | (col < 0) | (row<0)) {
			return false;
		} else {
			int pos = col + (row*8);
			return PackedLong.get(cells,pos);
		}
		
	   }

   public void setCell(int col, int row, boolean value)  {
	if ((col > 7) | (row > 7)) {
		
	} else {
	
		int pos = col + (row*8);
		cells = PackedLong.set(cells,pos,value);
		}
	
	}
											
   public int getWidth()  { return width; }
   public int getHeight()  { return height; }
   public int getGeneration()  { return generation; }
   public int getPopulation()  { return 0; }
   public void print(Writer w) {
   									PrintWriter pw = new PrintWriter(w);
   									
   									pw.println("-"); 
   									for (int row = 0; row < height; row++) { 
      									for (int col = 0; col < width; col++) {
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
   
   private TestPackedWorld nextGeneration() {
      //Construct a new TestPackedWorld object to hold the next generation:
      TestPackedWorld world = new TestPackedWorld(this);
      //TODO: Use for loops with "setCell" and "computeCell" to populate "world"
      for (int row = 0; row < height; row++) { 
      		for (int col = 0; col < width; col++) {
      			
         		world.setCell(col,row,computeCell(cells,col,row));
         		
      			} 
   		} 
      return world;
}

private int countNeighbours(long world, int col, int row) {
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
  	
  	private boolean computeCell(long world,int col,int row) {

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
      TestPackedWorld world = this;
      for (int i = 0; i < (1<<log2StepSize); i++)//TODO: repeat the statement in curly brackets 2^log2StepSize times
      {
         world = world.nextGeneration();
      }
      return world;
   }
   //TODO: Add any other private methods which you find helpful
}
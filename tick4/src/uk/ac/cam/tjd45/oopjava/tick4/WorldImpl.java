package uk.ac.cam.tjd45.oopjava.tick4;

import java.awt.Color;
//TODO: insert other appropriate "import" statements here
import java.awt.Graphics;
import java.io.PrintWriter;
import java.io.Writer;

import uk.ac.cam.acr31.life.World;

public abstract class WorldImpl implements World {

   private int width;
   private int height;
   private int generation;

   protected WorldImpl(int width, int height) {
      this.width = width;
      this.height = height;
      this.generation = 0;
   }
  
   protected WorldImpl(WorldImpl prev) {
      this.width = prev.width;
      this.height = prev.height;
      this.generation = prev.generation + 1;
   } 

   public int getWidth() { return this.width; }

   public int getHeight() { return this.height; }
  
   public int getGeneration() { return this.generation; }
 
   public int getPopulation() { return 0; }

   protected String getCellAsString(int col,int row) {
      return getCell(col,row) ? "#" : "_";
   }

   protected Color getCellAsColour(int col,int row) {
      return getCell(col,row) ? Color.BLACK : Color.WHITE;
   }  
   public void draw(Graphics g,int width, int height) {
   int worldWidth = getWidth();
   int worldHeight = getHeight();
  
   double colScale = (double)width/(double)worldWidth;
   double rowScale = (double)height/(double)worldHeight;
  
   for(int col=0; col<worldWidth; ++col) {
      for(int row=0; row<worldHeight; ++row) {
         int colPos = (int)(col*colScale);
         int rowPos = (int)(row*rowScale);
         int nextCol = (int)((col+1)*colScale);
         int nextRow = (int)((row+1)*rowScale);

         if (g.hitClip(colPos,rowPos,nextCol-colPos,nextRow-rowPos)) {
            g.setColor(getCellAsColour(col, row));
            g.fillRect(colPos,rowPos,nextCol-colPos,nextRow-rowPos);
         }
      } 
   }  
 }

   //TODO: Complete here in parent
   public World nextGeneration(int log2StepSize) {
	   WorldImpl world = this;
	   //generation += 1<<log2StepSize;
	  //System.out.println(world.getGeneration());
	    
	      for (int i = 0; i < 1<< log2StepSize; i++)//TODO: repeat the statement in curly brackets 2^log2StepSize times
	      {
	         world = world.nextGeneration();
	         
	      }
	      return world;//Remember to call nextGeneration 2^log2StepSize times
   }

   //TODO: Complete here in parent
   public void print(Writer w) {
	   
	   PrintWriter pw = new PrintWriter(w);
			
			pw.println("-"); 
			for (int row = 0; row < this.height; row++) { 
				for (int col = 0; col < this.width; col++) {
				pw.print(getCellAsString(col, row)); 
					}
				pw.println(); 
				} 
			
			pw.flush();
      //Use getCellAsString to get text representation of the cell
   }
 
   //TODO: Complete here in parent
   protected int countNeighbours(int col, int row) {
	    int pos = col + (row*8);
 		int i = 0;
 		if (getCell(col+1,row+1)) {i+=1;};
 		if (getCell(col,row+1)) {i+=1;};
 		if (getCell(col-1,row+1)) {i+=1;};
 		if (getCell(col-1,row)) {i+=1;};
 		if (getCell(col+1,row)) {i+=1;};
 		if (getCell(col-1,row-1)) {i+=1;};
 		if (getCell(col,row-1)) {i+=1;};
 		if (getCell(col+1,row-1)) {i+=1;};
 		
 		return i;//Compute the number of live neighbours
   }

   //TODO: Complete here in parent
   protected boolean computeCell(int col, int row) {

	   // liveCell is true if the cell at position (col,row) in world is live
	   		boolean liveCell = getCell(col, row);
		
	   // neighbours is the number of live neighbours to cell (col,row)
	   		int neighbours = countNeighbours(col, row);

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
	  //Compute whether this cell is alive or dead in the next generation
      //using "countNeighbours"
   }

   // Will be implemented by child class. Return true if cell (col,row) is alive.
   public abstract boolean getCell(int col,int row);

   // Will be implemented by child class. Set a cell to be live or dead.
   public abstract void setCell(int col, int row, boolean alive);

   // Will be implemented by child class. Step forward one generation.
   protected abstract WorldImpl nextGeneration();
}







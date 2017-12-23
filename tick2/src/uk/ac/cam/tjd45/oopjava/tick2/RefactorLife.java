package uk.ac.cam.tjd45.oopjava.tick2; //TODO: replace your-crsid

import java.io.OutputStreamWriter;
import java.io.Writer;

import uk.ac.cam.acr31.life.World;

public class RefactorLife {
      
/*	public static void main(String[] args) throws java.io.IOException {
     	try {
     		if (args.length == 0)
     			throw new PatternFormatException("Please specify a pattern.");
      		Pattern p = new Pattern(args[0]);
      		TestArrayWorld world = new TestArrayWorld(p.getWidth(),p.getHeight());
     		p.initialise(world);
     		play(world);
      	}
		catch (PatternFormatException e) {
			System.out.println(e.getMessage());
		}
     	
  	}*/
	
	public static void main(String[] args) throws java.io.IOException{
		   if (args.length != 1 && args.length != 2) {
		      System.out.println("RefactorLife [optional worldType] [pattern]");
		      return;
		   }
		   String worldType = args.length == 2 ? args[0] : "--array";
		   try {
			   Pattern p = null;
		   if (args.length > 1) {p = new Pattern(args[1]);}
		   else{
			   try{
			   p = new Pattern(args[0]);
			   }
			   catch (PatternFormatException e)
			   {
				   throw new PatternFormatException("Please specify a pattern.");
			   }
		   };//TODO: initialise other variables here and interpret format string
		   //      which defines the size and state of the world
		   World world = null;
		   if (worldType.equals("--array")) {
		      world = new ArrayWorld(p.getWidth(),p.getHeight());
		   }else if (worldType.equals("--long")) {
		      world = new PackedWorld();
		   } else {
			   System.out.println("You have not specified a valid type of world. Expected '--array' or '--long'");
		return; }
		   
		   p.initialise(world);
		   play(world);
		   }
		   catch (PatternFormatException e) {
				System.out.println(e.getMessage());
			}
		   
		   
		   //TODO: use the "world" variable to display the state of the world
		   //      on the terminal using the "print" method
		}

   
//   public static boolean getCell(boolean[][] world, int col, int row) {
//   		if (row < 0 || row > world.length - 1) return false;
//   		if (col < 0 || col > world[row].length - 1) return false;

//   		return world[row][col];
//	}

//   public static void setCell(boolean[][] world, int col, int row, boolean value)  {
//		if ((row > -1 & row < world.length ) & (col > -1 || col < world[row].length)){
//			world[row][col] = value;
//		}
//		
//	}
	
	
//	public static void print(boolean[][] world) { 
//   		System.out.println("-"); 
//   		for (int row = 0; row < world.length; row++) { 
//      		for (int col = 0; col < world[row].length; col++) {
//        		System.out.print(getCell(world, col, row) ? "#" : "_"); 
//      			}
//   			System.out.println(); 
//   		} 
//	}
  
//  	public static int countNeighbours(boolean[][] world, int col, int row) {
//  		int pos = col + (row*8);
//  		int posmod = pos % 8;
//  		int i = 0;
//  		if (getCell(world,col+1,row+1)) {i+=1;};
//  		if (getCell(world,col+1,row)) {i+=1;};
//  		if (getCell(world,col+1,row-1)) {i+=1;};
//  		if (getCell(world,col,row-1)) {i+=1;};
//  		if (getCell(world,col,row+1)) {i+=1;};
//  		if (getCell(world,col-1,row-1)) {i+=1;};
//  		if (getCell(world,col-1,row)) {i+=1;};
//  		if (getCell(world,col-1,row+1)) {i+=1;};
  		
//  		return i;
  		
//  	}
  	
//  	public static boolean computeCell(boolean[][] world,int col,int row) {

   // liveCell is true if the cell at position (col,row) in world is live
//   		boolean liveCell = getCell(world, col, row);
	
   // neighbours is the number of live neighbours to cell (col,row)
//   		int neighbours = countNeighbours(world, col, row);

   // we will return this value at the end of the method to indicate whether 
   // cell (col,row) should be live in the next generation
//   		boolean nextCell = false;
	
   //A live cell with less than two neighbours dies (underpopulation)
//   		if (neighbours < 2) {
//      		nextCell = false;
//   		}
 
   //A live cell with two or three neighbours lives (a balanced population)
//   		if (liveCell & ((neighbours == 2) || (neighbours == 3))) {
//   			nextCell = true;
//   		}

   //A live cell with with more than three neighbours dies (overcrowding)
//   		if (liveCell & (neighbours > 3)) {
//   			nextCell = false;
//		}
   //A dead cell with exactly three live neighbours comes alive
//   		if ((!liveCell) & (neighbours == 3)) {
//   			nextCell = true;
//   		}
	
//   return nextCell;
//}
  	
//  	public static boolean[][] nextGeneration(boolean[][] world) { 
//		boolean[][] oldworld = new boolean[world.length][];
//		for(int i=0;i<world.length;i++) {
//   			oldworld[i] = new boolean[world[i].length];
//   				for(int j=0;j<world[i].length;j++) {
//      				oldworld[i][j] = world[i][j];
//   				}
//		}
  		
//   		for (int row = 0; row < world.length; row++) { 
//      		for (int col = 0; col < world[row].length; col++) {
//      			
//         		setCell(world,col,row,computeCell(oldworld,col,row));
         		
//      			} 
//   		} 
//   		return world;
//  	}
  	
  	public static void play(World world) throws java.io.IOException {
   		int userResponse = 0;
   		while (userResponse != 'q') {
   			Writer w = new OutputStreamWriter(System.out);
   			world.print(w);
      		userResponse = System.in.read();
      		world = world.nextGeneration(0);
   		}
	}
  	
}
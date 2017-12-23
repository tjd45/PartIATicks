package uk.ac.cam.tjd45.oopjava.tick2;

public class PackedWorld extends WorldImpl{
	private long cells;

	protected PackedWorld() {
		super(8,8);
		cells = 0L;
		// TODO Auto-generated constructor stub
	}

	protected PackedWorld(PackedWorld prev) {
		super(prev);
		cells = 0L;
	      // TODO: set cells to reference a new rectangular two-dimensional
	      //       boolean array of size height by width
	}
	
	@Override
	public boolean getCell(int col, int row) {
		if ((col > 7) | (row > 7) | (col < 0) | (row<0)) {
			return false;
		} else {
			int pos = col + (row*8);
			return PackedLong.get(cells,pos);
		}
		
	   }

	@Override
	public void setCell(int col, int row, boolean alive) {
		if ((col > 7) | (row > 7)) {
			
		} else {
		
			int pos = col + (row*8);
			cells = PackedLong.set(cells,pos,alive);
			}
		
		}

	@Override
	protected PackedWorld nextGeneration() {
		//Construct a new TestPackedWorld object to hold the next generation:
	      PackedWorld world = new PackedWorld(this);
	      //TODO: Use for loops with "setCell" and "computeCell" to populate "world"
	      for (int row = 0; row < 8; row++) { 
	      		for (int col = 0; col < 8; col++) {
	      			
	         		world.setCell(col,row,computeCell(col,row));
	         		
	      			} 
	   		} 
	      return world;
	}

}

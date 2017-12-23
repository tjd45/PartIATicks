package uk.ac.cam.tjd45.oopjava.tick3;

public class ArrayWorld extends WorldImpl {
	private boolean[][] cells;

	protected ArrayWorld(int width, int height) {
		super(width, height);
		cells = new boolean[height][width];

		// TODO Auto-generated constructor stub
	}

	protected ArrayWorld(ArrayWorld prev) {
		super(prev);
		cells = new boolean[prev.getHeight()][prev.getWidth()];
		// TODO: set cells to reference a new rectangular two-dimensional
		// boolean array of size height by width
	}

	@Override
	public boolean getCell(int col, int row) {
		if (row < 0 || row > (getHeight() - 1))
			return false;
		if (col < 0 || col > (getWidth() - 1))
			return false;
		return cells[row][col];
	}

	@Override
	public void setCell(int col, int row, boolean alive) {
		if (row < 0 || row > getHeight() - 1) {
			return;
		}
		if (col < 0 || col > getWidth() - 1) {
			return;
		}
		{
			cells[row][col] = alive;
		}
	}

	@Override
	protected ArrayWorld nextGeneration() {
		// Construct a new TestArrayWorld object to hold the next generation:

		ArrayWorld world = new ArrayWorld(this);

		// TODO: Use for loops with "setCell" and "computeCell" to populate
		// "world"
		for (int row = 0; row < getHeight(); row++) {
			for (int col = 0; col < getWidth(); col++) {

				world.setCell(col, row, computeCell(col, row));

			}
		}
		return world;
	}

}

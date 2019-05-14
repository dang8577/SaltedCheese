package sudoku;

import java.util.*;


public class Grid 
{
	private int[][]						values;


	//
	// DON'T CHANGE THIS.
	//
	// Constructs a Grid instance from a string[] as provided by TestGridSupplier.
	// See TestGridSupplier for examples of input.
	// Dots in input strings represent 0s in values[][].
	//
	public Grid(String[] rows)
	{
		values = new int[9][9];
		for (int j=0; j<9; j++)
		{
			String row = rows[j];
			char[] charray = row.toCharArray();
			for (int i=0; i<9; i++)
			{
				char ch = charray[i];
				if (ch != '.')
					values[j][i] = ch - '0';
			}
		}
	}


	//
	// DON'T CHANGE THIS.
	//
	public String toString()
	{
		String s = "";
		for (int j=0; j<9; j++)
		{
			for (int i=0; i<9; i++)
			{
				int n = values[j][i];
				if (n == 0)
					s += '.';
				else
					s += (char)('0' + n);
			}
			s += "\n";
		}
		return s;
	}


	//
	// DON'T CHANGE THIS.
	// Copy ctor. Duplicates its source. You’ll call this 9 times in next9Grids.
	//
	Grid(Grid src)
	{
		values = new int[9][9];
		for (int j=0; j<9; j++)
			for (int i=0; i<9; i++)
				values[j][i] = src.values[j][i];
	}


	//
	// COMPLETE THIS
	//
	//
	// Finds an empty member of values[][]. Returns an array list of 9 grids that look like the current grid,
	// except the empty member contains 1, 2, 3 .... 9. Returns null if the current grid is full. Don’t change
	// “this” grid. Build 9 new grids.
	// 
	//
	// Example: if this grid = 1........
	//                         .........
	//                         .........
	//                         .........
	//                         .........
	//                         .........
	//                         .........
	//                         .........
	//                         .........
	//
	// Then the returned array list would contain:
	//
	// 11.......          12.......          13.......          14.......    and so on     19.......
	// .........          .........          .........          .........                  .........
	// .........          .........          .........          .........                  .........
	// .........          .........          .........          .........                  .........
	// .........          .........          .........          .........                  .........
	// .........          .........          .........          .........                  .........
	// .........          .........          .........          .........                  .........
	// .........          .........          .........          .........                  .........
	// .........          .........          .........          .........                  .........
	//
	public ArrayList<Grid> next9Grids()
	{		
		int xOfNextEmptyCell = 0;
		int yOfNextEmptyCell = 0;

		// Find x,y of an empty cell.
		

		if(isFull())
		{
			return null;
		}
		boolean found = false;

		for(int row = 0; row < 9 && found == false; row++)
		{
			for(int column = 0; column < 9 && found == false ; column++)
			{
				if(values[row][column] == 0)
				{
					xOfNextEmptyCell = row;
					yOfNextEmptyCell = column;
					found = true;
					break;

				}


			}

		}

		if(found == false)
		{
			return null;
		}


		/**

		boolean isFull = true;
		ArrayList<Grid> grids = new ArrayList<Grid>();


		for(int i = 1; i <= 9; i++)
		{
			Grid newGrid = null;
			for(int row = 0; row < 9; row++)
			{
				for(int column = 0; column < 9; column++)
				{
					if(values[row][column] == 0)
					{
						newGrid.values[row][column] = i;

					}
					else
					{
						newGrid.values[row][column] = values[row][column];
					}
				}


			}

			if(isFull)
			{
				return null;
			}
			else
			{
				grids.add(newGrid);
			}
		}
		 */

		// Construct array list to contain 9 new grids.


		ArrayList<Grid> grids = new ArrayList<Grid>();

		// Create 9 new grids as described in the comments above. Add them to grids.

		for(int i = 1; i <= 9; i++)
		{
			Grid newGrid = new Grid(this);
			newGrid.values[xOfNextEmptyCell][yOfNextEmptyCell] = i;
			grids.add(newGrid);

		}

		return grids;
	}


	//
	// COMPLETE THIS
	//
	// Returns true if this grid is legal. A grid is legal if no row, column, or
	// 3x3 block contains a repeated 1, 2, 3, 4, 5, 6, 7, 8, or 9.
	//

	public boolean ContainsUnique(int[] arr)
	{
		Arrays.sort(arr);
		for (int i = 0; i < arr.length - 1; i++) {
			if (arr[i] == 0 || arr[i + 1] == 0) return true;
			if (arr[i] == arr[i + 1]) return false;
		}
		return true;
	}


	public boolean isLegal()
	{
		// Check every row. If you find an illegal row, return false.

		//int count = 0;
		int[] temp = {0,0,0,0,0,0,0,0,0};
		int[] tempBlock = {0,0,0,0,0,0,0,0,0};


		for(int i = 0; i < values.length; i++)
		{
			for (int j = 0; j < values[0].length; j++) {
				temp[j] = values[i][j];
			}
			if(!ContainsUnique(temp))
			{
				return false;
			}
		}


		for(int column = 0; column < values.length; column++)
		{
			for(int row = 0; row < values.length; row++)
			{
				
				temp[row] = values[row][column];
			}
			if(!ContainsUnique(temp))
			{
				return false;
			}
		}





		// Check every block. If you find an illegal block, return false.

		for(int row = 0; row < values.length; row += 3)
		{
			for(int column = 0; column < values[0].length; column += 3)
			{
				int count = 0;
				for(int smallRow = row; smallRow < row + 3; smallRow++)
				{
					for(int smallColumn = column; smallColumn < column + 3; smallColumn++)
					{
						tempBlock[count++] = values[smallRow][smallColumn];

					}
				}
				if(!ContainsUnique(tempBlock))
				{
					return false;
				}
			}
		}




		// All rows/cols/blocks are legal.
		return true;
	}


	//
	// COMPLETE THIS
	//
	// Returns true if every cell member of values[][] is a digit from 1-9.
	//


	public boolean isFull()
	{
		for(int row = 0 ; row < values.length; row++)
		{
			for(int column = 0; column < values[0].length; column++)
			{
				//if(values[row][column] < 1 || values[row][column] > 9)
				if(values[row][column] == 0)
				{
					return false;
				}
			}
		}
		return true;
	}


	//
	// COMPLETE THIS
	//
	// Returns true if x is a Grid and, for every (i,j), 
	// x.values[i][j] == this.values[i][j].
	//
	public boolean equals(Object x)
	{
		Grid that = (Grid)x;
		for(int row = 0 ; row < values.length; row++)
		{
			for(int column = 0; column < values[0].length; column++)
			{
				if(that.values[row][column] != this.values[row][column] )
				{
					return false;
				}
			}
		}
		return true;
	}
}

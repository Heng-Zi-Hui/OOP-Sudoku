package sudoku;

/**
 * The Sudoku number puzzle to be solved (
 */

public class Puzzle {
	
	// All variables have package access  
	// The numbers on the puzzle
	int[][] numbers = new int[GameBoard.GRID_SIZE][GameBoard.GRID_SIZE];
	   
	// The masks - to show or not   
	boolean[][] isShown = new boolean[GameBoard.GRID_SIZE][GameBoard.GRID_SIZE];
	int edge = 3;
	   
	// Constructor   
	public Puzzle() {		
		super();   
	}

	   
	// Generate a new puzzle given the number of cells to be guessed  
		// Need to set the arrays numbers and isShown  
		public void newPuzzle(int numToGuess) {  
			
			fillDiagonal();
			
			fillRemaining(0, edge);
							
			boolean[][] hardcodedIsShown =
		         {{true, true, true, true, true, true, true, true, true},
		          {true, true, true, true, true, true, true, true, true},
		          {true, true, true, true, true, true, true, true, true},
		          {true, true, true, true, true, true, true, true, true},
		          {true, true, true, true, true, true, true, true, true},
		          {true, true, true, true, true, true, true, true, true},
		          {true, true, true, true, true, true, true, true, true},
		          {true, true, true, true, true, true, true, true, true},
		          {true, true, true, true, true, true, true, true, true}};
			
			  int count = numToGuess; 
			  while (count != 0) { 
				  int cellId = randomGenerator(GameBoard.GRID_SIZE * GameBoard.GRID_SIZE)-1;
			  
				  int row = (cellId / GameBoard.GRID_SIZE); 		  
				  int col = cellId % 9; 
				  
				  if (col != 0)
					  col = col - 1;
			  
				  if (hardcodedIsShown[row][col] == true) { 			  
					  count--; 
					  hardcodedIsShown[row][col] = false; 
				  } 
			  }
			 
		      
		      for (int row = 0; row < GameBoard.GRID_SIZE; ++row) {
		         for (int col = 0; col < GameBoard.GRID_SIZE; ++col) {
		            isShown[row][col] = hardcodedIsShown[row][col];
		         }
		      }
			
		}
		
		void  fillDiagonal() {
			for(int i=0; i<GameBoard.GRID_SIZE; i=i+edge) {
				
				// for diagonal box, start coordinates->i==j
				fillBox(i, i);
			}
		}
		
		void fillBox(int row,int col)
	    {
	        int num;
	        for (int i=0; i<edge; i++)
	        {
	            for (int j=0; j<edge; j++)
	            {
	                do
	                {
	                    num = randomGenerator(GameBoard.GRID_SIZE);
	                }
	                while (!unUsedInBox(row, col, num));
	 
	                numbers[row+i][col+j] = num;
	            }
	        }
	    }
		
		// Returns false if given 3 x 3 block contains number.
	    boolean unUsedInBox(int rowStart, int colStart, int num)
	    {
	        for (int i = 0; i<edge; i++)
	            for (int j = 0; j<edge; j++)
	                if (numbers[rowStart+i][colStart+j] == num)
	                    return false;
	 
	        return true;
	    }
		
		
		// Generate random number 
	    int randomGenerator(int num)
	    {
	        return (int) Math.floor((Math.random() * num+1));
	    }
	 
	    // Check if safe to insert to cell
	    boolean CheckIfSafe(int row, int col, int num)
	    {
	        return (unUsedInRow(row, num) &&
	                unUsedInCol(col, num) &&
	                unUsedInBox(row - row % edge, col - col % edge, num));
	    }
	 
	    // check in the row for existence
	    boolean unUsedInRow(int row, int num)
	    {
	        for (int j = 0; j<GameBoard.GRID_SIZE; j++)
	           if (numbers[row][j] == num)
	                return false;
	        return true;
	    }
	 
	    // check in the col for existence
	    boolean unUsedInCol(int col,int num)
	    {
	        for (int i = 0; i<GameBoard.GRID_SIZE; i++)
	            if (numbers[i][col] == num)
	                return false;
	        return true;
	    }
		
		
	    //Fill remaining
	    boolean fillRemaining(int row, int col)
	    {
	        if (col>=GameBoard.GRID_SIZE && row<GameBoard.GRID_SIZE-1)
	        {
	            row = row + 1;
	            col = 0;
	        }
	        if (row>=GameBoard.GRID_SIZE && col>=GameBoard.GRID_SIZE)
	            return true;
	 
	        if (row < edge)
	        {
	            if (col < edge)
	                col = edge;
	        }
	        else if (row < GameBoard.GRID_SIZE-edge)
	        {
	            if (col == (int)(row/edge)*edge)
	                col =  col + edge;
	        }
	        else
	        {
	            if (col == GameBoard.GRID_SIZE-edge)
	            {
	                row = row + 1;
	                col = 0;
	                if (row >= GameBoard.GRID_SIZE)
	                    return true;
	            }
	        }
	 
	        for (int num = 1; num<=GameBoard.GRID_SIZE; num++)
	        {
	            if (CheckIfSafe(row, col, num))
	            {
	                numbers[row][col] = num;
	                if (fillRemaining(row, col+1))
	                    return true;
	 
	                numbers[row][col] = 0;
	            }
	        }
	        return false;
	    }
}

package sudoku;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import java.util.Scanner;

import javax.swing.*;

public class GameBoard extends JPanel {
	
	// Name-constants for the game board properties	   
	public static final int GRID_SIZE = 9;    // Size of the board   
	public static final int SUBGRID_SIZE = 3; // Size of the sub-grid

	   
	// Name-constants for UI sizes	   
	public static final int CELL_SIZE = 60;   // Cell width/height in pixels   
	public static final int BOARD_WIDTH  = CELL_SIZE * GRID_SIZE;	   
	public static final int BOARD_HEIGHT = CELL_SIZE * GRID_SIZE; // Board width/height in pixels

	// The game board composes of 9x9 "Customized" JTextFields,	   
	private Cell[][] cells = new Cell[GRID_SIZE][GRID_SIZE];


	// It also contains a Puzzle	   
	private Puzzle puzzle = new Puzzle();

	Random random = new Random();
	int min, max;
	
	   
	// Constructor	   
	public GameBoard() {	      
		super.setLayout(new GridLayout(GRID_SIZE, GRID_SIZE));  // JPanel
	      
		// Allocate the 2D array of Cell, and added into JPanel.	      
		for (int row = 0; row < GRID_SIZE; ++row) {	         
			for (int col = 0; col < GRID_SIZE; ++col) {            
				cells[row][col] = new Cell(row, col);
	            super.add(cells[row][col]);   // JPanel        
	            cells[row][col].setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
	            cells[row][col].setHorizontalAlignment(JTextField.CENTER);
	            cells[row][col].setFont(new Font("Monospaced", Font.BOLD, 20));
	            if (row == 0 || row == 3 || row == 6) { //Top border
	            	if(col == 0) { //Top & Left border
	            		cells[row][col].setBorder(BorderFactory.createMatteBorder(6,6,1,1,Color.DARK_GRAY));
	            	}
	            	else if(col == 2 || col == 5 || col == 8) { //Top & Right border
	            		cells[row][col].setBorder(BorderFactory.createMatteBorder(6,1,1,6,Color.DARK_GRAY));
	            	}
	            	else { // Top border
	            		cells[row][col].setBorder(BorderFactory.createMatteBorder(6,1,1,1,Color.DARK_GRAY));
	            	}
	            	
	            }
	            if (row == 8) { //Bottom border
	            	if (col == 0) { //Bottom & Left border
	            		cells[row][col].setBorder(BorderFactory.createMatteBorder(1,6,6,1,Color.DARK_GRAY));
	            	}
	            	else if (col == 2 || col == 5 || col == 8) { //Bottom & right border
	            		cells[row][col].setBorder(BorderFactory.createMatteBorder(1,1,6,6,Color.DARK_GRAY));
	            	}
	            	else { //Bottom border
	            		cells[row][col].setBorder(BorderFactory.createMatteBorder(1,1,6,1,Color.DARK_GRAY));
	            	}
	            }
	            if (row == 1 || row == 2 || row == 4 || row == 5 || row == 7) { //Right/Left Borders
	            	if (col == 2 || col == 5 || col == 8) { //Right border
	            		cells[row][col].setBorder(BorderFactory.createMatteBorder(1,1,1,6,Color.DARK_GRAY));
	            	}
	            	else if (col == 0){ //Left border
	            		cells[row][col].setBorder(BorderFactory.createMatteBorder(1,6,1,1,Color.DARK_GRAY));
	            	}
	            }
			}
		}

	      
		
	// [TODO 3] Allocate a common listener as the ActionEvent listener for all the
	//  Cells (JTextFields)  
		CellInputListener listener = new CellInputListener();
	
	// [TODO 4] Every editable cell adds this common listener
		for (int row = 0; row < GRID_SIZE; ++row) {
			   for (int col = 0; col < GRID_SIZE; ++col) {
			      if (cells[row][col].isEditable()) {
			         cells[row][col].addActionListener(listener);   // For all editable rows and cols
			      }
			   }
			}
	      
		super.setPreferredSize(new Dimension(BOARD_WIDTH, BOARD_HEIGHT));
	   
	}

	
	/**
	* Initialize the puzzle number, status, background/foreground color,
	*   of all the cells from puzzle[][] and isRevealed[][].
	* Call to start a new game.
	*/
	   
	

	
	public void easyBoard() {  		//call this method for easy mode
		
		min = 10;
		max = 15;
		
		int value = random.nextInt(max + min) + min;
		
		// Get a new puzzle
		puzzle.newPuzzle(value);

	      
		// Based on the puzzle, initialize all the cells. 
		for (int row = 0; row < GRID_SIZE; ++row) {   
			for (int col = 0; col < GRID_SIZE; ++col) {     
				cells[row][col].init(puzzle.numbers[row][col], puzzle.isShown[row][col]);   
			}     
		}   
	}
	
	public void mediumBoard() {		//call this method for medium mode
		
		min = 15;
		max = 25;
		
		int value = random.nextInt(max + min) + min;
		
		
		// Get a new puzzle
		puzzle.newPuzzle(value);

	      
		// Based on the puzzle, initialize all the cells. 
		for (int row = 0; row < GRID_SIZE; ++row) {   
			for (int col = 0; col < GRID_SIZE; ++col) {     
				cells[row][col].init(puzzle.numbers[row][col], puzzle.isShown[row][col]);   
			}     
		}   
	}
	
	public void hardBoard() {		//call this method for hard mode
		
		min = 25;
		max = 35;
		
		int value = random.nextInt(max + min) + min;
		
		// Get a new puzzle
		puzzle.newPuzzle(value);

	      
		// Based on the puzzle, initialize all the cells. 
		for (int row = 0; row < GRID_SIZE; ++row) {   
			for (int col = 0; col < GRID_SIZE; ++col) {     
				cells[row][col].init(puzzle.numbers[row][col], puzzle.isShown[row][col]);   
			}     
		}   
	}

	// Generate random number 
    int randomGenerator(int num)
    {
        return (int) Math.floor((Math.random() * num+1));
    }
	
    
	/**
	* Return true if the puzzle is solved
	* i.e., none of the cell have status of NO_GUESS or WRONG_GUESS
	*/
    
	public boolean isSolved() {
	      
		for (int row = 0; row < GRID_SIZE; ++row) {        
			for (int col = 0; col < GRID_SIZE; ++col) {
				if (cells[row][col].status == CellStatus.NO_GUESS || cells[row][col].status == CellStatus.WRONG_GUESS) {
					return false;          
				} 
			} 
		}
		return true;
	   
	}

	   
	
	// [TODO 2] Define a Listener Inner Class
	private class CellInputListener implements ActionListener {
	      
		@Override	      
		public void actionPerformed(ActionEvent e) {
	         
			// Get a reference of the JTextField that triggers this action event         
			Cell sourceCell = (Cell)e.getSource();
			 
			//define the word "help" 
			String help = "help";
			
			/*
			 * String cellString = sourceCell.getText();
			 * 
			 * System.out.println(cellString);
			 * 
			 * if (cellString.equals(help)) {
			 * 
			 * System.out.println("help"); } else { System.out.println("error"); }
			 */
			
			
			if (sourceCell.getText().equals(help)) { 	//check if user inputs "help" in cell, can set as button as layout is ready
				
				int hintReveal = 0;
				int hintRow = sourceCell.row;
				int hintCol = sourceCell.col;
						 
						 
				//System.out.println("You entered row: " + hintRow + " and col: " + hintCol);	 
 
				//Reveals the number that user types "help" in that specific cell
				cells[hintRow][hintCol].init(hintReveal = puzzle.numbers[hintRow][hintCol], puzzle.isShown[hintRow][hintCol] = true);	
				System.out.println("Hint reaveal for row: " + hintRow + " and col: " + hintCol + " is " + hintReveal);
				
				
						 
			}
			
			
			
			else {		//proceeds to check the number input
				
				// Retrieve the int entered        
				int numberIn = Integer.parseInt(sourceCell.getText());
				
				// For debugging       
				System.out.println("You entered " + numberIn);
				
				
				/*
				* [TODO 5]
		        * Check the numberIn against sourceCell.number.
		        * Update the cell status sourceCell.status,
		        * and re-paint the cell via sourceCell.paint().
		        */        
				
				  if (numberIn == sourceCell.number) { 
					  sourceCell.status = CellStatus.CORRECT_GUESS; 
				  } 
				  else { 
					  sourceCell.status = CellStatus.WRONG_GUESS; 
				}
				  
				  sourceCell.paint();
			}
			
		 
	          
	        /*
	        * [TODO 6][Later] Check if the player has solved the puzzle after this move,
	        *   by call isSolved(). Put up a congratulation JOptionPane, if so.
	        */
			
			if(isSolved()) {
				JOptionPane.showMessageDialog(null, "Congratulation!");
			}
	      }
		
	   }
	
	
	void RevealNCells(int n) {
		
		//int n = Integer.parseInt(sourceCell.getText());
		
		//Reveals the cells that has this number n
		/*for (int row = 0; row < GRID_SIZE; ++row) {        
			for (int col = 0; col < GRID_SIZE; ++col) {
				if(puzzle.numbers[row][col] == n) {
					cells[row][col].init(puzzle.numbers[row][col], puzzle.isShown[row][col] = true);
				}
				
			} 
		}*/
		
			
		
	}
	

	

}

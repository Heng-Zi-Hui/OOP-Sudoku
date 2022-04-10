package sudoku;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


/**
 * The main Sudoku program
 */

public class SudokuMain extends JFrame{

	// private variables	   
	GameBoard board = new GameBoard(); 
	JButton btnNewGame = new JButton("New Game");
	   
	// Constructor	   
	public SudokuMain() {
	      
		Container cp = getContentPane();    
		cp.setLayout(new BorderLayout());
  
		cp.add(board, BorderLayout.CENTER);

	      
		// Add a button to the south to re-start the game

	    
		// Easy board for easy button
		//board.easyBoard();
		
		// Medium board for easy button
		//board.mediumBoard();
		
		// Hard board for hard button
		//board.hardBoard();
	      
		pack();     // Pack the UI components, instead of setSize()
  
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // Handle window closing  
		setTitle("Sudoku");     
		setVisible(true);   
	}

	   
	/** The entry main() entry method */
	   
	public static void main(String[] args) {   
		// [TODO 1] Check Swing program template on how to run the constructor
		
		// Recommended to run the GUI construction in      
		//  Event Dispatching thread for thread-safety operations
		SwingUtilities.invokeLater(new Runnable() {
	         @Override
	         public void run() {
	            new SudokuMain(); // Let the constructor does the job
	         }
	      });
	}
}

package sudoku;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


/**
 * The main Sudoku program
 */

public class SudokuMain {

	private static final String INTRO = "intro";
	private static final String GAME = "game";
	private CardLayout cardlayout = new CardLayout();	
	private JPanel mainPanel = new JPanel(cardlayout);
	private IntroPanel introPanel = new IntroPanel();
	private GamePanel gamePanel = new GamePanel();

	public SudokuMain() {    
		mainPanel.add(introPanel.getMainComponent(), INTRO);   
		mainPanel.add(gamePanel.getMainComponent(), GAME);
  
		introPanel.addBazBtnActionListener(new ActionListener() {  
			@Override    
			public void actionPerformed(ActionEvent e) { 
				cardlayout.show(mainPanel, GAME);   
			}   
		});
   
		gamePanel.addBackBtnActionListener(new ActionListener() {	         
			@Override    
			public void actionPerformed(ActionEvent e) {     
				cardlayout.show(mainPanel, INTRO);   
			} 
		});    
			
	}

	private JComponent getMainComponent() { 
		return mainPanel;  
	}
	   
	private static void createAndShowUI() { 
		JFrame frame = new JFrame("Sudoku!");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	      
		frame.getContentPane().add(new SudokuMain().getMainComponent()); 
		frame.pack();   
		frame.setLocationRelativeTo(null); 
		frame.setVisible(true);  
	}
	   
	public static void main(String[] args) {   
		java.awt.EventQueue.invokeLater(new Runnable() {    
			public void run() {   
				createAndShowUI();   
			}    
		});  
	}
}

	
class IntroPanel { 
	private JPanel mainPanel = new JPanel(); 
	private JLabel lblWelcome;    // Declare a Label component
	private JLabel lblLevel;    // Declare a Label component	   
	private JRadioButton rbEasy,rbMedium, rbHard;
	    
	private JButton start = new JButton("Start"); 
	private JButton exit = new JButton("Exit");

	public IntroPanel() {    
		mainPanel.setLayout(new FlowLayout());
	       
		lblWelcome = new JLabel("Welcome to Sudoku!!");    
		lblLevel = new JLabel("Choose difficulty level: ");
	       
		rbEasy = new JRadioButton("Easy");  
		rbEasy.setBounds(100,50,100,30);   
		rbMedium = new JRadioButton("Medium");   
		rbMedium.setBounds(100,50,100,30);    
		rbHard = new JRadioButton("Hard"); 
		rbHard.setBounds(100,50,100,30);  

		mainPanel.add(lblWelcome);  
		mainPanel.add(lblLevel);
	       
		mainPanel.add(rbEasy);	
		mainPanel.add(rbMedium);    
		mainPanel.add(rbHard);
	         
		mainPanel.add(start);   
		mainPanel.add(exit);

		exit.addActionListener(new ActionListener() {  
			@Override   
			public void actionPerformed(ActionEvent e) { 
				Window win = SwingUtilities.getWindowAncestor(mainPanel);
	            win.dispose(); 
			}	      
		});	   
	}
   
	public void addBazBtnActionListener(ActionListener listener) {	      
		start.addActionListener(listener);
	
	}

	public JComponent getMainComponent() {   
		return mainPanel; 
	}
}

	
class GamePanel {
	
	GameBoard board = new GameBoard();
		    
	private JPanel mainPanel = new JPanel();  
	private JButton back;
	private JButton reset;
	
		      
	/** Constructor to setup the game and the GUI */  
	public GamePanel() {  
		
		board.easyBoard();   
		mainPanel.add(board);
		        
		back = new JButton("Return to main menu");
		reset = new JButton("Reset puzzle");
		mainPanel.add(back);
		mainPanel.add(reset);
		
	}
		
   
	public JComponent getMainComponent() {   
		return mainPanel;   
	}

	     
	public void addBackBtnActionListener(ActionListener listener) {   
		back.addActionListener(listener);  
		
		
		reset.addActionListener(new ActionListener() {
	         @Override
	         public void actionPerformed(ActionEvent evt) {
	        	mainPanel.remove(board);
	 			
	 			GameBoard newBoard = new GameBoard();
	 			newBoard.easyBoard();
	 			
	 			mainPanel.add(newBoard);
	 			SwingUtilities.updateComponentTreeUI(newBoard);
	         }
	      });
	}
	
}


	
	

	

	

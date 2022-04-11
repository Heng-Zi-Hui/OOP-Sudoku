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
  
		introPanel.addStartBtnActionListener(new ActionListener() {  
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
	private JPanel panel = new JPanel();
	private JLabel lblWelcome;    // Declare a Label component
	//private JLabel lblLevel;    // Declare a Label component	   
	//private JRadioButton rbEasy,rbMedium, rbHard;
	    
	private JButton start = new JButton("Start"); 
	private JButton exit = new JButton("Exit");

	public IntroPanel() {    
		mainPanel.setLayout(new BorderLayout());
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		panel.setBackground(Color.DARK_GRAY);
		
		/*JLabel background=new JLabel(new ImageIcon("sudoku.jpeg"));
		mainPanel.add(background);
		background.setLayout(new FlowLayout());
		
		background.add(start);
		background.add(exit);*/
	       
		lblWelcome = new JLabel("Welcome to Sudoku!!");  
		lblWelcome.setForeground(Color.WHITE);
		/*lblLevel = new JLabel("Choose difficulty level: ");
	       
		rbEasy = new JRadioButton("Easy");  
		rbEasy.setBounds(100,50,100,30);   
		rbMedium = new JRadioButton("Medium");   
		rbMedium.setBounds(100,50,100,30);    
		rbHard = new JRadioButton("Hard"); 
		rbHard.setBounds(100,50,100,30);  
*/
		panel.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel.add(lblWelcome);  
		//mainPanel.add(lblLevel);
	       
		/*mainPanel.add(rbEasy);	
		mainPanel.add(rbMedium);    
		mainPanel.add(rbHard);*/
	         
		panel.add(start);   
		panel.add(exit);
		mainPanel.add(panel,BorderLayout.CENTER);

		exit.addActionListener(new ActionListener() {  
			@Override   
			public void actionPerformed(ActionEvent e) { 
				Window win = SwingUtilities.getWindowAncestor(mainPanel);
	            win.dispose(); 
			}	      
		});	   
	}
   
	public void addStartBtnActionListener(ActionListener listener) {	      
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
	private JMenu menu;  
    private JMenuItem easy, medium, hard;  
	
		      
	/** Constructor to setup the game and the GUI */  
	public GamePanel() {  
		mainPanel.setLayout(new BorderLayout());
		JPanel panel = new JPanel(new GridLayout(1, 3));
		
		board.easyBoard();   
		mainPanel.add(board, BorderLayout.CENTER);
		         
        JMenuBar mb=new JMenuBar();  
        menu=new JMenu("Change Difficulty Level");   
        easy=new JMenuItem("Easy");  
        medium=new JMenuItem("Medium");  
        hard=new JMenuItem("Hard");  
        menu.add(easy); menu.add(medium); menu.add(hard);
        mb.add(menu);  
        
		back = new JButton("Return to main menu");
		reset = new JButton("Reset puzzle");
		panel.add(mb);
		panel.add(reset);
		panel.add(back);
		mainPanel.add(panel, BorderLayout.SOUTH);
		
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
		
		easy.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mainPanel.remove(board);
	 			
	 			GameBoard newBoard = new GameBoard();
	 			newBoard.easyBoard();
	 			
	 			mainPanel.add(newBoard);
	 			SwingUtilities.updateComponentTreeUI(newBoard);
			}
		});
		
		medium.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mainPanel.remove(board);
	 			
	 			GameBoard newBoard = new GameBoard();
	 			newBoard.mediumBoard();
	 			
	 			mainPanel.add(newBoard);
	 			SwingUtilities.updateComponentTreeUI(newBoard);
			}
		});
		
		hard.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mainPanel.remove(board);
	 			
	 			GameBoard newBoard = new GameBoard();
	 			newBoard.hardBoard();
	 			
	 			mainPanel.add(newBoard);
	 			SwingUtilities.updateComponentTreeUI(newBoard);
			}
		});
	}
	
}


	
	

	

	

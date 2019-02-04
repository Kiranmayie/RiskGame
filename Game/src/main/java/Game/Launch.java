package Game;

import javax.swing.JButton;
import javax.swing.JFrame;

class Launch {
	
	public Launch()
	{
		
	JFrame frame= new JFrame("RISKGAME-STARTSCREEN");
		JButton Button1 = new JButton("Play New Game");
		JButton Button2 = new JButton("Play Saved Game");
		JButton Button3 = new JButton("Tournament Play");
		JButton Button4 = new JButton("Map Editor");
		JButton Button5 = new JButton("EXIT GAME");
		 Button1.setBounds(200, 150, 150, 50); 
		 Button2.setBounds(201, 200, 150, 50); 
		 Button3.setBounds(202, 250, 150, 50); 
		 Button4.setBounds(203, 300, 150, 50); 
		 //Button5.setBounds(204, 350, 150, 50); 
         
	        // setting close operation 
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
	  
	        // adds button in JFrame 
	        frame.add(Button1); 
	        frame.add(Button2);
	        frame.add(Button3);
	        frame.add(Button4);
	        frame.add(Button5);
	        // sets 500 width and 600 height 
	        frame.setSize(500, 600); 
	        // uses no layout managers 
	        frame.setLayout(null); 
	        // makes the frame visible 
	        frame.setVisible(true);
	       
	        Button5.addActionListener(e-> System.exit(0));        
	
	}
}

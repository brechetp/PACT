package iug;

import java.awt.Color;
import java.awt.Frame;

import javax.swing.JFrame;

public class Fenetre extends JFrame{
	
	Panneau pan = new Panneau();
	
	public Fenetre(){
		pan.setBackground(Color.orange);
		this.setContentPane(pan);
	    this.setTitle("CARDS : Communication Action Reaction and Display System");
	    this.setSize(1024, 768);
	    this.setLocationRelativeTo(null);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
	   this.setExtendedState(Frame.MAXIMIZED_BOTH);
	    //this.setResizable(false);
	    this.setUndecorated(true);
	    this.setVisible(true);
	    
	}
	
	public Panneau getPan(){
		return pan;
	}

}

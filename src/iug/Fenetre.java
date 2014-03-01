package iug;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

public class Fenetre extends JFrame implements ActionListener{
	
	Panneau pan = new Panneau();
	private JButton exit = new JButton("    ");
	private JButton bout1 = new JButton("Dame Pique");
	private JButton bout2 = new JButton ("Valet Coeur");
	JPanel container = new JPanel();

	public void build(){
		    JLabel image1 = new JLabel();
	        image1.setIcon(new ImageIcon("C:/Users/Benjamin-Zigaroula-/Desktop/Cartes/dame_pique.jpg"));
			this.pan.setBackground(Color.decode("#FFFFFF"));
			exit.addActionListener(this);
			this.setTitle("CARDS");
			this.setUndecorated(true);
			GraphicsEnvironment environment = GraphicsEnvironment.getLocalGraphicsEnvironment();
			GraphicsDevice device = environment.getDefaultScreenDevice();
			device.setFullScreenWindow(this);
			this.setLocationRelativeTo(null);
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			this.setAlwaysOnTop(true);
		    container.setBackground(Color.white);
		    container.setLayout(new BorderLayout());
		    container.add(pan, BorderLayout.NORTH);
		    container.add(exit, BorderLayout.SOUTH);
		    container.add(image1, BorderLayout.CENTER);
		    pan.add(bout1);
		    pan.add(bout2);
			this.setContentPane(container); 
			this.setVisible(true);

	}
	
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if (source == exit){
			this.dispose();
		}
		
	}

}

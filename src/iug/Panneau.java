package iug;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Panneau extends JPanel {
	
	Carte carte1 = new Carte(1);
	Carte carte2 = new Carte(2);
	Carte carte3 = new Carte(3);
	Carte carte4 = new Carte(4);
	Carte carte5 = new Carte(5);
	Carte carte6 = new Carte(6);
	Carte carte7 = new Carte(7);
	Carte carte8 = new Carte(8);
	Carte carte9 = new Carte(9);
	Carte carte10 = new Carte(10);
	Carte carte11 = new Carte(11);
	Carte carte12 = new Carte(12);
	Carte carte13 = new Carte(13);
	Carte carte14 = new Carte(14);
	Carte carte15 = new Carte(15);
	Carte carte16 = new Carte(16);
	Carte carte17 = new Carte(17);
	Carte carte18 = new Carte(18);
	Carte carte19 = new Carte(19);
	Carte carte20 = new Carte(20);
	Carte carte21 = new Carte(21);
	Carte carte22 = new Carte(22);
	Carte carte23 = new Carte(23);
	Carte carte24 = new Carte(24);
	Carte carte25 = new Carte(25);
	Carte carte26 = new Carte(26);
	Carte carte27 = new Carte(27);
	Carte carte28 = new Carte(28);
	Carte carte29 = new Carte(29);
	Carte carte30 = new Carte(30);
	Carte carte31 = new Carte(31);
	Carte carte32 = new Carte(32);
	
	int i = 0;
	
	public void paintComponent(Graphics g){
		
		try {
			if (i>32){
				i=0;
			}
		    Image img = ImageIO.read(new File("./Cartes/ARRIEREPLAN.png"));
		    g.drawImage(img, 0, 0, this);
		    if(i==1){
		    	  g.drawImage(carte1.getImage(), 460, 250, this);
		    }
		    else if(i==2){
		    	  g.drawImage(carte2.getImage(), 460, 250, this);
		    }
		    else if(i==0){
		    }
		} catch (IOException e) {
		    e.printStackTrace();
		}
		System.out.println(i);
	}
	
	public void setI(int i){
		this.i=i;
	}
	
	public int getI(){
		return i;
	}

}

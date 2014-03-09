package iug;

import java.awt.Color;
import java.awt.Font;
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
	int k = 0;
	int j = 0;
	int h = 0;
	int y = 0;
	String s1;
	String s2;
	
	public void paintComponent(Graphics g){
		
		try {
			if (i>32){
				i=0;
			}
		    Image img = ImageIO.read(new File("./Cartes/ARRIEREPLAN.png"));
		    g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
		    if(i==1){
		    	  g.drawImage(carte1.getImage(), 460, 250, this);
		    }
		    else if(i==2){
		    	  g.drawImage(carte2.getImage(), 460, 250, this);
		    }
		    else if(i==3){
		    	  g.drawImage(carte3.getImage(), 460, 250, this);
		    }
		    else if(i==4){
		    	  g.drawImage(carte4.getImage(), 460, 250, this);
		    }
		    else if(i==5){
		    	  g.drawImage(carte5.getImage(), 460, 250, this);
		    }
		    else if(i==6){
		    	  g.drawImage(carte6.getImage(), 460, 250, this);
		    }
		    else if(i==7){
		    	  g.drawImage(carte7.getImage(), 460, 250, this);
		    }
		    else if(i==8){
		    	  g.drawImage(carte8.getImage(), 460, 250, this);
		    }
		    else if(i==9){
		    	  g.drawImage(carte9.getImage(), 460, 250, this);
		    }
		    else if(i==10){
		    	  g.drawImage(carte10.getImage(), 460, 250, this);
		    }
		    else if(i==11){
		    	  g.drawImage(carte11.getImage(), 460, 250, this);
		    }
		    else if(i==12){
		    	  g.drawImage(carte12.getImage(), 460, 250, this);
		    }
		    else if(i==13){
		    	  g.drawImage(carte13.getImage(), 460, 250, this);
		    }
		    else if(i==14){
		    	  g.drawImage(carte14.getImage(), 460, 250, this);
		    }
		    else if(i==15){
		    	  g.drawImage(carte15.getImage(), 460, 250, this);
		    }
		    else if(i==16){
		    	  g.drawImage(carte16.getImage(), 460, 250, this);
		    }
		    else if(i==17){
		    	  g.drawImage(carte17.getImage(), 460, 250, this);
		    }
		    else if(i==18){
		    	  g.drawImage(carte18.getImage(), 460, 250, this);
		    }
		    else if(i==19){
		    	  g.drawImage(carte19.getImage(), 460, 250, this);
		    }
		    else if(i==20){
		    	  g.drawImage(carte20.getImage(), 460, 250, this);
		    }
		    else if(i==21){
		    	  g.drawImage(carte21.getImage(), 460, 250, this);
		    }
		    else if(i==22){
		    	  g.drawImage(carte22.getImage(), 460, 250, this);
		    }
		    else if(i==23){
		    	  g.drawImage(carte23.getImage(), 460, 250, this);
		    }
		    else if(i==24){
		    	  g.drawImage(carte24.getImage(), 460, 250, this);
		    }
		    else if(i==25){
		    	  g.drawImage(carte25.getImage(), 460, 250, this);
		    }
		    else if(i==26){
		    	  g.drawImage(carte26.getImage(), 460, 250, this);
		    }
		    else if(i==27){
		    	  g.drawImage(carte27.getImage(), 460, 250, this);
		    }
		    else if(i==28){
		    	  g.drawImage(carte28.getImage(), 460, 250, this);
		    }
		    else if(i==29){
		    	  g.drawImage(carte29.getImage(), 460, 250, this);
		    }
		    else if(i==30){
		    	  g.drawImage(carte30.getImage(), 460, 250, this);
		    }
		    else if(i==31){
		    	  g.drawImage(carte31.getImage(), 460, 250, this);
		    }
		    else if(i==32){
		    	  g.drawImage(carte32.getImage(), 460, 250, this);
		    }
		    else if(i==0){

		    }
		    
		    if (k==0){
		    	
		    }
		    else if (k>0 && k<5){
		    	g.setColor(new Color(204, 0, 51));
				g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 40));
		    	g.drawString("Le joueur " + k + " a coinché !!!!!", 300, 400);
		    }
		    else if (k==5){
		    	g.setColor(new Color(204, 0, 51));
				g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 40));
		    	g.drawString("CONTRE-COINCHE !!!", 320, 400);
		    }
		    else if (k==666){
		    	j=0;
		    	i=0;
		    	y=0;
		    	h=0;
		    	g.setColor(new Color(255, 0, 0));
				g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 80));
		    	g.drawString("PARTIE TERMINEE", 180, 400);
		    }
		    if (j!=0 && h==0){
		    	g.setColor(new Color(0,51,153));
		    	g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 25));
		    	g.drawString("C'est au joueur " + j + " de jouer !", 350 , 600);
		    }
		    if (j!=0 && h==1){
		    	g.setColor(new Color(0,153,153));
		    	g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 25));
		    	g.drawString("C'est au joueur " + j + " d'annoncer !", 350 , 600);
		    }
		    if (j==0){
		    	
		    }
		    if (y!=0){
		    	g.setColor(new Color(255,153,51));
		    	g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 25));
		    	g.drawString("Annonce en cours : ", 300 , 650);
		    	if(y==1){
		    		g.setColor(new Color(255,0,0));
			    	g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 25));
			    	g.drawString(s1, 540 , 650);
			    	g.setColor(new Color(255,153,51));
			    	g.drawString(s2, 650 , 650);
		    	}
		    	if(y==2){
		    		g.setColor(new Color(255,153,51));
			    	g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 25));
			    	g.drawString(s1, 540 , 650);
			    	g.setColor(new Color(255,0,0));
			    	g.drawString(s2, 670 , 650);
		    	}
		    }
		    if (y==0){
		    	
		    }
		} catch (IOException e) {
		    e.printStackTrace();
		}
	}
	
	public void setI(int i){
		this.i=i;
	}
	
	public int getI(){
		return i;
	}
	
	public void setK(int k){
		this.k = k;
	}
	
	public int getK(){
		return k;
	}
	
	public void setJ(int j){
		this.j = j;
	}
	
	public int getJ(){
		return j;
	}
	
	public void setH(int h){
		this.h = h;
	}
	
	public int getH(){
		return h;
	}
	
	public void setY(int y){
		this.y=y;
	}
	
	public int getY(){
		return y;
	}
	
	public void setStrings(String s1, String s2){
		this.s1=s1;
		this.s2=s2;
	}

}

package iug;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
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
	int m = 0;
	int yCarte = 0;
	String s1;
	String s2;
	String valeur;
	String couleur;
	
	public void paintComponent(Graphics g){
		
		try {
			if (i>32){
				i=0;
			}
		    Image img = ImageIO.read(new File("./Cartes/ARRIEREPLAN2.png"));
		    g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
		    if(i==1){	 
		    	  g.drawImage(carte1.getImage(), (this.getWidth()/2)-(carte1.getImage().getWidth(null)/2), yCarte, this);
		    }
		    else if(i==2){
		    	  g.drawImage(carte2.getImage(), (this.getWidth()/2)-(carte2.getImage().getWidth(null)/2), yCarte, this);
		    }
		    else if(i==3){
		    	  g.drawImage(carte3.getImage(), (this.getWidth()/2)-(carte3.getImage().getWidth(null)/2), yCarte, this);
		    }
		    else if(i==4){
		    	  g.drawImage(carte4.getImage(), (this.getWidth()/2)-(carte4.getImage().getWidth(null)/2), yCarte, this);
		    }
		    else if(i==5){
		    	  g.drawImage(carte5.getImage(), (this.getWidth()/2)-(carte5.getImage().getWidth(null)/2), yCarte, this);
		    }
		    else if(i==6){
		    	  g.drawImage(carte6.getImage(), (this.getWidth()/2)-(carte6.getImage().getWidth(null)/2), yCarte, this);
		    }
		    else if(i==7){
		    	  g.drawImage(carte7.getImage(), (this.getWidth()/2)-(carte7.getImage().getWidth(null)/2), yCarte, this);
		    }
		    else if(i==8){
		    	  g.drawImage(carte8.getImage(), (this.getWidth()/2)-(carte8.getImage().getWidth(null)/2), yCarte, this);
		    }
		    else if(i==9){
		    	  g.drawImage(carte9.getImage(), (this.getWidth()/2)-(carte9.getImage().getWidth(null)/2), yCarte, this);
		    }
		    else if(i==10){
		    	  g.drawImage(carte10.getImage(), (this.getWidth()/2)-(carte10.getImage().getWidth(null)/2), yCarte, this);
		    }
		    else if(i==11){
		    	  g.drawImage(carte11.getImage(), (this.getWidth()/2)-(carte11.getImage().getWidth(null)/2), yCarte, this);
		    }
		    else if(i==12){
		    	  g.drawImage(carte12.getImage(), (this.getWidth()/2)-(carte12.getImage().getWidth(null)/2), yCarte, this);
		    }
		    else if(i==13){
		    	  g.drawImage(carte13.getImage(), (this.getWidth()/2)-(carte13.getImage().getWidth(null)/2), yCarte, this);
		    }
		    else if(i==14){
		    	  g.drawImage(carte14.getImage(), (this.getWidth()/2)-(carte14.getImage().getWidth(null)/2), yCarte, this);
		    }
		    else if(i==15){
		    	  g.drawImage(carte15.getImage(), (this.getWidth()/2)-(carte15.getImage().getWidth(null)/2), yCarte, this);
		    }
		    else if(i==16){
		    	  g.drawImage(carte16.getImage(), (this.getWidth()/2)-(carte16.getImage().getWidth(null)/2), yCarte, this);
		    }
		    else if(i==17){
		    	  g.drawImage(carte17.getImage(), (this.getWidth()/2)-(carte17.getImage().getWidth(null)/2), yCarte, this);
		    }
		    else if(i==18){
		    	  g.drawImage(carte18.getImage(), (this.getWidth()/2)-(carte18.getImage().getWidth(null)/2), yCarte, this);
		    }
		    else if(i==19){
		    	  g.drawImage(carte19.getImage(), (this.getWidth()/2)-(carte19.getImage().getWidth(null)/2), yCarte, this);
		    }
		    else if(i==20){
		    	  g.drawImage(carte20.getImage(), (this.getWidth()/2)-(carte20.getImage().getWidth(null)/2), yCarte, this);
		    }
		    else if(i==21){
		    	  g.drawImage(carte21.getImage(), (this.getWidth()/2)-(carte21.getImage().getWidth(null)/2), yCarte, this);
		    }
		    else if(i==22){
		    	  g.drawImage(carte22.getImage(), (this.getWidth()/2)-(carte22.getImage().getWidth(null)/2), yCarte, this);
		    }
		    else if(i==23){
		    	  g.drawImage(carte23.getImage(), (this.getWidth()/2)-(carte23.getImage().getWidth(null)/2), yCarte, this);
		    }
		    else if(i==24){
		    	  g.drawImage(carte24.getImage(), (this.getWidth()/2)-(carte24.getImage().getWidth(null)/2), yCarte, this);
		    }
		    else if(i==25){
		    	  g.drawImage(carte25.getImage(), (this.getWidth()/2)-(carte25.getImage().getWidth(null)/2), yCarte, this);
		    }
		    else if(i==26){
		    	  g.drawImage(carte26.getImage(), (this.getWidth()/2)-(carte26.getImage().getWidth(null)/2), yCarte, this);
		    }
		    else if(i==27){
		    	  g.drawImage(carte27.getImage(), (this.getWidth()/2)-(carte27.getImage().getWidth(null)/2), yCarte, this);
		    }
		    else if(i==28){
		    	  g.drawImage(carte28.getImage(), (this.getWidth()/2)-(carte28.getImage().getWidth(null)/2), yCarte, this);
		    }
		    else if(i==29){
		    	  g.drawImage(carte29.getImage(), (this.getWidth()/2)-(carte29.getImage().getWidth(null)/2), yCarte, this);
		    }
		    else if(i==30){
		    	  g.drawImage(carte30.getImage(), (this.getWidth()/2)-(carte30.getImage().getWidth(null)/2), yCarte, this);
		    }
		    else if(i==31){
		    	  g.drawImage(carte31.getImage(), (this.getWidth()/2)-(carte31.getImage().getWidth(null)/2), yCarte, this);
		    }
		    else if(i==32){
		    	  g.drawImage(carte32.getImage(), (this.getWidth()/2)-(carte32.getImage().getWidth(null)/2), yCarte, this);
		    }
		    else if(i==0){

		    }
		    
		    if (k==0){
		    	
		    }
		    else if (k>0 && k<5){
		    	g.setColor(new Color(204, 0, 51));
				g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 50));
				FontMetrics metrics = g.getFontMetrics();
		    	g.drawString("Le joueur " + k + " a coinché !!!!!", (this.getWidth()/2)-(metrics.stringWidth("Le joueur " + k + " a coinché !!!!!")/2), (this.getHeight()/2));
		    }
		    else if (k==5){
		    	g.setColor(new Color(204, 0, 51));
				g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 50));
				FontMetrics metrics = g.getFontMetrics();
		    	g.drawString("CONTRE-COINCHE !!!", (this.getWidth()/2)-(metrics.stringWidth("CONTRE-COINCHE !!!")/2), (this.getHeight()/2));
		    }
		    else if (k==6){
		    	g.setColor(new Color(204, 0, 51));
				g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 50));
				FontMetrics metrics = g.getFontMetrics();
		    	g.drawString("Annonce joueur distant : " + valeur + " " + couleur, (this.getWidth()/2)-(metrics.stringWidth("Annonce joueur distant : " + valeur + " " + couleur)/2), (this.getHeight()/2));
		    }
		    else if (k==7){
		    	g.setColor(new Color(204, 0, 51));
				g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 50));
				FontMetrics metrics = g.getFontMetrics();
		    	g.drawString("Le joueur distant a passé", (this.getWidth()/2)-(metrics.stringWidth("Le joueur distant a passé")/2), (this.getHeight()/2));
		    }
		    else if (k==666){
		    	j=0;
		    	i=0;
		    	y=0;
		    	h=0;
		    	g.setColor(new Color(255, 0, 0));
				g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 80));
				FontMetrics metrics = g.getFontMetrics();
		    	g.drawString("PARTIE TERMINEE", (this.getWidth()/2)-(metrics.stringWidth("PARTIE TERMINEE")/2), (this.getHeight()/2));
		    }
		    if (j!=0 && h==0){
		    	g.setColor(new Color(0,51,153));
		    	g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 35));
		    	FontMetrics metrics = g.getFontMetrics();
		    	g.drawString("C'est au joueur " + j + " de jouer !", (this.getWidth()/2)-(metrics.stringWidth("C'est au joueur " + j + " de jouer !")/2) , (int) ((this.getHeight()*0.7)));
		    }
		    if (j!=0 && h==1){
		    	g.setColor(new Color(0,153,153));
		    	g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 35));
		    	FontMetrics metrics = g.getFontMetrics();
		    	g.drawString("C'est au joueur " + j + " d'annoncer !", (this.getWidth()/2)-(metrics.stringWidth("C'est au joueur " + j + " d'annoncer !")/2) , (int) ((this.getHeight())*0.7));
		    }
		    if (j==0){
		    	
		    }
		    if (y!=0){
		    	g.setColor(new Color(255,153,51));
		    	g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 35));
		    	FontMetrics metrics = g.getFontMetrics();
		    	g.drawString("Annonce en cours : ", (this.getWidth()/2)-(metrics.stringWidth("Annonce en cours : " + s1 + " - " + s2)/2) , (int) ((this.getHeight()*0.8)));
		    	if(y==1){
		    		g.setColor(new Color(255,0,0));
			    	g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 35));
			    	g.drawString(s1, (this.getWidth()/2)-(metrics.stringWidth("Annonce en cours : " + s1 + " - " + s2)/2)+metrics.stringWidth("Annonce en cours : ") , (int) ((this.getHeight()*0.8)));
			    	g.setColor(new Color(255,153,51));
			    	g.drawString(" - ", (this.getWidth()/2)-(metrics.stringWidth("Annonce en cours : " + s1 + " - " + s2)/2)+metrics.stringWidth("Annonce en cours : " + s1), (int) ((this.getHeight()*0.8)));
			    	g.drawString(s2, (this.getWidth()/2)-(metrics.stringWidth("Annonce en cours : " + s1 + " - " + s2)/2)+metrics.stringWidth("Annonce en cours : " + s1 + " - ") , (int) ((this.getHeight()*0.8)));
		    	}
		    	if(y==2){
		    		g.setColor(new Color(255,153,51));
			    	g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 35));
			    	g.drawString(s1, (this.getWidth()/2)-(metrics.stringWidth("Annonce en cours : " + s1 + " - " + s2)/2)+metrics.stringWidth("Annonce en cours : ") , (int) ((this.getHeight()*0.8)));
			    	g.drawString(" - ", (this.getWidth()/2)-(metrics.stringWidth("Annonce en cours : " + s1 + " - " + s2)/2)+metrics.stringWidth("Annonce en cours : " + s1), (int) ((this.getHeight()*0.8)));
			    	g.setColor(new Color(255,0,0));
			    	g.drawString(s2, (this.getWidth()/2)-(metrics.stringWidth("Annonce en cours : " + s1 + " - " + s2)/2)+metrics.stringWidth("Annonce en cours : " + s1 + " - ") , (int) ((this.getHeight()*0.8)));
		    	}
		    }
		    if (y==0){
		    	
		    }
		    if (m==0){
		    	
		    }
		    if (m==1){
		    	g.setColor(new Color(51,0,153));
		    	g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 50));
		    	FontMetrics metrics = g.getFontMetrics();
		    	g.drawString("Le jeu commence !", (this.getWidth()/2)-(metrics.stringWidth("Le jeu commence !")/2), this.getHeight()/2);
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
	
	public void setYCarte(int yCarte){
		this.yCarte=yCarte;
	}
	
	public void setValeurCouleur(String valeur, String couleur){
		this.valeur = valeur;
		this.couleur = couleur;
	}
	
	public void setM(int m){
		this.m = m ;
	}

}

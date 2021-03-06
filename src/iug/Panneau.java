package iug;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import logiqueDeJeux.EtatDuJeu;

public class Panneau extends JPanel {
	
	
	Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
	int height = (int)dimension.getHeight();
	int width  = (int)dimension.getWidth();
	
	int pos = 0;
	int secAD = 10;
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
	

	ImageMenu imageMenuCentre = new ImageMenu(1);
	ImageMenu imageMenuGauche = new ImageMenu(2);
	ImageMenu imageMenuDroite = new ImageMenu(3);
	ImageMenu imageMenuNew = new ImageMenu(4);

	int intro = 0;
	int modeActuel = 0;
	
	
	int i = 0;
	int k = 0;
	int j = 0;
	int h = 0;
	int y = 0;
	
	int points1;
	int points2;
	int pointsAnnonce1;
	int pointsAnnonce2;
	int pointsPartie1;
	int pointsPartie2;
	int pointsTotal1;
	int pointsTotal2;
	String annoncelol;
	
	String message = null;
	
	int placement = 0;
	int yCarte = 0;
	
	int option1 = 0;
	int option2 = 0;
	int option3 = EtatDuJeu.valeurFinPartie;
	int optionEnCours = 0;
	
	int quitterEnCours = 0;
	
	int xMenuCentre = 0;
	int yMenuCentre = 0;
	int xMenuGauche = 0;
	int yMenuGauche = 0;
	int xMenuDroite = 0;
	int yMenuDroite = 0;
	int xMenuNew = 0;
	
	int posXMenuCentre = (int) (width/2)-(imageMenuCentre.getImage().getWidth(null)/2);
	int posYMenuCentre = (int) (0.6*height);
	int posXMenuGauche = (int) (((width/2)-(imageMenuGauche.getImage().getWidth(null)/2))*0.5);
	int posYMenuGauche = (int) (0.4*height);
	int posXMenuDroite = (int) (((width/2)-(imageMenuDroite.getImage().getWidth(null)/2))*1.5);
	int posYMenuDroite = (int) (0.4*height);
	
	int dxdy=0;
	int sec = 5;
	int secJD = 15;
	int cartesDistribuees = 0;
	
	String annonce;
	
	String s1;
	String s2;
	String valeur;
	String couleur;
	
	public void paintComponent(Graphics g){
		
		try {
			
		    Image img = ImageIO.read(new File("./Cartes/ARRIEREPLAN2.png"));
		    g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
		    
		    /******************************************************************************************************/
		    /**                                         MODE SETTINGS                                            **/
		    /******************************************************************************************************/
		    
		    if (modeActuel == 10001){
		    	g.setColor(new Color(255,153,51));
		    	g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 35));
		    	FontMetrics metrics = g.getFontMetrics();
		    	if (message==null){
		    		g.drawString("Reglage des Settings en cours", (this.getWidth()/2)-(metrics.stringWidth("Reglage des Settings en cours")/2) , (int) ((this.getHeight())*0.1));
		    	}
		    	else{
		    		g.drawString(message, (this.getWidth()/2)-(metrics.stringWidth(message)/2) , (int) ((this.getHeight())*0.1));
		    	}
		    }
		    
		    
		    /******************************************************************************************************/
		    /**                                         MODE A PROPOS                                            **/
		    /******************************************************************************************************/
		    
		    Image rapact = ImageIO.read(new File("./Cartes/rapact.png"));
		    if (modeActuel == 777){
			    g.drawImage(rapact, (this.getWidth()/2)-(rapact.getWidth(null)/2), (int) ((this.getHeight())*0.15), this);
		    	g.setColor(new Color(255,153,51));
		    	g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 40));
		    	FontMetrics metrics = g.getFontMetrics();
		    	g.drawString("Nous sommes les RA'PACT", (this.getWidth()/2)-(metrics.stringWidth("Nous sommes les RA'PACT")/2) , (int) ((this.getHeight())*0.1));
		    	g.drawString("Ce menu s'auto-détruira dans " + secAD + " secondes", (this.getWidth()/2)-(metrics.stringWidth("Ce menu s'auto-détruira dans " + secAD + " secondes")/2) , (int) ((this.getHeight())*0.95));
		    	g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
		    	FontMetrics metricslol = g.getFontMetrics();
		    	g.drawString("Julia la buse : elle part à la chasse aux paresseux", (this.getWidth()/2)-(metricslol.stringWidth("Pierre le busard pêcheur : il hameçonne les experts à coups de mails pour ne plus les lâcher")/2) , (int) ((this.getHeight())*0.61));
		    	g.drawString("Pierre le busard pêcheur : il hameçonne les experts à coups de mails pour ne plus les lâcher", (this.getWidth()/2)-(metricslol.stringWidth("Pierre le busard pêcheur : il hameçonne les experts à coups de mails pour ne plus les lâcher")/2) , (int) ((this.getHeight())*0.65));
		    	g.drawString("Michaël l'aigle : sa vue perçante décode la moindre erreur de Java", (this.getWidth()/2)-(metricslol.stringWidth("Pierre le busard pêcheur : il hameçonne les experts à coups de mails pour ne plus les lâcher")/2) , (int) ((this.getHeight())*0.69));
		    	g.drawString("Benjamin la chouette : il trouve ça mignon", (this.getWidth()/2)-(metricslol.stringWidth("Pierre le busard pêcheur : il hameçonne les experts à coups de mails pour ne plus les lâcher")/2) , (int) ((this.getHeight())*0.73));
		    	g.drawString("Edouard M le vautour : son ombre plane autour des réunions agonisantes", (this.getWidth()/2)-(metricslol.stringWidth("Pierre le busard pêcheur : il hameçonne les experts à coups de mails pour ne plus les lâcher")/2) , (int) ((this.getHeight())*0.77));
		    	g.drawString("Edouard E le hibou : lui vit la nuit et dort le jour", (this.getWidth()/2)-(metricslol.stringWidth("Pierre le busard pêcheur : il hameçonne les experts à coups de mails pour ne plus les lâcher")/2) , (int) ((this.getHeight())*0.81));
		    	g.drawString("Timothée le condor : c'en est déjà un beau spécimen", (this.getWidth()/2)-(metricslol.stringWidth("Pierre le busard pêcheur : il hameçonne les experts à coups de mails pour ne plus les lâcher")/2) , (int) ((this.getHeight())*0.85));
		    }
		    
		    
		    
		    
		    /******************************************************************************************************/
		    /**                                         MODE QUITTER                                             **/
		    /******************************************************************************************************/
		    
		    if(modeActuel == 666){
		    	g.setColor(new Color(255,153,51));
		    	g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 35));
		    	FontMetrics metrics = g.getFontMetrics();
		    	g.drawString("Voulez-vous vraiment quitter la partie ?", (this.getWidth()/2)-(metrics.stringWidth("Voulez-vous vraiment quitter la partie ?")/2) , (int) ((this.getHeight())*0.4));
		    	g.drawString("Accepter (Accepter) - Refuser (Attendre " + sec + " secondes)", (this.getWidth()/2)-(metrics.stringWidth("Accepter (Accepter) - Refuser (Attendre " + sec + " secondes)")/2) , (int) ((this.getHeight())*0.6));
		    }
		    
		    /******************************************************************************************************/
		    /**                                          MODE INTRO                                              **/
		    /******************************************************************************************************/
		    
		    
		    if (modeActuel == 1548){
		    	g.setColor(new Color(51, 0, 204));
				g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, intro));
				FontMetrics metrics = g.getFontMetrics();
		    	g.drawString("Bienvenue dans CARDS", (this.getWidth()/2)-(metrics.stringWidth("Bienvenue dans CARDS")/2), (this.getHeight()/2));
		    	if (intro > 73){
		    		g.setColor(new Color(51, 0, 153));
		    		g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 35));
					FontMetrics metrics2 = g.getFontMetrics();
			    	g.drawString("Chargement des données...", (this.getWidth()/2)-(metrics2.stringWidth("Chargement des donn�es...")/2), (int) ((this.getHeight()/2)*1.1));
		    	}
		    }
		    
		    /******************************************************************************************************/
		    /**                                          MODE PARTIE                                             **/
		    /******************************************************************************************************/
		    
		    
		    if(modeActuel == 1){
		    	if (i>32){
					i=0;
				}
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
			    if (k==12){
			    	g.setColor(new Color(51, 102, 204));
					g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 50));
					FontMetrics metrics = g.getFontMetrics();
			    	g.drawString("Faites vos annonces !", (this.getWidth()/2)-(metrics.stringWidth("Faites vos annonces !")/2), (this.getHeight()/2));
			    }
			    if (k==10){
			    	g.setColor(new Color(204, 0, 51));
					g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 50));
					FontMetrics metrics = g.getFontMetrics();
			    	g.drawString("Distribution des cartes", (this.getWidth()/2)-(metrics.stringWidth("Distribution des cartes")/2), (this.getHeight()/2));
			    	if (cartesDistribuees != 0){
			    		g.drawString(cartesDistribuees + " cartes distribuées", (this.getWidth()/2)-(metrics.stringWidth(cartesDistribuees + " cartes distribu�es")/2), (int) (this.getHeight()*0.6));
			    	}
			    }
			    if (k==11){
			    	g.setColor(new Color(51, 102, 204));
			    	g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 50));
			    	FontMetrics metrics = g.getFontMetrics();
			    	g.drawString("Le jeu commence !", (this.getWidth()/2)-(metrics.stringWidth("Le jeu commence !")/2), this.getHeight()/2);
			    }
			    if (k==13){
			    	g.setColor(new Color(204, 0, 51));
					g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 50));
					FontMetrics metrics = g.getFontMetrics();
			    	g.drawString(annonce, (this.getWidth()/2)-(metrics.stringWidth(annonce)/2), (this.getHeight()/2));
			    }
			    if (k==40){
			    	g.setColor(new Color(204,255,0));
			    	g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 50));
			    	FontMetrics metrics = g.getFontMetrics();
			    	g.drawString("Annonce : " + annoncelol, (this.getWidth()/2)-(metrics.stringWidth("Annonce : " + annoncelol)/2),  (int) (this.getHeight()*0.1));
			    	g.setColor(new Color(51, 102, 204));
			    	g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 40));
			    	FontMetrics metrics3 = g.getFontMetrics();
			    	g.drawString("Equipe N/S", (int)(this.getWidth()*0.5)-(metrics3.stringWidth("Equipe N/S")/2), (int) (this.getHeight()*0.3));
			    	g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 25));
			    	FontMetrics metrics2 = g.getFontMetrics();
			    	g.drawString("(Joueurs Présents)", (int)(this.getWidth()*0.5)-(metrics2.stringWidth("(Joueurs Pr�sents)")/2), (int) (this.getHeight()*0.34));
			    	g.setColor(new Color(51,255,255));
			    	g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 50));
			    	g.drawString("" + points1, (int)(this.getWidth()*0.5)-(metrics.stringWidth("" + points1)/2), (int) (this.getHeight()*0.5));
			    	g.drawString("" + pointsAnnonce1, (int)(this.getWidth()*0.5)-(metrics.stringWidth("" + pointsAnnonce1)/2), (int) (this.getHeight()*0.6));
			    	g.drawString("" + pointsPartie1, (int)(this.getWidth()*0.5)-(metrics.stringWidth("" + pointsPartie1)/2), (int) (this.getHeight()*0.7));
			    	g.drawString("" + pointsTotal1, (int)(this.getWidth()*0.5)-(metrics.stringWidth("" + pointsTotal1)/2), (int) (this.getHeight()*0.9));
			    	g.setColor(new Color(51, 102, 204));
			    	g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 40));
			    	g.drawString("Equipe E/O", (int)(this.getWidth()*0.8)-(metrics3.stringWidth("Equipe E/O")/2), (int) (this.getHeight()*0.3));
			    	g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 25));
			    	g.drawString("(Joueur Présent + Distant)", (int)(this.getWidth()*0.8)-(metrics2.stringWidth("(Joueur Pr�sent + Distant)")/2), (int) (this.getHeight()*0.34));
			    	g.setColor(new Color(51,255,255));
			    	g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 50));
			    	g.drawString("" + points2, (int)(this.getWidth()*0.8)-(metrics.stringWidth("" + points2)/2), (int) (this.getHeight()*0.5));
			    	g.drawString("" + pointsAnnonce2, (int)(this.getWidth()*0.8)-(metrics.stringWidth("" + pointsAnnonce2)/2), (int) (this.getHeight()*0.6));
			    	g.drawString("" + pointsPartie2, (int)(this.getWidth()*0.8)-(metrics.stringWidth("" + pointsPartie2)/2), (int) (this.getHeight()*0.7));
			    	g.drawString("" + pointsTotal2, (int)(this.getWidth()*0.8)-(metrics.stringWidth("" + pointsTotal2)/2), (int) (this.getHeight()*0.9));
			    	g.setColor(new Color(51, 102, 204));
			    	g.drawString("Points :", (int)(this.getWidth()*0.1)-(metrics.stringWidth("Points :")/2), (int) (this.getHeight()*0.5));
			    	g.drawString("Annonces :", (int)(this.getWidth()*0.1)-(metrics.stringWidth("Points :")/2), (int) (this.getHeight()*0.6));
			    	g.drawString("Points manche :", (int)(this.getWidth()*0.1)-(metrics.stringWidth("Points :")/2), (int) (this.getHeight()*0.7));
			    	g.drawString("Points total :", (int)(this.getWidth()*0.1)-(metrics.stringWidth("Points :")/2), (int) (this.getHeight()*0.9));
			    }
			    else if (k>0 && k<5){
			    	g.setColor(new Color(204, 0, 51));
					g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 50));
					FontMetrics metrics = g.getFontMetrics();
			    	g.drawString("COINCHE !!!", (this.getWidth()/2)-(metrics.stringWidth("COINCHE !!!")/2), (this.getHeight()/2));
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
			    	g.drawString("Le joueur distant a passé", (this.getWidth()/2)-(metrics.stringWidth("Le joueur distant a pass�")/2), (this.getHeight()/2));
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
			    	g.setColor(new Color(204, 255, 102));
			    	g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 35));
			    	FontMetrics metrics = g.getFontMetrics();
			    	g.drawString("C'est au joueur " + j + " de jouer !", (this.getWidth()/2)-(metrics.stringWidth("C'est au joueur " + j + " de jouer !")/2) , (int) ((this.getHeight()*0.2)));
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
			    	if(y==3){
			    		g.setColor(new Color(255,153,51));
				    	g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 35));
				    	g.drawString(" - ", (this.getWidth()/2)-(metrics.stringWidth("Annonce en cours : " + s1 + " - " + s2)/2)+metrics.stringWidth("Annonce en cours : " + s1), (int) ((this.getHeight()*0.8)));
				    	g.setColor(new Color(255,0,0));
				    	g.drawString(s1, (this.getWidth()/2)-(metrics.stringWidth("Annonce en cours : " + s1 + " - " + s2)/2)+metrics.stringWidth("Annonce en cours : ") , (int) ((this.getHeight()*0.8)));
				    	g.drawString(s2, (this.getWidth()/2)-(metrics.stringWidth("Annonce en cours : " + s1 + " - " + s2)/2)+metrics.stringWidth("Annonce en cours : " + s1 + " - ") , (int) ((this.getHeight()*0.8)));
				    	g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 25));
			    		FontMetrics metricsK = g.getFontMetrics();
			    		g.setColor(new Color(255,153,51));
				    	g.drawString("Voulez-vous valider cette annonce ? (Quitter pour refuser)", (this.getWidth()/2)-(metricsK.stringWidth("Voulez-vous valider cette annonce ? (Quitter pour refuser)")/2), (int) ((this.getHeight()*0.85)));
			    	
			    	}
			    }
			    if (y==0){
			    	
			    }
			    if (s1 != null && s2 != null){
			    	if(pos == 0){
			    		g.setColor(new Color(255,153,51));
				    	g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 35));
				    	FontMetrics metricsX = g.getFontMetrics();
				    	g.drawString("Annonce actuelle : " + s1 + " - " + s2, (this.getWidth()/2)-(metricsX.stringWidth("Annonce actuelle : " + s1 + " - " + s2)/2) , (int) ((this.getHeight()*0.1)));
			    	}
			    	else if(pos == 1){
			    		g.setColor(new Color(255,153,51));
				    	g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 35));
				    	FontMetrics metricsX = g.getFontMetrics();
				    	g.drawString("Annonce actuelle : " + s1 + " - " + s2, (this.getWidth()/2)-(metricsX.stringWidth("Annonce actuelle : " + s1 + " - " + s2)/2) , (int) ((this.getHeight()*0.1)));
			    	}
			    }
			    
		    }
		    
		    
		    
		    /******************************************************************************************************/
		    /**                                          MODE MENUS                                              **/
		    /******************************************************************************************************/
		    
		    
		    if (modeActuel == 0){
		    	
		    	
		    	
		    	if (placement == 0){
		    		g.drawImage(imageMenuCentre.getImage(), posXMenuCentre+xMenuCentre, posYMenuCentre+yMenuCentre, this);
			    	g.drawImage(imageMenuGauche.getImage(), posXMenuGauche+xMenuGauche, posYMenuGauche+yMenuGauche, this);
			    	g.drawImage(imageMenuDroite.getImage(), posXMenuDroite+xMenuDroite, posYMenuDroite+yMenuDroite, this);
		    	}
		    	
		    	if (placement == -1){
		    		g.drawImage(imageMenuCentre.getImage(), posXMenuCentre+xMenuCentre, posYMenuCentre+yMenuCentre, this);
			    	g.drawImage(imageMenuGauche.getImage(), posXMenuGauche+xMenuGauche, posYMenuGauche+yMenuGauche, this);
			    	g.drawImage(imageMenuDroite.getImage(), posXMenuDroite+xMenuDroite, posYMenuDroite+yMenuDroite, this);
			    	g.drawImage(imageMenuNew.getImage(), width+xMenuNew, posYMenuGauche, this);
		    	}
		    	
		    	if (placement == 1){
		    		g.drawImage(imageMenuCentre.getImage(), posXMenuCentre+xMenuCentre, posYMenuCentre+yMenuCentre, this);
			    	g.drawImage(imageMenuGauche.getImage(), posXMenuGauche+xMenuGauche, posYMenuGauche+yMenuGauche, this);
			    	g.drawImage(imageMenuDroite.getImage(), posXMenuDroite+xMenuDroite, posYMenuDroite+yMenuDroite, this);
			    	g.drawImage(imageMenuNew.getImage(), xMenuNew, posYMenuGauche, this);
		    	}
		    	
		    	if (placement == 2){
		    		g.drawImage(imageMenuCentre.getImage(), (int) (posXMenuCentre+xMenuCentre-dxdy/1.5), posYMenuCentre+yMenuCentre-dxdy, (int) (imageMenuCentre.getImage().getWidth(null)+1.5*dxdy), imageMenuCentre.getImage().getHeight(null)+dxdy, this);
			    	g.drawImage(imageMenuGauche.getImage(), posXMenuGauche+xMenuGauche, posYMenuGauche+yMenuGauche, this);
			    	g.drawImage(imageMenuDroite.getImage(), posXMenuDroite+xMenuDroite, posYMenuDroite+yMenuDroite, this);
		    	}
		    	
		    }
		    
		    /******************************************************************************************************/
		    /**                                         MODE OPTIONS                                             **/
		    /******************************************************************************************************/
		    
		    
		    if (modeActuel == 2){
		    	
		    	if (optionEnCours == 1){
		    		if (option1 == 0 && option2 == 0){
		    			g.setColor(new Color(255,153,51));
				    	g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 35));
				    	FontMetrics metrics = g.getFontMetrics();
				    	g.drawString("Tout Atout / Sans Atout : ", (this.getWidth()/2)-(metrics.stringWidth("Tout Atout / Sans Atout : OUI - NON")/2) , (int) ((this.getHeight())*0.2));
				    	g.drawString("Annonces : ", (this.getWidth()/2)-(metrics.stringWidth("Tout Atout / Sans Atout : OUI - NON")/2) , (int) ((this.getHeight())*0.3));
				    	g.drawString("Nombre de points maximum : ", (this.getWidth()/2)-(metrics.stringWidth("Tout Atout / Sans Atout : OUI - NON")/2) , (int) ((this.getHeight())*0.4));
				    	g.drawString("Valider les options", (this.getWidth()/2)-(metrics.stringWidth("Tout Atout / Sans Atout : OUI - NON")/2) , (int) ((this.getHeight())*0.6));
				    	g.setColor(new Color(255, 0, 0));
				    	g.drawString(">", (this.getWidth()/2)-(metrics.stringWidth(">   Tout Atout / Sans Atout : OUI - NON")/2) , (int) ((this.getHeight())*0.2));
				    	g.setColor(new Color(255, 0, 0));
				    	g.drawString("OUI", (this.getWidth()/2)-(metrics.stringWidth("Tout Atout / Sans Atout : OUI - NON")/2)+metrics.stringWidth("Tout Atout / Sans Atout : "), (int) ((this.getHeight())*0.2));
				    	g.drawString("OUI", (this.getWidth()/2)-(metrics.stringWidth("Tout Atout / Sans Atout : OUI - NON")/2)+metrics.stringWidth("Annonces : "), (int) ((this.getHeight())*0.3));
				    	g.drawString("" + option3, (this.getWidth()/2)-(metrics.stringWidth("Tout Atout / Sans Atout : OUI - NON")/2)+metrics.stringWidth("Nombre de points maximum : ") , (int) ((this.getHeight())*0.4));
				    	g.setColor(new Color(255,153,51));
				    	g.drawString(" - NON", (this.getWidth()/2)-(metrics.stringWidth("Tout Atout / Sans Atout : OUI - NON")/2)+metrics.stringWidth("Tout Atout / Sans Atout : OUI"), (int) ((this.getHeight())*0.2));
				    	g.drawString(" - NON", (this.getWidth()/2)-(metrics.stringWidth("Tout Atout / Sans Atout : OUI - NON")/2)+metrics.stringWidth("Annonces : OUI"), (int) ((this.getHeight())*0.3));
		    		}
		    		
		    		if (option1 == 0 && option2 == 1){
		    			g.setColor(new Color(255,153,51));
				    	g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 35));
				    	FontMetrics metrics = g.getFontMetrics();
				    	g.drawString("Tout Atout / Sans Atout : ", (this.getWidth()/2)-(metrics.stringWidth("Tout Atout / Sans Atout : OUI - NON")/2) , (int) ((this.getHeight())*0.2));
				    	g.drawString("Annonces : ", (this.getWidth()/2)-(metrics.stringWidth("Tout Atout / Sans Atout : OUI - NON")/2) , (int) ((this.getHeight())*0.3));
				    	g.drawString("Nombre de points maximum : " + option3, (this.getWidth()/2)-(metrics.stringWidth("Tout Atout / Sans Atout : OUI - NON")/2) , (int) ((this.getHeight())*0.4));
				    	g.drawString("Valider les options", (this.getWidth()/2)-(metrics.stringWidth("Tout Atout / Sans Atout : OUI - NON")/2) , (int) ((this.getHeight())*0.6));
				    	g.setColor(new Color(255, 0, 0));
				    	g.drawString(">", (this.getWidth()/2)-(metrics.stringWidth(">   Tout Atout / Sans Atout : OUI - NON")/2) , (int) ((this.getHeight())*0.2));
				    	g.setColor(new Color(255, 0, 0));
				    	g.drawString("OUI", (this.getWidth()/2)-(metrics.stringWidth("Tout Atout / Sans Atout : OUI - NON")/2)+metrics.stringWidth("Tout Atout / Sans Atout : "), (int) ((this.getHeight())*0.2));
				    	g.drawString("NON", (this.getWidth()/2)-(metrics.stringWidth("Tout Atout / Sans Atout : OUI - NON")/2)+metrics.stringWidth("Annonces : OUI - "), (int) ((this.getHeight())*0.3));
				    	g.drawString("" + option3, (this.getWidth()/2)-(metrics.stringWidth("Tout Atout / Sans Atout : OUI - NON")/2)+metrics.stringWidth("Nombre de points maximum : ") , (int) ((this.getHeight())*0.4));
				    	g.setColor(new Color(255,153,51));
				    	g.drawString(" - NON", (this.getWidth()/2)-(metrics.stringWidth("Tout Atout / Sans Atout : OUI - NON")/2)+metrics.stringWidth("Tout Atout / Sans Atout : OUI"), (int) ((this.getHeight())*0.2));
				    	g.drawString("OUI - ", (this.getWidth()/2)-(metrics.stringWidth("Tout Atout / Sans Atout : OUI - NON")/2)+metrics.stringWidth("Annonces : "), (int) ((this.getHeight())*0.3));
		    		}
		    		
		    		if (option1 == 1 && option2 == 0){
		    			g.setColor(new Color(255,153,51));
				    	g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 35));
				    	FontMetrics metrics = g.getFontMetrics();
				    	g.drawString("Tout Atout / Sans Atout : ", (this.getWidth()/2)-(metrics.stringWidth("Tout Atout / Sans Atout : OUI - NON")/2) , (int) ((this.getHeight())*0.2));
				    	g.drawString("Annonces : ", (this.getWidth()/2)-(metrics.stringWidth("Tout Atout / Sans Atout : OUI - NON")/2) , (int) ((this.getHeight())*0.3));
				    	g.drawString("Nombre de points maximum : " + option3, (this.getWidth()/2)-(metrics.stringWidth("Tout Atout / Sans Atout : OUI - NON")/2) , (int) ((this.getHeight())*0.4));
				    	g.drawString("Valider les options", (this.getWidth()/2)-(metrics.stringWidth("Tout Atout / Sans Atout : OUI - NON")/2) , (int) ((this.getHeight())*0.6));
				    	g.setColor(new Color(255, 0, 0));
				    	g.drawString(">", (this.getWidth()/2)-(metrics.stringWidth(">   Tout Atout / Sans Atout : OUI - NON")/2) , (int) ((this.getHeight())*0.2));
				    	g.setColor(new Color(255, 0, 0));
				    	g.drawString("NON", (this.getWidth()/2)-(metrics.stringWidth("Tout Atout / Sans Atout : OUI - NON")/2)+metrics.stringWidth("Tout Atout / Sans Atout : OUI - "), (int) ((this.getHeight())*0.2));
				    	g.drawString("OUI", (this.getWidth()/2)-(metrics.stringWidth("Tout Atout / Sans Atout : OUI - NON")/2)+metrics.stringWidth("Annonces : "), (int) ((this.getHeight())*0.3));
				    	g.drawString("" + option3, (this.getWidth()/2)-(metrics.stringWidth("Tout Atout / Sans Atout : OUI - NON")/2)+metrics.stringWidth("Nombre de points maximum : ") , (int) ((this.getHeight())*0.4));
				    	g.setColor(new Color(255,153,51));
				    	g.drawString("OUI - ", (this.getWidth()/2)-(metrics.stringWidth("Tout Atout / Sans Atout : OUI - NON")/2)+metrics.stringWidth("Tout Atout / Sans Atout : "), (int) ((this.getHeight())*0.2));
				    	g.drawString(" - NON", (this.getWidth()/2)-(metrics.stringWidth("Tout Atout / Sans Atout : OUI - NON")/2)+metrics.stringWidth("Annonces : OUI"), (int) ((this.getHeight())*0.3));
		    		}
		    		
		    		if (option1 == 1 && option2 == 1){
		    			g.setColor(new Color(255,153,51));
				    	g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 35));
				    	FontMetrics metrics = g.getFontMetrics();
				    	g.drawString("Tout Atout / Sans Atout : ", (this.getWidth()/2)-(metrics.stringWidth("Tout Atout / Sans Atout : OUI - NON")/2) , (int) ((this.getHeight())*0.2));
				    	g.drawString("Annonces : ", (this.getWidth()/2)-(metrics.stringWidth("Tout Atout / Sans Atout : OUI - NON")/2) , (int) ((this.getHeight())*0.3));
				    	g.drawString("Nombre de points maximum : " + option3, (this.getWidth()/2)-(metrics.stringWidth("Tout Atout / Sans Atout : OUI - NON")/2) , (int) ((this.getHeight())*0.4));
				    	g.drawString("Valider les options", (this.getWidth()/2)-(metrics.stringWidth("Tout Atout / Sans Atout : OUI - NON")/2) , (int) ((this.getHeight())*0.6));
				    	g.setColor(new Color(255, 0, 0));
				    	g.drawString(">", (this.getWidth()/2)-(metrics.stringWidth(">   Tout Atout / Sans Atout : OUI - NON")/2) , (int) ((this.getHeight())*0.2));
				    	g.setColor(new Color(255, 0, 0));
				    	g.drawString("NON", (this.getWidth()/2)-(metrics.stringWidth("Tout Atout / Sans Atout : OUI - NON")/2)+metrics.stringWidth("Tout Atout / Sans Atout : OUI - "), (int) ((this.getHeight())*0.2));
				    	g.drawString("NON", (this.getWidth()/2)-(metrics.stringWidth("Tout Atout / Sans Atout : OUI - NON")/2)+metrics.stringWidth("Annonces : OUI - "), (int) ((this.getHeight())*0.3));
				    	g.drawString("" + option3, (this.getWidth()/2)-(metrics.stringWidth("Tout Atout / Sans Atout : OUI - NON")/2)+metrics.stringWidth("Nombre de points maximum : ") , (int) ((this.getHeight())*0.4));
				    	g.setColor(new Color(255,153,51));
				    	g.drawString("OUI - ", (this.getWidth()/2)-(metrics.stringWidth("Tout Atout / Sans Atout : OUI - NON")/2)+metrics.stringWidth("Tout Atout / Sans Atout : "), (int) ((this.getHeight())*0.2));
				    	g.drawString("OUI - ", (this.getWidth()/2)-(metrics.stringWidth("Tout Atout / Sans Atout : OUI - NON")/2)+metrics.stringWidth("Annonces : "), (int) ((this.getHeight())*0.3));
		    		}
		    		
		    	}
		    	
		    	if (optionEnCours == 2){
		    		if (option2 == 0 && option1 == 0){
		    			g.setColor(new Color(255,153,51));
				    	g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 35));
				    	FontMetrics metrics = g.getFontMetrics();
				    	g.drawString("Tout Atout / Sans Atout : ", (this.getWidth()/2)-(metrics.stringWidth("Tout Atout / Sans Atout : OUI - NON")/2) , (int) ((this.getHeight())*0.2));
				    	g.drawString("Annonces : ", (this.getWidth()/2)-(metrics.stringWidth("Tout Atout / Sans Atout : OUI - NON")/2) , (int) ((this.getHeight())*0.3));
				    	g.drawString("Nombre de points maximum : " + option3, (this.getWidth()/2)-(metrics.stringWidth("Tout Atout / Sans Atout : OUI - NON")/2) , (int) ((this.getHeight())*0.4));
				    	g.drawString("Valider les options", (this.getWidth()/2)-(metrics.stringWidth("Tout Atout / Sans Atout : OUI - NON")/2) , (int) ((this.getHeight())*0.6));
				    	g.setColor(new Color(255, 0, 0));
				    	g.drawString(">", (this.getWidth()/2)-(metrics.stringWidth(">   Tout Atout / Sans Atout : OUI - NON")/2) , (int) ((this.getHeight())*0.3));
				    	g.setColor(new Color(255, 0, 0));
				    	g.drawString("OUI", (this.getWidth()/2)-(metrics.stringWidth("Tout Atout / Sans Atout : OUI - NON")/2)+metrics.stringWidth("Tout Atout / Sans Atout : "), (int) ((this.getHeight())*0.2));
				    	g.drawString("OUI", (this.getWidth()/2)-(metrics.stringWidth("Tout Atout / Sans Atout : OUI - NON")/2)+metrics.stringWidth("Annonces : "), (int) ((this.getHeight())*0.3));
				    	g.drawString("" + option3, (this.getWidth()/2)-(metrics.stringWidth("Tout Atout / Sans Atout : OUI - NON")/2)+metrics.stringWidth("Nombre de points maximum : ") , (int) ((this.getHeight())*0.4));
				    	g.setColor(new Color(255,153,51));
				    	g.drawString(" - NON", (this.getWidth()/2)-(metrics.stringWidth("Tout Atout / Sans Atout : OUI - NON")/2)+metrics.stringWidth("Tout Atout / Sans Atout : OUI"), (int) ((this.getHeight())*0.2));
				    	g.drawString(" - NON", (this.getWidth()/2)-(metrics.stringWidth("Tout Atout / Sans Atout : OUI - NON")/2)+metrics.stringWidth("Annonces : OUI"), (int) ((this.getHeight())*0.3));
		    		}
		    		
		    		if (option2 == 0 && option1 == 1){
		    			g.setColor(new Color(255,153,51));
				    	g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 35));
				    	FontMetrics metrics = g.getFontMetrics();
				    	g.drawString("Tout Atout / Sans Atout : ", (this.getWidth()/2)-(metrics.stringWidth("Tout Atout / Sans Atout : OUI - NON")/2) , (int) ((this.getHeight())*0.2));
				    	g.drawString("Annonces : ", (this.getWidth()/2)-(metrics.stringWidth("Tout Atout / Sans Atout : OUI - NON")/2) , (int) ((this.getHeight())*0.3));
				    	g.drawString("Nombre de points maximum : " + option3, (this.getWidth()/2)-(metrics.stringWidth("Tout Atout / Sans Atout : OUI - NON")/2) , (int) ((this.getHeight())*0.4));
				    	g.drawString("Valider les options", (this.getWidth()/2)-(metrics.stringWidth("Tout Atout / Sans Atout : OUI - NON")/2) , (int) ((this.getHeight())*0.6));
				    	g.setColor(new Color(255, 0, 0));
				    	g.drawString(">", (this.getWidth()/2)-(metrics.stringWidth(">   Tout Atout / Sans Atout : OUI - NON")/2) , (int) ((this.getHeight())*0.3));
				    	g.setColor(new Color(255, 0, 0));
				    	g.drawString("NON", (this.getWidth()/2)-(metrics.stringWidth("Tout Atout / Sans Atout : OUI - NON")/2)+metrics.stringWidth("Tout Atout / Sans Atout : OUI - "), (int) ((this.getHeight())*0.2));
				    	g.drawString("OUI", (this.getWidth()/2)-(metrics.stringWidth("Tout Atout / Sans Atout : OUI - NON")/2)+metrics.stringWidth("Annonces : "), (int) ((this.getHeight())*0.3));
				    	g.drawString("" + option3, (this.getWidth()/2)-(metrics.stringWidth("Tout Atout / Sans Atout : OUI - NON")/2)+metrics.stringWidth("Nombre de points maximum : ") , (int) ((this.getHeight())*0.4));
				    	g.setColor(new Color(255,153,51));
				    	g.drawString("OUI - ", (this.getWidth()/2)-(metrics.stringWidth("Tout Atout / Sans Atout : OUI - NON")/2)+metrics.stringWidth("Tout Atout / Sans Atout : "), (int) ((this.getHeight())*0.2));
				    	g.drawString(" - NON", (this.getWidth()/2)-(metrics.stringWidth("Tout Atout / Sans Atout : OUI - NON")/2)+metrics.stringWidth("Annonces : OUI"), (int) ((this.getHeight())*0.3));
		    		}
		    		
		    		if (option2 == 1 && option1 == 0){
		    			g.setColor(new Color(255,153,51));
				    	g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 35));
				    	FontMetrics metrics = g.getFontMetrics();
				    	g.drawString("Tout Atout / Sans Atout : ", (this.getWidth()/2)-(metrics.stringWidth("Tout Atout / Sans Atout : OUI - NON")/2) , (int) ((this.getHeight())*0.2));
				    	g.drawString("Annonces : ", (this.getWidth()/2)-(metrics.stringWidth("Tout Atout / Sans Atout : OUI - NON")/2) , (int) ((this.getHeight())*0.3));
				    	g.drawString("Nombre de points maximum : " + option3, (this.getWidth()/2)-(metrics.stringWidth("Tout Atout / Sans Atout : OUI - NON")/2) , (int) ((this.getHeight())*0.4));
				    	g.drawString("Valider les options", (this.getWidth()/2)-(metrics.stringWidth("Tout Atout / Sans Atout : OUI - NON")/2) , (int) ((this.getHeight())*0.6));
				    	g.setColor(new Color(255, 0, 0));
				    	g.drawString(">", (this.getWidth()/2)-(metrics.stringWidth(">   Tout Atout / Sans Atout : OUI - NON")/2) , (int) ((this.getHeight())*0.3));
				    	g.setColor(new Color(255, 0, 0));
				    	g.drawString("OUI", (this.getWidth()/2)-(metrics.stringWidth("Tout Atout / Sans Atout : OUI - NON")/2)+metrics.stringWidth("Tout Atout / Sans Atout : "), (int) ((this.getHeight())*0.2));
				    	g.drawString("NON", (this.getWidth()/2)-(metrics.stringWidth("Tout Atout / Sans Atout : OUI - NON")/2)+metrics.stringWidth("Annonces : OUI - "), (int) ((this.getHeight())*0.3));
				    	g.drawString("" + option3, (this.getWidth()/2)-(metrics.stringWidth("Tout Atout / Sans Atout : OUI - NON")/2)+metrics.stringWidth("Nombre de points maximum : ") , (int) ((this.getHeight())*0.4));
				    	g.setColor(new Color(255,153,51));
				    	g.drawString(" - NON", (this.getWidth()/2)-(metrics.stringWidth("Tout Atout / Sans Atout : OUI - NON")/2)+metrics.stringWidth("Tout Atout / Sans Atout : OUI"), (int) ((this.getHeight())*0.2));
				    	g.drawString("OUI - ", (this.getWidth()/2)-(metrics.stringWidth("Tout Atout / Sans Atout : OUI - NON")/2)+metrics.stringWidth("Annonces : "), (int) ((this.getHeight())*0.3));
		    		}
		    		
		    		if (option2 == 1 && option1 == 1){
		    			g.setColor(new Color(255,153,51));
				    	g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 35));
				    	FontMetrics metrics = g.getFontMetrics();
				    	g.drawString("Tout Atout / Sans Atout : ", (this.getWidth()/2)-(metrics.stringWidth("Tout Atout / Sans Atout : OUI - NON")/2) , (int) ((this.getHeight())*0.2));
				    	g.drawString("Annonces : ", (this.getWidth()/2)-(metrics.stringWidth("Tout Atout / Sans Atout : OUI - NON")/2) , (int) ((this.getHeight())*0.3));
				    	g.drawString("Nombre de points maximum : " + option3, (this.getWidth()/2)-(metrics.stringWidth("Tout Atout / Sans Atout : OUI - NON")/2) , (int) ((this.getHeight())*0.4));
				    	g.drawString("Valider les options", (this.getWidth()/2)-(metrics.stringWidth("Tout Atout / Sans Atout : OUI - NON")/2) , (int) ((this.getHeight())*0.6));
				    	g.setColor(new Color(255, 0, 0));
				    	g.drawString(">", (this.getWidth()/2)-(metrics.stringWidth(">   Tout Atout / Sans Atout : OUI - NON")/2) , (int) ((this.getHeight())*0.3));
				    	g.setColor(new Color(255, 0, 0));
				    	g.drawString("NON", (this.getWidth()/2)-(metrics.stringWidth("Tout Atout / Sans Atout : OUI - NON")/2)+metrics.stringWidth("Tout Atout / Sans Atout : OUI - "), (int) ((this.getHeight())*0.2));
				    	g.drawString("NON", (this.getWidth()/2)-(metrics.stringWidth("Tout Atout / Sans Atout : OUI - NON")/2)+metrics.stringWidth("Annonces : OUI - "), (int) ((this.getHeight())*0.3));
				    	g.drawString("" + option3, (this.getWidth()/2)-(metrics.stringWidth("Tout Atout / Sans Atout : OUI - NON")/2)+metrics.stringWidth("Nombre de points maximum : ") , (int) ((this.getHeight())*0.4));
				    	g.setColor(new Color(255,153,51));
				    	g.drawString("OUI - ", (this.getWidth()/2)-(metrics.stringWidth("Tout Atout / Sans Atout : OUI - NON")/2)+metrics.stringWidth("Tout Atout / Sans Atout : "), (int) ((this.getHeight())*0.2));
				    	g.drawString("OUI - ", (this.getWidth()/2)-(metrics.stringWidth("Tout Atout / Sans Atout : OUI - NON")/2)+metrics.stringWidth("Annonces : "), (int) ((this.getHeight())*0.3));
		    		}
		    	}
		    	
		    	if (optionEnCours == 3){
		    		if (option1 == 0 && option2 == 0){
		    			g.setColor(new Color(255,153,51));
				    	g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 35));
				    	FontMetrics metrics = g.getFontMetrics();
				    	g.drawString("Tout Atout / Sans Atout : ", (this.getWidth()/2)-(metrics.stringWidth("Tout Atout / Sans Atout : OUI - NON")/2) , (int) ((this.getHeight())*0.2));
				    	g.drawString("Annonces : ", (this.getWidth()/2)-(metrics.stringWidth("Tout Atout / Sans Atout : OUI - NON")/2) , (int) ((this.getHeight())*0.3));
				    	g.drawString("Nombre de points maximum : " + option3, (this.getWidth()/2)-(metrics.stringWidth("Tout Atout / Sans Atout : OUI - NON")/2) , (int) ((this.getHeight())*0.4));
				    	g.drawString("Valider les options", (this.getWidth()/2)-(metrics.stringWidth("Tout Atout / Sans Atout : OUI - NON")/2) , (int) ((this.getHeight())*0.6));
				    	g.setColor(new Color(255, 0, 0));
				    	g.drawString(">", (this.getWidth()/2)-(metrics.stringWidth(">   Tout Atout / Sans Atout : OUI - NON")/2) , (int) ((this.getHeight())*0.4));
				    	g.setColor(new Color(255, 0, 0));
				    	g.drawString("OUI", (this.getWidth()/2)-(metrics.stringWidth("Tout Atout / Sans Atout : OUI - NON")/2)+metrics.stringWidth("Tout Atout / Sans Atout : "), (int) ((this.getHeight())*0.2));
				    	g.drawString("OUI", (this.getWidth()/2)-(metrics.stringWidth("Tout Atout / Sans Atout : OUI - NON")/2)+metrics.stringWidth("Annonces : "), (int) ((this.getHeight())*0.3));
				    	g.drawString("" + option3, (this.getWidth()/2)-(metrics.stringWidth("Tout Atout / Sans Atout : OUI - NON")/2)+metrics.stringWidth("Nombre de points maximum : ") , (int) ((this.getHeight())*0.4));
				    	g.setColor(new Color(255,153,51));
				    	g.drawString(" - NON", (this.getWidth()/2)-(metrics.stringWidth("Tout Atout / Sans Atout : OUI - NON")/2)+metrics.stringWidth("Tout Atout / Sans Atout : OUI"), (int) ((this.getHeight())*0.2));
				    	g.drawString(" - NON", (this.getWidth()/2)-(metrics.stringWidth("Tout Atout / Sans Atout : OUI - NON")/2)+metrics.stringWidth("Annonces : OUI"), (int) ((this.getHeight())*0.3));
		    		}
		    		
		    		if (option1 == 0 && option2 == 1){
		    			g.setColor(new Color(255,153,51));
				    	g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 35));
				    	FontMetrics metrics = g.getFontMetrics();
				    	g.drawString("Tout Atout / Sans Atout : ", (this.getWidth()/2)-(metrics.stringWidth("Tout Atout / Sans Atout : OUI - NON")/2) , (int) ((this.getHeight())*0.2));
				    	g.drawString("Annonces : ", (this.getWidth()/2)-(metrics.stringWidth("Tout Atout / Sans Atout : OUI - NON")/2) , (int) ((this.getHeight())*0.3));
				    	g.drawString("Nombre de points maximum : " + option3, (this.getWidth()/2)-(metrics.stringWidth("Tout Atout / Sans Atout : OUI - NON")/2) , (int) ((this.getHeight())*0.4));
				    	g.drawString("Valider les options", (this.getWidth()/2)-(metrics.stringWidth("Tout Atout / Sans Atout : OUI - NON")/2) , (int) ((this.getHeight())*0.6));
				    	g.setColor(new Color(255, 0, 0));
				    	g.drawString(">", (this.getWidth()/2)-(metrics.stringWidth(">   Tout Atout / Sans Atout : OUI - NON")/2) , (int) ((this.getHeight())*0.4));
				    	g.setColor(new Color(255, 0, 0));
				    	g.drawString("OUI", (this.getWidth()/2)-(metrics.stringWidth("Tout Atout / Sans Atout : OUI - NON")/2)+metrics.stringWidth("Tout Atout / Sans Atout : "), (int) ((this.getHeight())*0.2));
				    	g.drawString("NON", (this.getWidth()/2)-(metrics.stringWidth("Tout Atout / Sans Atout : OUI - NON")/2)+metrics.stringWidth("Annonces : OUI - "), (int) ((this.getHeight())*0.3));
				    	g.drawString("" + option3, (this.getWidth()/2)-(metrics.stringWidth("Tout Atout / Sans Atout : OUI - NON")/2)+metrics.stringWidth("Nombre de points maximum : ") , (int) ((this.getHeight())*0.4));
				    	g.setColor(new Color(255,153,51));
				    	g.drawString(" - NON", (this.getWidth()/2)-(metrics.stringWidth("Tout Atout / Sans Atout : OUI - NON")/2)+metrics.stringWidth("Tout Atout / Sans Atout : OUI"), (int) ((this.getHeight())*0.2));
				    	g.drawString("OUI - ", (this.getWidth()/2)-(metrics.stringWidth("Tout Atout / Sans Atout : OUI - NON")/2)+metrics.stringWidth("Annonces : "), (int) ((this.getHeight())*0.3));
		    		}
		    		
		    		if (option1 == 1 && option2 == 0){
		    			g.setColor(new Color(255,153,51));
				    	g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 35));
				    	FontMetrics metrics = g.getFontMetrics();
				    	g.drawString("Tout Atout / Sans Atout : ", (this.getWidth()/2)-(metrics.stringWidth("Tout Atout / Sans Atout : OUI - NON")/2) , (int) ((this.getHeight())*0.2));
				    	g.drawString("Annonces : ", (this.getWidth()/2)-(metrics.stringWidth("Tout Atout / Sans Atout : OUI - NON")/2) , (int) ((this.getHeight())*0.3));
				    	g.drawString("Nombre de points maximum : " + option3, (this.getWidth()/2)-(metrics.stringWidth("Tout Atout / Sans Atout : OUI - NON")/2) , (int) ((this.getHeight())*0.4));
				    	g.drawString("Valider les options", (this.getWidth()/2)-(metrics.stringWidth("Tout Atout / Sans Atout : OUI - NON")/2) , (int) ((this.getHeight())*0.6));
				    	g.setColor(new Color(255, 0, 0));
				    	g.drawString(">", (this.getWidth()/2)-(metrics.stringWidth(">   Tout Atout / Sans Atout : OUI - NON")/2) , (int) ((this.getHeight())*0.4));
				    	g.setColor(new Color(255, 0, 0));
				    	g.drawString("NON", (this.getWidth()/2)-(metrics.stringWidth("Tout Atout / Sans Atout : OUI - NON")/2)+metrics.stringWidth("Tout Atout / Sans Atout : OUI - "), (int) ((this.getHeight())*0.2));
				    	g.drawString("OUI", (this.getWidth()/2)-(metrics.stringWidth("Tout Atout / Sans Atout : OUI - NON")/2)+metrics.stringWidth("Annonces : "), (int) ((this.getHeight())*0.3));
				    	g.drawString("" + option3, (this.getWidth()/2)-(metrics.stringWidth("Tout Atout / Sans Atout : OUI - NON")/2)+metrics.stringWidth("Nombre de points maximum : ") , (int) ((this.getHeight())*0.4));
				    	g.setColor(new Color(255,153,51));
				    	g.drawString("OUI - ", (this.getWidth()/2)-(metrics.stringWidth("Tout Atout / Sans Atout : OUI - NON")/2)+metrics.stringWidth("Tout Atout / Sans Atout : "), (int) ((this.getHeight())*0.2));
				    	g.drawString(" - NON", (this.getWidth()/2)-(metrics.stringWidth("Tout Atout / Sans Atout : OUI - NON")/2)+metrics.stringWidth("Annonces : OUI"), (int) ((this.getHeight())*0.3));
		    		}
		    		
		    		if (option1 == 1 && option2 == 1){
		    			g.setColor(new Color(255,153,51));
				    	g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 35));
				    	FontMetrics metrics = g.getFontMetrics();
				    	g.drawString("Tout Atout / Sans Atout : ", (this.getWidth()/2)-(metrics.stringWidth("Tout Atout / Sans Atout : OUI - NON")/2) , (int) ((this.getHeight())*0.2));
				    	g.drawString("Annonces : ", (this.getWidth()/2)-(metrics.stringWidth("Tout Atout / Sans Atout : OUI - NON")/2) , (int) ((this.getHeight())*0.3));
				    	g.drawString("Nombre de points maximum : " + option3, (this.getWidth()/2)-(metrics.stringWidth("Tout Atout / Sans Atout : OUI - NON")/2) , (int) ((this.getHeight())*0.4));
				    	g.drawString("Valider les options", (this.getWidth()/2)-(metrics.stringWidth("Tout Atout / Sans Atout : OUI - NON")/2) , (int) ((this.getHeight())*0.6));
				    	g.setColor(new Color(255, 0, 0));
				    	g.drawString(">", (this.getWidth()/2)-(metrics.stringWidth(">   Tout Atout / Sans Atout : OUI - NON")/2) , (int) ((this.getHeight())*0.4));
				    	g.setColor(new Color(255, 0, 0));
				    	g.drawString("NON", (this.getWidth()/2)-(metrics.stringWidth("Tout Atout / Sans Atout : OUI - NON")/2)+metrics.stringWidth("Tout Atout / Sans Atout : OUI - "), (int) ((this.getHeight())*0.2));
				    	g.drawString("NON", (this.getWidth()/2)-(metrics.stringWidth("Tout Atout / Sans Atout : OUI - NON")/2)+metrics.stringWidth("Annonces : OUI - "), (int) ((this.getHeight())*0.3));
				    	g.drawString("" + option3, (this.getWidth()/2)-(metrics.stringWidth("Tout Atout / Sans Atout : OUI - NON")/2)+metrics.stringWidth("Nombre de points maximum : ") , (int) ((this.getHeight())*0.4));
				    	g.setColor(new Color(255,153,51));
				    	g.drawString("OUI - ", (this.getWidth()/2)-(metrics.stringWidth("Tout Atout / Sans Atout : OUI - NON")/2)+metrics.stringWidth("Tout Atout / Sans Atout : "), (int) ((this.getHeight())*0.2));
				    	g.drawString("OUI - ", (this.getWidth()/2)-(metrics.stringWidth("Tout Atout / Sans Atout : OUI - NON")/2)+metrics.stringWidth("Annonces : "), (int) ((this.getHeight())*0.3));
		    		}
		    		
		    	}
		    	
		    	if (optionEnCours == 4){
		    		if (option1 == 0 && option2 == 0){
		    			g.setColor(new Color(255,153,51));
				    	g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 35));
				    	FontMetrics metrics = g.getFontMetrics();
				    	g.drawString("Tout Atout / Sans Atout : ", (this.getWidth()/2)-(metrics.stringWidth("Tout Atout / Sans Atout : OUI - NON")/2) , (int) ((this.getHeight())*0.2));
				    	g.drawString("Annonces : ", (this.getWidth()/2)-(metrics.stringWidth("Tout Atout / Sans Atout : OUI - NON")/2) , (int) ((this.getHeight())*0.3));
				    	g.drawString("Nombre de points maximum : " + option3, (this.getWidth()/2)-(metrics.stringWidth("Tout Atout / Sans Atout : OUI - NON")/2) , (int) ((this.getHeight())*0.4));
				    	g.drawString("Valider les options", (this.getWidth()/2)-(metrics.stringWidth("Tout Atout / Sans Atout : OUI - NON")/2) , (int) ((this.getHeight())*0.6));
				    	g.setColor(new Color(255, 0, 0));
				    	g.drawString(">", (this.getWidth()/2)-(metrics.stringWidth(">   Tout Atout / Sans Atout : OUI - NON")/2) , (int) ((this.getHeight())*0.6));
				    	g.setColor(new Color(255, 0, 0));
				    	g.drawString("OUI", (this.getWidth()/2)-(metrics.stringWidth("Tout Atout / Sans Atout : OUI - NON")/2)+metrics.stringWidth("Tout Atout / Sans Atout : "), (int) ((this.getHeight())*0.2));
				    	g.drawString("OUI", (this.getWidth()/2)-(metrics.stringWidth("Tout Atout / Sans Atout : OUI - NON")/2)+metrics.stringWidth("Annonces : "), (int) ((this.getHeight())*0.3));
				    	g.drawString("" + option3, (this.getWidth()/2)-(metrics.stringWidth("Tout Atout / Sans Atout : OUI - NON")/2)+metrics.stringWidth("Nombre de points maximum : ") , (int) ((this.getHeight())*0.4));
				    	g.setColor(new Color(255,153,51));
				    	g.drawString(" - NON", (this.getWidth()/2)-(metrics.stringWidth("Tout Atout / Sans Atout : OUI - NON")/2)+metrics.stringWidth("Tout Atout / Sans Atout : OUI"), (int) ((this.getHeight())*0.2));
				    	g.drawString(" - NON", (this.getWidth()/2)-(metrics.stringWidth("Tout Atout / Sans Atout : OUI - NON")/2)+metrics.stringWidth("Annonces : OUI"), (int) ((this.getHeight())*0.3));
				    }
		    		
		    		if (option1 == 0 && option2 == 1){
		    			g.setColor(new Color(255,153,51));
				    	g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 35));
				    	FontMetrics metrics = g.getFontMetrics();
				    	g.drawString("Tout Atout / Sans Atout : ", (this.getWidth()/2)-(metrics.stringWidth("Tout Atout / Sans Atout : OUI - NON")/2) , (int) ((this.getHeight())*0.2));
				    	g.drawString("Annonces : ", (this.getWidth()/2)-(metrics.stringWidth("Tout Atout / Sans Atout : OUI - NON")/2) , (int) ((this.getHeight())*0.3));
				    	g.drawString("Nombre de points maximum : " + option3, (this.getWidth()/2)-(metrics.stringWidth("Tout Atout / Sans Atout : OUI - NON")/2) , (int) ((this.getHeight())*0.4));
				    	g.drawString("Valider les options", (this.getWidth()/2)-(metrics.stringWidth("Tout Atout / Sans Atout : OUI - NON")/2) , (int) ((this.getHeight())*0.6));
				    	g.setColor(new Color(255, 0, 0));
				    	g.drawString(">", (this.getWidth()/2)-(metrics.stringWidth(">   Tout Atout / Sans Atout : OUI - NON")/2) , (int) ((this.getHeight())*0.6));
				    	g.setColor(new Color(255, 0, 0));
				    	g.drawString("OUI", (this.getWidth()/2)-(metrics.stringWidth("Tout Atout / Sans Atout : OUI - NON")/2)+metrics.stringWidth("Tout Atout / Sans Atout : "), (int) ((this.getHeight())*0.2));
				    	g.drawString("NON", (this.getWidth()/2)-(metrics.stringWidth("Tout Atout / Sans Atout : OUI - NON")/2)+metrics.stringWidth("Annonces : OUI - "), (int) ((this.getHeight())*0.3));
				    	g.drawString("" + option3, (this.getWidth()/2)-(metrics.stringWidth("Tout Atout / Sans Atout : OUI - NON")/2)+metrics.stringWidth("Nombre de points maximum : ") , (int) ((this.getHeight())*0.4));
				    	g.setColor(new Color(255,153,51));
				    	g.drawString(" - NON", (this.getWidth()/2)-(metrics.stringWidth("Tout Atout / Sans Atout : OUI - NON")/2)+metrics.stringWidth("Tout Atout / Sans Atout : OUI"), (int) ((this.getHeight())*0.2));
				    	g.drawString("OUI - ", (this.getWidth()/2)-(metrics.stringWidth("Tout Atout / Sans Atout : OUI - NON")/2)+metrics.stringWidth("Annonces : "), (int) ((this.getHeight())*0.3));
				    }
		    		
		    		if (option1 == 1 && option2 == 0){
		    			g.setColor(new Color(255,153,51));
				    	g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 35));
				    	FontMetrics metrics = g.getFontMetrics();
				    	g.drawString("Tout Atout / Sans Atout : ", (this.getWidth()/2)-(metrics.stringWidth("Tout Atout / Sans Atout : OUI - NON")/2) , (int) ((this.getHeight())*0.2));
				    	g.drawString("Annonces : ", (this.getWidth()/2)-(metrics.stringWidth("Tout Atout / Sans Atout : OUI - NON")/2) , (int) ((this.getHeight())*0.3));
				    	g.drawString("Nombre de points maximum : " + option3, (this.getWidth()/2)-(metrics.stringWidth("Tout Atout / Sans Atout : OUI - NON")/2) , (int) ((this.getHeight())*0.4));
				    	g.drawString("Valider les options", (this.getWidth()/2)-(metrics.stringWidth("Tout Atout / Sans Atout : OUI - NON")/2) , (int) ((this.getHeight())*0.6));
				    	g.setColor(new Color(255, 0, 0));
				    	g.drawString(">", (this.getWidth()/2)-(metrics.stringWidth(">   Tout Atout / Sans Atout : OUI - NON")/2) , (int) ((this.getHeight())*0.6));
				    	g.setColor(new Color(255, 0, 0));
				    	g.drawString("NON", (this.getWidth()/2)-(metrics.stringWidth("Tout Atout / Sans Atout : OUI - NON")/2)+metrics.stringWidth("Tout Atout / Sans Atout : OUI - "), (int) ((this.getHeight())*0.2));
				    	g.drawString("OUI", (this.getWidth()/2)-(metrics.stringWidth("Tout Atout / Sans Atout : OUI - NON")/2)+metrics.stringWidth("Annonces : "), (int) ((this.getHeight())*0.3));
				    	g.drawString("" + option3, (this.getWidth()/2)-(metrics.stringWidth("Tout Atout / Sans Atout : OUI - NON")/2)+metrics.stringWidth("Nombre de points maximum : ") , (int) ((this.getHeight())*0.4));
				    	g.setColor(new Color(255,153,51));
				    	g.drawString("OUI - ", (this.getWidth()/2)-(metrics.stringWidth("Tout Atout / Sans Atout : OUI - NON")/2)+metrics.stringWidth("Tout Atout / Sans Atout : "), (int) ((this.getHeight())*0.2));
				    	g.drawString(" - NON", (this.getWidth()/2)-(metrics.stringWidth("Tout Atout / Sans Atout : OUI - NON")/2)+metrics.stringWidth("Annonces : OUI"), (int) ((this.getHeight())*0.3));
				    }
		    		
		    		if (option1 == 1 && option2 == 1){
		    			g.setColor(new Color(255,153,51));
				    	g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 35));
				    	FontMetrics metrics = g.getFontMetrics();
				    	g.drawString("Tout Atout / Sans Atout : ", (this.getWidth()/2)-(metrics.stringWidth("Tout Atout / Sans Atout : OUI - NON")/2) , (int) ((this.getHeight())*0.2));
				    	g.drawString("Annonces : ", (this.getWidth()/2)-(metrics.stringWidth("Tout Atout / Sans Atout : OUI - NON")/2) , (int) ((this.getHeight())*0.3));
				    	g.drawString("Nombre de points maximum : " + option3, (this.getWidth()/2)-(metrics.stringWidth("Tout Atout / Sans Atout : OUI - NON")/2) , (int) ((this.getHeight())*0.4));
				    	g.drawString("Valider les options", (this.getWidth()/2)-(metrics.stringWidth("Tout Atout / Sans Atout : OUI - NON")/2) , (int) ((this.getHeight())*0.6));
				    	g.setColor(new Color(255, 0, 0));
				    	g.drawString(">", (this.getWidth()/2)-(metrics.stringWidth(">   Tout Atout / Sans Atout : OUI - NON")/2) , (int) ((this.getHeight())*0.6));
				    	g.setColor(new Color(255, 0, 0));
				    	g.drawString("NON", (this.getWidth()/2)-(metrics.stringWidth("Tout Atout / Sans Atout : OUI - NON")/2)+metrics.stringWidth("Tout Atout / Sans Atout : OUI - "), (int) ((this.getHeight())*0.2));
				    	g.drawString("NON", (this.getWidth()/2)-(metrics.stringWidth("Tout Atout / Sans Atout : OUI - NON")/2)+metrics.stringWidth("Annonces : OUI - "), (int) ((this.getHeight())*0.3));
				    	g.drawString("" + option3, (this.getWidth()/2)-(metrics.stringWidth("Tout Atout / Sans Atout : OUI - NON")/2)+metrics.stringWidth("Nombre de points maximum : ") , (int) ((this.getHeight())*0.4));
				    	g.setColor(new Color(255,153,51));
				    	g.drawString("OUI - ", (this.getWidth()/2)-(metrics.stringWidth("Tout Atout / Sans Atout : OUI - NON")/2)+metrics.stringWidth("Tout Atout / Sans Atout : "), (int) ((this.getHeight())*0.2));
				    	g.drawString("OUI - ", (this.getWidth()/2)-(metrics.stringWidth("Tout Atout / Sans Atout : OUI - NON")/2)+metrics.stringWidth("Annonces : "), (int) ((this.getHeight())*0.3));
				    }
		    		
		    	}
		    	
		    	if (optionEnCours == 5){
		    		if (option1 == 0 && option2 == 0){
		    			g.setColor(new Color(255,153,51));
				    	g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 35));
				    	FontMetrics metrics = g.getFontMetrics();
				    	g.drawString("Tout Atout / Sans Atout : ", (this.getWidth()/2)-(metrics.stringWidth("Tout Atout / Sans Atout : OUI - NON")/2) , (int) ((this.getHeight())*0.2));
				    	g.drawString("Annonces : ", (this.getWidth()/2)-(metrics.stringWidth("Tout Atout / Sans Atout : OUI - NON")/2) , (int) ((this.getHeight())*0.3));
				    	g.drawString("Nombre de points maximum : " + option3, (this.getWidth()/2)-(metrics.stringWidth("Tout Atout / Sans Atout : OUI - NON")/2) , (int) ((this.getHeight())*0.4));
				    	g.setColor(new Color(102, 255, 153));
				    	g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 25));
				    	g.drawString("Attente de connexion du joueur distant...", (this.getWidth()/2)-(metrics.stringWidth("Tout Atout / Sans Atout : OUI - NON")/2) , (int) ((this.getHeight())*0.7));
				    	g.drawString(secJD + " secondes restantes", (this.getWidth()/2)-(metrics.stringWidth("Tout Atout / Sans Atout : OUI - NON")/2) , (int) ((this.getHeight())*0.75));
				    	g.setColor(new Color(255, 0, 0));
				    	g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 35));
				    	g.drawString("Valider les options", (this.getWidth()/2)-(metrics.stringWidth("Tout Atout / Sans Atout : OUI - NON")/2) , (int) ((this.getHeight())*0.6));
				    	g.drawString("OUI", (this.getWidth()/2)-(metrics.stringWidth("Tout Atout / Sans Atout : OUI - NON")/2)+metrics.stringWidth("Tout Atout / Sans Atout : "), (int) ((this.getHeight())*0.2));
				    	g.drawString("OUI", (this.getWidth()/2)-(metrics.stringWidth("Tout Atout / Sans Atout : OUI - NON")/2)+metrics.stringWidth("Annonces : "), (int) ((this.getHeight())*0.3));
				    	g.drawString("" + option3, (this.getWidth()/2)-(metrics.stringWidth("Tout Atout / Sans Atout : OUI - NON")/2)+metrics.stringWidth("Nombre de points maximum : ") , (int) ((this.getHeight())*0.4));
				    	g.setColor(new Color(255,153,51));
				    	g.drawString(" - NON", (this.getWidth()/2)-(metrics.stringWidth("Tout Atout / Sans Atout : OUI - NON")/2)+metrics.stringWidth("Tout Atout / Sans Atout : OUI"), (int) ((this.getHeight())*0.2));
				    	g.drawString(" - NON", (this.getWidth()/2)-(metrics.stringWidth("Tout Atout / Sans Atout : OUI - NON")/2)+metrics.stringWidth("Annonces : OUI"), (int) ((this.getHeight())*0.3));
				    }
		    		
		    		if (option1 == 0 && option2 == 1){
		    			g.setColor(new Color(255,153,51));
				    	g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 35));
				    	FontMetrics metrics = g.getFontMetrics();
				    	g.drawString("Tout Atout / Sans Atout : ", (this.getWidth()/2)-(metrics.stringWidth("Tout Atout / Sans Atout : OUI - NON")/2) , (int) ((this.getHeight())*0.2));
				    	g.drawString("Annonces : ", (this.getWidth()/2)-(metrics.stringWidth("Tout Atout / Sans Atout : OUI - NON")/2) , (int) ((this.getHeight())*0.3));
				    	g.drawString("Nombre de points maximum : " + option3, (this.getWidth()/2)-(metrics.stringWidth("Tout Atout / Sans Atout : OUI - NON")/2) , (int) ((this.getHeight())*0.4));
				    	g.setColor(new Color(102, 255, 153));
				    	g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 25));
				    	g.drawString("Attente de connexion du joueur distant...", (this.getWidth()/2)-(metrics.stringWidth("Tout Atout / Sans Atout : OUI - NON")/2) , (int) ((this.getHeight())*0.7));
				    	g.drawString(secJD + " secondes restantes", (this.getWidth()/2)-(metrics.stringWidth("Tout Atout / Sans Atout : OUI - NON")/2) , (int) ((this.getHeight())*0.75));
				    	g.setColor(new Color(255, 0, 0));
				    	g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 35));
				    	g.drawString("Valider les options", (this.getWidth()/2)-(metrics.stringWidth("Tout Atout / Sans Atout : OUI - NON")/2) , (int) ((this.getHeight())*0.6));
				    	g.drawString("OUI", (this.getWidth()/2)-(metrics.stringWidth("Tout Atout / Sans Atout : OUI - NON")/2)+metrics.stringWidth("Tout Atout / Sans Atout : "), (int) ((this.getHeight())*0.2));
				    	g.drawString("NON", (this.getWidth()/2)-(metrics.stringWidth("Tout Atout / Sans Atout : OUI - NON")/2)+metrics.stringWidth("Annonces : OUI - "), (int) ((this.getHeight())*0.3));
				    	g.drawString("" + option3, (this.getWidth()/2)-(metrics.stringWidth("Tout Atout / Sans Atout : OUI - NON")/2)+metrics.stringWidth("Nombre de points maximum : ") , (int) ((this.getHeight())*0.4));
				    	g.setColor(new Color(255,153,51));
				    	g.drawString(" - NON", (this.getWidth()/2)-(metrics.stringWidth("Tout Atout / Sans Atout : OUI - NON")/2)+metrics.stringWidth("Tout Atout / Sans Atout : OUI"), (int) ((this.getHeight())*0.2));
				    	g.drawString("OUI - ", (this.getWidth()/2)-(metrics.stringWidth("Tout Atout / Sans Atout : OUI - NON")/2)+metrics.stringWidth("Annonces : "), (int) ((this.getHeight())*0.3));
				    }
		    		
		    		if (option1 == 1 && option2 == 0){
		    			g.setColor(new Color(255,153,51));
				    	g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 35));
				    	FontMetrics metrics = g.getFontMetrics();
				    	g.drawString("Tout Atout / Sans Atout : ", (this.getWidth()/2)-(metrics.stringWidth("Tout Atout / Sans Atout : OUI - NON")/2) , (int) ((this.getHeight())*0.2));
				    	g.drawString("Annonces : ", (this.getWidth()/2)-(metrics.stringWidth("Tout Atout / Sans Atout : OUI - NON")/2) , (int) ((this.getHeight())*0.3));
				    	g.drawString("Nombre de points maximum : " + option3, (this.getWidth()/2)-(metrics.stringWidth("Tout Atout / Sans Atout : OUI - NON")/2) , (int) ((this.getHeight())*0.4));
				    	g.setColor(new Color(102, 255, 153));
				    	g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 25));
				    	g.drawString("Attente de connexion du joueur distant...", (this.getWidth()/2)-(metrics.stringWidth("Tout Atout / Sans Atout : OUI - NON")/2) , (int) ((this.getHeight())*0.7));
				    	g.drawString(secJD + " secondes restantes", (this.getWidth()/2)-(metrics.stringWidth("Tout Atout / Sans Atout : OUI - NON")/2) , (int) ((this.getHeight())*0.75));
				    	g.setColor(new Color(255, 0, 0));
				    	g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 35));
				    	g.drawString("Valider les options", (this.getWidth()/2)-(metrics.stringWidth("Tout Atout / Sans Atout : OUI - NON")/2) , (int) ((this.getHeight())*0.6));
				    	g.drawString("NON", (this.getWidth()/2)-(metrics.stringWidth("Tout Atout / Sans Atout : OUI - NON")/2)+metrics.stringWidth("Tout Atout / Sans Atout : OUI - "), (int) ((this.getHeight())*0.2));
				    	g.drawString("OUI", (this.getWidth()/2)-(metrics.stringWidth("Tout Atout / Sans Atout : OUI - NON")/2)+metrics.stringWidth("Annonces : "), (int) ((this.getHeight())*0.3));
				    	g.drawString("" + option3, (this.getWidth()/2)-(metrics.stringWidth("Tout Atout / Sans Atout : OUI - NON")/2)+metrics.stringWidth("Nombre de points maximum : ") , (int) ((this.getHeight())*0.4));
				    	g.setColor(new Color(255,153,51));
				    	g.drawString("OUI - ", (this.getWidth()/2)-(metrics.stringWidth("Tout Atout / Sans Atout : OUI - NON")/2)+metrics.stringWidth("Tout Atout / Sans Atout : "), (int) ((this.getHeight())*0.2));
				    	g.drawString(" - NON", (this.getWidth()/2)-(metrics.stringWidth("Tout Atout / Sans Atout : OUI - NON")/2)+metrics.stringWidth("Annonces : OUI"), (int) ((this.getHeight())*0.3));
				    }
		    		
		    		if (option1 == 1 && option2 == 1){
		    			g.setColor(new Color(255,153,51));
				    	g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 35));
				    	FontMetrics metrics = g.getFontMetrics();
				    	g.drawString("Tout Atout / Sans Atout : ", (this.getWidth()/2)-(metrics.stringWidth("Tout Atout / Sans Atout : OUI - NON")/2) , (int) ((this.getHeight())*0.2));
				    	g.drawString("Annonces : ", (this.getWidth()/2)-(metrics.stringWidth("Tout Atout / Sans Atout : OUI - NON")/2) , (int) ((this.getHeight())*0.3));
				    	g.drawString("Nombre de points maximum : " + option3, (this.getWidth()/2)-(metrics.stringWidth("Tout Atout / Sans Atout : OUI - NON")/2) , (int) ((this.getHeight())*0.4));
				    	g.setColor(new Color(102, 255, 153));
				    	g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 25));
				    	g.drawString("Attente de connexion du joueur distant...", (this.getWidth()/2)-(metrics.stringWidth("Tout Atout / Sans Atout : OUI - NON")/2) , (int) ((this.getHeight())*0.7));
				    	g.drawString(secJD + " secondes restantes", (this.getWidth()/2)-(metrics.stringWidth("Tout Atout / Sans Atout : OUI - NON")/2) , (int) ((this.getHeight())*0.75));
				    	g.setColor(new Color(255, 0, 0));
				    	g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 35));
				    	g.drawString("Valider les options", (this.getWidth()/2)-(metrics.stringWidth("Tout Atout / Sans Atout : OUI - NON")/2) , (int) ((this.getHeight())*0.6));
				    	g.drawString("NON", (this.getWidth()/2)-(metrics.stringWidth("Tout Atout / Sans Atout : OUI - NON")/2)+metrics.stringWidth("Tout Atout / Sans Atout : OUI - "), (int) ((this.getHeight())*0.2));
				    	g.drawString("NON", (this.getWidth()/2)-(metrics.stringWidth("Tout Atout / Sans Atout : OUI - NON")/2)+metrics.stringWidth("Annonces : OUI - "), (int) ((this.getHeight())*0.3));
				    	g.drawString("" + option3, (this.getWidth()/2)-(metrics.stringWidth("Tout Atout / Sans Atout : OUI - NON")/2)+metrics.stringWidth("Nombre de points maximum : ") , (int) ((this.getHeight())*0.4));
				    	g.setColor(new Color(255,153,51));
				    	g.drawString("OUI - ", (this.getWidth()/2)-(metrics.stringWidth("Tout Atout / Sans Atout : OUI - NON")/2)+metrics.stringWidth("Tout Atout / Sans Atout : "), (int) ((this.getHeight())*0.2));
				    	g.drawString("OUI - ", (this.getWidth()/2)-(metrics.stringWidth("Tout Atout / Sans Atout : OUI - NON")/2)+metrics.stringWidth("Annonces : "), (int) ((this.getHeight())*0.3));
				    }
		    		
		    	}
		    	
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
	
	public Carte getCarte1(){
		return carte1;
	}
	
	
	
	public void setXMenuCentre(int xMenuCentre){
		if (posXMenuCentre+xMenuCentre < posXMenuDroite +1 && posXMenuCentre+xMenuCentre > posXMenuGauche -1)
			this.xMenuCentre = xMenuCentre;
	}
	
	public void setYMenuCentre(int yMenuCentre){
		if (posYMenuCentre+yMenuCentre > posYMenuGauche -1 && posYMenuCentre+yMenuCentre < posYMenuCentre +1)
			this.yMenuCentre = yMenuCentre;
	}
	
	public void setXMenuGauche(int xMenuGauche){
		if (posXMenuGauche+xMenuGauche < posXMenuCentre +1 && posXMenuGauche+xMenuGauche > -imageMenuGauche.getImage().getWidth(null)-10)
			this.xMenuGauche = xMenuGauche;
	}
	
	public void setYMenuGauche(int yMenuGauche){
		if (posYMenuGauche+yMenuGauche < posYMenuCentre +1)
			this.yMenuGauche = yMenuGauche;
	}
	
	public void setXMenuDroite(int xMenuDroite){
		if (posXMenuDroite+xMenuDroite > posXMenuCentre -1 && posXMenuDroite+xMenuDroite < width+imageMenuGauche.getImage().getWidth(null)+10)
			this.xMenuDroite = xMenuDroite;
	}
	
	public void setYMenuDroite(int yMenuDroite){
		if (posYMenuDroite+yMenuDroite < posYMenuCentre +1)
			this.yMenuDroite = yMenuDroite;
	}
	
	public void setXMenuNew1(int xMenuNew){
		if(xMenuNew < posXMenuGauche)
			this.xMenuNew = xMenuNew;
	}
	
	public void setXMenuNew2(int xMenuNew){
		if(width+xMenuNew > posXMenuDroite)
			this.xMenuNew = xMenuNew;
	}
	
	public int getXMenuCentre(){
		return xMenuCentre;
	}
	
	public int getYMenuCentre(){
		return yMenuCentre;
	}
	
	public int getXMenuGauche(){
		return xMenuGauche;
	}
	
	public int getYMenuGauche(){
		return yMenuGauche;
	}
	
	public int getXMenuDroite(){
		return xMenuDroite;
	}
	
	public int getYMenuDroite(){
		return yMenuDroite;
	}
	
	public void setPlacement (int placement){
		this.placement = placement;
	}
	
	public void setImageMenuCentre(ImageMenu imageMenuCentre){
		this.imageMenuCentre = imageMenuCentre;
	}
	
	public void setImageMenuGauche(ImageMenu imageMenuGauche){
		this.imageMenuGauche = imageMenuGauche;
	}
	
	public void setImageMenuDroite(ImageMenu imageMenuDroite){
		this.imageMenuDroite = imageMenuDroite;
	}
	
	public void setImageMenuNew(ImageMenu imageMenuNew){
		this.imageMenuNew = imageMenuNew;
	}
	
	public ImageMenu getImageMenuCentre(){
		return imageMenuCentre;
	}
	
	public ImageMenu getImageMenuGauche(){
		return imageMenuGauche;
	}
	
	public ImageMenu getImageMenuDroite(){
		return imageMenuDroite;
	}
	 
	public ImageMenu getImageMenuNew(){
		return imageMenuNew;
	}
	 
	public void resetCoords(){
		xMenuCentre = 0;
		yMenuCentre = 0;
		xMenuGauche = 0;
		yMenuGauche = 0;
		xMenuDroite = 0;
		yMenuDroite = 0;
		xMenuNew = 0;
	}
	
	public void setModeActuel(int modeActuel){
		this.modeActuel = modeActuel;
	}
	
	public void resizeImage(int dxdy){
		placement = 2;
		this.dxdy=dxdy;
	}
	
	public void setOptionEnCours(int optionEnCours){
		this.optionEnCours=optionEnCours;
	}
	
	public void setOption1(int option1){
		this.option1=option1;
	}
	
	public void setOption2(int option2){
		this.option2=option2;
	}
	
	public void setOption3(int option3){
		this.option3=option3;
	}
	
	public int getOption1(){
		return option1;
	}
	
	public int getOption2(){
		return option2;
	}
	
	public int getOption3(){
		return option3;
	}
	
	public int getOptionEnCours(){
		return optionEnCours;
	}
	
	public void setAnnonce(String annonce){
		this.annonce=annonce;
	}
	
	public void setIntro(int intro){
		this.intro = intro;
	}
	
	public void setSec(int sec){
		this.sec = sec;
	}
	
	public void setSecJD(int secJD){
		this.secJD = secJD;
	}

	public void setPoints(int points1, int points2, int pointsAnnonce1, int pointsAnnonce2, int pointsPartie1, int pointsPartie2, int pointsTotal1, int pointsTotal2, String annoncelol){
		this.points1 = points1;
		this.points2 = points2;
		this.pointsAnnonce1 = pointsAnnonce1;
		this.pointsAnnonce2 = pointsAnnonce2;
		this.pointsPartie1 = pointsPartie1;
		this.pointsPartie2 = pointsPartie2;
		this.pointsTotal1 = pointsTotal1;
		this.pointsTotal2 = pointsTotal2;
		this.annoncelol = annoncelol;
	}
	
	public void setPos(int pos){
		this.pos = pos;
	}

	public void setCartesDistribuees(int cartesDistribuees) {
		this.cartesDistribuees = cartesDistribuees;
	}
	
	public void setMessage(String message){
		this.message = message;
	}
	
	public void setSecAD(int secAD){
		this.secAD = secAD;
	}

}

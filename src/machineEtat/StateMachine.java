package machineEtat;

import camera.CaptureLive;
import camera.CaptureLiveDistribution;
import iug.ViewControllerInterface;
import structure.AnnonceInterface;
import structure.CarteList;
import structure.CarteListInterface;
import logiqueDeJeux.*;
import menus.MenuOptions;

public class StateMachine 
{
	private enum State {Distribution,
						Annonce,AnnoncePasse1,AnnonceFaite,AnnoncePasse2,AnnoncePasse3,AnnonceAFaire,AnnonceAFaire2,
						DebutTour,DebutTour1,DebutTour2,DebutTour3,DebutTour4,
						SecondTour,SecondTour1, SecondTour2, SecondTour3, SecondTour4,
						MontreCarte1,MontreCarte2,MontreCarte3,MontreCarte4,
						ResteDesTours,
						MancheTermine,
						Quit;
						};
						
	private int nbPasse=0;
	//Thread pour quitter
	private Thread t1;
	private State previousState;
	private State state;
	private EtatDuJeuInterface etat;
	private JoueurDistantInterface joueurD;
	private int valeurAnnonceMax = 80;
	private int premierAJouer =1;
	private ViewControllerInterface vci;
	public static int numJoueurDistant=4;
	private CarteListInterface carteAnnonce = new CarteList();
	private BeloteCoinche belote;
	private CaptureLiveDistribution captureQrCode;
	private CaptureLive captureCarte;
	
	public StateMachine(JoueurDistantInterface joueurD,ViewControllerInterface vci, BeloteCoinche beloteCoinche) 
	{
		this.belote = beloteCoinche;
		this.captureQrCode = new CaptureLiveDistribution(belote);
		new Thread(captureQrCode).start();
		this.state = State.Distribution;
		this.etat = new EtatDuJeu(this);
		this.joueurD = joueurD;
		this.vci = vci;
	}
	 
	public void eventCarte(CardEvent carte) 
	{
		switch(this.state) {
		 
/*********************** Distribution *******************/		 
		case Distribution:
			if (etat.valide(carte.getCarte(),joueurD,numJoueurDistant,true))
			{
				joueurD.addCard(carte.getCarte());
				vci.distribution(joueurD.nbCard());
				if (joueurD.aHuitCarte())
				{
					captureQrCode.stop();
					captureCarte = new CaptureLive(belote);
					new Thread(captureCarte).start();
					this.state = State.Annonce;
					vci.modeAnnonce();
					vci.joueurEnCours(premierAJouer);
					if (premierAJouer == 4)
						joueurD.waitAnnonce();
				}
				else
					this.state = State.Distribution;
			}
			break;
			 
/*********************** Premier Tour **********************/			 
		case DebutTour:
			if (etat.valide(carte.getCarte(),joueurD,numJoueurDistant,false))
			{
				if (StateMachine.numJoueurDistant==etat.getNumJoueur())
					vci.afficherCarte(carte.getCarte().getLabelNum()+carte.getCarte().getSuit());
				else
					joueurD.sendCard(carte.getCarte(), etat.getNumJoueur());
				etat.joue(carte.getCarte(),vci,joueurD,numJoueurDistant); //joue la carte et passe au joueur suivant
				this.state = State.DebutTour1;
			}
			break;
		case DebutTour1:
			if (etat.valide(carte.getCarte(),joueurD,numJoueurDistant,false))
			{
				if (StateMachine.numJoueurDistant==etat.getNumJoueur())
					vci.afficherCarte(carte.getCarte().getLabelNum()+carte.getCarte().getSuit());
				else
					joueurD.sendCard(carte.getCarte(), etat.getNumJoueur());
				etat.joue(carte.getCarte(),vci,joueurD,numJoueurDistant);	//joue la carte et passe au joueur suivant
				this.state = State.DebutTour2;
			}
			break;
		case DebutTour2:
			if (etat.valide(carte.getCarte(),joueurD,numJoueurDistant,false))
			{
				if (StateMachine.numJoueurDistant==etat.getNumJoueur())
					vci.afficherCarte(carte.getCarte().getLabelNum()+carte.getCarte().getSuit());
				else
					joueurD.sendCard(carte.getCarte(), etat.getNumJoueur());
				etat.joue(carte.getCarte(),vci,joueurD,numJoueurDistant);	//joue la carte et passe au joueur suivant
				this.state = State.DebutTour3;
			}
			break;
		case DebutTour3:
			if (etat.valide(carte.getCarte(),joueurD,numJoueurDistant,false))
			{
				if (StateMachine.numJoueurDistant==etat.getNumJoueur())
					vci.afficherCarte(carte.getCarte().getLabelNum()+carte.getCarte().getSuit());
				else
					joueurD.sendCard(carte.getCarte(), etat.getNumJoueur());
				etat.joue(carte.getCarte(),vci,joueurD,numJoueurDistant);	//joue la carte et passe au joueur suivant
				this.state = State.DebutTour4;
			}
			break;
			
/************************ Second Tour ************************/			 
		case SecondTour:
			if (etat.valide(carte.getCarte(),joueurD,numJoueurDistant,false))
			{
				if (StateMachine.numJoueurDistant==etat.getNumJoueur())
					vci.afficherCarte(carte.getCarte().getLabelNum()+carte.getCarte().getSuit());
				else
					joueurD.sendCard(carte.getCarte(), etat.getNumJoueur());
				etat.joue(carte.getCarte(),vci,joueurD,numJoueurDistant);	//joue la carte et passe au joueur suivant
				this.state = State.SecondTour1;
			}
			break;
		case SecondTour1:
			if (etat.valide(carte.getCarte(),joueurD,numJoueurDistant,false))
			{
				if (StateMachine.numJoueurDistant==etat.getNumJoueur())
					vci.afficherCarte(carte.getCarte().getLabelNum()+carte.getCarte().getSuit());
				else
					joueurD.sendCard(carte.getCarte(), etat.getNumJoueur());
				etat.joue(carte.getCarte(),vci,joueurD,numJoueurDistant);	//joue la carte et passe au joueur suivant
				this.state = State.SecondTour2;
			}
			break;
		case SecondTour2:
			if (etat.valide(carte.getCarte(),joueurD,numJoueurDistant,false))
			{
				if (StateMachine.numJoueurDistant==etat.getNumJoueur())
					vci.afficherCarte(carte.getCarte().getLabelNum()+carte.getCarte().getSuit());
				else
					joueurD.sendCard(carte.getCarte(), etat.getNumJoueur());
				etat.joue(carte.getCarte(),vci,joueurD,numJoueurDistant);	//joue la carte et passe au joueur suivant
				this.state = State.SecondTour3;
			}
			break;
		case SecondTour3:
			if (etat.valide(carte.getCarte(),joueurD,numJoueurDistant,false))
			{
				if (StateMachine.numJoueurDistant==etat.getNumJoueur())
					vci.afficherCarte(carte.getCarte().getLabelNum()+carte.getCarte().getSuit());
				else
					joueurD.sendCard(carte.getCarte(), etat.getNumJoueur());
				etat.joue(carte.getCarte(),vci,joueurD,numJoueurDistant);	//joue la carte et passe au joueur suivant
				this.state = State.SecondTour4;
			}
			break;
			 
/********************* Montre Cartes ************************/
		case MontreCarte1:
			this.carteAnnonce.ajoute(carte.getCarte());
			this.state = State.MontreCarte1;
			break;	 
		case MontreCarte2:
			this.carteAnnonce.ajoute(carte.getCarte());
			this.state = State.MontreCarte2;
			break;	 
		case MontreCarte3:
			this.carteAnnonce.ajoute(carte.getCarte());
			this.state = State.MontreCarte3;
			break;	 
		case MontreCarte4:
			this.carteAnnonce.ajoute(carte.getCarte());
			this.state = State.MontreCarte4;
			break;	 
			
			 
/********************* Reste Des Tours **********************/			 
		case ResteDesTours:
			if (etat.valide(carte.getCarte(),joueurD,numJoueurDistant,false))
			{
				if (StateMachine.numJoueurDistant==etat.getNumJoueur())
					vci.afficherCarte(carte.getCarte().getLabelNum()+carte.getCarte().getSuit());
				else
					joueurD.sendCard(carte.getCarte(), etat.getNumJoueur());
				etat.joue(carte.getCarte(),vci,joueurD,numJoueurDistant); //joue la carte et passe au joueur suivant
				this.state = State.ResteDesTours;
			}
			break;
			
		default:
			//Nothing
			break;
		}
	}
		 
	public void eventGestePasser()
	{
		nbPasse++;
		switch(this.state) 
		{
		
		/********************* Annnonce ********************/
		case Annonce:
			nbPasse++;
			if (numJoueurDistant != etat.getNumJoueur())
			{
				joueurD.sendAnnonce(null, etat.getNumJoueur());
			}
			etat.joueurSuivant(vci,joueurD,"annonce",numJoueurDistant);
			this.state = State.AnnoncePasse1;
			break;
			
		case AnnoncePasse1:
			nbPasse++;
			if (numJoueurDistant != etat.getNumJoueur())
			{
				joueurD.sendAnnonce(null, etat.getNumJoueur());
			}
			etat.joueurSuivant(vci,joueurD, "annonce", numJoueurDistant);
			this.state = State.AnnoncePasse2;
			break;
			
		case AnnonceFaite:
			nbPasse++;
			if (numJoueurDistant != etat.getNumJoueur())
			{
				joueurD.sendAnnonce(null, etat.getNumJoueur());
			}
			etat.joueurSuivant(vci,joueurD, "annonce", numJoueurDistant);
			this.state = State.AnnoncePasse2;
			break;
			
		case AnnoncePasse2:
			nbPasse++;
			if (numJoueurDistant != etat.getNumJoueur())
			{
				joueurD.sendAnnonce(null, etat.getNumJoueur());
			}
			etat.joueurSuivant(vci,joueurD, "annonce", numJoueurDistant);
			this.state = State.AnnoncePasse3;
			break;
			
		case AnnoncePasse3:
			nbPasse=0;
			if (etat.annonceFaite())
			{
				if (numJoueurDistant != etat.getNumJoueur())
				{
					joueurD.sendAnnonce(null, etat.getNumJoueur());
				}
				vci.modeJeu();
				etat.setNumJoueur(premierAJouer,joueurD,numJoueurDistant);
				joueurD.sendFinAnnonce();
				vci.joueurEnCours(premierAJouer);
				this.state = State.DebutTour;
			}
			else /*annonce pas faite*/
			{
				etat.joueurSuivant(vci,joueurD, "annonce", numJoueurDistant);
				etat.joueurSuivant(vci,joueurD, "annonce", numJoueurDistant);
				this.premierAJouer = etat.getNumJoueur();
				this.state = State.Distribution;
			}
			break;
			
		case AnnonceAFaire:
			etat.annonceValueUp(vci);
			this.state = State.AnnonceAFaire;
			break;
			
		case AnnonceAFaire2:
			etat.annonceNextSuit(vci);
			this.state = State.AnnonceAFaire2;
			break;
			
		default:
			//Nothing
			break;
		}
	}
	 
	public void eventGesteAccept()
	{
		nbPasse=0;
		 switch(this.state) 
		 {
		 
/*************************** Annonce ***********************/
		 case Annonce:
			 etat.initAnnonce(vci);
			 this.state = State.AnnonceAFaire;
			 break;
		 
		 case AnnonceFaite:
			 etat.initAnnonce(vci);
			 this.state = State.AnnonceAFaire;
			 break;
		 
		 case AnnoncePasse1:
			 etat.initAnnonce(vci);
			 this.state = State.AnnonceAFaire;
			 break;
			 
		 case AnnoncePasse2:
			 etat.initAnnonce(vci);
			 this.state = State.AnnonceAFaire;
			 break;
		 
		 case AnnoncePasse3:
			 etat.initAnnonce(vci);
			 this.state = State.AnnonceAFaire;
			 break;
		 
		 case AnnonceAFaire:
			 //Affiche l'annonce
			 vci.valideValeurAnnonce();
			 this.state = State.AnnonceAFaire2;
			 break;
		 
		 case AnnonceAFaire2:
			 nbPasse=0;
			 etat.valideAnnonce(vci);
			 joueurD.sendAnnonce(etat.getAnnonce(), etat.getNumJoueur());
			 this.valeurAnnonceMax=etat.valeurAnnonce();
			 if (this.valeurAnnonceMax<250)
			 {
				 etat.joueurSuivant(vci,joueurD, "annonce", numJoueurDistant);
				 this.state = State.AnnonceFaite;
			 }
			 else
			 {
				 vci.modeJeu();
				 etat.setNumJoueur(premierAJouer,joueurD,numJoueurDistant);
				 joueurD.sendFinAnnonce();
				 vci.joueurEnCours(premierAJouer);
				 this.state = State.DebutTour;
			 }
			 break;
		 
/************************ Second Tour/Montre Carte **********************/
		 case SecondTour1:
			 this.state = State.MontreCarte1;
			 vci.faireAnnonce();
			 break;
		 case SecondTour2:
			 this.state = State.MontreCarte2;
			 vci.faireAnnonce();
			 break;
		 case SecondTour3:
			 this.state = State.MontreCarte3;
			 vci.faireAnnonce();
			 break;
		 case SecondTour4:
			 this.state = State.MontreCarte4;
			 vci.faireAnnonce();
			 break;
			 
			 
		 case MontreCarte1:
			 if (MenuOptions.annonceCartes) {
				etat.verifieAnnonceCartes(joueurD, carteAnnonce, vci);
				this.state = State.SecondTour1;
			}
			break;
		 case MontreCarte2:
			 if (MenuOptions.annonceCartes) {
					etat.verifieAnnonceCartes(joueurD, carteAnnonce, vci);
					this.state = State.SecondTour2;
				}
			 break;
		 case MontreCarte3:
			 if (MenuOptions.annonceCartes) {
					etat.verifieAnnonceCartes(joueurD, carteAnnonce, vci);
					this.state = State.SecondTour3;
				}
			 break;
		 case MontreCarte4:
			 if (MenuOptions.annonceCartes) {
					etat.verifieAnnonceCartes(joueurD, carteAnnonce, vci);
					this.state = State.SecondTour4;
				}
			 break;
			 
			 
		 case MancheTermine:
			 etat.setNumJoueur(premierAJouer,joueurD,numJoueurDistant);
			 vci.distribution(0);
			 captureQrCode = new CaptureLiveDistribution(belote);
			 new Thread(captureQrCode).start();
			 this.state=State.Distribution;
			 break;
/******************************* Quitter *****************************/
		 case Quit:
			 if (captureCarte!=null)
				 captureCarte.stop();
			 if(captureQrCode!=null)
				 captureQrCode.stop();
			 t1.interrupt();
		 default:
			break;
		 }
		 
	}
	
	public void eventGesteRetour()
	{
		switch(this.state)
		{
		case AnnonceAFaire:
			if (etat.valeurAnnonce()==this.valeurAnnonceMax)
				this.state=State.AnnonceFaite;  //peut etre revoir les etats pour am�lior� ici (pour ne pas avoir a faire des passe en plus)
			else	
			{
				etat.annonceValueDown();
				this.state = State.AnnonceAFaire;
			}
			break;
		case AnnonceAFaire2:
			this.state = State.AnnonceAFaire; //je supose que l'on arrive pas ici pour anuler ensuite
			break;
			
		default:
			break;
		}
	}
	
	
	public void eventGesteCoinche()
	{
		nbPasse=0;
		 switch(this.state) 
		 {
		 case AnnonceFaite:
			 etat.coinche();
			 vci.coinche(etat.getNumJoueur());
			 if (etat.getNumJoueur()!=numJoueurDistant)
				 joueurD.sendCoinche(etat.getNumJoueur());
			 etat.setNumJoueur(premierAJouer,joueurD,numJoueurDistant);
			 vci.modeJeu();
			 vci.joueurEnCours(premierAJouer);
			 joueurD.sendFinAnnonce();
			 this.state = State.DebutTour;
			 break;

		 case DebutTour:
			 etat.coinche();
			 if (etat.getCoinche()==4)
			 {
				 if (etat.getNumJoueur()!=numJoueurDistant)
					 joueurD.sendCoinche(etat.getNumJoueur());
				 vci.contreCoinche();
			 }
			 else
			 {
				 if (etat.getNumJoueur()!=numJoueurDistant)
					 joueurD.sendCoinche(etat.getNumJoueur());
				 vci.coinche(1);
			 }
			 this.state = State.DebutTour;
			 break;
		 
		 default:
			 break;
			 
		 }
	}

	public void eventFinPli()
	{
		switch(this.state)
		{
		case DebutTour4:
			etat.finpli(vci, joueurD);
			System.out.println("Premier tour terminer");
			this.state = State.SecondTour;
			break;
			
		case SecondTour4:
			etat.finpli(vci, joueurD);
			System.out.println("Second tour terminer");
			this.state = State.ResteDesTours;
			break;
				
		case ResteDesTours:
			etat.finpli(vci, joueurD);
			if (etat.dernierPli())
				this.eventMancheTerminer();
			else
				this.state = State.ResteDesTours;
			break;
				
		default:
			break;
			
		}
	}
	
	public void eventDistributionTerminer(CardEvent Carte)
	{
		 switch(this.state) 
		 {
		 	 
		 case Distribution:
				 this.state = State.Annonce;
				 
		default:
			break;
		 }
	}
	
	public void eventMancheTerminer()
	{
		switch (this.state)
		{
		case ResteDesTours:
			captureCarte.stop();
			this.premierAJouer++;
			etat.mancheTerminer(vci,joueurD,premierAJouer);
			this.state = State.MancheTermine;
		default:
			break;
		}
	}
	
	public void eventPartieTerminer()
	{
		switch (this.state)
		{
		case ResteDesTours:
			vci.partieTerminer();
			break;
		default:
			//Nothing
			break;
		}
	}
	
	public void eventQuit() 
	 {
		switch(this.state)
		{
		case AnnonceAFaire:
			if (nbPasse==0&&getEtat().annonceFaite())
			{
				this.state=State.AnnonceFaite;
				vci.effaceAnnonce();
				getEtat().actualiseAnnonce(vci);
			}	
			else if (nbPasse==0)
			{
				vci.effaceAnnonce();
				getEtat().setAnnonceNull();
				vci.actualiseAnnonce(null, null);
				this.state=State.Annonce;
			}
			else if (nbPasse==1)
			{
				vci.effaceAnnonce();
				getEtat().actualiseAnnonce(vci);
				this.state=State.AnnoncePasse1;
			}
			else if (nbPasse==2)
			{
				vci.effaceAnnonce();
				getEtat().actualiseAnnonce(vci);
				this.state=State.AnnoncePasse2;
			}
			else if (nbPasse==3)
			{
				vci.effaceAnnonce();
				getEtat().actualiseAnnonce(vci);
				this.state=State.AnnoncePasse3;
			}
			break;
		case AnnonceAFaire2:
			if (nbPasse==0&&getEtat().annonceFaite())
			{
				vci.effaceAnnonce();
				getEtat().actualiseAnnonce(vci);
				this.state=State.AnnonceFaite;
			}	
			else if (nbPasse==0)
			{
				vci.effaceAnnonce();
				getEtat().setAnnonceNull();
				vci.actualiseAnnonce(null, null);
				this.state=State.Annonce;
			}
			else if (nbPasse==1)
			{
				vci.effaceAnnonce();
				getEtat().actualiseAnnonce(vci);
				this.state=State.AnnoncePasse1;
			}
			else if (nbPasse==2)
			{
				vci.effaceAnnonce();
				getEtat().actualiseAnnonce(vci);
				this.state=State.AnnoncePasse2;
			}
			else if (nbPasse==3)
			{
				vci.effaceAnnonce();
				getEtat().actualiseAnnonce(vci);
				this.state=State.AnnoncePasse3;
			}
			break;
		case Annonce:
			break;
		case AnnonceFaite:
			break;
		case AnnoncePasse1:
			break;
		case AnnoncePasse2:
			break;
		case AnnoncePasse3:
			break;
			
		case Quit:
			break;
/*********************************** reste du jeu ************************************/			
		case Distribution:
			this.previousState=this.state;
			this.state=State.Quit;
			vci.modeQuitter(5);
			t1 = new Thread(new AlarmQuit(vci,belote,true,joueurD, this));
			t1.start();
			break;
		default:
			this.previousState=this.state;
			this.state=State.Quit;
			vci.modeQuitter(5);
			t1 = new Thread(new AlarmQuit(vci,belote,false, joueurD, this));
			t1.start();
			break;
		}
	 }

	public void setAnnonce(AnnonceInterface annonce) 
	{
		switch 	(this.state)
		{
		case Annonce:
			int retour = etat.setAnnonce(annonce, vci, joueurD);
			if (retour!=2) 
			{
				this.valeurAnnonceMax = etat.valeurAnnonce();
				etat.joueurSuivant(vci, joueurD, "annonce", numJoueurDistant);
				if (retour == 1) 
				{
					nbPasse=0;
					this.state = State.AnnonceFaite;
				} else if (retour == 0) 
				{
					nbPasse++;
					this.state = State.AnnoncePasse1;
				}
			}	
			break;
			
		case AnnonceFaite:
			retour = etat.setAnnonce(annonce, vci, joueurD);
			if (retour!=2) 
			{
				this.valeurAnnonceMax = etat.valeurAnnonce();
				etat.joueurSuivant(vci, joueurD, "annonce", numJoueurDistant);
				if (retour == 1) 
				{
					nbPasse=0;
					this.state = State.AnnonceFaite;
				} else if (retour == 0) 
				{
					nbPasse++;
					this.state = State.AnnoncePasse2;
				}
			}
			break;
			
		case AnnoncePasse1:
			retour = etat.setAnnonce(annonce, vci, joueurD);
			if (retour!=2) 
			{
				this.valeurAnnonceMax = etat.valeurAnnonce();
				etat.joueurSuivant(vci, joueurD, "annonce", numJoueurDistant);
				if (retour == 1) 
				{
					nbPasse=0;
					this.state = State.AnnonceFaite;
				} else if (retour == 0) 
				{
					nbPasse++;
					this.state = State.AnnoncePasse2;
				}
			}
			break;
			
		case AnnoncePasse2:
			retour = etat.setAnnonce(annonce, vci, joueurD);
			if (retour!=2) 
			{
				this.valeurAnnonceMax = etat.valeurAnnonce();
				etat.joueurSuivant(vci, joueurD, "annonce", numJoueurDistant);
				if (retour == 1) 
				{
					nbPasse=0;
					this.state = State.AnnonceFaite;
				} else if (retour == 0) 
				{
					nbPasse++;
					this.state = State.AnnoncePasse3;
				}
			}
			break;
			
		case AnnoncePasse3:
			retour = etat.setAnnonce(annonce, vci, joueurD);
			if (retour!=2) 
			{
				this.valeurAnnonceMax = etat.valeurAnnonce();
				etat.joueurSuivant(vci, joueurD, "annonce", numJoueurDistant);
				if (retour == 1) 
				{
					nbPasse=0;
					this.state = State.AnnonceFaite;
				} else if (retour == 0) {
					if (etat.annonceFaite()) 
					{
						nbPasse=0;
						vci.modeJeu();
						joueurD.sendFinAnnonce();
						etat.setNumJoueur(premierAJouer, joueurD,
								numJoueurDistant);
						vci.joueurEnCours(premierAJouer);
						this.state = State.DebutTour;
					}
					else /*annonce pas faite*/
					{
						etat.joueurSuivant(vci,joueurD, "annonce", numJoueurDistant);
						etat.joueurSuivant(vci,joueurD, "annonce", numJoueurDistant);
						this.premierAJouer = etat.getNumJoueur();
						this.state = State.Distribution;
					}
				}
			}
			break;
			
			
		default:
			break;
		}
	}

	public EtatDuJeuInterface getEtat() 
	{
		return this.etat;
	}

	
	public boolean isStateDistrib() {
		return this.state==State.Distribution;
		
	}

	public void previousState() 
	{
		this.state=this.previousState;
		
	} 
}

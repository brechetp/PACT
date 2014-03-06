package machineEtat;

import iug.ViewController;
import iug.ViewControllerInterface;
import structure.AnnonceInterface;
import logiqueDeJeux.*;

public class StateMachine 
{
	private enum State {Distribution,
						Annonce,AnnoncePasse1,AnnoncePasse2,AnnoncePasse3,AnnonceAFaire,AnnonceAFaire2,
						DebutTour,DebutTour1,DebutTour2,DebutTour3,DebutTour4,FinDebutTour, 
						SecondTour,SecondTour1, SecondTour2, SecondTour3, SecondTour4,
						MontreCarte1,MontreCarte2,MontreCarte3,MontreCarte4,
						ResteDesTours, };
	private State state;
	private EtatDuJeuInterface etat;
	private JoueurDistantInterface joueurD;
	private int valeurAnnonceMax = 80;
	private int premierAJouer =1;
	private ViewControllerInterface vci;
	private int numJoueurDistant=2;
	
	public StateMachine(JoueurDistantInterface joueurD, EtatDuJeu etat) 
	{
		this.state = State.Distribution;
		this.etat = etat;
		this.joueurD = joueurD;
		this.vci = new ViewController();
	}
	 
	public void eventCarte(CardEvent carte) 
	{
		switch(this.state) {
		 
/*********************** Distribution *******************/		 
		case Distribution:
			joueurD.addCard(carte.getCarte());
			if (joueurD.aHuitCarte())
			{
				this.state = State.Annonce;
				vci.joueurEnCours(premierAJouer);
				System.out.println("Distribution Terminer");
			}
			else
				this.state = State.Distribution;
			break;
			 
/*********************** Premier Tour **********************/			 
		case DebutTour:
			if (etat.valide(carte.getCarte()))
			{
				if (this.numJoueurDistant==etat.getNumJoueur())
					vci.afficherCarte(carte.getCarte().getLabelNum()+carte.getCarte().getSuit());
				else
					joueurD.sendCard(carte.getCarte(), etat.getNumJoueur());
				etat.joue(carte.getCarte(),vci); //joue la carte et passe au joueur suivant
				this.state = State.DebutTour1;
			}
			break;
		case DebutTour1:
			if (etat.valide(carte.getCarte()))
			{
				if (this.numJoueurDistant==etat.getNumJoueur())
					vci.afficherCarte(carte.getCarte().getLabelNum()+carte.getCarte().getSuit());
				else
					joueurD.sendCard(carte.getCarte(), etat.getNumJoueur());
				etat.joue(carte.getCarte(),vci);	//joue la carte et passe au joueur suivant
				this.state = State.DebutTour2;
			}
			break;
		case DebutTour2:
			if (etat.valide(carte.getCarte()))
			{
				if (this.numJoueurDistant==etat.getNumJoueur())
					vci.afficherCarte(carte.getCarte().getLabelNum()+carte.getCarte().getSuit());
				else
					joueurD.sendCard(carte.getCarte(), etat.getNumJoueur());
				etat.joue(carte.getCarte(),vci);	//joue la carte et passe au joueur suivant
				this.state = State.DebutTour3;
			}
			break;
		case DebutTour3:
			if (etat.valide(carte.getCarte()))
			{
				if (this.numJoueurDistant==etat.getNumJoueur())
					vci.afficherCarte(carte.getCarte().getLabelNum()+carte.getCarte().getSuit());
				else
					joueurD.sendCard(carte.getCarte(), etat.getNumJoueur());
				etat.joue(carte.getCarte(),vci);	//joue la carte et passe au joueur suivant
				this.state = State.DebutTour4;
			}
			break;
		case DebutTour4:
			if (etat.valide(carte.getCarte()))
			{
				if (this.numJoueurDistant==etat.getNumJoueur())
					vci.afficherCarte(carte.getCarte().getLabelNum()+carte.getCarte().getSuit());
				else
					joueurD.sendCard(carte.getCarte(), etat.getNumJoueur());
				etat.joue(carte.getCarte(),vci);	//joue la carte et passe au joueur suivant
				this.state = State.FinDebutTour;
			}
			break;
			
/************************ Second Tour ************************/			 
		case SecondTour:
			if (etat.valide(carte.getCarte()))
			{
				if (this.numJoueurDistant==etat.getNumJoueur())
					vci.afficherCarte(carte.getCarte().getLabelNum()+carte.getCarte().getSuit());
				else
					joueurD.sendCard(carte.getCarte(), etat.getNumJoueur());
				etat.joue(carte.getCarte(),vci);	//joue la carte et passe au joueur suivant
				this.state = State.SecondTour1;
			}
			break;
		case SecondTour1:
			if (etat.valide(carte.getCarte()))
			{
				if (this.numJoueurDistant==etat.getNumJoueur())
					vci.afficherCarte(carte.getCarte().getLabelNum()+carte.getCarte().getSuit());
				else
					joueurD.sendCard(carte.getCarte(), etat.getNumJoueur());
				etat.joue(carte.getCarte(),vci);	//joue la carte et passe au joueur suivant
				this.state = State.SecondTour2;
			}
			break;
		case SecondTour2:
			if (etat.valide(carte.getCarte()))
			{
				if (this.numJoueurDistant==etat.getNumJoueur())
					vci.afficherCarte(carte.getCarte().getLabelNum()+carte.getCarte().getSuit());
				else
					joueurD.sendCard(carte.getCarte(), etat.getNumJoueur());
				etat.joue(carte.getCarte(),vci);	//joue la carte et passe au joueur suivant
				this.state = State.SecondTour3;
			}
			break;
		case SecondTour3:
			if (etat.valide(carte.getCarte()))
			{
				if (this.numJoueurDistant==etat.getNumJoueur())
					vci.afficherCarte(carte.getCarte().getLabelNum()+carte.getCarte().getSuit());
				else
					joueurD.sendCard(carte.getCarte(), etat.getNumJoueur());
				etat.joue(carte.getCarte(),vci);	//joue la carte et passe au joueur suivant
				this.state = State.SecondTour4;
			}
			break;
			 
/********************* Montre Cartes ************************/
		case MontreCarte1:
			if (true /*carte valide*/)
				this.state = State.MontreCarte1;
			break;	 
		case MontreCarte2:
			if (true /*carte valide*/)
				this.state = State.MontreCarte2;
			break;	 
		case MontreCarte3:
			if (true /*carte valide*/)
				this.state = State.MontreCarte3;
			break;	 
		case MontreCarte4:
			if (true /*carte valide*/)
				this.state = State.MontreCarte4;
			break;	 
			
			 
/********************* Reste Des Tours **********************/			 
		case ResteDesTours:
			if (etat.valide(carte.getCarte()))
			{
				if (this.numJoueurDistant==etat.getNumJoueur())
					vci.afficherCarte(carte.getCarte().getLabelNum()+carte.getCarte().getSuit());
				else
					joueurD.sendCard(carte.getCarte(), etat.getNumJoueur());
				etat.joue(carte.getCarte(),vci); //joue la carte et passe au joueur suivant
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
		switch(this.state) 
		{
		case Annonce:
			etat.joueurSuivant(vci);
			this.state = State.AnnoncePasse1;
			break;
		case AnnoncePasse1:
			etat.joueurSuivant(vci);
			this.state = State.AnnoncePasse2;
			break;
		case AnnoncePasse2:
			etat.joueurSuivant(vci);
			this.state = State.AnnoncePasse3;
			break;
		case AnnoncePasse3:
			if (etat.annonceFaite())
			{
				etat.joueurSuivant(vci);
				this.state = State.DebutTour;
			}
			else /*annonce pas faite*/
			{
				etat.joueurSuivant(vci);
				etat.joueurSuivant(vci);
				this.state = State.Distribution;
			}
			break;
			
		case AnnonceAFaire:
			etat.annonceValueUp();
			this.state = State.AnnonceAFaire;
			break;
			
		case AnnonceAFaire2:
			etat.annonceNextSuit();
			this.state = State.AnnonceAFaire2;
			break;
			
		default:
			//Nothing
			break;
		}
	}
	 
	public void eventGesteAccept()
	{
		 switch(this.state) 
		 {
		 
/*************************** Annonce ***********************/
		 case Annonce:
			 etat.initAnnonce();
			 this.state = State.AnnonceAFaire;
			 break;
		 
		 case AnnoncePasse1:
			 etat.initAnnonce();
			 this.state = State.AnnonceAFaire;
			 break;
		 
		 case AnnoncePasse2:
			 etat.initAnnonce();
			 this.state = State.AnnonceAFaire;
			 break;
		 
		 case AnnoncePasse3:
			 etat.initAnnonce();
			 this.state = State.AnnonceAFaire;
			 break;
		 
		 case AnnonceAFaire:
			 //Action graphique
			 this.state = State.AnnonceAFaire2;
			 break;
		 
		 case AnnonceAFaire2:
			 etat.valideAnnonce();
			 this.valeurAnnonceMax=etat.valeurAnnonce();
			 etat.joueurSuivant(vci);
			 this.state = State.AnnoncePasse1;
			 break;
		 
/************************ Second Tour/Montre Carte **********************/
	/*	 case SecondTour1:
			 this.state = State.MontreCarte1;
			 break;
		 case SecondTour2:
			 this.state = State.MontreCarte2;
			 break;
		 case SecondTour3:
			 this.state = State.MontreCarte3;
			 break;
		 case SecondTour4:
			 this.state = State.MontreCarte4;
			 break;		*/
			 
			 
		 case MontreCarte1:
			 //Action
			 this.state = State.SecondTour1;
			 break;
		 case MontreCarte2:
			 //Action
			 this.state = State.SecondTour2;
			 break;
		 case MontreCarte3:
			 //Action
			 this.state = State.SecondTour3;
			 break;
		 case MontreCarte4:
			 //Action
			 this.state = State.SecondTour4;
			 break;
		 
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
				this.state=State.AnnoncePasse1;  //peut etre revoir les etats pour amélioré ici (pour ne pas avoir a faire des passe en plus)
			else	
			{
				etat.annonceValueDown();
				this.state = State.AnnonceAFaire;
			}
			break;
		case AnnonceAFaire2:
			this.state = State.AnnonceAFaire; //je supose que l'on arrive pas ici pour anuler ensuite
			break;
			
		case MontreCarte1:
			this.state = State.SecondTour1;
			break;
		case MontreCarte2:
			this.state = State.SecondTour2;
			break;
		case MontreCarte3:
			this.state = State.SecondTour3;
			break;
		case MontreCarte4:
			this.state = State.SecondTour4;
			break;
			
		default:
			break;
		}
	}
	
	
	public void eventGesteCoinche()
	{
		 switch(this.state) 
		 {
		 case AnnoncePasse1:
			 etat.coinche();
			 vci.coinche(etat.getNumJoueur());
			 this.state = State.DebutTour;
			 break;
		 
		 case DebutTour:
			 etat.coinche();
			 vci.coinche(etat.getNumJoueur());
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
			vci.effacerCartes();
			etat.finpli();
			System.out.println("Premier tour terminer");
			this.state = State.SecondTour;
			break;
			
		case SecondTour4:
			vci.effacerCartes();
			etat.finpli();
			System.out.println("Second tour terminer");
			this.state = State.ResteDesTours;
			break;
				
		case ResteDesTours:
			vci.effacerCartes();
			etat.finpli();
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
			etat.mancheTerminer();
			this.premierAJouer++;
			etat.setNumJoueur(premierAJouer);
			this.state = State.Distribution;
			System.out.println("Manche terminer");
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
			joueurD.partieTerminer();
			break;
		default:
			//Nothing
			break;
		}
	}
	
	public void eventQuit() 
	 {
		 System.exit(0);
	 }

	public void setAnnonce(AnnonceInterface annonce) 
	{
		switch 	(this.state)
		{
		case Annonce:
			etat.setAnnonce(annonce);
			etat.valeurAnnonce();
			this.valeurAnnonceMax=etat.valeurAnnonce();
			etat.joueurSuivant(vci);
			this.state= State.AnnoncePasse1;
			break;
			
		case AnnoncePasse1:
			etat.setAnnonce(annonce);
			etat.valeurAnnonce();
			this.valeurAnnonceMax=etat.valeurAnnonce();
			etat.joueurSuivant(vci);
			this.state= State.AnnoncePasse1;
			break;
			
		case AnnoncePasse2:
			etat.setAnnonce(annonce);
			etat.valeurAnnonce();
			this.valeurAnnonceMax=etat.valeurAnnonce();
			etat.joueurSuivant(vci);
			this.state= State.AnnoncePasse1;
			break;
			
		case AnnoncePasse3:
			etat.setAnnonce(annonce);
			etat.valeurAnnonce();
			this.valeurAnnonceMax=etat.valeurAnnonce();
			etat.joueurSuivant(vci);
			this.state= State.AnnoncePasse1;
			break;
			
			
		default:
			break;
		}
	}

	public EtatDuJeuInterface getEtat() 
	{
		return this.etat;
	} 
}

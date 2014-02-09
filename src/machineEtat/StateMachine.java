package machineEtat;

import logiqueDeJeux.*;

public class StateMachine 
{
	private enum State {Distribution,
						Annonce,AnnoncePasse1,AnnoncePasse2,AnnoncePasse3,AnnonceAFaire,
						DebutTour,DebutTour1,DebutTour2,DebutTour3,DebutTour4,FinDebutTour, 
						SecondTour,SecondTour1, SecondTour2, SecondTour3, SecondTour4,
						MontreCarte1,MontreCarte2,MontreCarte3,MontreCarte4,
						ResteDesTours, };
	private State state;
	private EtatDuJeuInterface etat;
	private JoueurDistantInterface joueur;
	
	 public StateMachine(JoueurDistantInterface joueur) 
	 {
		    this.state = State.Distribution;
		    this.etat = new EtatDuJeu();
		    this.joueur = joueur;
	 }
	 
	 public void eventCarte(CardEvent carte) 
	 {
		 switch(this.state) {
		 
/*********************** Distribution *******************/		 
		 case Distribution:
			 if (etat.valide(carte.getCarte()))
			 {
				 joueur.addCard(carte.getCarte());
				 this.state = State.Distribution;
			 }
			 break;
			 
/*********************** Premier Tour **********************/			 
		 case DebutTour:
			 if (etat.valide(carte.getCarte()))
			 {
				 etat.joue(carte.getCarte());
				 this.state = State.DebutTour1;
			 }
			 break;
		 case DebutTour1:
			 if (etat.valide(carte.getCarte()))
			 {
				 etat.joue(carte.getCarte());
				 this.state = State.DebutTour2;
			 }
			 break;
		 case DebutTour2:
			 if (etat.valide(carte.getCarte()))
			 {
				 etat.joue(carte.getCarte());
				 this.state = State.DebutTour3;
			 }
			 break;
		 case DebutTour3:
			 if (etat.valide(carte.getCarte()))
			 {
				 etat.joue(carte.getCarte());
				 this.state = State.DebutTour4;
			 }
			 break;
		 case DebutTour4:
			 if (etat.valide(carte.getCarte()))
			 {
				 etat.joue(carte.getCarte());
				 this.state = State.FinDebutTour;
			 }
			 break;
			 
/************************ Second Tour ************************/			 
		 case SecondTour:
			 if (etat.valide(carte.getCarte()))
			 {
				 etat.joue(carte.getCarte());
				 this.state = State.SecondTour1;
			 }
			 break;
		 case SecondTour1:
			 if (etat.valide(carte.getCarte()))
			 {
				 etat.joue(carte.getCarte());
				 this.state = State.SecondTour2;
			 }
			 break;
		 case SecondTour2:
			 if (etat.valide(carte.getCarte()))
			 {
				 etat.joue(carte.getCarte());
				 this.state = State.SecondTour3;
			 }
			 break;
		 case SecondTour3:
			 if (etat.valide(carte.getCarte()))
			 {
				 etat.joue(carte.getCarte());
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
				 etat.joue(carte.getCarte());
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
			 etat.joueurSuivant();
			 this.state = State.AnnoncePasse1;
			 break;
		 case AnnoncePasse1:
			 etat.joueurSuivant();
			 this.state = State.AnnoncePasse2;
			 break;
		 case AnnoncePasse2:
			 etat.joueurSuivant();
			 this.state = State.AnnoncePasse3;
			 break;
		 case AnnoncePasse3:
			 if (etat.annonceFaite())
			 {
				 etat.joueurSuivant();
				 this.state = State.DebutTour;
			 }
			 else /*annonce pas faite*/
			 {
				 etat.joueurSuivant();
				 etat.joueurSuivant();
				 this.state = State.Distribution;
			 }
			 break;
			 
		 default:
			 //Nothing
			 break;
		 }
	 }

	public void eventGeste(MouvementEvent Geste) 
	 {
		 switch(this.state) 
		 {
		 
/*************************** Annonce ***********************/
		 case Annonce:
			 if (true /*bon geste*/)
				 //Action
				 this.state = State.AnnonceAFaire;
			 break;
		 case AnnoncePasse1:
			 if (true /*coinche*/)
				 //Action
				 this.state = State.DebutTour;
			 else if(true /*bon geste*/)
				 //Action
				 this.state = State.AnnonceAFaire;
			 break;
		 case AnnoncePasse2:
			 if (true /*bon geste*/)
				 //Action
				 this.state = State.AnnonceAFaire;
			 break;
		 case AnnoncePasse3:
			 if (true /*bon geste*/)
				 //Action
				 this.state = State.AnnonceAFaire;
			 break;
		 case AnnonceAFaire:
			 if (true /*bon geste*/)
				 //Action
				 this.state = State.AnnoncePasse1;
			 break;
			 
/************************ Second Tour/Montre Carte **********************/
		 case SecondTour1:
			 if (true /*bon geste*/)
				 this.state = State.MontreCarte1;
			 break;
		 case SecondTour2:
			 if (true /*bon geste*/)
				 this.state = State.MontreCarte2;
			 break;
		 case SecondTour3:
			 if (true /*bon geste*/)
				 this.state = State.MontreCarte3;
			 break;
		 case SecondTour4:
			 if (true /*bon geste*/)
				 this.state = State.MontreCarte4;
			 break;
			 
			 
		 case MontreCarte1:
			 if (true /*bon geste*/)
				 this.state = State.SecondTour1;
			 break;
		 case MontreCarte2:
			 if (true /*bon geste*/)
				 this.state = State.SecondTour2;
			 break;
		 case MontreCarte3:
			 if (true /*bon geste*/)
				 this.state = State.SecondTour3;
			 break;
		 case MontreCarte4:
			 if (true /*bon geste*/)
				 this.state = State.SecondTour4;
			 break;
			 
		 default:
			 //Nothing
			 break;
		 }
	 }

	public void eventFinPli()
	{
		switch(this.state)
		{
			case DebutTour4:
				etat.finpli();
				this.state = State.SecondTour;
				break;
				
			case SecondTour4:
				etat.finpli();
				this.state = State.ResteDesTours;
				break;
				
			case ResteDesTours:
				etat.finpli();
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
			 joueur.addCard(Carte.getCarte());
				 this.state = State.Annonce;
				 
		default:
			//Nothing
			break;
		 }
	}
	
	public void eventMancheTerminer()
	{
		switch (this.state)
		{
		case ResteDesTours:
			etat.finpli();
			etat.mancheTerminer();
			this.state = State.Distribution;
			
		default:
			//Nothing
			break;
		}
	}
	
	public void eventPartieTerminer()
	{
		switch (this.state)
		{
		case ResteDesTours:
			//Action
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
}

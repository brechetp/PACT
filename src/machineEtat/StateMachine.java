package machineEtat;

public class StateMachine 
{
	private enum State {Distribution,
						Annonce,AnnoncePasse1,AnnoncePasse2,AnnoncePasse3,AnnonceAFaire,
						DebutTour,DebutTour1,DebutTour2,DebutTour3,DebutTour4,FinDebutTour, 
						SecondTour,SecondTour1, SecondTour2, SecondTour3, SecondTour4,
						MontreCarte1,MontreCarte2,MontreCarte3,MontreCarte4,
						ResteDesTours, };
	private State state;
	
	 public StateMachine() 
	 {
		    this.state = State.Distribution;
	 }
	 
	 public void eventCarte(CardEvent carte) 
	 {
		 switch(this.state) {
		 
/*********************** Distribution *******************/		 
		 case Distribution:
			 //Action
			 if (true /*carte valide*/)
				 //Action
				 this.state = State.Distribution;
			 break;
			 
/*********************** Premier Tour **********************/			 
		 case DebutTour:
			 if (true /*carte valide*/)
				 this.state = State.DebutTour1;
			 break;
		 case DebutTour1:
			 if (true /*carte valide*/)
				 this.state = State.DebutTour2;
			 break;
		 case DebutTour2:
			 if (true /*carte valide*/)
				 this.state = State.DebutTour3;
			 break;
		 case DebutTour3:
			 if (true /*carte valide*/)
				 this.state = State.DebutTour4;
			 break;
		 case DebutTour4:
			 if (true /*carte valide*/)
				 this.state = State.FinDebutTour;
			 break;
			 
/************************ Second Tour ************************/			 
		 case SecondTour:
			 if (true /*carte valide*/)
				 this.state = State.SecondTour1;
			 break;
		 case SecondTour1:
			 if (true /*carte valide*/)
				 this.state = State.SecondTour2;
			 break;
		 case SecondTour2:
			 if (true /*carte valide*/)
				 this.state = State.SecondTour3;
			 break;
		 case SecondTour3:
			 if (true /*carte valide*/)
				 this.state = State.SecondTour4;
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
			 if (true /*carte valide*/)
				 this.state = State.ResteDesTours;	 
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
			 //Action
			 this.state = State.AnnoncePasse1;
			 break;
		 case AnnoncePasse1:
			 //Action
			 this.state = State.AnnoncePasse2;
			 break;
		 case AnnoncePasse2:
			 //Action
			 this.state = State.AnnoncePasse3;
			 break;
		 case AnnonceAFaire:
			 //Action
			 this.state = State.AnnoncePasse1;
			 break;
		 case AnnoncePasse3:
			 if (true /*si il y a un annonce de faite*/)
				 //Action
				 this.state = State.DebutTour;
			 else /*annonce pas faite*/
				 //Action
				 this.state = State.Distribution;
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
			 if (true /*bon geste*/)
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
			 
/*********************** Premier Tour **********************/			 
		 case DebutTour1:
			 if (true /*bon geste*/)
				 //Action
				 this.state = State.DebutTour1;
			 break;
		 case DebutTour2:
			 if (true /*bon geste*/)
				 //Action
				 this.state = State.DebutTour2;
			 break;
		 case DebutTour3:
			 if (true /*bon geste*/)
				 //Action
				 this.state = State.DebutTour3;
			 break;
		 case DebutTour4:
			 if (true /*bon geste*/)
				 //Action
				 this.state = State.DebutTour4;
			 break;
			 
/************************ Second Tour/Montre Carte **********************/
		 case SecondTour1:
			 if (true /*bon geste*/)
				 //Action
				 this.state = State.MontreCarte1;
			 break;
		 case SecondTour2:
			 if (true /*bon geste*/)
				 //Action
				 this.state = State.MontreCarte2;
			 break;
		 case SecondTour3:
			 if (true /*bon geste*/)
				 //Action
				 this.state = State.MontreCarte3;
			 break;
		 case SecondTour4:
			 if (true /*bon geste*/)
				 //Action
				 this.state = State.MontreCarte4;
			 break;
			 
			 
		 case MontreCarte1:
			 if (true /*bon geste*/)
				 //Action
				 this.state = State.SecondTour1;
			 break;
		 case MontreCarte2:
			 if (true /*bon geste*/)
				 //Action
				 this.state = State.SecondTour2;
			 break;
		 case MontreCarte3:
			 if (true /*bon geste*/)
				 //Action
				 this.state = State.SecondTour3;
			 break;
		 case MontreCarte4:
			 if (true /*bon geste*/)
				 //Action
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
				//Action
				this.state = State.SecondTour;
				break;
				
			case SecondTour4:
				//Action
				this.state = State.ResteDesTours;
				break;
				
			case ResteDesTours:
				this.state = State.ResteDesTours;
				break;
				
			default:
				break;
			
		}
	}
	public void eventDistributionTerminer()
	{
		 switch(this.state) 
		 {
		 	 
		 case Distribution:
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
			//Action
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

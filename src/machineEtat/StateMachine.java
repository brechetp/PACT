package machineEtat;

public class StateMachine 
{
	private enum State {Distribution,
						Annonce,AnnoncePasse1,AnnoncePasse2,AnnoncePasse3,AnnonceAFaire,
						DebutTour,DebutTour1,DebutTour2,DebutTour3,DebutTour4,FinDebutTour, 
						SecondTour,SecondTour1, SecondTour2, SecondTour3, SecondTour4, 
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
			 if (true/*pas toutes les cartes*/) 
				 this.state = State.Annonce;
			 else 
				 //Action
				 if (true /*carte valide*/)
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
			 this.state = State.AnnoncePasse1;
			 break;
		 case AnnoncePasse1:
			 this.state = State.AnnoncePasse2;
			 break;
		 case AnnoncePasse2:
			 this.state = State.AnnoncePasse3;
			 break;
		 case AnnonceAFaire:
				 this.state = State.AnnoncePasse1;
			 break;
		 case AnnoncePasse3:
			 if (true /*si il y a un annonce de faite*/)
				 //Action
				 this.state = State.DebutTour;
			 else /*annonce pas faite*/
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
				if(true /*partie terminée*/)
					;//Action
				else if(true /*manche terminée*/)
					//Action
					this.state = State.Distribution;
				else this.state = State.ResteDesTours;
				break;
				
			default:
				break;
			
		}
	}
	
	public void eventQuit() 
	 {
		 System.exit(0);
	 } 
}

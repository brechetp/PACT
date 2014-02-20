package logiqueDeJeux;

import machineEtat.*;

public class BeloteCoinche implements GlobalListener
{
	private StateMachine machine;
	
	
	public BeloteCoinche(JoueurDistantInterface joueurD, EtatDuJeu etat)
	{
		this.machine = new StateMachine(joueurD, etat);
	}
	
	public void nouvelleCarte(CardEvent carte) 
	{
		machine.eventCarte(carte);
	}

	public void nouveauGeste(MouvementEvent mouvement) 
	{
		if (mouvement.getLabel()=="passer")
			machine.eventGestePasser();
		else if (mouvement.getLabel()=="retour")
			machine.eventGesteRetour();
		else if (mouvement.getLabel()=="coinche")
			machine.eventGesteCoinche();
		else if (mouvement.getLabel()=="accepter")
			machine.eventGesteAccept();
		else if (mouvement.getLabel()=="quitter")
			machine.eventQuit();
	}

	public void finPli() 
	{
		// TODO Auto-generated method stub
		machine.eventFinPli();
	}
	
}

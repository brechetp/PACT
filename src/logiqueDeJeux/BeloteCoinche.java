package logiqueDeJeux;

import structure.AnnonceInterface;
import machineEtat.*;

public class BeloteCoinche implements GlobalListener
{
	private StateMachine machine;
	
	
	public BeloteCoinche(EtatDuJeu etat)
	{
		
		this.machine = new StateMachine(new JoueurDistant(this), etat);
	}
	
	public void nouvelleCarte(CardEvent carte) 
	{
		System.out.println(carte.getCarte().getLabelNum());
		System.out.println(carte.getCarte().getSuit());
		machine.eventCarte(carte);
	}

	public void nouveauGeste(MouvementEvent mouvement) 
	{
		System.out.println("Message Recu !!");
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
	
	public void setAnnonce(AnnonceInterface annonce)
	{
		machine.setAnnonce(annonce);
	}

	public EtatDuJeuInterface getEtat() 
	{
		return machine.getEtat();
	}
}

package logiqueDeJeux;

import iug.ViewControllerInterface;
import structure.AnnonceInterface;
import machineEtat.*;

public class BeloteCoinche implements GlobalListener
{
	private StateMachine machine;
	
	
	public BeloteCoinche(ViewControllerInterface vci)
	{
		
		this.machine = new StateMachine(new JoueurDistant(this), vci);
	}
	
	public void nouvelleCarte(CardEvent carte) 
	{
		System.out.println(carte.getCarte().getLabelNum());
		System.out.println(carte.getCarte().getSuit());
		synchronized (machine) 
		{
			machine.eventCarte(carte);
		}
	}

	public void nouveauGeste(MouvementEvent mouvement) 
	{
		System.out.println("Message Recu !!");
		if (mouvement.getLabel()=="passer")
		{
			synchronized(machine)
			{
				machine.eventGestePasser();
			}
		}
		else if (mouvement.getLabel()=="retour")
		{
			synchronized(machine)
			{
				machine.eventGesteRetour();
			}
		}
		else if (mouvement.getLabel()=="coinche")
		{
			synchronized(machine)
			{
				machine.eventGesteCoinche();
			}
		}
		else if (mouvement.getLabel()=="accepter")
		{
			synchronized(machine)
			{
				machine.eventGesteAccept();
			}
		}
		else if (mouvement.getLabel()=="quitter")
		{
			synchronized(machine)
			{
				machine.eventQuit();
			}
		}
	}

	public void finPli() 
	{
		System.out.println("fin de pli");
		synchronized (machine) 
		{
			machine.eventFinPli();
		}
	}
	
	public void setAnnonce(AnnonceInterface annonce)
	{
		synchronized (machine) 
		{
			machine.setAnnonce(annonce);
		}
	}

	public EtatDuJeuInterface getEtat() 
	{
		synchronized (machine) 
		{
			return machine.getEtat();
		}
	}
}
